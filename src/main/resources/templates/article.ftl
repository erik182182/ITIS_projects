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
<div class="container">
    <div class="row">
        <h2>${article.title}</h2>
    </div>
    <div class="row">
        <p>${article.text}</p>
    </div>
</div>
<h2>${article.title}</h2>
<p>${article.text}</p>
</body>
</html>