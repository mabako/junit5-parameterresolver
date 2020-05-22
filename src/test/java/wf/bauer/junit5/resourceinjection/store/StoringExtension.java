package wf.bauer.junit5.resourceinjection.store;

import java.io.InputStream;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import wf.bauer.junit5.resourceinjection.ExampleResource;

public class StoringExtension implements BeforeAllCallback, ParameterResolver {

  private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(StoringExtension.class);
  private static final String KEY_EXAMPLE_RESOURCE = "example-resource";

  @Override
  public void beforeAll(final ExtensionContext context) {
    context.getStore(NAMESPACE)
        .put(KEY_EXAMPLE_RESOURCE, new ExampleResource());
  }

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext,
      final ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType() == ExampleResource.class;
  }

  @Override
  public ExampleResource resolveParameter(
      final ParameterContext parameterContext,
      final ExtensionContext extensionContext) {
    return extensionContext.getStore(NAMESPACE).get(KEY_EXAMPLE_RESOURCE, ExampleResource.class);
  }
}
