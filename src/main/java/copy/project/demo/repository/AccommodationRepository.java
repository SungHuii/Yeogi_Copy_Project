package copy.project.demo.repository;

import copy.project.demo.entity.Accommodation;
import copy.project.demo.entity.enums.AccommodationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 숙소 Repository
*  Accommodation 엔티티와 연결되어 숙소 정보를 조회, 저장, 삭제하는 기능을 제공 (CRUD)
* */
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    // 이름, 타입, 주소를 기준으로 숙소들을 조회하는 메서드
    Page<Accommodation> findByNameContainingAndTypeAndAddressContaining(
            String name, AccommodationType type, String address, Pageable pageable);

    // 이름, 주소를 기준으로 숙소들을 조회하는 메서드 (타입 무시)
    Page<Accommodation> findByNameContainingAndAddressContaining(
            String name, String address, Pageable pageable);
}
