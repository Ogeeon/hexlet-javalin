package org.example.hexlet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class Car {
    private Long id;

    private String make;
    private String model;

    public Car(String carMake, String carModel) {
        this.make = carMake;
        this.model = carModel;
    }
}
