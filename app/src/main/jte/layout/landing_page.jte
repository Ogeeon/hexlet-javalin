@import gg.jte.Content

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
            <a href="/users">Users</a>
            <a href="/courses">Courses</a>
        </p>
        ${content}
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>