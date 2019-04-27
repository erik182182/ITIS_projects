<!DOCTYPE html>
<html lang="en">
<head>
    <title>Wellness - лучший сервис для похудения! ${article.title}</title>
    <meta name="keywords" content="Похудение, wellness, здоровье, питание, еда, сервис">
    <meta name="description" content="Wellness - лучший сервис для похудения! С его помощью вы быстро и легко достигните своей цели!">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="https://fonts.googleapis.com/css?family=Damion|Dancing+Script|Oswald|Caveat|Merriweather" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <link href="${springMacroRequestContext.contextPath}/server.img/logo.png" rel="icon" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="${springMacroRequestContext.contextPath}/articles" >
        <i class="fa fa-reply back-button"></i>
        <span class="back-button ml-3" >Назад</span>
    </a>
    <form method="post" action="${springMacroRequestContext.contextPath}/articles/addfavorite" >
        <span class="navbar-text mr-2">
            <#if usersGrade??>
                Оценка: ${usersGrade}
                <#else>
            </#if>
            
        </span>
        <input type="hidden" value="${article.id}" name="article_id">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i class="fa fa-heart"></i> В избранное</button>
    </form>
</nav>
<div class="container article col-lg-10 article-form">
    <h2>${article.title}</h2>
    <div id="carouselExampleSlidesOnly" class="carousel slide my-5" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${springMacroRequestContext.contextPath}/server.img/loginback.jpg" class="d-block w-100">
        </div>
      </div>
    </div>
    <p>
    ${article.text}
    </p>
</div>
</body>
</html>