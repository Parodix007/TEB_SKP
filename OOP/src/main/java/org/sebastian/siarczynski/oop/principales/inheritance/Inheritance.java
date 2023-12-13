package org.sebastian.siarczynski.oop.principales.inheritance;

/**
 * Dziedziczenie (Inheritance) Wyjaśnienie: Dziedziczenie pozwala na tworzenie nowych klas na bazie
 * już istniejących, dziedzicząc ich cechy i zachowania. Umożliwia to ponowne wykorzystanie kodu i
 * organizację klas w hierarchiczne struktury.
 */
class Inheritance extends Parent {
  @Override
  protected void makeSound() {
    System.out.println("Making new sound from " + this);
  }
}
