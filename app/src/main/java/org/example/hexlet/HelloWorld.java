package org.example.hexlet;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
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
           ctx.render("courses/build.jte");
        });
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(id).orElseThrow(() -> new NotFoundResponse("Course with id = " + id + " not found"));
            course.setId(id);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });
        app.post("/courses", ctx -> {
            var namePrm = ctx.formParam("name");
            var descrPrm = ctx.formParam("description");
            if (namePrm != null && descrPrm != null) {
                var name = namePrm.trim();
                var description = descrPrm.trim();

                var Course = new Course(name, description);
                CourseRepository.save(Course);
            }
            ctx.redirect("/courses");
        });
        app.get("/users", ctx -> {
            var usersPage = new UsersPage(UserRepository.getEntities(), "List of users");
            ctx.render("users/index.jte", model("page", usersPage));
        });
        app.get("/users/build", ctx -> {
           ctx.render("users/build.jte");
        });
        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
            user.setId(id);
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });
        app.post("/users", ctx -> {
            var namePrm = ctx.formParam("name");
            var emailPrm = ctx.formParam("email");
            var pwdPrm = ctx.formParam("password");
            if (namePrm != null && emailPrm != null && pwdPrm != null) {
                var name = namePrm.trim();
                var email = emailPrm.trim().toLowerCase();
                var password = pwdPrm;

                var user = new User(name, email, password);
                UserRepository.save(user);
            }
            ctx.redirect("/users");
        });
        app.start(7070);
    }
}
