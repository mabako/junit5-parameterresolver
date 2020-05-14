package wf.bauer.junit5.parameterresolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@ExtendWith(SimpleParameterResolver.class)
class SomeSimpleTest {

  @BeforeEach
  void before(@ResolvedParameter(1) int y) {
    assertEquals(7, y);
  }

  @AfterEach
  void after(@ResolvedParameter(0) String x) {
    assertEquals(1, x.length());
  }

  @ParameterizedTest
  @MethodSource("data")
  void parametersWork(String x, int y) {
    assertEquals(1, x.length());
    assertEquals(7, y);
  }

  static List<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"a", 7},
        {"c", 7}
    });
  }
}
