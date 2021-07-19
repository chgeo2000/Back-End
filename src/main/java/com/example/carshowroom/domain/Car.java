package com.example.carshowroom.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "km_travelled")
    private Integer kmTravelled;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "city")
    private String city;

    @Column(name = "transmission_type")
    private String transmissionType;

}