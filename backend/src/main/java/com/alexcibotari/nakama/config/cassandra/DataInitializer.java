package com.alexcibotari.nakama.config.cassandra;

import com.alexcibotari.nakama.config.ProfileConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlOperations;
import org.springframework.stereotype.Component;

@Component
@Order
@Profile(ProfileConstants.DEV)
public class DataInitializer {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private CassandraOperations cassandraOperations;
  private ResourceLoader resourceLoader;

  /**
   * Data Initialization.
   *
   * @param cassandraOperations service Injection
   */
  public DataInitializer(CassandraOperations cassandraOperations,
    ResourceLoader resourceLoader) {
    this.cassandraOperations = cassandraOperations;
    this.resourceLoader = resourceLoader;
  }

  /**
   * Insert dummy data for testing.
   */
  @EventListener(ContextRefreshedEvent.class)
  public void init() throws IOException {
    logger.info("Start data initialization ....");
    execute("users.cql");
  }

  private void execute(String fileName) throws IOException {
    logger.info("Initialize {}", fileName);
    Resource resource = resourceLoader.getResource("classpath:cassandra/" + fileName);
    CqlOperations cqlOperations = cassandraOperations.getCqlOperations();
    BufferedReader reader = new BufferedReader(
      new InputStreamReader(resource.getURL().openStream()));
    long rows = reader.lines()
      .filter(line -> !line.startsWith("#"))
      .peek(line -> {
        if (logger.isDebugEnabled()) {
          logger.debug(line);
        }
      })
      .peek(cqlOperations::execute)
      .count();
    logger.info("{} rows inserted.", rows);
  }
}
