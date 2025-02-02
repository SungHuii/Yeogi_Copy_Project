package copy.project.demo.controller;

import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SungHui on 2025. 1. 24.
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper mm;

    /* ModelMapper를 이용해 MemberDTO를 가져옴 */   
    @PostMapping
    public MemberDTO createMember(@RequestBody Member member){
        Member savedMember = memberService.saveMember(member);
        return mm.map(savedMember, MemberDTO.class);
    }

    /* 식별 값을 받아와서 회원 찾기*/
    @GetMapping("/id/{id}")
    public MemberDTO getMemberById(@PathVariable Long id){
        Member memberById = memberService.findMemberById(id).
                orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));
        return mm.map(memberById, MemberDTO.class);
    }

    /* 이메일(loginId)로 회원 찾기*/
    @GetMapping("/loginId/{loginId}")
    public MemberDTO getMemberByLoginId(@PathVariable String loginId) {
        Member memberByLoginId = memberService.findMemberByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("해당 아이디의 회원이 없습니다."));
        return mm.map(memberByLoginId, MemberDTO.class);
    }

    /* 이름으로 회원 찾기 */
    @GetMapping("/name/{name}")
    public List<MemberDTO> getMemberByName(@PathVariable String name) {
        List<Member> memberByName = memberService.findMemberByName(name);

        if(memberByName.isEmpty()) {
            throw new RuntimeException("해당 이름의 회원이 없습니다.");
        }

        return memberByName.stream()
                .map(member -> mm.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /* 닉네임으로 회원 찾기 */
    @GetMapping("/nickname/{nickname}")
    public MemberDTO getMemberByNickname(@PathVariable String nickname) {
        Member memberByNickname = memberService.findMemberByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("해당 닉네임의 회원이 없습니다."));
        return mm.map(memberByNickname, MemberDTO.class);
    }

    /* 휴대폰 번호로 회원 찾기 */
    @GetMapping("/phone/{phone}")
    public MemberDTO getMemberByPhone(@PathVariable String phone) {
        Member memberByPhone = memberService.findMemberByPhone(phone)
                .orElseThrow(() -> new RuntimeException("해당 번호의 회원이 없습니다."));
        return mm.map(memberByPhone, MemberDTO.class);
    }
}