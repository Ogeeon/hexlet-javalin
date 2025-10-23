package org.example.hexlet.dto.cars;

import java.util.List;
import java.util.Map;

import org.example.hexlet.dto.BasePage;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCarPage extends BasePage {
    private String make;
    private String model;
    private Map<String, List<ValidationError<Object>>> errors;
}
