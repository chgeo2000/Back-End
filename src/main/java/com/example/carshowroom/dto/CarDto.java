package com.example.carshowroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {

    private Long id;

    private String model;
    private Integer price;
    private String imageUrl;
    private Integer kmTravelled;
    private Integer manufacturingYear;
    private String city;
    private String transmissionType;

}