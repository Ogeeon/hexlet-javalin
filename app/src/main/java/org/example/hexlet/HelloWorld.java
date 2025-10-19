package org.example.hexlet;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.BasePage;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        /*app.before(ctx -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.printf("%s|%s\n", LocalDateTime.now().format(formatter), ctx.path());
        });
        app.get("/", ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", model("page", page));
        });
        // Отображение формы логина
        app.get("/sessions/build", SessionsController::build);
        // Процесс логина
        app.post("/sessions", SessionsController::create);
        // Процесс выхода из аккаунта
        app.delete("/sessions", SessionsController::destroy);*/

        app.get("/", ctx -> {
            var page = new BasePage();
            ctx.render("layout/landing_page.jte", model("page", page));
        });

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath(), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.coursesPath() + "/{id}/edit", CoursesController::edit);
        app.patch(NamedRoutes.coursesPath() + "/{id}", CoursesController::update);
        app.delete(NamedRoutes.coursesPath() + "/{id}", CoursesController::destroy);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.usersPath() + "/{id}", UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.usersPath() + "/{id}/edit", UsersController::edit);
        app.patch(NamedRoutes.usersPath() + "/{id}", UsersController::update);
        app.delete(NamedRoutes.usersPath() + "/{id}", UsersController::destroy);
        app.start(7070);
    }
}
