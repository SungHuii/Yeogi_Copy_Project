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
@Service // 스프링 빈 등록. 서비스 계층 명시
@Transactional // 트랜잭션 처리
@RequiredArgsConstructor // final 필드를 매개변수로 받는 생성자를 자동 생성
public class AuthService {

    private final MemberRepository memberRepository; // 회원 정보 저장 및 조회 담당 리파지토리
    private final JwtUtil jwtUtil; // JWT 토큰 생성 및 검증 담당 유틸
    private final PasswordEncoder passwordEncoder; // BCryptPasswordEncoder 주입

    /* 회원가입 */
    public MemberDTO register(MemberDTO memberDTO) { // 회원가입 요청을 보내면 memberDTO를 받아서 처리

        // 비밀번호 암호화
        String encodedPw = passwordEncoder.encode(memberDTO.getPassword());

        // Member 엔티티 생성
        Member member = new Member(
                null, // ID -> DB에서 자동 생성
                memberDTO.getLoginId(),
                encodedPw, // 암호화된 비밀번호 저장
                memberDTO.getType(),
                memberDTO.getName(),
                memberDTO.getNickname(),
                memberDTO.getPhone(),
                memberDTO.getGender(),
                memberDTO.getBirthDate(),
                memberDTO.getRole()
        );

        // 생성한 Member 엔티티를 DB에 저장
        Member savedMember = memberRepository.save(member);

        // 저장된 Member 엔티티를 DTO로 변환해서 반환
        return new MemberDTO(
                savedMember.getId(),
                savedMember.getLoginId(),
                null, // 보안 상 비밀번호 반환 X
                savedMember.getType(),
                savedMember.getName(),
                savedMember.getNickname(),
                savedMember.getPhone(),
                savedMember.getGender(),
                savedMember.getBirthDate(),
                savedMember.getRole(),
                null // JWT는 회원가입 시 발급 X
        );
    }

    /* 로그인 */
    public Optional<String> login(String loginId, String unencryptedPW) {
        // 로그인 요청 시 loginId와 평문 비밀번호를 입력 받음

        // loginId로 회원 조회
        return memberRepository.findByLoginId(loginId)
                // 조회된 회원 정보가 존재하고, 사용자가 입력한 비밀번호와 DB에 저장된 암호화된 비밀번호가 일치하는지 확인
                // matches(입력값, 저장된 암호화 값) 을 사용해 검증
                .filter(member -> passwordEncoder.matches(unencryptedPW, member.getPassword()))
                // 로그인 성공 시 JWT 발급 후 반환
                .map(member -> jwtUtil.generateToken(member.getLoginId(), member.getRole()));
    }


}
