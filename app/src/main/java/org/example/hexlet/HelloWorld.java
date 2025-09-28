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
        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name");
            ctx.result(String.format("Hello, %s", name == null ? "World" : name));
        });
        app.start(7070); // Стартуем веб-сервер
    }
}