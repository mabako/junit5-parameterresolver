package wf.bauer.junit5.resourceinjection.instancefactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import wf.bauer.junit5.resourceinjection.ExampleResource;

@ExtendWith(FactoryResourceExtension.class)
class FactoryTest {

  private final ExampleResource resource;

  public FactoryTest(ExampleResource resource) {
    this.resource = resource;
  }

  @Test
  void ensureInstanceExists() {
    assertNotNull(resource);
  }

  @Test
  void isItStillTheSame() {
    assertNotNull(resource);
  }
}
