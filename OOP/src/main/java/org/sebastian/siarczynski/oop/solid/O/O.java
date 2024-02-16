package org.sebastian.siarczynski.oop.solid.O;

import java.util.List;

/**
 * Open/Closed Principle (OCP) - Zasada Otwarte/Zamknięte Klasa powinna być otwarta na rozszerzenie,
 * ale zamknięta na modyfikację. Oznacza to, że powinno być możliwe dodanie nowej funkcjonalności
 * bez zmieniania istniejącego kodu.
 */
class O {

  boolean isNumberEven(final int number) {
    return number % 2 == 0;
  }
//  boolean isNumberEven1(final List<Integer> numbers) {
//    for (Integer number : numbers) {
////      ...
//    }
//    return number % 2 == 0;
//  }

  boolean areNumbersInArrayEven(final List<Integer> numbers) {
    return numbers.stream().allMatch(this::isNumberEven);
  }
}
