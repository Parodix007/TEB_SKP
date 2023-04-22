package com.sebastiansiarczynski.SpringBootTest.user.repo;

import com.sebastiansiarczynski.SpringBootTest.user.model.Habitation;
import com.sebastiansiarczynski.SpringBootTest.user.model.User;
import jakarta.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

  private final List<User> users = new LinkedList<>();

  @PostConstruct
  private void init() {
    for (int i = 0; i < 10; i++) {
      final Habitation habitation = new Habitation("Poland", "Fabryczna", "Wroclaw", 13);
      final User user = new User(i, "Jan", "Kowalski", 30, List.of("czytanie", "programowanie"),
          12345, habitation);

      users.add(user);
    }
  }

  public User getUserById(final int id) {
    return users.stream().filter(user -> user.id() == id).findFirst()
        .orElseThrow(() -> new IllegalArgumentException("There is no user with this id " + id));
  }

  public List<User> getUsersWithinAgeRange(final int minAge, final int maxAge) {
    return users.stream().filter(user -> user.age() >= minAge && user.age() <= maxAge).toList();
  }
}
