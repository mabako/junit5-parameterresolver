package wf.bauer.junit5.parameterresolver.beforetestexecution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import wf.bauer.junit5.parameterresolver.ResolvedParameter;

@ExtendWith(BeforeTestExecutionResolver.class)
class BeforeTestExecutionTest {

  @ResolvedParameter(0)
  int fieldValue;

  @BeforeEach
  void ensureFieldIsBlank() {
    assertEquals(0, fieldValue);
  }

  @ParameterizedTest
  @ValueSource(ints = {7, 8})
  void parametersWork(int parameterValue) {
    assertEquals(parameterValue, fieldValue);
    assertNotEquals(0, parameterValue);
    assertNotEquals(0, fieldValue);
  }
}
