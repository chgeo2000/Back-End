package com.example.carshowroom.service;

import com.example.carshowroom.domain.Car;
import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.dto.CarDtoValidate;
import com.example.carshowroom.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarDtoValidate validator;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void getAllCars() {

        Car firstCar = Car.builder()
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999).price(2000)
                .build();

        Car secondCar = Car.builder().
                model("bmw")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .build();

        List<Car> mockRepositoryResult = new ArrayList<>();
        mockRepositoryResult.add(firstCar);
        mockRepositoryResult.add(secondCar);

        when(carRepository.findAll()).thenReturn(mockRepositoryResult);

        List<CarDto> result = carService.getAllCars();

        assertEquals(4, result.size());
    }


    @Test
    public void getCarById() {
        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();

        Car car = Car.builder()
                .id(20L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();


        when(carRepository.existsById(20L)).thenReturn(true);
        when(carRepository.findCarById(20L)).thenReturn(car);
        when(modelMapper.map(car, CarDto.class)).thenReturn(carDto);

        assertEquals(carDto, carService.getCarById(carDto.getId()));
    }

    @Test
    public void addCar_whenCarIsNotValid_expectException() {
        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();


        IllegalArgumentException blankField = new IllegalArgumentException(" Fields can't be left blank!");

        doThrow(blankField).when(validator).validate(carDto);



        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> carService.addCar(carDto));

        String expectedMessage = " Fields can't be left blank!";


        assertEquals(expectedMessage, exception.getMessage());


    }

    @Test
    public void addCar_whenCarIsValid_expectToSaveCar(){
        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();

        Car car = Car.builder()
                .id(20L)
                .model("")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();

        when(modelMapper.map(carDto, Car.class)).thenReturn(car);
        carService.addCar(carDto);

        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void updateCar_whenCarIsNotValid_expectException() {

        CarDto carDto = CarDto.builder()
                .id(19L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("M")
                .build();

        when(carRepository.existsById(19L)).thenReturn(true);

        IllegalArgumentException wrongTransmissionType = new IllegalArgumentException("Transmision can only be Manual, Automatic or CVT");

        doThrow(wrongTransmissionType).when(validator).validate(carDto);



        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> carService.updateCar(carDto, carDto.getId()));

        String expectedMessage = "Transmision can only be Manual, Automatic or CVT";


        assertEquals(expectedMessage, exception.getMessage());

    }


    @Test
    public void updateCar_whenCarIsValid_expectToSave(){
        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();

        Car car = Car.builder()
                .id(20L)
                .model("")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();

        when(modelMapper.map(carDto, Car.class)).thenReturn(car);
        when(carRepository.existsById(carDto.getId())).thenReturn(true);

        carService.updateCar(carDto, car.getId());

        verify(carRepository,times(1)).save(car);



    }

    @Test
    public void deleteCarById() {

        Car car = Car.builder()
                .id(19L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(1999)
                .price(2000)
                .transmissionType("Manual")
                .build();


        when(carRepository.existsById(car.getId())).thenReturn(true);

        carService.deleteCarById(car.getId());

        verify(carRepository).deleteCarById(car.getId());


    }
}