package org.sebastian.siarczynski.oop.principales.abstraction;

public interface SomeProcessInterface {
  default void startProcess() {
    System.out.println("If you want to start a process you should implement this interface!");
  }
}
