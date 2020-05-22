package wf.bauer.junit5.resourceinjection.instancefactory;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstantiationException;
import wf.bauer.junit5.resourceinjection.ExampleResource;

public class FactoryResourceExtension implements TestInstanceFactory {

  private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(FactoryResourceExtension.class);
  private static final String KEY_EXAMPLE_RESOURCE = "example-resource";

  @Override
  public Object createTestInstance(
      final TestInstanceFactoryContext factoryContext,
      final ExtensionContext extensionContext)
      throws TestInstantiationException {

    ExampleResource resource = extensionContext
        .getStore(NAMESPACE)
        .getOrComputeIfAbsent(KEY_EXAMPLE_RESOURCE, k -> new ExampleResource(), ExampleResource.class);

    try {
      return factoryContext.getTestClass()
        .getDeclaredConstructor(ExampleResource.class)
        .newInstance(resource);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new TestInstantiationException("Could not create test class: " + e.getMessage(), e);
    }
  }
}
