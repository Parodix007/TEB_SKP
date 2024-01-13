package org.sebastiansiarczynski;

import org.sebastiansiarczynski.fibonacci.Fibonacci;
import org.sebastiansiarczynski.fibonacci.writer.MyFileWriter;

public class Main {
  public static void main(String[] args) {
    final MyFileWriter myFileWriter = new MyFileWriter(Fibonacci.FIB_FILE_NAME);
    final Fibonacci fibonacci = new Fibonacci(myFileWriter);

    fibonacci.generateFib(10);
  }
}
