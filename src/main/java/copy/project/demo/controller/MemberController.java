package copy.project.demo.controller;

import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import copy.project.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SungHui on 2025. 1. 24.
 */
@CrossOrigin(origins = "http://localhost:5173") // CORS 정책을 해결하기 위해 추가. 5173 포트에서 실행 중인 클라이언트에서 요청 허용
@RestController // REST API 컨트롤러.
@RequestMapping("/members") // /members 경로를 기본 URL로 설정. 이 컨트롤러의 모든 엔드포인트는 /members 하위에 위치.
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 자동 생성. 의존성을 주입받음
public class MemberController {

    // 비즈니스 로직 처리 서비스 계층 객체
    private final MemberService memberService;
    // Member 엔티티를 MemberDTO로 변환하는데 사용
    private final ModelMapper mm;

    /* ModelMapper를 이용해 MemberDTO를 가져옴
    * 생성한 회원 정보를 DB에 저장하는 API */
    @PostMapping // POST /members 요청 처리
    // @RequestBody로 요청의 JSON 데이터를 MemberDTO 객체로 변환
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO){
        // MemberDTO를 Member 엔티티로 변환
        Member member = mm.map(memberDTO, Member.class);
        // 회원 정보 저장
        Member savedMember = memberService.saveMember(member);
        // 저장된 Member 엔티티를 MemberDTO로 변환 후 반환
        return mm.map(savedMember, MemberDTO.class);
    }

    /* 식별 값을 받아와서 회원 찾기*/
    @GetMapping("/id/{id}") // GET /members/id/{id}
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        Member memberById = memberService.findMemberById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));

        MemberDTO memberDTO = mm.map(memberById, MemberDTO.class);
        return ResponseEntity.ok(memberDTO);  // 200 OK 상태 코드로 DTO 반환
    }


    /* 이메일(loginId)로 회원 찾기*/
    @GetMapping("/loginId/{loginId}") // GET /members/loginId/{loginId}
    public MemberDTO getMemberByLoginId(@PathVariable String loginId) { // URL에서 loginId 값 가져옴
        // loginId로 회원 조회. 조회 안될 시 예외 발생
        Member memberByLoginId = memberService.findMemberByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("해당 아이디의 회원이 없습니다."));
        // 조회한 회원 정보를 DTO로 변환 후 반환
        return mm.map(memberByLoginId, MemberDTO.class);
    }

    /* 이름으로 회원 찾기 */
    @GetMapping("/name/{name}") // GET /members/name/{name}
    public List<MemberDTO> getMemberByName(@PathVariable String name) { // URL에서 name 값 가져옴
        // name 값으로 회원 리스트 조회
        List<Member> memberByName = memberService.findMemberByName(name);

        // 회원 리스트가 비어있을 때 예외 발생
        if(memberByName.isEmpty()) {
            throw new RuntimeException("해당 이름의 회원이 없습니다.");
        }
        // 회원 리스트를 MemberDTO 리스트로 변환 후 반환
        return memberByName.stream()
                .map(member -> mm.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /* 닉네임으로 회원 찾기 */
    @GetMapping("/nickname/{nickname}") // GET /members/nickname/{nickname}
    public MemberDTO getMemberByNickname(@PathVariable String nickname) { // URL에서 nickname 값을 가져옴
        // nickname 값으로 회원 조회. 없으면 예외 발생
        Member memberByNickname = memberService.findMemberByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("해당 닉네임의 회원이 없습니다."));
        // 조회된 회원을 DTO로 변환 후 반환
        return mm.map(memberByNickname, MemberDTO.class);
    }

    /* 휴대폰 번호로 회원 찾기 */
    @GetMapping("/phone/{phone}") // GET /members/phone/{phone}
    public MemberDTO getMemberByPhone(@PathVariable String phone) { // URL에서 phone 값을 가져옴
        // phone 값으로 회원 조회. 없으면 예외 발생
        Member memberByPhone = memberService.findMemberByPhone(phone)
                .orElseThrow(() -> new RuntimeException("해당 번호의 회원이 없습니다."));
        // 조회된 회원을 DTO로 변환 후 반환
        return mm.map(memberByPhone, MemberDTO.class);
    }
}