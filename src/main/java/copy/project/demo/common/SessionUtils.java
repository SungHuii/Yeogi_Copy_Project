package copy.project.demo.common;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * Created by SungHui on 2025. 2. 24.
 */
/* 카카오페이 tid를 결제준비에서 결제승인으로 넘기기 위해 Session에 저장하는 유틸 클래스 */
public class SessionUtils {

    public static void addAttribute(String name, Object value) {
        Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static String getStringAttributeValue(String name) {
        return String.valueOf(getAttribute(name));
    }

    public static Object getAttribute(String name) {
        return Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
}
