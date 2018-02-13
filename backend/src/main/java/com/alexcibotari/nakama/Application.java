package com.alexcibotari.nakama;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);

    ApplicationContext context = app.run(args);
    Environment env = context.getEnvironment();
    String serverPort = env.getProperty("server.port", "8080");
  }

}
