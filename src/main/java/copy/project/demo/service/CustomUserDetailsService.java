package copy.project.demo.service;

import copy.project.demo.repository.MemberRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by SungHui on 2025. 2. 6.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

   private MemberRepository memberRepository;


   @Override
   public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

      return memberRepository.findByLoginId(loginId)
            .map(member -> new org.springframework.security.core.userdetails.User(
                  member.getLoginId(),
                  member.getPassword(),
                  AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().name())
            ))
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      /* if ("test".equals(username)) {
         return User.withUsername("test")
               .password("{noop}password")  // 비밀번호 인코딩 안 된 경우 (noop 사용)
               .roles("USER")
               .build();
      }
      throw new UsernameNotFoundException("User not found");*/
   }
}
