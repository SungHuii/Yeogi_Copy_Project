package copy.project.demo.controller;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SungHui on 2025. 3. 11.
 */
@RestController // REST 컨트롤러 선언
@RequestMapping("/favorite") // REST API 경로
@RequiredArgsConstructor
public class FavoriteController {

    // 서비스 주입
    private final FavoriteService favoriteService;

    // 찜 목록에 추가
    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestParam Long memberId, @RequestParam Long accommodationId) {
        // 회원과 숙소 식별자로 찜 목록 추가
        favoriteService.addFavorite(memberId, accommodationId);
        return ResponseEntity.ok("찜 목록 추가 완료"); // ok 반환
    }

    // 찜 목록 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AccommodationDTO>> getFavorites(@PathVariable Long memberId) {
        // 멤버 아이디로 찜 목록 조회하여 리턴
        return ResponseEntity.ok(favoriteService.getFavorites(memberId));
    }

    // 찜 목록 삭제
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestParam Long memberId, @RequestParam Long accommodationId) {
        // 회원과 숙소 식별자로 찜 목록 삭제
        favoriteService.removeFavorite(memberId, accommodationId);
        return ResponseEntity.ok("찜 목록 삭제 완료"); // ok 반환
    }
}
