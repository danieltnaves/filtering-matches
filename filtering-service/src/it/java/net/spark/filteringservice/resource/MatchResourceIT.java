package net.spark.filteringservice.resource;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public class MatchResourceIT {

  @ClassRule
  public static DockerComposeContainer compose =
      new DockerComposeContainer(new File("src/it/resources/test-compose.yml"))
          .withExposedService("mongodb", 27017);

  @Test
  public void test() {
    Assert.assertEquals("ddd", "33");
  }
}
