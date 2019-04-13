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
        <div class="nav navbar-nav navbar-left" href="#">
            <img src="${springMacroRequestContext.contextPath}/img/logo.png" width="50" height="50" class="" alt="">
            Wellness
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">Главная</a></li>
                <li><a href="#">Питание</a></li>
                <li><a href="#">Лента</a></li>


                <li class="menu__item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="${springMacroRequestContext.contextPath}/img/profileLogo.png" width="40">Мой профиль
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

    <div id="gridSystemModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridModalLabel1">${article.title}</h4>
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


</div>



<section class="cities">
    <div class="container">
        <div class="row priceTitle">
            <div class="row priceTitle">
                <h1>${article.title}</h1>
            </div>
        </div>
           <div class="card-deck mb-3 text-center">
               <div class="card mb-4 box-shadow">
                   <div class="card-header">
                       <h4 class="my-0 font-weight-normal">${article.text}</h4>
                   </div>
                   <div class="card-img-top">
                       <div id="carouselExampleIndicators${article.id}" data-ride="carousel">
                           <ol class="carousel-indicators">

                               <li data-target="#carouselExampleIndicators${article.id}" class="active" </li>

                           </ol>
                           <div class="carousel-inner">
                            <#list article.resources as res>
                               <div class='carousel-item'>
                                   <img class="d-block w-100" src="${springMacroRequestContext.contextPath}/${res}" alt="Second slide">
                               </div>
                            </#list>
                           </div>
                           <a class="carousel-control-prev" href="#carouselExampleIndicators${article.id}" role="button" data-slide="prev">
                               <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                               <span class="sr-only">Previous</span>
                           </a>
                           <a class="carousel-control-next" href="#carouselExampleIndicators${article.id}" role="button" data-slide="next">
                               <span class="carousel-control-next-icon" aria-hidden="true"></span>
                               <span class="sr-only">Next</span>
                           </a>
                       </div>
                   </div>
                   <div class="card-body">
                       <ul class="list-unstyled mt-3 mb-4">
                           <li>${article.text}</li>
                       </ul>
                   </div>
               </div>
           </div>

    </div>
</section>


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

<script src="${springMacroRequestContext.contextPath}/main/js/index.js"></script>
</body>
</html>