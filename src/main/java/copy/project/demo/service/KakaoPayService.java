package copy.project.demo.service;

import copy.project.demo.dto.ApproveResponse;
import copy.project.demo.dto.ReadyResponse;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.Reservation;
import copy.project.demo.repository.MemberRepository;
import copy.project.demo.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SungHui on 2025. 2. 21.
 */
/* 카카오페이 결제 관련 비즈니스 로직 처리 서비스 */
@Service
@Slf4j
public class KakaoPayService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // 결제 준비 요청
    public ReadyResponse payReady(Long reservationId) {
        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 존재하지 않습니다."));
        // 회원 정보 조회
        Member member = memberRepository.findById(reservation.getMember().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        // 결제 준비 요청 로직
        Map<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME"); // 가맹점 코드(테스트용)
        params.put("partner_order_id", reservation.getMember().toString()); // 예약 ID
        params.put("partner_user_id", member.getId().toString()); // 회원 ID
        params.put("item_name", reservation.getAccommodationRoom().getAccommodation().toString()); // 상품명
        params.put("quantity", "1"); // 상품 수량
        params.put("total_amount", String.valueOf(reservation.getTotalPrice())); // 상품 총액
        params.put("tax_free_amount", "0"); // 상품 비과세 금액
        params.put("approval_url", "http://localhost/order/pay/completed"); // 결제 성공 시 URL
        params.put("cancel_url", "http://localhost/order/pay/cancel"); // 결제 취소 시 URL
        params.put("fail_url", "http://localhost/order/pay/fail"); // 결제 실패 시 URL

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        ResponseEntity<ReadyResponse> response = template.postForEntity(url, request, ReadyResponse.class);
        log.info("결제준비 응답객체: " + response.getBody());

        return response.getBody();
    }

    // 카카오페이 결제 승인
    public ApproveResponse payApprove(String tid, String pgToken, String reservationId2) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME"); // 가맹점 코드(테스트용)
        parameters.put("tid", tid); // 결제 고유번호
        parameters.put("partner_order_id", "1234567890"); // 주문번호
        parameters.put("partner_user_id", "roommake"); // 회원 아이디
        parameters.put("pg_token", pgToken); // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결제승인 응답객체: " + approveResponse);

        return approveResponse;
    }

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + System.getenv("KAKAO_SECRET_KEY"));  // 환경변수로 Secret Key 가져오기
        headers.set("Content-type", "application/json");

        return headers;
    }
}
