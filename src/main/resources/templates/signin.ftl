<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="img/logo.png" rel="icon">
    <title>Главная</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://fonts.googleapis.com/css?family=Philosopher:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js" integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU" crossorigin="anonymous"></script>

    <link href="/css/style.css" rel="stylesheet">

</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" id="header-bar">
        <div class="nav navbar-nav navbar-left" href="#">
            <img src="../static/img/logo.png" width="50" height="50" class="" alt="">
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
        <h1>Вход</h1>

        <form action="#" method="post">

            <div class="form-group">
                <label for="email">
                    Эл. почта<span class="req">*</span>
                </label>
                <input type="email" name="email" id="email" autocomplete="off" class="form-control" placeholder="Введите логин"/>
            </div>

            <div class="form-group">
                <label for="password">
                    Пароль<span class="req">*</span>
                </label>
                <input type="password" name="password" id="password" autocomplete="off" class="form-control" placeholder="Введите пароль"/>
            </div>

            <p><a href="#">Не зарегистрированы?</a></p>


            <button type="submit" class="btn btn-light btn-lg">Войти</button>

            <#if error??>
                <div class="error">
                    <p>${error}</p>
                    <!--Для валидации-->

                </div>
            </#if>


        </form>
    </div>

</div>


<div class="footer" style="padding-top: 44%">
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