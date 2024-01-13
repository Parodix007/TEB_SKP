package org.sebastiansiarczynski.fibonacci.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileWriter {
  private final Path filePath;

  public MyFileWriter(final String filePathString) {
    filePath = Paths.get(filePathString);
  }

  public void writeToFile(final String text) {
    try (final BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(filePath.toFile()))) {
      bufferedWriter.write(text);
      bufferedWriter.newLine();
    } catch (final IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
