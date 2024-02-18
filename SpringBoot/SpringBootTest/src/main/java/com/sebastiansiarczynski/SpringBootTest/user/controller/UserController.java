package com.sebastiansiarczynski.SpringBootTest.user.controller;

import com.sebastiansiarczynski.SpringBootTest.user.model.dto.UserDto;
import com.sebastiansiarczynski.SpringBootTest.user.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
//127.0.0.1:8080/user
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(value = "/{id}")
  //GET -> 127.0.0.1:8080/user/{id}
  //POST -> 127.0.0.1:8080/user/add
  //DELETE -> 127.0.0.1:8080/user/delete/{id}
  public ResponseEntity<UserDto> get(final @PathVariable int id) {
    final UserDto userById = userService.findUserById(id);

    return new ResponseEntity<>(userById, HttpStatus.OK);
  }

  @GetMapping(value = "/range/age")
  public ResponseEntity<List<UserDto>> getUsersAgeRange(@RequestParam int minAge,
      @RequestParam int maxAge) {
    List<UserDto> usersWithinAgeRange = userService.getUsersWithinAgeRange(minAge, maxAge);

    return new ResponseEntity<>(usersWithinAgeRange, HttpStatus.OK);
  }
}
