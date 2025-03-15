package copy.project.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import copy.project.demo.common.JwtUtil;
import copy.project.demo.common.NaverApi;
import copy.project.demo.dto.NaverTokenResponse;
import copy.project.demo.dto.NaverUserInfo;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * Created by SungHui on 2025. 3. 12.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NaverAuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil; // JWT 토큰
    private final RestTemplate restTemplate; // 외부 REST API 호출용. 네이버 API와의 통신에 사용
    private final MemberService memberService;

    private final NaverApi naverApi;
    private NaverTokenResponse ntr = null;

    public String createNaverURL () throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();

        // 네이버 API 명세에 맞춰서 작성
        String redirectURI = URLEncoder.encode( naverApi.getNaverRedirectUri(), "UTF-8"); // redirectURI 설정 부분
        SecureRandom random = new SecureRandom();
        // 보안상 랜덤한 문자열로 생성함
        String state = new BigInteger(130, random).toString();

        url.append("https://nid.naver.com/oauth2.0/authorize?response_type=code");
        url.append("&client_id=" + naverApi.getNaverClientId());
        url.append("&state=" + state);
        url.append("&redirect_uri=" + redirectURI);

        return url.toString();
    }

    public void loginNaver(String code, String state, HttpServletResponse response) throws JsonProcessingException {

        // http 요청을 위한 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        // 응답 포맷은 JSON이고, 요청 포맷은 x-www-form-urlencoded; charset=UTF-8.
        headers.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverApi.getNaverClientId());
        params.add("client_secret", naverApi.getNaverClientSecret());
        params.add("code", code);
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

        // 서비스 서버에서 네이버 인증 서버로 요청 전송
        ResponseEntity<String> oauthTokenResponse = restTemplate.exchange(
                "http://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );

        // oauthTokenResponse에서 받은 응답을 토큰정보로 객체화
        ObjectMapper om = new ObjectMapper();
        try {
            ntr =om.readValue(oauthTokenResponse.getBody(), NaverTokenResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 토큰을 이용해 정보를 받아올 API 요청을 보낼 로직
        RestTemplate rt = new RestTemplate();
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.add("Authorization", "Bearer " + ntr.getAccessToken());
        userInfoHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
        HttpEntity<MultiValueMap<String, String>> naverUserInfoRequest = new HttpEntity<>(userInfoHeaders);

        // 서비스 서버 - 네이버 인증서버 : 유저 정보 API 요청
        ResponseEntity<String> userInfoResponse = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                naverUserInfoRequest,
                String.class
        );

        // 네이버로부터 받은 정보 객체화
        ObjectMapper naverOm = new ObjectMapper();
        NaverUserInfo naverUserInfo = null;
        try {
            naverUserInfo = naverOm.readValue(userInfoResponse.getBody(), NaverUserInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 받아온 정보로 서비스 로직에 적용
        Member naverMember = memberService.createNaverMember(naverUserInfo, ntr.getAccessToken());

        // 시큐리티 Authentication을 SecurtiyContextHolder에 저장
        Authentication authentication = new UsernamePasswordAuthenticationToken(naverMember.getLoginId(),naverMember.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT 토큰 생성 및 HttpServletResponse의 헤더에 저장 (클라이언트 응답)
        String accessToken = jwtUtil.generateAccessToken(naverMember);
        String refreshToken = jwtUtil.generateRefreshToken(naverMember);
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("RefreshToken", refreshToken);

        log.info("Naver Login Success");
    }
}
