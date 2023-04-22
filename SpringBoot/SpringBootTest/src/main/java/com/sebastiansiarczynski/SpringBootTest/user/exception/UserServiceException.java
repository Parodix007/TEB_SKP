package com.sebastiansiarczynski.SpringBootTest.user.exception;

public class UserServiceException extends RuntimeException {

  public UserServiceException(final String mess, final Throwable error) {
    super(mess, error);
  }
}
