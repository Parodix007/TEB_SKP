package org.sebastian.siarczynski.oop.solid.I;

/**
 * Ten przykład obrazuje, że w sytuacji gdy tworzymy jeden obszerny interfejs zamiast kilku
 * mniejszych jesteśmy narażeni na to, że będziemy musieli implementować metody, których nie chcemy
 */
public class I_Invalid implements InvalidInterface {
  @Override
  public void x() {
    // nasza implementacja nawet jezeli jej nie potrzebujemy
  }

  @Override
  public void y() {
    // nasza implementacja
  }

  @Override
  public void z() {
    // nasza implementacja
  }
}
