package com.example.carshowroom.controller;


import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    private final CarService carService;

    @GetMapping(path = "/")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path = "/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }


    @PostMapping(path = "/")
    public void addCar(@RequestBody CarDto carDto) {
        carService.addCar(carDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }


    @PutMapping(path = "/{id}")
    public void updateCar(@RequestBody CarDto carDto,
                          @PathVariable("id") Long id) {

        carService.updateCar(carDto, id);

    }


}
