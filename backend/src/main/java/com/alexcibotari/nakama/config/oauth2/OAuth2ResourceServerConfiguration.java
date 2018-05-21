package com.alexcibotari.nakama.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
      .cors().and()
      .authorizeRequests()
      .antMatchers("/api/", "/graphql").authenticated().and()
      .headers().frameOptions().sameOrigin().and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .csrf().disable();
  }

}
