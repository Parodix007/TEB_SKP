package org.sebastian.siarczynski.oop.solid.I;

/**
 * Interface Segregation Principle (ISP) - Zasada Segregacji Interfejsu Klient nie powinien być
 * zmuszany do zależności od interfejsów, których nie używa.
 *
 * <p>Przykład ten pokazuje, że poprzez tworzeniu kilku mniejszych interfejsów implementujemy tylko
 * metody, które potrzebujemy a nie te, które musimy
 */
class I implements InterfaceY, InterfaceX {

  @Override
  public void y() {
    // nasza implementacja
  }

  @Override
  public void x() {
    // nasza implementacja
  }
}
