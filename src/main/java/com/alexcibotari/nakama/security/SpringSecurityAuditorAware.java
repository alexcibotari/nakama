package com.alexcibotari.nakama.security;

import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserName();
        if (userName == null) {
            userName = "system";
        }
        return userRepository.findOneByUserName(AuthoritiesConstants.SYSTEM_ACCOUNT).get();
    }
}
