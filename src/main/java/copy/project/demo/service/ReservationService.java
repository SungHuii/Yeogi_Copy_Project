package copy.project.demo.service;

import copy.project.demo.dto.ReservationDTO;
import copy.project.demo.entity.AccommodationRoom;
import copy.project.demo.entity.Member;
import copy.project.demo.entity.Reservation;
import copy.project.demo.repository.AccommodationRoomRepository;
import copy.project.demo.repository.MemberRepository;
import copy.project.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by SungHui on 2025. 2. 20.
 */
/* 예약 정보 관련 비즈니스 로직 처리 서비스 */
@Service
@RequiredArgsConstructor
@Transactional // 트랜잭션 처리
public class ReservationService {

    // 예약 정보 리파지토리 주입
    ReservationRepository reservationRepository;
    // 회원 정보 리파지토리 주입
    MemberRepository memberRepository;
    // 숙소 방 정보 리파지토리 주입
    AccommodationRoomRepository accommodationRoomRepository;
    // ModelMapper 주입
    private final ModelMapper mm;

    // 예약 정보 생성
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {

        // 식별자 값으로 회원 정보 조회
        Member member = memberRepository.findById(reservationDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        // 식별자 값으로 숙소 방 정보 조회
        AccommodationRoom accommodationRoom = accommodationRoomRepository.findById(reservationDTO.getAccommodationRoomId())
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소 방이 존재하지 않습니다."));

        // 예약 정보 생성
        Reservation reservation = new Reservation(
                null,
                member, // reservationDTO.getMemberId()를 직접 사용하면 안됨
                accommodationRoom,
                reservationDTO.getReservationDate(),
                reservationDTO.getCheckIn(),
                reservationDTO.getCheckOut(),
                reservationDTO.getGuestCount(),
                reservationDTO.getTotalPrice(),
                reservationDTO.getStatus()
        );
        // 예약 정보 DB에 저장
        reservationRepository.save(reservation);

        // 예약 정보 DTO 반환
        return reservationDTO;
    }

    // 모든 예약 정보 조회
    public List<ReservationDTO> findAllReservations() {
        // 예약 정보 전체 조회
        List<Reservation> reservations = reservationRepository.findAll();
        // 예약 정보 DTO로 변환
        return reservations.stream()
                .map(reservation -> mm.map(reservation, ReservationDTO.class))
                .toList();
    }

    // 식별자 값으로 예약 정보 조회
    public Optional<ReservationDTO> findReservationById(Long id) {

        Optional<Reservation> reservation = reservationRepository.findById(id);

        return reservation.map(res -> mm.map(res, ReservationDTO.class));
    }

    // 예약 취소
    public ReservationDTO cancelReservation(Long id) {

        // 예약 정보 조회
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 존재하지 않습니다."));

        // 예약 정보 상태값 변경
        Reservation canceledReservation = reservation.cancel();
        // 변경된 예약 정보 저장
        reservationRepository.save(canceledReservation);
        // 변경된 예약 정보 DTO 반환
        return mm.map(canceledReservation, ReservationDTO.class);

    }

}
