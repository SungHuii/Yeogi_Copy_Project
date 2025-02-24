package copy.project.demo.dto;

import lombok.*;

/**
 * Created by SungHui on 2025. 2. 24.
 */
/* 카카오페이 결제 요청 DTO */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPayRequestDTO {

    private String cid; // 가맹점 코드
    private String partner_order_id; // 가맹점 주문번호
    private String partner_user_id; // 가맹점 회원 id
    private String item_name; // 상품 이름
    private int quantity; // 상품 수량
    private int total_amount; // 상품 총액
    private int tax_free_amount; // 상품 비과세 금액
    private String approval_url; // 결제 성공 시 URL
    private String cancel_url; // 결제 취소 시 URL
    private String fail_url; // 결제 실패 시 URL


}
