package copy.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	/*// 테스트 데이터 생성 메서드
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
	}*/
}
