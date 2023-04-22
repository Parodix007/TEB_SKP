package com.sebastiansiarczynski.SpringBootTest.user.exception;

public class UserRepoException extends RuntimeException {

  public UserRepoException(final String mess) {
    super(mess);
  }

}
