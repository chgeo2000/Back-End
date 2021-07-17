package com.example.carshowroom.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Component
public class CarDtoValidate {

    public void validate(CarDto carDto) {

        StringBuilder errors = new StringBuilder();

        List<String> possibleTransmissions = new ArrayList<>();
        possibleTransmissions.add("Manual");
        possibleTransmissions.add("Automatic");
        possibleTransmissions.add("CVT");

        if (carDto.getModel().equals("") || carDto.getImageUrl().equals("") || carDto.getCity().equals("") || carDto.getTransmissionType().equals("")) {
            errors.append(" Fields can't be left blank!");
        }

        if (carDto.getPrice() < 0 || carDto.getKmTravelled() < 0) {
            errors.append("Nor price, neither kilometers travelled  can not be less than 0.");
        }

        if (!possibleTransmissions.contains(carDto.getTransmissionType())) {
            errors.append("Transmision can only be Manual, Automatic or CVT");
        }

        if (errors.length() != 0) {
            throw new IllegalArgumentException(errors.toString());
        }

    }
}


