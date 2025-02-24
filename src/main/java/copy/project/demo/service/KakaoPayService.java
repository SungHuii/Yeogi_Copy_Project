package copy.project.demo.service;

import copy.project.demo.dto.ApproveResponse;
import copy.project.demo.dto.KakaoPayApproveRequest;
import copy.project.demo.dto.KakaoPayRequestDTO;
import copy.project.demo.dto.ReadyResponse;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.Reservation;
import copy.project.demo.repository.MemberRepository;
import copy.project.demo.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Value("${kakao.cid}")
    String cid;

    // 결제 준비 요청
    public ReadyResponse payReady(Long reservationId) {
        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 존재하지 않습니다."));
        // 예약한 회원 정보 조회
        Member member = reservation.getMember();

        KakaoPayRequestDTO requestDTO = new KakaoPayRequestDTO(
                cid, // 가맹점 코드(테스트용)
                reservationId.toString(),
                member.getId().toString(),
                reservation.getAccommodationRoom().getAccommodation().getName(),
                1,
                reservation.getTotalPrice(),
                0,
                "http://localhost:8080/kakao-pay/pay/completed?reservationId=" + reservationId,
                "http://localhost:8080/kakao-pay/pay/cancel",
                "http://localhost:8080/kakao-pay/pay/fail"
        );

        /*// 결제 준비 요청 로직
        Map<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME"); // 가맹점 코드(테스트용)
        params.put("partner_order_id", reservationId.toString()); // 예약 ID
        params.put("partner_user_id", member.getId().toString()); // 회원 ID
        params.put("item_name", reservation.getAccommodationRoom().getAccommodation().getName()); // 상품명
        params.put("quantity", "1"); // 상품 수량
        params.put("total_amount", String.valueOf(reservation.getTotalPrice())); // 상품 총액
        params.put("tax_free_amount", "0"); // 상품 비과세 금액
        params.put("approval_url", "http://localhost:8080/kakao-pay/pay/completed?reservationId=" + reservationId); // 결제 성공 시 URL
        params.put("cancel_url", "http://localhost:8080/kakao-pay/pay/cancel"); // 결제 취소 시 URL
        params.put("fail_url", "http://localhost:8080/kakao-pay/pay/fail"); // 결제 실패 시 URL
*/

        HttpEntity<KakaoPayRequestDTO> request = new HttpEntity<>(requestDTO, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";

        ResponseEntity<ReadyResponse> response = template.postForEntity(url, request, ReadyResponse.class);

        log.info("결제 준비 완료: " + response.getBody());
        return response.getBody();
    }

    // 카카오페이 결제 승인
    public ApproveResponse payApprove(String tid, String pgToken, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 존재하지 않습니다."));

        KakaoPayApproveRequest kakaoPayRequest = new KakaoPayApproveRequest(
                cid,
                tid,
                reservationId.toString(),
                reservation.getMember().getId().toString(),
                pgToken
        );

        /*Map<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");
        params.put("tid", tid);
        params.put("partner_order_id", reservationId.toString());
        params.put("partner_user_id", reservation.getMember().getId().toString());
        params.put("pg_token", pgToken);*/

        HttpEntity<KakaoPayApproveRequest> request = new HttpEntity<>(kakaoPayRequest, this.getHeaders());
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponse approveResponse = template.postForObject(url, request, ApproveResponse.class);

        log.info("결제 승인 완료: " + approveResponse);

        // 예약 상태 변경 (예: 'PAYMENT_COMPLETED' 상태로 업데이트)
        reservation.confirmed();
        reservationRepository.save(reservation);

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
