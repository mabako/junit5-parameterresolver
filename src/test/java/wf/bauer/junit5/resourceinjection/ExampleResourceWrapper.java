package wf.bauer.junit5.resourceinjection;

import java.io.Closeable;

import org.junit.jupiter.api.extension.ExtensionContext;

public class ExampleResourceWrapper<T extends Closeable> implements ExtensionContext.Store.CloseableResource {

  private final T resource;

  public ExampleResourceWrapper(final T resource) {
    this.resource = resource;
  }

  public T get() {
    return resource;
  }

  @Override
  public void close() throws Throwable {
    resource.close();
  }
}
