package org.example.hexlet.controller;
import org.example.hexlet.dto.BasePage;

import io.javalin.http.Context;
import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    private RootController() {
        throw new IllegalStateException("Utility class");
    }

    public static void index(Context ctx) {
        var page = new BasePage();
        ctx.render("layout/landing_page.jte", model("page", page));
    }
}