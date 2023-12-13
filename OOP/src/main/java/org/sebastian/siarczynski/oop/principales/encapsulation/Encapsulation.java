package org.sebastian.siarczynski.oop.principales.encapsulation;

/**
 * Hermetyzacja (Encapsulation) Wyjaśnienie: Hermetyzacja polega na ukrywaniu szczegółów
 * wewnętrznych klasy i udostępnianiu tylko tych elementów, które są niezbędne dla użytkowników tej
 * klasy. Osiąga się to poprzez użycie modyfikatorów dostępu i metod dostępowych (getters/setters).
 */
class Encapsulation {
  private final String x;
  private final int y;

  Encapsulation(final String x, final int y) {
    this.x = x;
    this.y = y;
  }

  String getX() {
    return x;
  }

  int getY() {
    return y;
  }
}
