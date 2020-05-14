package wf.bauer.junit5.parameterresolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.commons.util.ReflectionUtils;

public class SimpleParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    Optional<ResolvedParameter> annotation = parameterContext.findAnnotation(ResolvedParameter.class);
    if (annotation.isEmpty())
      return false;

    return annotation.get().value() >= 0 && annotation.get().value() < resolveArguments(extensionContext).length;
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  @Override
  public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
    Optional<ResolvedParameter> annotation = parameterContext.findAnnotation(ResolvedParameter.class);
    return resolveArguments(extensionContext)[annotation.get().value()];
  }

  private Object[] resolveArguments(final ExtensionContext context) {
    try {
      Method method = ReflectionUtils.findMethod(context.getClass(),"getTestDescriptor")
          .orElseThrow(() -> new ParameterResolutionException("Cannot resolve parameter, internal getTestDescriptor not available"));
      Object rawDescriptor = ReflectionUtils.invokeMethod(method, context);
      TestMethodTestDescriptor descriptor = (TestMethodTestDescriptor) rawDescriptor;

      Field templateField = descriptor.getClass().getDeclaredField("invocationContext");
      TestTemplateInvocationContext template = (TestTemplateInvocationContext) ReflectionUtils.makeAccessible(templateField).get(descriptor);

      Field argumentsField = template.getClass().getDeclaredField("arguments");
      return (Object[]) ReflectionUtils.makeAccessible(argumentsField).get(template);

    } catch(NoSuchFieldException | IllegalAccessException e) {
      throw new ParameterResolutionException("Unable to inject parameters", e);
    }
  }
}
