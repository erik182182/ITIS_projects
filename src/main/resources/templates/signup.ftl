
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../resources/img/logo.png" rel="icon">
    <title>Регистрация</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://fonts.googleapis.com/css?family=Philosopher:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


    <link href="../../resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">

    <link href="../../resources/css/style.css" rel="stylesheet">

</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" id="header-bar">
        <div class="nav navbar-nav navbar-left" href="#">
            <img src="../../resources/img/logo.png" width="50" height="50" class="" alt="">
            Wellness
        </div>
    </div>
</nav>

<div class="headerwrap col-lg-7">
    <div class="row centered">
        <div class="col-lg-offset-2">
            <h1>«Быть <b>здоровым,</b> жить активно – это стильно, позитивно» </h1>
            <h2>Цитата</h2>
        </div>
    </div>
</div>


<div class="form">
    <div class="tab-content">
        <h1>Регистрация</h1>
        <form method="post" action="">
            <div class="form-group">
                <label for="name">Имя</label>
                <input type="text" name="first_name" id="name" class="form-control" placeholder="Введите имя" required>
            </div>
            <div class="form-group">
                <label for="surname">Фамилия</label>
                <input type="text" name="last_name" id="surname" class="form-control" placeholder="Введите фамилию" required>
            </div>
            <div class="form-group">
                <label>Пол</label><br>
                <label>
                    <input type="radio" name="sex" value="male" class="form-check-input">мужской
                    <div class="radio-control male"></div>
                </label><br>
                <label>
                    <input type="radio" name="sex" value="female" class="form-check-input">женский
                    <div class="radio-control female"></div>
                </label>
            </div>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="придумайте пароль" required>
            </div>
            <div class="form-group">
                <label for="repassword">Repassword</label>
                <input type="password" name="repeat_password" id="repassword" class="form-control" placeholder="повторите пароль" required>
            </div>
            <div class="form-group">
                <label for="subscription"  class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="consent_emails" value="true" id="subscription">
                    Подписка
                </label>
                <p>
                    <label for="consent"  class="form-check-label">
                        <input class="form-check-input" type="checkbox" name="consent_data" value="true" id="consent" required>
                        Согласие
                    </label>
                </p>

            </div>
            <p><a href="#">У меня есть аккаунт</a></p>

            <button type="submit" class="btn btn-light btn-lg">Отправить</button>
        </form>
    </div>

</div>




<div class="footer" style="padding-top: 55%">
    <footer class="footer fixed-bottom" style="background-color:darkslategrey;">
        <div class="container" style="color: white">
                <span class="text" style="color: white;">
                    &copy; Made by Samat, Ernest, Rustem, Alina<br>
                        All rights reserved</span>
        </div>
    </footer>
</div>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</body>
</html>