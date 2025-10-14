@import gg.jte.Content
@import org.example.hexlet.NamedRoutes

@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Javalin rocks!</title>
    </head>
    <body>
        <p>
            <a href="${NamedRoutes.usersPath()}">Users</a>
            <a href="${NamedRoutes.coursesPath()}">Courses</a>
        </p>
        ${content}
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>