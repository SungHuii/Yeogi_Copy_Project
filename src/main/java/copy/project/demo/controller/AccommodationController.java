package copy.project.demo.controller;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.enums.AccommodationType;
import copy.project.demo.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 17.
 */
/* 숙소 관련 요청 처리 컨트롤러 */
//@CrossOrigin(origins = "http://localhost:5173")
@RestController // REST API 컨트롤러로 사용
@RequestMapping("/accommodation") // 숙소 관련 요청 시작 URL
@RequiredArgsConstructor // 생성자 자동 주입
public class AccommodationController {

    // 숙소 관련 비즈니스 로직 처리 서비스 주입
    private final AccommodationService accommodationService;

    // 숙소 정보 등록 메서드
    @PostMapping // POST 요청 처리
    public ResponseEntity<AccommodationDTO> register(@RequestBody AccommodationDTO accommodationDTO) {
        // 숙소 정보 등록
        AccommodationDTO savedAccommodation = accommodationService.registerAccommodation(accommodationDTO);
        // 등록된 숙소 정보 반환
        return ResponseEntity.ok(savedAccommodation);
    }

    // 모든 숙소 목록 조회 메서드
    @GetMapping // GET /accommodation 요청 처리
    public ResponseEntity<List<AccommodationDTO>> getAccommodationList() {
        // 숙소 목록 조회
        List<AccommodationDTO> accommodationList = accommodationService.getAccommodationList();
        // 숙소 목록 반환
        return ResponseEntity.ok(accommodationList);

    }

    // 숙소 이름으로 조회 메서드
    @GetMapping("/name/{name}") // GET /accommodation/name/{name} 요청 처리
    public ResponseEntity<List<AccommodationDTO>> getAccommodationByName(@PathVariable String name) {
        // 숙소 이름으로 조회
        List<AccommodationDTO> accommodations = accommodationService.getAccommodationByName(name);
        // 숙소 목록 반환
        return ResponseEntity.ok(accommodations);
    }

    // 숙소 상세 조회 메서드
    @GetMapping("/{id}") // GET /accommodation/{id} 요청 처리.
    public ResponseEntity<AccommodationDTO> getAccommodationById(@PathVariable Long id) {
        // id로 숙소 조회
        Optional<AccommodationDTO> accommodation = accommodationService.getAccommodationById(id);
        // 숙소가 존재하면 200 반환, 없으면 404 반환
        return accommodation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 숙소 정보 수정 메서드
    @PutMapping("/{id}") // PUT /accommodation/{id} - 숙소 정보 수정
    public ResponseEntity<AccommodationDTO> updateAccommodation(@PathVariable Long id, @RequestBody AccommodationDTO accommodationDTO) {
        // 숙소 정보 수정
        AccommodationDTO updatedAccommodation = accommodationService.updateAccommodation(id, accommodationDTO);
        // 수정된 숙소 정보 반환
        return ResponseEntity.ok(updatedAccommodation);
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

    // 타입을 리스트로 반환하는 메서드
    @GetMapping("/types") // GET /accommodation/types - 숙소 타입 목록 조회
    public ResponseEntity<List<String>> getAccommodationTypes() {
        // 숙소 타입 목록
        List<String> types = Arrays.stream(AccommodationType.values())
                .map(Enum::name)
                .toList();
        // 타입 목록 반환
        return ResponseEntity.ok(types);
    }

    // 이름, 타입, 주소를 기준으로 숙소들을 조회하는 메서드
    @GetMapping("/search") // GET /accommodation/search - 숙소 검색
    public ResponseEntity<Page<AccommodationDTO>> searchAccommodations(
            @RequestParam(required = false) String name, // required = false 설정 시 필수값이 아님
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page, // 페이지 번호, 사이즈는 기본값 0, 10으로 설정
            @RequestParam(defaultValue = "10") int size) {

        // 숙소 검색
        Page<AccommodationDTO> accommodations = accommodationService.searchAccommodations(name, type, address, PageRequest.of(page, size));
        // DTO 페이지 반환 (페이징 처리된 숙소 목록)
        return ResponseEntity.ok(accommodations);
    }


}
