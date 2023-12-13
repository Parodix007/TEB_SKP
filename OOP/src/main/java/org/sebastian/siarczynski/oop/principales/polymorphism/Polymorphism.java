package org.sebastian.siarczynski.oop.principales.polymorphism;

import org.sebastian.siarczynski.oop.principales.inheritance.Parent;

/**
 * Polimorfizm (Polymorphism) Wyjaśnienie: Polimorfizm pozwala na traktowanie obiektów różnych klas,
 * które są ze sobą powiązane przez dziedziczenie, w podobny sposób. Najczęściej manifestuje się to
 * przez nadpisywanie metod (overriding) i przeciążanie metod (overloading).
 */
class Polymorphism extends Parent {
  @Override
  protected void makeSound() {
    System.out.println("Testing Polymorphism - making sound from " + this);
  }

  void x() {
    System.out.println("Default method");
  }

  void x(final String text) {
    System.out.println("Override method x with parameter: " + text);
  }
}
