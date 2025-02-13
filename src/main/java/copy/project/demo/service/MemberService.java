package copy.project.demo.service;

import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 회원 정보 관련 비즈니스 로직 처리 서비스 */
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    // 회원 정보 저장 및 조회 담당 리파지토리
    private final MemberRepository memberRepository;

    // 회원 중복확인
    private void validateDuplicateMember(Member member) {
        // 전달받은 회원의 로그인 아이디로 DB의 회원 정보 조회
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if (findMember.isPresent()) { // 회원이 이미 존재할 경우 예외 발생
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 정보 리포지토리에 저장
    public Member saveMember(Member member) {
        validateDuplicateMember(member); // 회원 중복 확인
        return memberRepository.save(member); // 회원 정보 저장
    }

    // 전달받은 로그인 아이디로 회원 정보 조회
    public Optional<Member> findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    /* 회원 정보 조회 관련 메서드들 */
    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // 이름으로 조회는 여러명이 조회될 수 있으므로 List로 반환
    public List<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public Optional<Member> findMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Optional<Member> findMemberByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }


    /* 회원 정보 조회
    * Optional<Member>로 조회한 후 MemberDTO로 변환하여 반환 */
    public Optional<MemberDTO> getMemberById(Long id) {
        return memberRepository.findById(id).map(member -> new MemberDTO(
                member.getId(),
                member.getType(),
                member.getName(),
                member.getNickname(),
                member.getPhone(),
                member.getGender(),
                member.getBirthDate()
        ));
    }

    /* 회원 정보 수정 */
    public void updateMember(Long id, MemberDTO updateDto) {
        // 아이디(식별자 값)로 사용자 정보 가져옴
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        // 없을 경우 예외 발생
        
        // 새로 변경된 닉네임과 휴대폰 번호 저장
        // copy 메서드는 Member 엔티티의 닉네임과 휴대폰 번호를 변경하는 메서드. 불변 객체로 관리하기 위해 Setter 대신 사용
        Member updateMember = member.copy(updateDto.getNickname(), updateDto.getPhone());
        
        // 새로 변경된 멤버를 저장
        memberRepository.save(updateMember);

    }

    /* 회원 탈퇴
    * 회원의 식별자 값을 받아 리포지토리에서 삭제 */
    public void deleteMember(Long id) {
        // 회원이 존재하는 지 확인 후 삭제 여부 결정
        if(memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("회원이 존재하지 않습니다.");
        }
    }

}
