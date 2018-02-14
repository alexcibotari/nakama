package com.alexcibotari.nakama.config.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.alexcibotari.nakama.config.ConfigurationConstants.PROFILE_DEV;

@Component
@Order
@Profile(PROFILE_DEV)
public class DataInitializer {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private CassandraOperations cassandraOperations;

  /**
   * Data Initialization.
   *
   * @param cassandraOperations service Injection
   */
  public DataInitializer(CassandraOperations cassandraOperations) {
    this.cassandraOperations = cassandraOperations;
  }

  /**
   * Insert dummy data for testing.
   */
  @EventListener(ContextRefreshedEvent.class)
  public void init() throws IOException {
    logger.info("Start data initialization ....");

    DirectoryStream<Path> directory = Files
      .newDirectoryStream(Paths.get(ResourceUtils.getFile("classpath:cassandra").toURI()));
    for (Path file : directory) {
      logger.info("Initialize {}", file.getFileName());
      long rows = Files.lines(file)
        .filter(line -> !line.startsWith("#"))
        .peek(line -> {
          if (logger.isDebugEnabled()) {
            logger.debug(line);
          }
        })
        .peek(cassandraOperations::execute)
        .count();
      logger.info("{} rows inserted.", rows);
    }
  }
}
