package org.sebastiansiarczynski.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sebastiansiarczynski.fibonacci.writer.MyFileWriter;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

  @Mock private MyFileWriter myFileWriter;
  private Fibonacci objectUnderTest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    objectUnderTest = new Fibonacci(myFileWriter);
  }

  @Test
  @DisplayName("Should properly generate fibonacci sequence")
  void generateFib() {
    // given
    final int size = 10;
    // when
    Mockito.doNothing().when(myFileWriter).writeToFile(Mockito.anyString());
    // then
    objectUnderTest.generateFib(size);
  }

  @Test
  @DisplayName("Should throws IllegalArgumentException when size is 0")
  void generateFibThrows() {
    // given
    final int size = 0;
    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> objectUnderTest.generateFib(size));
  }

  @Test
  @DisplayName("Should throws IllegalArgumentException when size is equal 5")
  void generateFibThrowsWhenSize5() {
    // given
    final int size = 5;
    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> objectUnderTest.generateFib(size));
  }
}
