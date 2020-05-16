package wf.bauer.junit5.parameterresolver.beforeeach;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;
import wf.bauer.junit5.parameterresolver.ResolvedParameter;
import wf.bauer.junit5.parameterresolver.SimpleParameterResolver;

public class BeforeEachResolver extends SimpleParameterResolver implements BeforeEachCallback {

  @Override
  public void beforeEach(final ExtensionContext context) throws IllegalAccessException {
    Object testInstance = context.getRequiredTestInstance();

    Object[] availableParameters = resolveArguments(context);
    List<Field> fieldsToInject = ReflectionSupport.findFields(
        context.getRequiredTestClass(),
        field -> field.isAnnotationPresent(ResolvedParameter.class),
        HierarchyTraversalMode.BOTTOM_UP);

    for (Field field : fieldsToInject) {
      ResolvedParameter desiredParameter = field.getAnnotation(ResolvedParameter.class);
      field.set(testInstance, availableParameters[desiredParameter.value()]);
    }
  }
}
