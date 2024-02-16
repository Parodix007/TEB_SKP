package org.sebastiansiarczynski;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sebastiansiarczynski.Pet.Breed;
import org.sebastiansiarczynski.Pet.Pet;
import org.sebastiansiarczynski.Pet.PetFacade;
import org.sebastiansiarczynski.User.User;
import org.sebastiansiarczynski.User.UserFacade;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.JdbcSettings.*;

public class Main {
  public static void main(String[] args) {
    final String dbURI = args[0];
    final String dbUser = args[1];
    final String dbUserPassword = args[2];

    try (final SessionFactory sessionFactory =
        new Configuration()
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(Pet.class)
            .setProperty(URL, dbURI)
            .setProperty(USER, dbUser)
            .setProperty(PASS, dbUserPassword)
            .setProperty(SHOW_SQL, TRUE.toString())
            .setProperty(FORMAT_SQL, TRUE.toString())
            .setProperty(HIGHLIGHT_SQL, TRUE.toString())
            .buildSessionFactory()) {
      sessionFactory.getSchemaManager().dropMappedObjects(true);
      sessionFactory.getSchemaManager().exportMappedObjects(true);
      final EntityManager entityManager = sessionFactory.createEntityManager();

      final PetFacade petFacade = new PetFacade();
      final UserFacade userFacade = new UserFacade();

      final User user = userFacade.createUser("John", "Doe");
      final Pet cat = petFacade.createPet("some-name", Breed.CAT, user);
      final Pet dog = petFacade.createPet("some-name", Breed.DOG, user);

      final UserAndPetAggregate userAndPetAggregate = new UserAndPetAggregate(entityManager);

      userAndPetAggregate.addUser(user);
      userAndPetAggregate.addPetWithExistedUser(cat);
      userAndPetAggregate.addPetWithExistedUser(dog);
    }
  }
}
