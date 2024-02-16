package org.sebastiansiarczynski.User;

public class UserFacade {
  public User createUser(final String firstname, final String lastname) {
    if (firstname.isBlank() || lastname.isBlank()) {
      throw new IllegalArgumentException("User's firstname or lastname cannot be empty!");
    }

    return new User(firstname, lastname);
  }
}
