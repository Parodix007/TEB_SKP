package org.sebastiansiarczynski.Pet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.sebastiansiarczynski.User.User;

@Entity
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Breed breed;

  @ManyToOne private User owner;

  public Pet() {}

  public Pet(final String name, final Breed breed, final User user) {
    this.name = name;
    this.breed = breed;
    this.owner = user;
  }

  public User getOwner() {
    return owner;
  }
}
