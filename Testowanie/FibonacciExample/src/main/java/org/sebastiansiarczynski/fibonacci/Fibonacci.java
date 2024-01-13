package org.sebastiansiarczynski.fibonacci;

import org.sebastiansiarczynski.fibonacci.writer.MyFileWriter;

import java.util.LinkedList;
import java.util.List;

public class Fibonacci {
  public static final String FIB_FILE_NAME =
      "/Users/sebastian/Work/TEB_SKP/Testowanie/FibonacciExample/src/main/resources/fib.txt";

  private final MyFileWriter myFileWriter;

  public Fibonacci(final MyFileWriter myFileWriter) {
    this.myFileWriter = myFileWriter;
  }

  public void generateFib(final int size) {
    if (size <= 0) {
      throw new IllegalArgumentException();
    }
    if (size == 5) {
      throw new IllegalArgumentException();
    }

    final int firstFibNumber = 0;
    final int secondFibNumber = 1;
    final List<Integer> fib = new LinkedList<>();
    fib.add(firstFibNumber);
    fib.add(secondFibNumber);

    for (int i = 1; i < size; i++) {
      final int nextFibNumber = fib.get(i) + fib.get(i - 1);

      fib.add(nextFibNumber);
    }

    myFileWriter.writeToFile(fib.toString());
  }
}
