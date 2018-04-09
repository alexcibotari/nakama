package com.alexcibotari.nakama;

import com.alexcibotari.nakama.config.ProfileConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(ProfileConstants.UNIT_TEST)
public class ExampleTest {

  @Test
  public void test() {
    Assert.assertTrue(true);
  }
}
