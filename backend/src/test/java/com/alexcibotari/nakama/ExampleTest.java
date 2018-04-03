package com.alexcibotari.nakama;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import com.alexcibotari.nakama.config.ProfileConstants;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@EmbeddedCassandra
@CassandraDataSet(value = {"cassandra/tables.cql"})
@TestExecutionListeners(
  listeners = CassandraUnitDependencyInjectionTestExecutionListener.class,
  mergeMode = MERGE_WITH_DEFAULTS)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(ProfileConstants.UNIT_TEST)
public class ExampleTest {

  @Test
  public void test() {
    Assert.assertTrue(true);
  }
}
