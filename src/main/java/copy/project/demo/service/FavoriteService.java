package copy.project.demo.service;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.Accommodation;
import copy.project.demo.entity.Favorite;
import copy.project.demo.entity.Member;
import copy.project.demo.repository.AccommodationRepository;
import copy.project.demo.repository.FavoriteRepository;
import copy.project.demo.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SungHui on 2025. 3. 11.
 */
@Service
@RequiredArgsConstructor
public class FavoriteService {

    // 리파지토리 추가
    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;
    // 모델매퍼 추가
    private final ModelMapper mm;

    // 찜 목록 추가
    public void addFavorite(Long memberId, Long accommodationId) {
        if(favoriteRepository.existsByMemberIdAccommodationId(memberId, accommodationId)) {
            throw new IllegalStateException("이미 즐겨찾기에 추가된 숙소입니다.");
        }

        // 회원 존재여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("회원이 존재하지 않습니다."));
        // 숙소 존재여부 확인
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new EntityNotFoundException("숙소가 존재하지 않습니다."));

        // 존재여부 확인한 회원과 숙소로 찜 생성
        Favorite favorite = Favorite.createFavorite(member, accommodation);
        // 생성한 찜 DB 저장
        favoriteRepository.save(favorite);
    }

    // 찜 목록 조회
    public List<AccommodationDTO> getFavorites(Long memberId) {
        // 회원으로 찜 목록 조회
        List<Favorite> favorites = favoriteRepository.findByMemberId(memberId);

        // 조회한 찜 목록을 DTO로 변환하여 반환
        return favorites.stream()
                .map(favorite -> mm.map(favorite.getAccommodation(), AccommodationDTO.class))
                .toList();
    }

    // 찜 목록 삭제
    public void removeFavorite(Long memberId, Long accommodationId) {
        // 찜 존재 여부 확인
        if(!favoriteRepository.existsByMemberIdAccommodationId(memberId, accommodationId)) {
            throw new IllegalStateException("찜 목록에 해당 숙소가 없습니다.");
        }

        // 찜 목록에서 삭제
        favoriteRepository.deleteByMemberIdAndAccommodationId(memberId, accommodationId);
    }
}
