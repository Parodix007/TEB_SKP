package org.sebastian.siarczynski.oop.solid.D;

/**
 * Przykład ten pokazuje zamykanie się z abstrakcja na konkretna implementację i tworzenie
 * zależności idących od dołu (tego co zhardkodowaliśmy) a nie od góry (od tego co akurat
 * potrzebujemy w logice)
 */
public class D_Invalid {
  private final SomeClassInvalid someClassInvalid;

  D_Invalid() {
    someClassInvalid = new SomeClassInvalid();
  }

  void startSomeProcess() {
    someClassInvalid.someAction();
  }
}
