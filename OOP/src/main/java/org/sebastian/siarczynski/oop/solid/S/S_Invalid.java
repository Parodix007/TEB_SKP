package org.sebastian.siarczynski.oop.solid.S;

import java.util.Set;

/**
 * Ta klasa prezentuje łamanie zasady S z SOLID, ponieważ metoda authUserById nie powinna być w tej
 * klasie bo to nie jest jej odpowiedzialność
 */
public class S_Invalid {
  private final Set<String> usersId;

  S_Invalid(final Set<String> usersId) {
    this.usersId = usersId;
  }

  void addUserId(final String id) {
    if (id.isBlank()) {
      throw new IllegalArgumentException("User id cannot be blank!");
    }

    usersId.add(id);
  }

  void removeUserId(final String id) {
    if (id.isBlank()) {
      throw new IllegalArgumentException("User id cannot be blank!");
    }

    usersId.remove(id);
  }

  void authUserById(final String id) {
    // tutaj byłaby implementacja, której nie powinno być w tej klasie
  }
}
