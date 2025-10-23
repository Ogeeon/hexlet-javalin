@import org.example.hexlet.dto.cars.CarPage
@param CarPage page

@template.layout.landing_page(
    content = @`
        <main>
            <h1>${page.getCar().getMake()}</h1>
            <p>${page.getCar().getModel()}</p>
        </main>
    `
)