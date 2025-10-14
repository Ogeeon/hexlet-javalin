@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.landing_page(
    content = @`
        <html>
            <head>
                <title>Хекслет</title>
                <meta charset="utf-8" />
            </head>
            <body>
                <h1>List of users</h1>
                @if(page.getUsers().isEmpty())
                    <p>Пока не добавлено ни одного пользователя</p>
                @else
                    @for(var user : page.getUsers())
                    <div>
                        <h2><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></h2>
                        <p>${user.getEmail()}</p>
                    </div>
                    @endfor
                @endif
            </body>
        </html>
    `,
    footer = @`
        <hr>
        <a href="${NamedRoutes.buildUserPath()}">Register a User</a>
    `
)