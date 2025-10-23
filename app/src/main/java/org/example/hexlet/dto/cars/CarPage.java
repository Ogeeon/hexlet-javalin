package org.example.hexlet.dto.cars;

import org.example.hexlet.model.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarPage {
    private final Car car;
}
