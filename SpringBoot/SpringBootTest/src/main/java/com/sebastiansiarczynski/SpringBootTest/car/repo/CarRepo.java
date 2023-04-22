package com.sebastiansiarczynski.SpringBootTest.car.repo;

import com.sebastiansiarczynski.SpringBootTest.car.exception.CarRepoException;
import com.sebastiansiarczynski.SpringBootTest.car.model.Car;
import jakarta.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepo {

  private final List<Car> cars = new LinkedList<>();

  @PostConstruct
  private void init() {
    for (int i = 0; i < 10; i++) {
      final Car car = new Car(i, "Mercedes-Benz", "S500", 2022);

      cars.add(car);
    }
  }

  public Car findCarById(final int id) {
    return cars.stream().filter(car -> car.id() == id).findFirst()
        .orElseThrow(() -> new CarRepoException("There is no car with id " + id));
  }

  public List<Car> findCarsByYearAndBrand(final int year, final String brand) {
    return cars.stream().filter(car -> car.year() == year && car.brand().equals(brand)).toList();
  }
}
