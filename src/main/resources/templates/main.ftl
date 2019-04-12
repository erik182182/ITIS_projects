<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../resources/img/logo.png" rel="icon">
    <title>Главная</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://fonts.googleapis.com/css?family=Philosopher:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="../../resources/lib/bootstrap/js/bootstrap.js"></script>

    <link href="../../resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">

    <link href="../../resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/lib/font-awesome/css/font-awesome.min.css">

</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" id="header-bar">
        <div class="nav navbar-nav navbar-left" href="#">
            <img src="../../resources/img/logo.png" width="50" height="50" class="" alt="">
            Wellness
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">Главная</a></li>
                <li><a href="#">Питание</a></li>
                <li><a href="#">Лента</a></li>


                <li class="menu__item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="../../resources/img/profileLogo.png" width="40">Мой профиль
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
            <button type="button" class="btn btn-secondary btn-lg btn-block  btn-success">Статьи</button>
            <button type="button" class="btn btn-secondary btn-lg btn-block  btn-success">Избранные</button>
        </div>
    </div>
</div>


<div class="other-container col-lg-10">

    <#--начало list-->
    <list article as articles>

    <div class="col-md-4">
        <div class="card mb-4 box-shadow">
            <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Картинка" alt="Картинка [100%x225]" style="height: 225px; width: 100%; display: block;" src="../../resources/img/bg-1.png" data-holder-rendered="true">
            <div class="card-body">
                <p class="card-text"> <#--начало статьи--></p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <div class="bd-example bd-example-padded-bottom">
                            <button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#gridSystemModal1">
                                Прочитать
                            </button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">В избранное</button>
                        </div>
                    </div>
                    <small class="text-muted">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                    </small>
                </div>
            </div>
        </div>
    </div>
    <div id="gridSystemModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridModalLabel1">Заголовок</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid bd-example-row">
                        <div class="row">
                            <div class="col-md-4">${article.text}</div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                    <div id="reviewStars-input">
                        <input id="star-4" type="radio" name="reviewStars"/>
                        <label title="gorgeous" for="star-4"></label>

                        <input id="star-3" type="radio" name="reviewStars"/>
                        <label title="good" for="star-3"></label>

                        <input id="star-2" type="radio" name="reviewStars"/>
                        <label title="regular" for="star-2"></label>

                        <input id="star-1" type="radio" name="reviewStars"/>
                        <label title="poor" for="star-1"></label>

                        <input id="star-0" type="radio" name="reviewStars"/>
                        <label title="bad" for="star-0"></label>
                    </div>
                </div>

            </div>
        </div>
    </div>

    </list>

    <#--конец list-->

</div>


<div class="footer" style="padding-top: 75%">
    <footer class="footer" style="background-color:darkslategrey;">
        <div class="container" style="color: white">
                <span class="text" style="color: white;">
                    &copy; Made by Samat, Ernest, Rustem, Alina<br>
                        All rights reserved</span>
        </div>
    </footer>
</div>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script  src="../../resources/main/js"></script>
</body>
</html>