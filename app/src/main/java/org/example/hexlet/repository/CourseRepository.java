package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository {
    // Тип зависит от того, с какой сущностью идет работа в упражнении
    private static final List<Course> ENTITIES = new ArrayList<>();

    public static void save(Course course) {
        // Формируется идентификатор
        course.setId((long) ENTITIES.size() + 1);
        ENTITIES.add(course);
    }

    public static List<Course> search(String term) {
        var courses = ENTITIES.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
        return courses;
    }

    public static Optional<Course> find(Long id) {
        var course = ENTITIES.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
        return course;
    }

    public static List<Course> getEntities() {
        return ENTITIES;
    }

    public static void delete(Long id) {
        var target = find(id);
        if (target.isPresent()) {
            ENTITIES.remove(target.get());
        }
    }
}
