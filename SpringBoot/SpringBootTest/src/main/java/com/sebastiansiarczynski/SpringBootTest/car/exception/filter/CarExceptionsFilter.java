package com.sebastiansiarczynski.SpringBootTest.car.exception.filter;

import com.sebastiansiarczynski.SpringBootTest.car.exception.CarRepoException;
import com.sebastiansiarczynski.SpringBootTest.car.exception.CarServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CarExceptionsFilter extends ResponseEntityExceptionHandler {

  @ExceptionHandler({CarRepoException.class})
  @ResponseBody
  public ResponseEntity<String> handleAuthenticationException(Exception ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler({CarServiceException.class})
  @ResponseBody
  public ResponseEntity<String> handleUserServiceException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }
}
