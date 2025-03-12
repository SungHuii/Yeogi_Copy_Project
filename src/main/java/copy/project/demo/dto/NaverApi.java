package copy.project.demo.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * Created by SungHui on 2025. 3. 12.
 */
@Component(value = "naverApi")
@Slf4j
@Data
public class NaverApi {

    @Value("${naver.client-id}")
    private String naverClientId;

    @Value("${naver.redirect-uri}")
    private String naverRedirectUri;

    @Value("${naver.client-secret}")
    private String naverClientSecret;
}
