package com.sebastiansiarczynski.SpringBootTest.user.controller;

import com.sebastiansiarczynski.SpringBootTest.user.model.UserDto;
import com.sebastiansiarczynski.SpringBootTest.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDto> get(final @PathVariable int id) {
    try {
      final UserDto userById = userService.findUserById(id);

      return new ResponseEntity<>(userById, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      log.error("Error while getting user!", e);

      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/range/age")
  public ResponseEntity<List<UserDto>> getUsersAgeRange(@RequestParam int minAge,
      @RequestParam int maxAge) {
    List<UserDto> usersWithinAgeRange = userService.getUsersWithinAgeRange(minAge, maxAge);

    return new ResponseEntity<>(usersWithinAgeRange, HttpStatus.OK);
  }
}
