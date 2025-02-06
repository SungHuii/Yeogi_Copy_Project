package copy.project.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      if ("test".equals(username)) {
         return User.withUsername("test")
               .password("{noop}password")  // 비밀번호 인코딩 안 된 경우 (noop 사용)
               .roles("USER")
               .build();
      }
      throw new UsernameNotFoundException("User not found");
   }
}
