
<html>
<head>

    <title>${article.getTitle()} | Articles| Wellness</title>
    <meta charset="UTF-8">
    <link href="${springMacroRequestContext.contextPath}/resources/img/logo.png" rel="icon">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://fonts.googleapis.com/css?family=Philosopher:300,400,700,900|Raleway:400,300,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link href="${springMacroRequestContext.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${springMacroRequestContext.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" id="header-bar">
        <div class="nav navbar-nav navbar-left" href="#">
            <img src="${springMacroRequestContext.contextPath}/img/logo.png" width="50" height="50" class="" alt="">
            Wellness
        </div>
    </div>
</nav>

<h1>${article.getTitle()}</h1>
<img src="${springMacroRequestContext.contextPath}/resources/${article.getResources()[0]}" alt="Main article's image">
<p>${article.getText()}</p>

<div class="" style="padding-top: 44%">
    <footer class="footer fixed-bottom" style="background-color:darkslategrey;">
        <div class="container" style="color: white">
                <span class="text" style="color: white;">
                    &copy; Made by Samat, Ernest, Rustem, Alina<br>
                        All rights reserved</span>
        </div>
    </footer>
</div>

</body>
</html>
