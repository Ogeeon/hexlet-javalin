@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.cars.BuildCarPage
@param BuildCarPage page

@template.layout.landing_page(
    page = page,
    content = @`
      @if(page.getErrors() != null)
          <ul>
              @for(var validator : page.getErrors().values())
                  @for(var error : validator)
                      <li>${error.getMessage()}</li>
                  @endfor
              @endfor
          </ul>
      @endif

      <form action="${NamedRoutes.carsPath()}" method="post">
        <div>
          <label>
            Бренд
            <input type="text" name="make" value="${page.getMake()}" />
          </label>
        </div>
        <div>
          <label>
            Модель
            <input type="model" name="model" value="${page.getModel()}" />
          </label>
        </div>
        <input type="submit" value="Зарегистрировать" />
      </form>
      `
)