package com.alexcibotari.nakama.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

  @Bean
  Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    JavaTimeModule module = new JavaTimeModule();
    return new Jackson2ObjectMapperBuilder()
      .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .findModulesViaServiceLoader(true)
      .modulesToInstall(module);
  }
}
