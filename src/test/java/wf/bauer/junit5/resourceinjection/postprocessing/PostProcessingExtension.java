package wf.bauer.junit5.resourceinjection.postprocessing;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;
import wf.bauer.junit5.resourceinjection.ExampleResource;
import wf.bauer.junit5.resourceinjection.instancefactory.FactoryResourceExtension;

public class PostProcessingExtension implements TestInstancePostProcessor {

  private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(FactoryResourceExtension.class);
  private static final String KEY_EXAMPLE_RESOURCE = "example-resource";

  @Override
  public void postProcessTestInstance(final Object testInstance, final ExtensionContext extensionContext) throws IllegalAccessException {

    ExampleResource resource = extensionContext
      .getStore(NAMESPACE)
      .getOrComputeIfAbsent(KEY_EXAMPLE_RESOURCE, k -> new ExampleResource(), ExampleResource.class);

    List<Field> fieldsToInject = ReflectionSupport.findFields(testInstance.getClass(), field -> field.getType() == ExampleResource.class, HierarchyTraversalMode.TOP_DOWN);
    for (Field field : fieldsToInject) {
      field.set(testInstance, resource);
    }
  }
}
