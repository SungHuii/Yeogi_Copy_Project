package copy.project.demo.common;

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

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String naverRedirectUri;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;
}
