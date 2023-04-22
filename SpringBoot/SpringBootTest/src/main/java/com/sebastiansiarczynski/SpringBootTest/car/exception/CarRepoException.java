package com.sebastiansiarczynski.SpringBootTest.car.exception;

public class CarRepoException extends RuntimeException {

  public CarRepoException(final String mess) {
    super(mess);
  }

}
