package copy.project.demo.controller;


import copy.project.demo.dto.MemberDTO;
import copy.project.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 2.
 */
/* 회원가입, 로그인 API */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /* 회원가입 API */
    @PostMapping("/register")
    public ResponseEntity<MemberDTO> register(@RequestBody MemberDTO memberDTO, @RequestParam String pw) {
        MemberDTO regiMember = authService.register(memberDTO, pw);
        return ResponseEntity.ok(regiMember);
    }

    /* 로그인 API
    *  JWT 반환 */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String loginId, @RequestParam String pw) {
        Optional<String> token = authService.login(loginId, pw);
        return token.map(user -> ResponseEntity.ok("로그인 ok"))
                .orElse(ResponseEntity.status(401).body("로그인 X -> 잘못된 이메일 혹은 비밀번호"));
    }
}
