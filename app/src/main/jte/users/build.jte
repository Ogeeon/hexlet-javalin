<!doctype html>
<html lang="en">
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
    </head>
    <body>
      <form action="/users" method="post">
        <div>
          <label>
            Имя
            <input type="text" name="name" />
          </label>
        </div>
        <div>
          <label>
            Email
            <input type="email" required name="email" />
          </label>
        </div>
        <div>
          <label>
            Пароль
            <input type="password" required name="password" />
          </label>
        </div>
        <div>
          <label>
            Подтверждение пароля
            <input type="password" required name="passwordConfirmation" />
          </label>
        </div>
        <input type="submit" value="Зарегистрировать" />
      </form>
    </body>
</html>