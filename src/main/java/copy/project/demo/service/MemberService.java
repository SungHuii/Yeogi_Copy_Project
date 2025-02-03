package copy.project.demo.service;

import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * Created by SungHui on 2025. 1. 24.
 */

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 중복확인
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public Optional<Member> findMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Optional<Member> findMemberByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }

    /* 회원 정보 조회 */
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
    /*public void updateMember(Long id, MemberDTO updateDto) {
        memberRepository.findById(id).isPresent(member -> {
            member.setNickname(updateDto.getNickname());
            member.setPhone(updateDto.getPhone());
        });
    }*/

    /* 회원 탈퇴 */
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
