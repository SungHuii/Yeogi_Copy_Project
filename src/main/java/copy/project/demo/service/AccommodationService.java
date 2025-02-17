package copy.project.demo.service;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.Accommodation;
import copy.project.demo.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHui on 2025. 2. 17.
 */
/* 숙소 관련 비즈니스 로직 처리 서비스
*  숙소 정보를 저장하고 조회하는 기능을 제공
* */
@Service // 서비스
@RequiredArgsConstructor // 생성자 자동 주입
@Transactional // 트랜잭션안에서 처리
public class AccommodationService {

    // 숙소 정보 저장 및 조회 담당 리파지토리
    private final AccommodationRepository accommodationRepository;

    // 숙소 정보 등록
    public Accommodation registerAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }


    public List<Accommodation> getAccommodationList() {
        return accommodationRepository.findAll();
    }

    public Accommodation getAccommodationById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 숙소가 없습니다."));
    }

    public Accommodation updateAccommodation(Long id, AccommodationDTO accommodationDTO) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 숙소가 없습니다."));

        accommodation.copy(accommodationDTO);
        accommodationRepository.save(accommodation);

        return accommodation;
    }

    // 숙소 삭제 처리
    public boolean deleteAccommodation(Long id) {
        // 아이디 값으로 숙소 조회
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 숙소가 없습니다."));

        // 숙소 삭제 처리
        accommodationRepository.delete(accommodation);

        return true; // 삭제되면 true 반환
    }
}
