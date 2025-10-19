package org.example.hexlet.dto.courses;

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
public class BuildCoursePage extends BasePage {
    private String name;
    private String description;
    private Map<String, List<ValidationError<Object>>> errors;
}
