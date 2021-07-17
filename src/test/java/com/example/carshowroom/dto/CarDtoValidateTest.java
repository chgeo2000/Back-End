package com.example.carshowroom.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarDtoValidateTest {


    private CarDtoValidate validator;

    @BeforeEach
    public void setUp(){
        validator = new CarDtoValidate();
    }

    @Test
    public void validate_whenFieldIsEmpty_expectException() {

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

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(carDto));

        String expectedMessage = " Fields can't be left blank!";


        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    public void validate_whenPriceAndKilometersAreNegative_expectException(){

        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(-1)
                .manufacturingYear(1999)
                .price(-2)
                .transmissionType("Manual")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(carDto));

        String expectedMessage = "Nor price, neither kilometers travelled  can not be less than 0.";


        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    public void validate_whenTransmissionTypeIsWrong_expectException(){

        CarDto carDto = CarDto.builder()
                .id(20L)
                .model("ford")
                .city("Oradea")
                .imageUrl("img")
                .kmTravelled(1)
                .manufacturingYear(1999)
                .price(2)
                .transmissionType("M")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(carDto));

        String expectedMessage = "Transmision can only be Manual, Automatic or CVT";


        assertEquals(expectedMessage, exception.getMessage());
    }
}