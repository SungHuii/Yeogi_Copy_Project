package copy.project.demo.controller;

import copy.project.demo.dto.ReservationDTO;
import copy.project.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
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

    // 예약 정보 생성 API
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.findAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Optional<ReservationDTO> reservation = reservationService.findReservationById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long id) {
        ReservationDTO canceledReservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(canceledReservation);
    }
}
