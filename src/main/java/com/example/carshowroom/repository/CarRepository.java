package com.example.carshowroom.repository;

import com.example.carshowroom.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarById(Long id);

    void deleteCarById(Long id);

}