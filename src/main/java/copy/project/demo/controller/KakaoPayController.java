package copy.project.demo.controller;

import copy.project.demo.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SungHui on 2025. 2. 21.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-pay")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
}
