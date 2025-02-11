package copy.project.demo;

import copy.project.demo.entity.Member;
import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.entity.enums.MemberType;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 28.
 */

public class TestUtils {
   public static Member createTestMember() {
      LocalDate localDate = LocalDate.of(1994, 10, 2);
      return new Member(null, "sunghui@gmail.com", null, MemberType.EMAIL, "sunghui", "crong", "01012341234", MemberGender.M, localDate, MemberRole.USER);
   }
}
