package copy.project.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import copy.project.demo.service.NaverAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by SungHui on 2025. 3. 12.
 */
@RestController
@RequestMapping("/auth/naver")
@RequiredArgsConstructor
public class NaverAuthController {

    private final NaverAuthService naverAuthService;

    @GetMapping("/oauth")
    public ResponseEntity<?> naverConnect() throws UnsupportedEncodingException {
        String url = naverAuthService.createNaverURL();

        return new ResponseEntity<>(url, HttpStatus.OK); // 프론트 브라우저로 보내는 주소
    }

    @GetMapping("/callback")
    public String naverLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) throws JsonProcessingException {
        naverAuthService.loginNaver(code, state, response);

        /* Header가 아닌 Redis 서버에 잘 저장이 되었는지 확인하기 */
        return response.getHeader("Authorization") == null ? "Fail Login: User" :  "Success Login: User";
    }

}
