package org.example.hexlet;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursesPath()+"/{id}", CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.coursesPath()+"/{id}/edit", CoursesController::edit);
        app.patch(NamedRoutes.coursesPath()+"/{id}", CoursesController::update);
        app.delete(NamedRoutes.coursesPath()+"/{id}", CoursesController::destroy);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.usersPath()+"/{id}", UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.usersPath()+"/{id}/edit", UsersController::edit);
        app.patch(NamedRoutes.usersPath()+"/{id}", UsersController::update);
        app.delete(NamedRoutes.usersPath()+"/{id}", UsersController::destroy);
        app.start(7070);
    }
}
