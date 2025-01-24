package copy.project.demo.controller;

import copy.project.demo.entity.Member;
import copy.project.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member){
        return memberService.saveMember(member);
    }

    /* 식별 값을 받아와서 회원 찾기*/
    @GetMapping("/member/id/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.findMemberById(id).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    /* 이름으로 회원 찾기 */
    @GetMapping("/member/name/{name}")
    public Member getMemberByName(@PathVariable String name) {
        return memberService.findMemberByName(name).orElseThrow(() -> new RuntimeException("해당 이름의 회원이 없습니다."));
    }

    /* 닉네임으로 회원 찾기 */
    @GetMapping("/member/nickname/{nickname}")
    public Member getMemberByNickname(@PathVariable String nickname) {
        return memberService.findMemberByNickname(nickname).orElseThrow(() -> new RuntimeException("해당 닉네임의 회원이 없습니다"));
    }

    /* 휴대폰 번호로 회원 찾기 */
    @GetMapping("/member/phone/{phone}")
    public Member getMemberByPhone(@PathVariable String phone) {
        return memberService.findMemberByPhone(phone).orElseThrow(() -> new RuntimeException("해당 번호를 가진 회원이 없습니다."));
    }


}
