package copy.project.demo.repository;

import copy.project.demo.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 숙소 Repository
*  Accommodation 엔티티와 연결되어 숙소 정보를 조회, 저장, 삭제하는 기능을 제공 (CRUD)
* */
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    // 이름을 기준으로 숙소를 조회하는 메서드
    Optional<Accommodation> findByName(String name);
}
