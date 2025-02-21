package copy.project.demo.service;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.Accommodation;
import copy.project.demo.entity.enums.AccommodationType;
import copy.project.demo.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    // ModelMapper 주입
    private final ModelMapper mm;

    // 숙소 정보 등록
    public AccommodationDTO registerAccommodation(AccommodationDTO accommodationDTO) {
        // DTO -> 엔티티로 변환
        Accommodation accommodation = mm.map(accommodationDTO, Accommodation.class);
        // 숙소 정보 저장
        accommodation = accommodationRepository.save(accommodation);
        // 엔티티 -> DTO로 변환해서 반환
        return mm.map(accommodation, AccommodationDTO.class);
    }


    public List<AccommodationDTO> getAccommodationList() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream()
                .map(accommodation -> mm.map(accommodation, AccommodationDTO.class))
                .toList();
    }

    public List<AccommodationDTO> getAccommodationByName(String name) {
        return accommodationRepository.findByName(name).stream()
                .map(accommodation -> mm.map(accommodation, AccommodationDTO.class))
                .toList();
    }

    public AccommodationDTO updateAccommodation(Long id, AccommodationDTO accommodationDTO) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 숙소가 없습니다."));

        mm.map(accommodationDTO, accommodation);
        accommodationRepository.save(accommodation);

        return mm.map(accommodation, AccommodationDTO.class);
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

    // 숙소 검색 (숙소명, 숙소타입, 주소)
    public Page<AccommodationDTO> searchAccommodations(String name, String type, String address, Pageable pageable) {
        name = (name == null) ? "" : name;
        address = (address == null) ? "" : address;

        Page<Accommodation> accommodations;
        if (type == null || type.isEmpty() || type.equalsIgnoreCase("ALL")) {
            accommodations = accommodationRepository.findByNameContainingAndAddressContaining(name, address, pageable);
        } else {
            AccommodationType accommodationType = AccommodationType.valueOf(type.toUpperCase());
            accommodations = accommodationRepository.findByNameContainingAndTypeAndAddressContaining(name, accommodationType, address, pageable);
        }

        return accommodations.map(accommodation -> mm.map(accommodation, AccommodationDTO.class));
    }

    // 숙소 상세 조회
    public Optional<AccommodationDTO> getAccommodationById(Long id) {
        return accommodationRepository.findById(id)
                .map(accommodation -> mm.map(accommodation, AccommodationDTO.class));
    }
}
