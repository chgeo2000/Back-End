package com.example.carshowroom.repository;

import com.example.carshowroom.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class CarRepositoryTest {


    @Autowired
    private CarRepository carRepositoryTest;

    @Test
    void findCarById() {

        Car car = Car.builder()
                .model("ford").city("Oradea").imageUrl("img").kmTravelled(100).manufacturingYear(1999).price(2000).build();

        carRepositoryTest.save(car);

        assertEquals(car, carRepositoryTest.findCarById(car.getId()));


    }

    @Test
    void deleteCarById() {
        Car car = Car.builder()
                .model("ford").city("Oradea").imageUrl("img").kmTravelled(100).manufacturingYear(1999).price(2000).build();

        carRepositoryTest.save(car);
        assertEquals(1, carRepositoryTest.findAll().size());

        carRepositoryTest.deleteCarById(1L);

        assertEquals(0, carRepositoryTest.findAll().size());
    }
}