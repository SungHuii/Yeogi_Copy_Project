package copy.project.demo.controller;


import copy.project.demo.dto.LoginRequest;
import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.repository.MemberRepository;
import copy.project.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 2.
 */
/* 회원가입, 로그인, 관리자 페이지 접근 API */
@RestController // 이 클래스가 REST API 요청을 처리하는 컨트롤러임
@RequestMapping("/auth") // /auth 경로 아래에서 요청을 처리하는 컨트롤러임을 정의
@RequiredArgsConstructor // final 필드를 매개변수로 받는 생성자를 자동 생성
public class AuthController {

    /* RequiredArgsConstructor 때문에
    이 두 필드는 생성자 주입 방식으로 자동 주입*/
    // 회원가입 및 로그인 로직을 처리하는 서비스 클래스
    private final AuthService authService;
    // JWT 토큰을 생성하고, 토큰에서 역할 등의 정보를 추출하는 유틸 클래스
    private final JwtUtil jwtUtil;

    private final MemberRepository memberRepository;

    /* 회원가입 API */
    @PostMapping("/register") // POST /auth/register 요청을 처리하는 API
    // 요청 본문(JSON)에서 MemberDTO 데이터를 받아옴
    public ResponseEntity<MemberDTO> register(@RequestBody MemberDTO memberDTO) {
        MemberDTO regiMember = authService.register(memberDTO); // 회원가입을 후 등록된 회원 정보를 반환
        return ResponseEntity.ok(regiMember); // HTTP 응답 상태 200(OK)와 함께 회원 정보를 반환
    }

    /* 로그인 API
    *  JWT 반환 */
    /*@PostMapping("/login") // POST /auth/login 요청을 처리하는 API
    // 요청 파라미터에서 loginId와 pw를 받아옴
    public ResponseEntity<String> login(@RequestParam String loginId, @RequestParam String pw) {
        Optional<String> token = authService.login(loginId, pw); // 로그인 검증 후, 성공하면 JWT 토큰 반환
        return token.map(user -> ResponseEntity.ok("로그인 ok")) // 로그인 ok (200)
                .orElse(ResponseEntity.status(401).body("로그인 X -> 잘못된 이메일 혹은 비밀번호")); // 로그인 X (401 Unauthorized)
    }*/

    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody LoginRequest loginRequest) {
        Optional<String> tokenOpt = authService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        if (tokenOpt.isPresent()) {
            Member member = memberRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow();
            MemberDTO responseDto = new MemberDTO(
                    member.getId(),
                    member.getLoginId(),
                    null, // 비밀번호 제외
                    member.getType(),
                    member.getName(),
                    member.getNickname(),
                    member.getPhone(),
                    member.getGender(),
                    member.getBirthDate(),
                    member.getRole(),
                    tokenOpt.get() // ✅ JWT 포함
            );
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /* 관리자 페이지 API
    *  JWT 기반 권한 체크 */
    @GetMapping("/admin") // GET /auth/admin 요청을 처리하는 API
    public String adminPage(String token) { // String token으로 사용자가 요청 시 전달한 JWT 토큰을 받음
        token = token.substring(7); // Bearer 접두 제거

        // role(USER, ADMIN) 추출
        MemberRole role = jwtUtil.extractRole(token);

        // role이 관리자가 아닐 때 예외 발생
        if (role != MemberRole.ADMIN) {
            throw new RuntimeException("관리자 권한이 없음");
        }

        // role이 관리자일 때 반환되는 uri)
        return "관리자 전용 페이지 /uri/admin";
    }

    // JWT 유효성 확인 토큰 검증 API
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean isValid = jwtUtil.validateToken(token);
        return ResponseEntity.ok(Map.of("isValid", isValid));
    }

    /* 사용자 정보 조회 API */
    @GetMapping("/checkUser")
    public ResponseEntity<MemberDTO> getUserInfo(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String loginId = jwtUtil.extractEmail(token);
        Member member = memberRepository.findByLoginId(loginId).orElseThrow();

        MemberDTO responseDto = new MemberDTO(
                member.getId(),
                member.getLoginId(),
                null,
                member.getType(),
                member.getName(),
                member.getNickname(),
                member.getPhone(),
                member.getGender(),
                member.getBirthDate(),
                member.getRole(),
                null
        );

        return ResponseEntity.ok(responseDto);
    }
}
