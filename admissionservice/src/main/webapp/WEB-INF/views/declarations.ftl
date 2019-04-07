
<html>
<head>

    <meta charset="utf-8">
    <title>ЕГЭ - Заявления</title>
    <link rel="shortcut icon" href="http://s1.iconbird.com/ico/2013/6/281/w256h25613715677480005BookmarkOpen.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/styles/style.css" >

    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/home" >ЕГЭ</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" href="/home" style="font-weight: bold;">Главная</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href=/declarations" style="font-weight: bold; margin-left: 3%;">
                Заявления<span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <#if name??>
            <span style="color: #fff; margin-left: 15px; font-weight: bold">${name}</span>
        </#if>


        <#if !name??>
            <button type="button" class="btn btn-light" style=" margin-left: 15px;" id="sign-in-button">Войти</button>

        <#else>
            <form method="post" action="/logout" >
            <button type="submit" class="btn btn-light" style=" margin-left: 15px;" id="log-out-button">Выйти</button>
            </form>

        </#if>
    </div>
</nav>
    <#if dirs?size = 0>
        <h2 style="text-align: center; font-weight: bold;">Вы еще не подали ни одного заявления.</h2>

    <#else>
        <h2 style="text-align: center; font-weight: bold;">Направления, на которые Вы подали заявления:</h2>
    </#if>
    <#list dirs as dir>
        <div class="card dirs" >
            <div class="card-header" style="background-color: #56b8ff; border-radius: 15px;">
                <h2 style="text-align: center; color: white;">${dir.university.name}, ${dir.name}, количество бюджетных мест: ${dir.budgetPlaces}</h2>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <#list dir.declarations as dec>
                        <#if dec_index < dir.budgetPlaces>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${dec_index+1}. ${dec.user.fullName}
                                <span class="badge badge-primary badge-pill">${dec.sumScore}</span>
                            </li>
                        <#else>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${dec_index+1}. ${dec.user.fullName}
                                <span class="badge badge-danger badge-pill">${dec.sumScore}</span>
                            </li>
                        </#if>

                    </#list>
                </ul>
            </div>
        </div>
    </#list>
    </body>

</html>