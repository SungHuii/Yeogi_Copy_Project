package copy.project.demo.service;

import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 2.
 */
/* 회원가입, 로그인용 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // BCryptPasswordEncoder 주입

    /* 회원가입 */
    public MemberDTO register(MemberDTO memberDTO) {

        String encodedPw = passwordEncoder.encode(memberDTO.getPassword()); // 비밀번호 암호화

        Member member = new Member(
                null,
                memberDTO.getLoginId(),
                encodedPw,
                memberDTO.getType(),
                memberDTO.getName(),
                memberDTO.getNickname(),
                memberDTO.getPhone(),
                memberDTO.getGender(),
                memberDTO.getBirthDate(),
                memberDTO.getRole()
        );

        Member savedMember = memberRepository.save(member);

        return new MemberDTO(
                savedMember.getId(),
                savedMember.getLoginId(),
                null,
                savedMember.getType(),
                savedMember.getName(),
                savedMember.getNickname(),
                savedMember.getPhone(),
                savedMember.getGender(),
                savedMember.getBirthDate(),
                savedMember.getRole(),
                null // JWT
        );
    }

    /* 로그인 */
    public Optional<String> login(String loginId, String unencryptedPW) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> passwordEncoder.matches(unencryptedPW, member.getPassword()))
                .map(member -> jwtUtil.generateToken(member.getLoginId(), member.getRole())); // 로그인 성공 시 JWT 발급
    }

    /* 로그인 *//*
    public MemberDTO login(String loginId, String rawPassword) {
        Member member = memberRepository.findByLoginId(loginId)
              .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 틀렸습니다."));

        // ✅ 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 틀렸습니다.");
        }

        // ✅ 로그인 성공 시 JWT 생성
        String token = jwtUtil.generateToken(member.getLoginId(), member.getRole());

        // ✅ 사용자 정보를 포함한 DTO 반환
        return new MemberDTO(
              member.getId(),
              member.getLoginId(),
              null, // ✅ 비밀번호 정보는 포함하지 않음
              member.getType(),
              member.getName(),
              member.getNickname(),
              member.getPhone(),
              member.getGender(),
              member.getBirthDate(),
              member.getRole(),
              token // ✅ 토큰을 DTO에 포함
        );
    }*/

}
