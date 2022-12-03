package org.sebastiansiarczynski.util;

public class Validator {

  public static void validateEmptyStringOrNull(final String textToValidate, final String errorMsg) {
    if (textToValidate == null || textToValidate.isBlank()) {
      throw new IllegalArgumentException(errorMsg);
    }
  }
}
