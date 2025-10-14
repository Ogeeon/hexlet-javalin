package org.example.hexlet.controller;

import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {
    public static void index(Context ctx) {
        var courses = CourseRepository.getEntities();
        var page = new CoursesPage(courses);
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");

        var course = new Course(name, description);
        CourseRepository.save(course);
        ctx.redirect(NamedRoutes.coursesPath());
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/edit.jte", model("page", page));
    }


    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var name = ctx.formParam("name");
        var description = ctx.formParam("description");

        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        course.setName(name);
        course.setDescription(description);
        CourseRepository.save(course);
        ctx.redirect(NamedRoutes.coursesPath());
        /* var rawName = ctx.formParam("name");
        var rawDescription = ctx.formParam("description");
        try {
            var name = ctx.formParamAsClass("name", String.class)
                .check(value -> value.length() > 2, "Слишком короткое название")
                .get();
            var description = ctx.formParamAsClass("description", String.class)
                .check(value -> String.valueOf(value).length() > 10, "Слишком короткое описание")
                .get();
            var Course = new Course(name, description);
            CourseRepository.save(Course);
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var page = new BuildCoursePage(rawName, rawDescription, e.getErrors());
            ctx.render("courses/build.jte", model("page", page));
        }*/
    }

    public static void destroy(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        CourseRepository.delete(id);
        ctx.redirect(NamedRoutes.coursesPath());
    }
}