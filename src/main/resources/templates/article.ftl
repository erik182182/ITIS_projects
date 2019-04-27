<!DOCTYPE html>
<html lang="en">
<head>
    <title>Wellness - лучший сервис для похудения! ${article.title}</title>
    <meta name="keywords" content="Похудение, wellness, здоровье, питание, еда, сервис">
    <meta name="description" content="Wellness - лучший сервис для похудения! С его помощью вы быстро и легко достигните своей цели!">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="https://fonts.googleapis.com/css?family=Damion|Dancing+Script|Oswald|Caveat" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="${springMacroRequestContext.contextPath}/articles" >
        <i class="fa fa-reply back-button"></i>
        <span class="back-button ml-3" >Назад</span>
    </a>
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
                            <div class="col-md-4">${article.text} </div>
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
    <div class="row">
        <p>${article.text}</p>
    </div>
</div>
<h2>${article.title}</h2>
<p>${article.text}</p>
</body>
</html>