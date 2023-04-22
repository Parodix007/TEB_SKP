package com.sebastiansiarczynski.SpringBootTest.car.controller;

import com.sebastiansiarczynski.SpringBootTest.car.model.CarDto;
import com.sebastiansiarczynski.SpringBootTest.car.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<CarDto> carGet(final @PathVariable int id) {
    try {
      final CarDto carById = carService.findCarById(id);

      return new ResponseEntity<>(carById, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      log.error("Error while getting car by ID!", e);

      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{year}/{brand}")
  public ResponseEntity<List<CarDto>> getCarsBy(final @PathVariable int year,
      final @PathVariable String brand) {
    final List<CarDto> carsByYearAndBrand = carService.findCarsByYearAndBrand(year, brand);

    return new ResponseEntity<>(carsByYearAndBrand, HttpStatus.OK);
  }
}

