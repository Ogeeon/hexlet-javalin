package org.example.hexlet.controller;

import java.sql.SQLException;

import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.validation.ValidationException;

public class UsersController {
    private static final String INDEXJTE = "users/index.jte";
    private static final String BUILDJTE = "users/build.jte";
    private static final String SHOWJTE = "users/show.jte";
    private static final String EDITJTE = "users/edit.jte";
    private static final String NAMEFLD = "name";
    private static final String EMAILFLD = "email";

    private UsersController() {
        throw new IllegalStateException("Utility class");
    }
    public static void index(Context ctx) throws SQLException {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        ctx.render(INDEXJTE, model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render(SHOWJTE, model("page", page));
    }

    public static void build(Context ctx) throws SQLException {
        var page = new BuildUserPage();
        ctx.render(BUILDJTE, model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        try {
            var name = ctx.formParamAsClass(NAMEFLD, String.class)
                .check(value -> value.length() > 0, "Имя не должно быть пустым")
                .get();
            var email = ctx.formParamAsClass(EMAILFLD, String.class)
                .check(value -> value.contains("@"), "В email должен быть знак @")
                .get();
            var password = ctx.formParam("password");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.sessionAttribute("flash", "User has been created!");
            ctx.sessionAttribute("flashType", 0);
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(ctx.formParam(NAMEFLD), ctx.formParam(EMAILFLD), e.getErrors());
            page.setFlash("User cannot be created!");
            page.setFlashType(1);
            ctx.render(BUILDJTE, model("page", page));
        }
    }

    public static void edit(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render(EDITJTE, model("page", page));
    }


    public static void update(Context ctx) throws SQLException {
        var name = ctx.formParam(NAMEFLD);
        var email = ctx.formParam(EMAILFLD);

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(name, email, e.getErrors());
            ctx.render(BUILDJTE, model("page", page));
        }
    }

    public static void destroy(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        UserRepository.delete(id);
        ctx.redirect(NamedRoutes.usersPath());
    }
}
