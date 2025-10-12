@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

@template.layout.landing_page(
    content = @`
        <main>
            <h1>${page.getCourse().getName()}</h1>
            <p>${page.getCourse().getDescription()}</p>
        </main>
    `
)