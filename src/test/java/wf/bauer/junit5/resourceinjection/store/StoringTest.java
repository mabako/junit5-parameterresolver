package wf.bauer.junit5.resourceinjection.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wf.bauer.junit5.resourceinjection.ExampleResource;

@ExtendWith(StoringExtension.class)
class StoringTest {

  private static final Logger LOG = LoggerFactory.getLogger(StoringTest.class);

  @Test
  void sayHello1(final ExampleResource resource) {
    LOG.debug("sayHello1: {}", resource.getResourceId());
  }

  @Test
  void sayHello2(final ExampleResource resource) {
    LOG.debug("sayHello2: {}", resource.getResourceId());
  }
}
