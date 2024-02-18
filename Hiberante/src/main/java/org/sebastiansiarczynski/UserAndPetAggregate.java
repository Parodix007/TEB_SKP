package org.sebastiansiarczynski;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sebastiansiarczynski.Pet.Pet;
import org.sebastiansiarczynski.User.User;

public class UserAndPetAggregate {
  private final EntityManager entityManager;

  public UserAndPetAggregate(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void addPetWithExistedUser(final Pet pet) {
    if (!entityManager.contains(pet.getOwner())) {
      throw new IllegalStateException(
          String.format("User: %s does not exists! Cannot create Pet: %s", pet.getOwner(), pet));
    }

    this.saveEntityWithTransaction(pet);
  }

  public void addUser(final User user) {
    if (entityManager.contains(user)) {
      throw new IllegalStateException(String.format("User: %s already exists!", user));
    }

    this.saveEntityWithTransaction(user);
  }

  private void saveEntityWithTransaction(final Object entity) {
    final EntityTransaction entityTransaction = entityManager.getTransaction();

    try {
      entityTransaction.begin();
      entityManager.persist(entity);
      entityTransaction.commit();
    } catch (final Exception e) {
      if (entityTransaction.isActive()) {
        entityTransaction.rollback();
      }
      throw e;
    }
  }
}
