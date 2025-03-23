package copy.project.demo.controller;

import copy.project.demo.common.SessionUtils;
import copy.project.demo.dto.ApproveResponse;
import copy.project.demo.dto.ReadyResponse;
import copy.project.demo.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SungHui on 2025. 2. 21.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-pay")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    @PostMapping("/pay/ready")
    public ReadyResponse payReady(@RequestParam Long reservationId) {

        log.info("결제 요청 예약 ID : " + reservationId);

        ReadyResponse response = kakaoPayService.payReady(reservationId);
        SessionUtils.addAttribute("tid", response.getTid());
        SessionUtils.addAttribute("reservationId", reservationId);

        log.info("결제 고유번호 : " + response.getTid());

        return response;
    }

    @GetMapping("/pay/completed")
    public ApproveResponse payCompleted(@RequestParam("pg_token") String pgToken) {

        String tid = SessionUtils.getStringAttributeValue("tid");
        Long reservationId = Long.parseLong(SessionUtils.getStringAttributeValue("reservationId"));
        String reservationId2 = (String) SessionUtils.getAttribute("reservationId");

        log.info("결제승인 요청을 인증하는 토큰: {}", pgToken);
        log.info("예약 ID : {}", reservationId);

        return kakaoPayService.payApprove(tid, pgToken, reservationId2);
    }
}