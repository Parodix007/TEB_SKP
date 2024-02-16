package org.sebastiansiarczynski.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.sebastiansiarczynski.Pet.Pet;

import java.util.Set;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull private String firstname;

  @NotNull private String lastname;

  @OneToMany(mappedBy = "owner")
  private Set<Pet> pets;

  public User() {}

  public User(final String firstname, final String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }
}
