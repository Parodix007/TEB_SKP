package org.sebastian.siarczynski.oop.solid.S;

import java.util.Set;

/**
 * Single Responsibility Principle (SRP) - Zasada Jednej Odpowiedzialności Zasada ta mówi, że klasa
 * powinna mieć tylko jeden powód do zmiany, co oznacza, że powinna być odpowiedzialna tylko za
 * jedną funkcjonalność.
 *
 * Klasa w podanym przykładzie odpowiada za to aby przechowywać jakieś id, to jest jej odpowiedzialność, nie powinna ona być odpowiedzialna za żadne inne czynności.
 */
class S {
  private final Set<String> usersId;

  S(final Set<String> usersId) {
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
}
