package com.alexcibotari.nakama.config.cassandra;

import static com.alexcibotari.nakama.config.ConfigurationConstants.PROFILE_DEV;

import com.alexcibotari.nakama.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.cassandra.core.keyspace.DropKeyspaceSpecification;
import org.springframework.cassandra.core.keyspace.KeyspaceOption;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CustomConversions;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration implements
  BeanClassLoaderAware {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Environment environment;
  private CassandraProperties properties;
  private ObjectMapper objectMapper;

  /**
   * Default constructor for Cassandra Configuration.
   *
   * @param environment environment properties
   * @param properties cassandra properties
   * @param objectMapper JSON object mapper
   */
  public CassandraConfiguration(Environment environment, CassandraProperties properties,
    ObjectMapper objectMapper) {
    this.environment = environment;
    this.properties = properties;
    this.objectMapper = objectMapper;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {

  }

  @Override
  protected String getKeyspaceName() {
    return properties.getKeyspaceName();
  }

  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    if (environment.acceptsProfiles(PROFILE_DEV)) {
      CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
        .createKeyspace(properties.getKeyspaceName())
        .ifNotExists()
        .with(KeyspaceOption.DURABLE_WRITES, true);
      return Collections.singletonList(specification);
    }
    return Collections.emptyList();
  }

  @Override
  protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
    if (environment.acceptsProfiles(PROFILE_DEV)) {
      return Collections
        .singletonList(DropKeyspaceSpecification.dropKeyspace(properties.getKeyspaceName()));
    }
    return Collections.emptyList();
  }

  @Override
  public CustomConversions customConversions() {
    return new CustomConversions(
      Arrays.asList()
    );
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.valueOf(properties.getSchemaAction().toUpperCase());
  }

  @Override
  protected int getPort() {
    return properties.getPort();
  }

  @Override
  public String[] getEntityBasePackages() {
    return new String[]{User.class.getPackage().getName()};
  }

  @PostConstruct
  public void init() {

  }

}
