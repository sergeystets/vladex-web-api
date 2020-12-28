package com.sergeystets.vladex.web.api.config;

import com.sergeystets.vladex.web.api.entity.PendingVerificationEntity;
import com.sergeystets.vladex.web.api.repository.PendingVerificationRepository;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcUserDetailsService implements UserDetailsService {

  private final Clock clock;
  private final PasswordEncoder passwordEncoder;
  private final PendingVerificationRepository pendingVerificationRepository;

  @Override
  public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
    final PendingVerificationEntity pendingUser = pendingVerificationRepository.findOneByPhoneNumber(userPhone);
    if (pendingUser == null) {
      throw new UsernameNotFoundException("No user found with phone number: " + userPhone);
    }

    final String password = passwordEncoder.encode(pendingUser.getOtp());
    final boolean credentialsNonExpired = LocalDateTime.now(clock).isBefore(pendingUser.getExpiration());
    final boolean enabled = true;
    final boolean accountNonExpired = true;
    final boolean accountNonLocked = true;

    return new User(
        userPhone,
        password,
        enabled,
        accountNonExpired,
        credentialsNonExpired,
        accountNonLocked,
        Collections.singletonList(new SimpleGrantedAuthority("USER")));
  }
}
