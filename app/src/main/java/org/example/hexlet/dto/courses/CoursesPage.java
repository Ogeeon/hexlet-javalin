package org.example.hexlet.dto.courses;

import java.util.List;

import org.example.hexlet.model.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoursesPage {
    private final List<Course> courses;
    private final String header;
}