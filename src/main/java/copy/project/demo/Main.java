package copy.project.demo;

import copy.project.demo.entity.Member;
import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.entity.enums.MemberType;
import copy.project.demo.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public static Member createTestMember() {
		LocalDate localDate = LocalDate.of(1994, 10, 2);
		return new Member(null, "sunghui@gmail.com", null, MemberType.EMAIL, "sunghui", "crong", "01012341234", MemberGender.M, localDate, MemberRole.USER);
	}

	@Bean
	public CommandLineRunner demo(MemberService memberService) {
		return args -> {
			// 테스트 데이터 생성
			Member member = createTestMember();
			memberService.saveMember(member);

			System.out.println("테스트 데이터 생성 완료");
		};
	}
}
