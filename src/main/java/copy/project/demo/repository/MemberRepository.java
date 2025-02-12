package copy.project.demo.repository;

import copy.project.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 회원 Repository
* Member 엔티티와 연결되어 회원 정보를 조회, 저장, 삭제하는 기능을 제공 (CRUD)
* */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // CRUD 메서드 (save, findById, findAll)는 JpaRepository로 해결
    // 추가적인 메서드는 메서드 이름을 통해 쿼리 메서드로 해결

    // 로그인 아이디로 회원 조회
    Optional<Member> findByLoginId(String loginId);
    // 이름으로 회원 조회
    List<Member> findByName(String name);
    // 닉네임으로 회원 조회
    Optional<Member> findByNickname(String nickname);
    // 전화번호로 회원 조회
    Optional<Member> findByPhone(String phone);


}
