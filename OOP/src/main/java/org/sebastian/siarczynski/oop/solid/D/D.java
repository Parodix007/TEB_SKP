package org.sebastian.siarczynski.oop.solid.D;

/**
 * Dependency Inversion Principle (DIP) - Zasada Odwrócenia Zależności Moduły wysokopoziomowe nie
 * powinny zależeć od modułów niskopoziomowych. Oba typy modułów powinny zależeć od abstrakcji.
 *
 * <p>Przykład ten prezentuje, że nasza abstrakcja, która tworzymy powinna być jak najbardziej
 * modularna abyśmy nie byli ogarniczeni przez klasy, które są niżej w procesie tylko zawsze mielie
 * możliwość składania naszej aplikacji według interfejsów logiki i tworzyli zależności według tego
 * co akurat potrzebujemy
 */
class D {
  private final SomeInterface someInterface;

  D(final SomeInterface someClass) {
    this.someInterface = someClass;
  }

  void startSomeProcess() {
    someInterface.someAction();
  }
}
