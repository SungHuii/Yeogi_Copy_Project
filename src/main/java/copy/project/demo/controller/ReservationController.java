package copy.project.demo.controller;

import copy.project.demo.dto.ReservationDTO;
import copy.project.demo.entity.Reservation;
import copy.project.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 20.
 */
/* 예약 정보 관련 API 요청 처리 컨트롤러 */
@RestController // REST API 컨트롤러로 사용
@RequestMapping("/reservation") // 결제 관련 요청 시작 URL
@RequiredArgsConstructor
public class ReservationController {

    // 예약 정보 관련 비즈니스 로직 처리 서비스 주입
    private final ReservationService reservationService;
    // 예약 정보 DTO로 변환하는데 사용
    private final ModelMapper mm;

    // 예약 정보 생성 API
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        // DTO -> 엔티티로 변환
        Reservation reservation = mm.map(reservationDTO, Reservation.class);

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(mm.map(createdReservation, ReservationDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.findAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Optional<ReservationDTO> reservation = Optional.ofNullable(reservationService.findReservationById(id));
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
