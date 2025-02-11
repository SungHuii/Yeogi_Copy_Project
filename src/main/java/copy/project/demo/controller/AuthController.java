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
@RestController // 이 클래스는 REST API 요청을 처리하는 컨트롤러임
@RequestMapping("/auth") // /auth 경로 아래에서 요청을 처리하는 컨트롤러임을 정의
@RequiredArgsConstructor // final 필드를 매개변수로 받는 생성자를 자동 생성
public class AuthController {

    /* RequiredArgsConstructor 때문에
    이 두 필드는 생성자 주입 방식으로 자동 주입*/
    // 회원가입 및 로그인 로직을 처리하는 서비스 클래스
    private final AuthService authService;
    // JWT 토큰을 생성하고, 토큰에서 역할 등의 정보를 추출하는 유틸 클래스
    private final JwtUtil jwtUtil;
    // 회원정보 리파지토리
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

    /* 로그인 API. JWT + 사용자 정보 반환 */
    @PostMapping("/login") // POST /auth/login 요청을 처리하는 API
    // 요청 파라미터로 LoginRequest DTO를 받아옴 (ID, PW)
    public ResponseEntity<MemberDTO> login(@RequestBody LoginRequest loginRequest) {
        // 받아온 파라미터로 토큰 생성 (login 메서드)
        Optional<String> tokenOpt = authService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        // 토큰이 값을 가지고 있을 때
        if (tokenOpt.isPresent()) {
            // 토큰 값의 로그인 아이디로 회원정보 조회
            Member member = memberRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow();
            // 사용자 정보를 객체로 새로 만들어서 저장
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
                    tokenOpt.get() // JWT 포함
            );
            // 신규 객체와 함께 ok(200)
            return ResponseEntity.ok(responseDto);
        } else {
            // 토큰 값이 없으면 인가되지 않은 상태 반환(401)
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

        // role이 관리자일 때 반환되는 uri
        return "관리자 전용 페이지 /uri/admin";
    }

    // JWT 유효성 확인 토큰 검증 API
    @GetMapping("/validate") // GET /auth/validate 요청을 처리하는 API
    public ResponseEntity<?> validateToken(@RequestParam String token) { // 요청 쿼리 파라미터로 토큰을 받음
        boolean isValid = jwtUtil.validateToken(token); // 토큰 유효성 검사로 확인
        return ResponseEntity.ok(Map.of("isValid", isValid));
        // 결과값 JSON 형태로 응답 {"isValid": true / false}
    }

    /* 사용자 정보 조회 API */
    @GetMapping("/checkUser") // GET /auth/checkUser 요청 처리 API
    // 요청 헤더에서 Autorization 값을 받음
    public ResponseEntity<MemberDTO> getUserInfo(@RequestHeader("Authorization") String token) {
        token = token.substring(7); // "Bearer " 문자열 접두 제거
        if(!jwtUtil.validateToken(token)) { // 토큰 유효성 검사
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            // 유효하지 않은 토큰일 때 401 응답 반환
        }

        // loginId 추출
        String loginId = jwtUtil.extractEmail(token);
        // 추출한 로그인 id로 회원 정보 조회
        Member member = memberRepository.findByLoginId(loginId).orElseThrow();

        // 조회한 정보로 DTO로 변환
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
                null // JWT 제외
        );
        // 조회한 정보를 JSON으로 응답 반환
        return ResponseEntity.ok(responseDto);
    }
}
