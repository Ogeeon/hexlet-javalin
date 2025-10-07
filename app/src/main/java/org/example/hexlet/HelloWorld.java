package org.example.hexlet;

import java.util.ArrayList;
import java.util.List;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import io.javalin.Javalin;
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
            List<Course> courses = new ArrayList<>();/* Список курсов извлекается из базы данных */
            var c = new Course("aaa", "aaa descr");
            c.setId(1l);
            courses.add(c);
            c = new Course("bbb", "bbb descr");
            c.setId(2l);
            courses.add(c);
            c = new Course("ccc", "ccc descr");
            c.setId(3l);
            courses.add(c);
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = new Course("aaa", "aaa descr");
            course.setId(Long.valueOf(id));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.start(7070);
    }
}