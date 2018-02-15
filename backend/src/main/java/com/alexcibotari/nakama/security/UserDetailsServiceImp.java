package com.alexcibotari.nakama.security;

import com.alexcibotari.nakama.model.User;
import com.alexcibotari.nakama.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.debug("Authenticating {}", username);
    String lowercaseUsername = username.toLowerCase();
    Optional<User> userFromDatabase = userRepository.findOneByLogin(lowercaseUsername);

    return userFromDatabase.map(user -> {
      if (!user.isEnabled()) {
        throw new UserNotEnabledException("User " + lowercaseUsername + " was not enabled");
      }
      List<GrantedAuthority> grantedAuthorities = new ArrayList<>(/*user.getAuthorities()*/);
      return new org.springframework.security.core.userdetails.User(lowercaseUsername,
        user.getPassword(),
        grantedAuthorities);
    }).orElseThrow(
      () -> new UsernameNotFoundException("User " + lowercaseUsername + " was not found in the "
        + "database"));
  }

}
