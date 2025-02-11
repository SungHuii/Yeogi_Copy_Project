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


}
