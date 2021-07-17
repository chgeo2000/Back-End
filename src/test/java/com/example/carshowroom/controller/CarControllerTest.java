package com.example.carshowroom.controller;

import com.example.carshowroom.dto.CarDto;
import com.example.carshowroom.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(CarController.class)
class CarControllerTest {

    @MockBean
    private CarService carService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCars() throws Exception {

        CarDto request = CarDto.builder()
                .id(19L)
                .model("audi a4")
                .price(1000)
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(2008)
                .city("Oradea")
                .transmissionType("Manual")
                .build();

        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(request);


        Mockito.when(
                carService.getAllCars()).thenReturn(carDtoList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                    "/cars/"
        ).accept(
                MediaType.APPLICATION_JSON
        );

         mockMvc.perform(requestBuilder)
                .andExpect( status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath(("$.length()")).value(carDtoList.size()));

    }

    @Test
    void getCarById() throws Exception {
        CarDto request = CarDto.builder()
                .id(19L)
                .model("audi a4")
                .price(1000)
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(2008)
                .city("Oradea")
                .transmissionType("Manual")
                .build();

        Mockito.when(
                carService.getCarById(19L)).thenReturn(request);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/cars/19"
        ).accept(
                MediaType.APPLICATION_JSON
        );

      mockMvc.perform(requestBuilder)
              .andExpect( status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("audi a4"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000))
              .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("img"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.kmTravelled").value(100))
              .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturingYear").value(2008))
              .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Oradea"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.transmissionType").value("Manual"));



    }



    @Test
    void addCar() throws Exception {
        CarDto request = CarDto.builder()
                .id(19L)
                .model("audi a4")
                .price(1000)
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(2008)
                .city("Oradea")
                .transmissionType("Manual")
                .build();




      RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
              "/cars/"
       )
              .content(mapper.writeValueAsString(request))
              .contentType(
               MediaType.APPLICATION_JSON
       );

        mockMvc.perform(requestBuilder)
                .andExpect( status().isOk());

        verify(carService).addCar(request);

    }

    @Test
    void deleteCar() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/cars/19"
        )
                .contentType(
                        MediaType.APPLICATION_JSON
                );

        mockMvc.perform(requestBuilder)
                .andExpect( status().isOk());

        verify(carService).deleteCarById(19L);

    }

    @Test
    void updateCar() throws Exception {

        CarDto request = CarDto.builder()
                .id(19L)
                .model("audi a4")
                .price(1000)
                .imageUrl("img")
                .kmTravelled(100)
                .manufacturingYear(2008)
                .city("Oradea")
                .transmissionType("Manual")
                .build();


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/cars/19"
        )
                .content(mapper.writeValueAsString(request))
                .contentType(
                        MediaType.APPLICATION_JSON
                );

        mockMvc.perform(requestBuilder)
                .andExpect( status().isOk());

        verify(carService).updateCar(request, request.getId());

    }
}