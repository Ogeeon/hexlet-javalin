package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello from Hexlet"));
        app.get("users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId =  ctx.pathParam("id");
            ctx.result("User ID: " + userId + " post ID: " + postId);
        });
        app.start(7070); // Стартуем веб-сервер
    }
}