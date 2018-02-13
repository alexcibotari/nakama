package com.alexcibotari.nakama.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  @Override
  public String getCurrentAuditor() {
    String userName = SecurityUtils.getCurrentUserLogin();
    return (userName != null ? userName : AuthoritiesConstants.SYSTEM_ACCOUNT);
  }
}
