package wf.bauer.junit5.resourceinjection.postprocessing;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import wf.bauer.junit5.resourceinjection.ExampleResource;

@ExtendWith(PostProcessingExtension.class)
class PostProcessingTest {

  ExampleResource resource;

  @Test
  void ensureInstanceExists() {
    assertNotNull(resource);
  }

  @Test
  void isItStillTheSame() {
    assertNotNull(resource);
  }
}
