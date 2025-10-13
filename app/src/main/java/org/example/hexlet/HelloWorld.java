package org.example.hexlet;

import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.validation.ValidationException;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/courses", ctx -> {
            var coursesPage = new CoursesPage(CourseRepository.getEntities(), "List of courses");
            ctx.render("courses/index.jte", model("page", coursesPage));
        });
        app.get("/courses/build", ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", model("page", page));
        });
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(id).orElseThrow(() -> new NotFoundResponse("Course with id = " + id + " not found"));
            course.setId(id);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });
        app.post("/courses", ctx -> {
            var rawName = ctx.formParam("name");
            var rawDescription = ctx.formParam("description");
            try {
                var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> String.valueOf(value).length() > 2, "Слишком короткое название")
                    .get();
                var description = ctx.formParamAsClass("description", String.class)
                    .check(value -> String.valueOf(value).length() > 10, "Слишком короткое описание")
                    .get();
                var Course = new Course(name, description);
                CourseRepository.save(Course);
                ctx.redirect("/courses");
            } catch (ValidationException e) {
                var name = ctx.formParam("name");
                var description = ctx.formParam("description");
                System.err.printf("name=%s, descr=%s, rn=%s, rd=%s", name, description, rawName, rawDescription);
                var page = new BuildCoursePage(rawName, rawDescription, e.getErrors());
                ctx.render("courses/build.jte", model("page", page));
            }
        });

        app.get("/users", ctx -> {
            var usersPage = new UsersPage(UserRepository.getEntities(), "List of users");
            ctx.render("users/index.jte", model("page", usersPage));
        });
        app.get("/users/build", ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });
        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
            user.setId(id);
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });
        app.post("/users", ctx -> {
            var name = ctx.formParam("name");
            var email = ctx.formParam("email");

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect("/users");
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });
        app.start(7070);
    }
}
