package com.sebastiansiarczynski.SpringBootTest.car.exception;

public class CarServiceException extends RuntimeException {

  public CarServiceException(final String mess, final Throwable error) {
    super(mess, error);
  }
}
