package copy.project.demo.repository;

import copy.project.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Created by SungHui on 2025. 3. 11.
 */
/* 찜 목록 리포지토리 */
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // 찜 목록 회원 아이디로 찾기
    List<Favorite> findByMemberId(Long id);

    // 찜 목록 존재여부 확인
    boolean existsByMemberIdAccommodationId(Long memberId, Long accommodationId);

    // 찜 목록 삭제
    void deleteByMemberIdAndAccommodationId(Long memberId, Long accommodationId);
}
