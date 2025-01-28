package copy.project.demo.repository;

import copy.project.demo.TestUtils;
import copy.project.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by SungHui on 2025. 1. 24.
 */

@Transactional
@DataJpaTest
class MemberRepositoryTest {

   @Autowired
   private MemberRepository memberRepository;


   @Test
   void saveMember() {

      // Given
      Member member = TestUtils.createTestMember();

      // When
      memberRepository.save(member);
      Optional<Member> foundMember = memberRepository.findById(member.getId());

      // Then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getName(), foundMember.get().getName());

   }

   @Test
   void findAll() {

      // given
      Member member = TestUtils.createTestMember();
      memberRepository.save(member);

      // When
      memberRepository.findAll();

      // then
      assertTrue(memberRepository.findAll().contains(member));

   }

   @Test
   void findByName() {

      // Given
      Member member = TestUtils.createTestMember();

      // When
      memberRepository.save(member);
      Optional<Member> foundMember = memberRepository.findByName("sunghui");

      // Then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getName(), foundMember.get().getName());
   }

   @Test
   void findByNickname() {

      // Given
      Member member = TestUtils.createTestMember();

      // When
      memberRepository.save(member);
      Optional<Member> foundMember = memberRepository.findByNickname("crong");

      // Then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getNickname(), foundMember.get().getNickname());
   }

   @Test
   void findByPhone() {

      // Given
      Member member = TestUtils.createTestMember();

      // When
      memberRepository.save(member);
      Optional<Member> foundMember = memberRepository.findByPhone("01012341234");

      // Then
      assertTrue(foundMember.isPresent());
      assertEquals(member.getPhone(), foundMember.get().getPhone());
   }
}