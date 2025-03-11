package copy.project.demo.controller;

import copy.project.demo.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SungHui on 2025. 3. 11.
 */
/* 카카오 로그인용 컨트롤러 */
@RestController
@RequestMapping("/auth/kakao")
@RequiredArgsConstructor
public class KaKaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    @GetMapping("/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code) {
        String jwtToken = kakaoAuthService.kakaoLogin(code);

        return ResponseEntity.ok(jwtToken);
    }
}
