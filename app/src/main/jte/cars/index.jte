@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.cars.CarsPage
@param CarsPage page

@template.layout.landing_page(
    page = page,
    content = @`
        <html>
            <head>
                <title>Хекслет</title>
                <meta charset="utf-8" />
            </head>
            <body>
                <h1>List of cars</h1>
                @if(page.getCars().isEmpty())
                    <p>Пока не добавлено ни одной машины</p>
                @else
                    @for(var car : page.getCars())
                    <div>
                        <h2><a href="${NamedRoutes.carPath(car.getId())}">${car.getMake()}</a></h2>
                        <p>${car.getModel()}</p>
                    </div>
                    @endfor
                @endif
            </body>
        </html>
    `,
    footer = @`
        <hr>
        <a href="${NamedRoutes.buildCarPath()}">Register a Car</a>
    `
)