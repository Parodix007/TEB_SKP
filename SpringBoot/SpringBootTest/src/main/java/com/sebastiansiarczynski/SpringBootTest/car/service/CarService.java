package com.sebastiansiarczynski.SpringBootTest.car.service;

import com.sebastiansiarczynski.SpringBootTest.car.model.Car;
import com.sebastiansiarczynski.SpringBootTest.car.model.CarDto;
import com.sebastiansiarczynski.SpringBootTest.car.repo.CarRepo;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

  private final CarRepo carRepo;

  public CarDto findCarById(final int id) {
    final Car carById = carRepo.findCarById(id);

    return new CarDto(carById.brand(), carById.model(), carById.year());
  }

  public List<CarDto> findCarsByYearAndBrand(final int id, final String brand) {
    final List<Car> carsByYearAndBrand = carRepo.findCarsByYearAndBrand(id, brand);

    if (carsByYearAndBrand.isEmpty()) {
      return Collections.emptyList();
    }

    return carsByYearAndBrand.stream().map(car -> new CarDto(car.brand(), car.model(),
        car.year())).toList();
  }
}
