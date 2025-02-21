package copy.project.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by SungHui on 2025. 2. 21.
 */
/* 카카오페이 결제 준비 요청 응답 DTO */
@Getter
@Setter
@ToString
public class ReadyResponse {

    private String tid; // 결제 고유 번호
    private String next_redirect_pc_url; // PC버전 카카오페이 결제 페이지 URL
    private String created_at; // 결제 준비 요청 시간
}
