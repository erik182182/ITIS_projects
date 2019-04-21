<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${springMacroRequestContext.contextPath}/img/logo.png" rel="icon">
    <title>Главная</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://fonts.googleapis.com/css?family=Philosopher:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js" integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU" crossorigin="anonymous"></script>

    <link href="${springMacroRequestContext.contextPath}/css/style.css" rel="stylesheet">


</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" id="header-bar">
        <div class="nav navbar-nav navbar-left" style="width: 29%" href="#">
            <img src="${springMacroRequestContext.contextPath}/resources/img/logo.png" width="50" height="50" class="" alt="">
            Wellness
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Главная</a></li>
                <li><a href="#">Питание</a></li>
                <li><a href="#">Лента</a></li>
                <!--<div class="nav navbar-right">-->
                <!--<a data-toggle="modal" data-target="#myModal" href="#myModal"><img src="../../resources/img/profileLogo.png" width="40">Мой профиль</a>-->
                <!--</div>-->


                <li class="menu__item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="https://pp.userapi.com/c850232/v850232710/10b9cd/sdzFQ5uN3L4.jpg" width="40">Мой профиль
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <li><a class="dropdown-item" href="#">Мой профиль</a></li>
                        <li><a class="dropdown-item" href="#">Мой прогресс</a></li>
                        <li><a class="dropdown-item" href="#">Мои сообщения</a></li>
                        <li><div class="dropdown-divider"></div></li>
                        <li><a class="dropdown-item" href="#">Настройки</a></li>
                        <li><a class="dropdown-item" href="#">Выход</a></li>

                    </ul>
                </li>

            </ul>
        </div>
    </div>

</nav>

<div class="panel col-lg-2">
    <div class="row left">
        <div class="col-lg-offset-2">
            <button type="button" class="btn btn-secondary btn-lg btn-block btn-success">Мой прогресс</button>
            <button type="button" class="btn btn-secondary btn-lg btn-block btn-success">Мои сообщения</button>
            <button type="button" class="btn btn-secondary btn-lg btn-block btn-success">Настройки</button>

        </div>
    </div>
</div>

<div class="container">

    <div class="profile-container">
        <h1>Зайдуллин Самат</h1>
        <ul>
            <li>
                <span class="settings">
                    <label for="upload"><a><input type="file" id="upload" style="display: none"><i class="fa fa-upload"></i>Загрузить новую</a></label>
                    <p><a href="#"><i class="fa fa-shopping-basket"></i>Удалить</a></p>
                </span>
                <img src="https://pp.userapi.com/c850232/v850232710/10b9cd/sdzFQ5uN3L4.jpg" height="440">
            </li>

        </ul>
    </div>

    <div class="data-container">
        <form method="post" action="profile.html">
            <div class="form-group">
                <label for="name">Имя</label>
                <input type="text" name="firstName" id="name" class="form-control" placeholder="Введите имя" required>
            </div>
            <div class="form-group">
                <label for="surname">Фамилия</label>
                <input type="text" name="lastName" id="surname" class="form-control" placeholder="Введите фамилию" required>
            </div>
            <div class="form-group">
                <label>Пол</label><br>
                <label>
                    <input type="radio" name="gender" value="male" class="form-check-input">мужской
                    <div class="radio-control male"></div>
                </label><br>
                <label>
                    <input type="radio" name="gender" value="female" class="form-check-input">женский
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
                <input type="password" name="repassword" id="repassword" class="form-control" placeholder="повторите пароль" required>
            </div>
            <div class="form-group">
                <label for="subscription"  class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="subscription" value="true" id="subscription">
                    Подписка
                </label>

            </div>

            <button type="submit" class="btn btn-light btn-lg">Обновить данные</button>
        </form>
    </div>

</div>



<div class="footer" style="padding-top: 6%">
    <footer class="footer" style="background-color:darkslategrey;">
        <div class="container" style="color: white">
                <span class="text" style="color: white;">
                    &copy; Made by Samat, Ernest, Rustem, Alina<br>
                        All rights reserved</span>
        </div>
    </footer>
</div>









<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script  src="${springMacroRequestContext.contextPath}/js/index.js"></script>
</body>
</html>