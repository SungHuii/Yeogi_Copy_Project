package copy.project.demo.service;

import copy.project.demo.TestUtils;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by SungHui on 2025. 1. 28.
 */
@SpringBootTest
@Transactional
class MemberServiceTest {

   @Autowired
   private MemberRepository memberRepository;

   @Autowired
   private MemberService memberService;

   @Test
   void saveMember() {

      // given
      Member member = TestUtils.createTestMember();

      // when
      Member savedMember = memberService.saveMember(member);

      // then
      assertNotNull(savedMember);
      assertEquals(member.getName(), savedMember.getName());
   }

   @Test
   void findMemberById() {

      // given
      Member member = TestUtils.createTestMember();
      Member savedMember = memberService.saveMember(member);

      // when
      Optional<Member> foundMember = memberService.findMemberById(savedMember.getId());

      // then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getId(), foundMember.get().getId());
   }

   @Test
   void findMemberByName() {

      // given
      Member member = TestUtils.createTestMember();
      Member savedMember = memberService.saveMember(member);

      // when
      Optional<Member> foundMember = memberService.findMemberByName(savedMember.getName());

      // then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getName(), foundMember.get().getName());
   }

   @Test
   void findMemberByNickname() {

      // given
      Member member = TestUtils.createTestMember();
      Member savedMember = memberService.saveMember(member);

      // when
      Optional<Member> foundMember = memberService.findMemberByNickname(savedMember.getNickname());

      // then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getNickname(), foundMember.get().getNickname());
   }



   public Optional<Member> findMemberByNickname(String nickname) {
      return memberRepository.findByNickname(nickname);
   }

   public Optional<Member> findMemberByPhone(String phone) {
      return memberRepository.findByPhone(phone);
   }

}