package copy.project.demo.service;

import copy.project.demo.common.JwtUtil;
import copy.project.demo.dto.KakaoTokenResponse;
import copy.project.demo.dto.KakaoUserInfo;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.entity.enums.MemberType;
import copy.project.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by SungHui on 2025. 3. 11.
 */
/* 카카오 로그인 구현용 서비스 */
@Service
@RequiredArgsConstructor // final로 선언된 필드 기반 생성자 자동 생성. 자동주입.
public class KakaoAuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil; // JWT 토큰
    private final RestTemplate restTemplate; // 외부 REST API 호출용. 카카오 API와의 통신에 사용

    /* 카카오 연결용 정적 정보 */
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token"; // 카카오 OAuth 인증을 통해 액세스 토큰 요청 URL
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 카카오 사용자 정보 가져오는 URL
    private static final String CLIENT_ID = "{e9615e19f95999a56d1866131be5e1e6}"; // 카카오 API 사용하기 위한 REST API 키
    // 카카오 인증 후 리디렉션 URL. 인증 코드가 여기로 전달됨
    private static final String REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";
    // 카카오 클라이언트 시크릿. 토큰 발급 시, 보안을 강화하기 위함.
    private static final String CLIENT_SECRET = "{qNro1YVq7tOD2BpNQaNon0c8rNNSy7Ih}";

    // 카카오 로그인 메서드
    public String kakaoLogin(String code) {

        // 인가 코드로 액세스 토큰 요청
        String accessToken = getAccessToken(code);

        // 액세스 토큰으로 카카오 사용자 정보 가져오기
        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(accessToken);

        // 회원가입 또는 로그인 처리 (사용자 존재여부 확인)
        Member member = memberRepository.findByLoginId(kakaoUserInfo.getEmail())
                .orElseGet(() -> registerNewKakaoUser(kakaoUserInfo));

        // 최종적으로 로그인한 사용자 정보를 바탕으로 JWT 발급
        return jwtUtil.generateToken(member.getLoginId(), member.getRole());
    }

    // 카카오에서 인증 코드를 받아서 액세스 토큰을 요청하는 메서드
    private String getAccessToken(String code) {
        // params에 카카오 인증 API에 필요한 파라미터들을 담음
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_tpye", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("client_secret", CLIENT_SECRET);
        params.add("code", code);

        // 요청의 Content-Type을 application/x-www-form-urlencoded 로 설정함.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // params와 headers를 HttpEntity로 감싸서 요청에 필요한 데이터를 만듦.
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        // TOKEN_URL로 POST 요청을 보내서 액세스 토큰을 받아옴
        ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(TOKEN_URL, request, KakaoTokenResponse.class);

        return response.getBody().getAccessToken();
    }

    // accessToken 을 사용해서 카카오 사용자 정보를 가져오는 메서드
    private KakaoUserInfo getKakaoUserInfo(String accessToken) {
        // Bearer 토큰을 헤더에 추가해서 인증함
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        // GET 요청을 보내서 카카오 사용자 정보 가져오기
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, request, KakaoUserInfo.class);

        return response.getBody();
    }

    // 카카오 사용자 정보로 새로운 회원 등록하는 메서드
    private Member registerNewKakaoUser(KakaoUserInfo kakaoUserInfo) {
        Member newMember = new Member(
                null, kakaoUserInfo.getEmail(), null, MemberType.KAKAO,
                kakaoUserInfo.getName(), kakaoUserInfo.getNickname(), kakaoUserInfo.getPhone(),
                MemberGender.valueOf(kakaoUserInfo.getGender()), kakaoUserInfo.getBirthDate(), MemberRole.USER
        );
        return memberRepository.save(newMember);
    }

}
