package com.sebastiansiarczynski.SpringBootTest.user.exception.filter;

import com.sebastiansiarczynski.SpringBootTest.user.exception.UserRepoException;
import com.sebastiansiarczynski.SpringBootTest.user.exception.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionsFilter extends ResponseEntityExceptionHandler {

  @ExceptionHandler({UserRepoException.class})
  @ResponseBody
  public ResponseEntity<String> handleAuthenticationException(Exception ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler({UserServiceException.class})
  @ResponseBody
  public ResponseEntity<String> handleUserServiceException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }
}
