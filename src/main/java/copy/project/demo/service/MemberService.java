package copy.project.demo.service;

import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /*
    @RequiredArgsConstructor 애너테이션으로 생성자 자동 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public Optional<Member> findMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Optional<Member> findMemberByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }

}
