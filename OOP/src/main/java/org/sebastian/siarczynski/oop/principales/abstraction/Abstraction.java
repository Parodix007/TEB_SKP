package org.sebastian.siarczynski.oop.principales.abstraction;

/**
 * Abstrakcja (Abstraction) Wyjaśnienie: Abstrakcja polega na skupieniu się na ogólnych cechach
 * obiektu, ignorując mniej istotne szczegóły. W Javie abstrakcję osiąga się często za pomocą klas
 * abstrakcyjnych i interfejsów.
 */
class Abstraction extends AbstractParent implements SomeProcessInterface {
  @Override
  public void youShouldOverrideThis() {
    System.out.println("I have override my parent abstract method!");
  }

  @Override
  public void startProcess() {
    System.out.println("I have also implemented an interface!");
  }
}
