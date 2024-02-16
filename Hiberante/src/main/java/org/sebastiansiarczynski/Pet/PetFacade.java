package org.sebastiansiarczynski.Pet;

import org.sebastiansiarczynski.User.User;

public class PetFacade {

  public Pet createPet(final String name, final Breed breed, final User user) {
    if (name.isBlank()) {
      throw new IllegalArgumentException("Pet's name cannot be empty!");
    }

    return new Pet(name, breed, user);
  }
}
