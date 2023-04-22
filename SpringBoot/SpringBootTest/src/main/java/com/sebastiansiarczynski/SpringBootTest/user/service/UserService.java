package com.sebastiansiarczynski.SpringBootTest.user.service;

import com.sebastiansiarczynski.SpringBootTest.user.model.User;
import com.sebastiansiarczynski.SpringBootTest.user.model.UserDto;
import com.sebastiansiarczynski.SpringBootTest.user.repo.UserRepo;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepo userRepo;

  public UserDto findUserById(final int id) {
    final User userById = userRepo.getUserById(id);

    return new UserDto(userById.name(), userById.lastName());
  }

  public List<UserDto> getUsersWithinAgeRange(final int minAge, final int maxAge) {
    final List<User> usersWithinAgeRange = userRepo.getUsersWithinAgeRange(minAge, maxAge);

    if (usersWithinAgeRange.isEmpty()) {
      return Collections.emptyList();
    }

    return usersWithinAgeRange.stream().map(user -> new UserDto(user.name(), user.lastName()))
        .toList();
  }
}
