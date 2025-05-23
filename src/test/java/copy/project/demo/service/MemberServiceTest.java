package copy.project.demo.service;

import copy.project.demo.TestUtils;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by SungHui on 2025. 1. 28.
 */

/* MemberService 메서드 테스트 */
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
      List<Member> foundMember = memberService.findMemberByName(savedMember.getName());

      // then
      assertFalse(foundMember.isEmpty());
      assertEquals(member.getName(), foundMember.iterator().next().getName());
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

   @Test
   void findMemberByPhone() {

      // given
      Member member = TestUtils.createTestMember();
      Member savedMember = memberService.saveMember(member);

      // when
      Optional<Member> foundMember = memberService.findMemberByPhone(savedMember.getPhone());

      // then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getPhone(), foundMember.get().getPhone());
   }

}