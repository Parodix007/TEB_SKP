package org.sebastian.siarczynski.oop.solid.L;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Liskov Substitution Principle (LSP) - Zasada Podstawienia Liskov Zasada ta mówi, że obiekty w
 * programie powinny być zastępowalne przez instancje ich podtypów bez wpływu na poprawność
 * programu.
 */
class L {
  private final Collection<String> names;

  L(final List<String> names) {
    this.names = names;
  }

  L(final Set<String> names) {
    this.names = names;
  }

  Collection<String> sortNames() {
    return names.stream().sorted().toList();
  }
}
