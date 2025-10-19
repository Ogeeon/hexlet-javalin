package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository {
    private static final List<User> ENTITIES = new ArrayList<>();

    public static void save(User user) {
        // Формируется идентификатор
        user.setId((long) ENTITIES.size() + 1);
        ENTITIES.add(user);
    }

    public static List<User> search(String term) {
        var users = ENTITIES.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
        return users;
    }

    public static Optional<User> find(Long id) {
        var user = ENTITIES.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
        return user;
    }

    public static List<User> getEntities() {
        return ENTITIES;
    }

    public static void delete(Long id) {
        var target = find(id);
        if (target.isPresent()) {
            ENTITIES.remove(target.get());
        }
    }
}
