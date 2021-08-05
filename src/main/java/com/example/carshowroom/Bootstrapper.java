package com.example.carshowroom;

import com.example.carshowroom.domain.Car;
import com.example.carshowroom.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final CarRepository carRepository;

    @Value("${app.bootstrap}")
    private boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        carRepository.deleteAll();

        if (bootstrap) {
            Car sandero = new Car(1L, "Sandero",
                    2000,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.euroncap.com%2Fen%2Fresults%2Fdacia%2Fsandero-stepway%2F42506&psig=AOvVaw30CRiQ3X9fOxgoMOlk49-z&ust=1628248544177000&source=images&cd=vfe&ved=0CAcQjRxqFwoTCMiruNLgmfICFQAAAAAdAAAAABAE", 1000, 2021, "Oradea", "Manual");
            Car volkswagen = new Car(2L, "VW",
                    2000,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.euroncap.com%2Fen%2Fresults%2Fdacia%2Fsandero-stepway%2F42506&psig=AOvVaw30CRiQ3X9fOxgoMOlk49-z&ust=1628248544177000&source=images&cd=vfe&ved=0CAcQjRxqFwoTCMiruNLgmfICFQAAAAAdAAAAABAE", 1000, 2021, "Oradea", "Manual");
            Car audi = new Car(3L, "Audi",
                    2000,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.euroncap.com%2Fen%2Fresults%2Fdacia%2Fsandero-stepway%2F42506&psig=AOvVaw30CRiQ3X9fOxgoMOlk49-z&ust=1628248544177000&source=images&cd=vfe&ved=0CAcQjRxqFwoTCMiruNLgmfICFQAAAAAdAAAAABAE", 1000, 2021, "Oradea", "Manual");

            carRepository.save(sandero);
            carRepository.save(volkswagen);
            carRepository.save(audi);
        }

    }
}
