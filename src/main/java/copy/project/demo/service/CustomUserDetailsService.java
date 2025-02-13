package copy.project.demo.service;

import copy.project.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by SungHui on 2025. 2. 6.
 */
/* Spring Security에서 사용자 정보를 가져오는 인터페이스 구현 */
@Slf4j // 로그 출력용
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

   // DB에서 회원 정보를 조회하는 데 사용
   private final MemberRepository memberRepository;

   // 로그인 시 사용자 정보를 조회하는 메서드
   @Override
   public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

      // loginId로 회원 정보 조회
      return memberRepository.findByLoginId(loginId)
              // 회원 정보가 있으면, UserDetails 객체로 변환하여 반환
            .map(member -> new org.springframework.security.core.userdetails.User(
                  member.getLoginId(), // 로그인 아이디
                  member.getPassword(), // DB에 저장된 암호화된 비밀번호
                  AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().name())
                    // Spring Security에서 사용하는 권한 목록. ROLE_ ~ 형태로 인식하기 때문에 ROLE_ 접두어를 붙임
            ))
            .orElseThrow(() -> {
                log.warn("로그인 실패, loginId: {}", loginId);
                return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
            });
   }
}
