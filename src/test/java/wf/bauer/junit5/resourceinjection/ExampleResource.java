package wf.bauer.junit5.resourceinjection;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleResource implements ExtensionContext.Store.CloseableResource {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleResource.class);
  private final UUID resourceId = UUID.randomUUID();

  public ExampleResource() {
    LOG.debug("Creating resource {}", resourceId);
  }

  public UUID getResourceId() {
    return resourceId;
  }

  @Override
  public void close() {
    LOG.debug("Closing resource {}", resourceId);
  }
}
