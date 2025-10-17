@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.landing_page(
    content = @`
    <p>Привет, Хекслет!</p>
    @if(!page.isVisited())
        Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
    @endif
    `,
)