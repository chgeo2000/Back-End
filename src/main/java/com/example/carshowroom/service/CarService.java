package com.example.carshowroom.service;

import com.example.carshowroom.domain.Car;
import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.dto.CarDtoValidate;
import com.example.carshowroom.repository.CarRepository;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CarService{

    private final CarRepository carRepository;
    private final CarDtoValidate validator;
    private final ModelMapper mapper;

    public List<CarDto> getAllCars(){

        List<CarDto> carsDto = new ArrayList<>();
        for(Car car: carRepository.findAll()){
            CarDto carDto = mapper.map(car, CarDto.class);
            carsDto.add(carDto);
        }

        return carsDto;
    }

    public CarDto getCarById(Long id){
        boolean exists = carRepository.existsById(id);
        Car car = carRepository.findCarById(id);

        if (! exists ){ throw new IllegalStateException("The car with id: " + id + " does not exist"); }

        return mapper.map(car, CarDto.class);
    }

    public void addCar(CarDto carDto){
        validator.validate(carDto);

        carRepository.save(mapper.map(carDto, Car.class));
    }

    @Transactional
    public void updateCar(CarDto carDto, Long id){
        boolean exists = carRepository.existsById(id);

        if (! exists ){ throw new IllegalStateException("The car with id: " + carDto.getId() + " does not exist"); }

         validator.validate(carDto);
         carRepository.save(mapper.map(carDto, Car.class));
    }
@Transactional
    public void deleteCarById(Long id) {

        boolean exists = carRepository.existsById(id);
        if (! (exists)){
            throw new IllegalStateException("The car with id: " + id + " does not exist");
        }
        carRepository.deleteCarById(id);

    }


}