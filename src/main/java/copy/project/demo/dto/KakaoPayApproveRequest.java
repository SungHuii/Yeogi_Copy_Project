package copy.project.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SungHui on 2025. 2. 24.
 */
// 카카오페이 결제 승인 요청 DTO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPayApproveRequest {

    private String cid; // 가맹점 코드
    private String tid; // 결제 고유 번호
    private String partner_order_id; // 가맹점 주문번호
    private String partner_user_id; // 가맹점 회원 id
    private String pg_token; // 결제 고유 토큰
}
