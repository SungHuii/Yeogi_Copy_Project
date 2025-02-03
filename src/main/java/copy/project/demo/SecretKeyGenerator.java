package copy.project.demo;

import java.util.Base64;

/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 1회성 비밀키 생성기 */
public class SecretKeyGenerator {
    public static void main(String[] args) {
        String rawKey = "your-very-secure-secret-key-your-very-secure-secret-key"; // 32바이트 이상
        String encodedKey = Base64.getEncoder().encodeToString(rawKey.getBytes());
        System.out.println("Base64 Encoded Key: " + encodedKey);
    }
}

