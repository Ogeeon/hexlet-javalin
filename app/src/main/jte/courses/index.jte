@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.landing_page(
    page = page,
    content = @`
        <html>
            <head>
                <title>Хекслет</title>
                <meta charset="utf-8" />
            </head>
            <body>
                <h1>List of courses</h1>
                @if(page.getCourses().isEmpty())
                    <p>Пока не добавлено ни одного курса</p>
                @else
                    @for(var course : page.getCourses())
                    <div>
                        <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
                        <p>${course.getDescription()}</p>
                    </div>
                    @endfor
                @endif
            </body>
        </html>
    `,
    footer = @`
        <hr>
        <a href="${NamedRoutes.buildCoursePath()}">Add a course</a>
    `
)