package copy.project.demo.repository;

import copy.project.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * Created by SungHui on 2025. 1. 24.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    // CRUD 메서드 (save, findById, findAll)는 JpaRepository로 해결

    List<Member> findByName(String name);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByPhone(String phone);


}
