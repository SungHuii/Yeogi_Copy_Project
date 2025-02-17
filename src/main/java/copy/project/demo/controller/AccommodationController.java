package copy.project.demo.controller;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.Accommodation;
import copy.project.demo.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SungHui on 2025. 2. 17.
 */
/* 숙소 관련 요청 처리 컨트롤러 */
@RestController // REST API 컨트롤러로 사용
@RequestMapping("/accommodation") // 숙소 관련 요청 시작 URL
@RequiredArgsConstructor // 생성자 자동 주입
public class AccommodationController {

    // 숙소 관련 비즈니스 로직 처리 서비스 주입
    private final AccommodationService accommodationService;
    // Accommodation 엔티티를 AccommodationDTO로 변환하는데 사용
    private final ModelMapper mm;

    // 숙소 정보 등록 메서드
    @PostMapping // POST 요청 처리
    public ResponseEntity<AccommodationDTO> register(@RequestBody AccommodationDTO accommodationDTO) {
        // DTO -> 엔티티로 변환
        Accommodation accommodation = mm.map(accommodationDTO, Accommodation.class);
        // 숙소 정보 등록
        Accommodation savedAccommodation = accommodationService.registerAccommodation(accommodation);
        // 엔티티 -> DTO로 변환 후 ok(200) 반환
        return ResponseEntity.ok(mm.map(savedAccommodation, AccommodationDTO.class));
    }

    // 모든 숙소 목록 조회 메서드
    @GetMapping // GET /accommodation 요청 처리
    public ResponseEntity<List<AccommodationDTO>> getAccommodationList() {
        // 숙소 목록 조회
        List<Accommodation> accommodationList = accommodationService.getAccommodationList();

        // 엔티티 -> DTO로 변환
        List<AccommodationDTO> accommodationDTOS = accommodationList.stream()
                .map(accommodation -> mm.map(accommodation, AccommodationDTO.class))
                .collect(Collectors.toList());

        // DTO 목록 반환
        return ResponseEntity.ok(accommodationDTOS);

    }

    // 숙소 상세 조회 메서드
    @GetMapping("/{id}") // GET /accommodation/{id} 요청 처리.
    public ResponseEntity<AccommodationDTO> getAccommodationById(@PathVariable Long id) {
        // id로 숙소 조회
        Accommodation accommodation = accommodationService.getAccommodationById(id);
        // 엔티티 -> DTO로 변환 후 반환
        return ResponseEntity.ok(mm.map(accommodation, AccommodationDTO.class));
    }

    // 숙소 정보 수정 메서드
    @PutMapping("/{id}") // PUT /accommodation/{id} - 숙소 정보 수정
    public ResponseEntity<AccommodationDTO> updateAccommodation(@PathVariable Long id, @RequestBody AccommodationDTO accommodationDTO) {
        Accommodation updatedAccommodation = accommodationService.updateAccommodation(id, accommodationDTO);
        if (updatedAccommodation == null) {
            return ResponseEntity.notFound().build(); // 숙소를 찾을 수 없으면 404 반환
        }
        return ResponseEntity.ok(mm.map(updatedAccommodation, AccommodationDTO.class));
    }

    // 숙소 정보 삭제 메서드
    @DeleteMapping("/{id}") // DELETE /accommodation/{id} - 숙소 정보 삭제
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        boolean isDeleted = accommodationService.deleteAccommodation(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build(); // 삭제할 숙소가 없으면 404 반환
        }
        return ResponseEntity.noContent().build(); // 삭제되었을 땐 204 반환
    }

}
