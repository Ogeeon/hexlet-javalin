@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Javalin rocks!</title>
    </head>
    <body>
        <p>
            <a href="${NamedRoutes.usersPath()}">Users</a>
            <a href="${NamedRoutes.coursesPath()}">Courses</a>
        </p>
        @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType() == 0 ? "success" : "danger"}" role="alert">
            <p>${page.getFlash()}</p>
        </div>
        @endif
        ${content}
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>