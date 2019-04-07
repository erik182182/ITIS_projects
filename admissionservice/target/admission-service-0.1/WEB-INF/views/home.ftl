
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
            <li class="nav-item active">
                <a class="nav-link" href="/home" style="font-weight: bold;">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/declarations" style="font-weight: bold; margin-left: 3%;">
                Заявления<span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <#if name??>
            <span style="color: #fff; margin-left: 15px; font-weight: bold">${name}</span>
        </#if>


        <#if !name??>
            <button type="button" class="btn btn-light" style=" margin-left: 15px;" id="sign-in-button">Войти</button>

        <#else>
            <form method="post" action="/logout">
            <button type="submit" class="btn btn-light" style=" margin-left: 15px;" id="log-out-button">Выйти</button>
            </form>

        </#if>
    </div>
</nav>
    <#if name??>
        <#if directions?size != 0>
            <h2 style="width: 65%; font-weight: bold; margin-bottom: 0px; ">Список всех направлений, на которые Вы можете пройти:</h2>
        <#else>
            <h2 style="width: 65%; font-weight: bold; margin-bottom: 0px; ">К сожалению, вы не проходите ни на одно направление.</h2>
        </#if>
    <#else>
        <h2 style="width: 65%; font-weight: bold; margin-bottom: 0px;">Список всех направлений:</h2>
    </#if>


    <#list directions as dir>
        <div class="container direction">
            <h3><span class="info">Университет: </span>${dir.university.name}</h3>
            <h3><span class="info">Направление обучения: </span >${dir.name}</h3>
            <hr>
            <div class="row" style="padding-top:2%; padding-bottom: 2%;">
                <div class="col-3" style="padding: 10px;">
                    <div class="nav flex-column nav-pills"  role="tablist" aria-orientation="vertical">
                        <a class="nav-link active"  data-toggle="pill" href="#v-pills-home${dir.id}" role="tab" aria-controls="v-pills-home${dir.id}" aria-selected="true">Об университете</a>
                        <a class="nav-link" data-toggle="pill" href="#v-pills-profile${dir.id}" role="tab" aria-controls="v-pills-profile${dir.id}" aria-selected="false">О направлении</a>
                        <a class="nav-link"  data-toggle="pill" href="#v-pills-messages${dir.id}" role="tab" aria-controls="v-pills-messages${dir.id}" aria-selected="false">Проходные баллы</a>

                    </div>
                </div>
                <div class="col-9">
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="v-pills-home${dir.id}" role="tabpanel" >
                            <div>
                                    ${dir.university.info}
                                <p><span class="info">Город: </span>${dir.university.city}</p>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-profile${dir.id}" role="tabpanel" ><div>
                                ${dir.info}
                            <p><span class="info">Количество бюджетных мест: </span>${dir.budgetPlaces}</p>
                        </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-messages${dir.id}" role="tabpanel">
                            <div>
                                <ul class="list-group">
                                    <#list dir.examsWithMinScore as exams>
                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                                ${exams.subject}
                                            <span class="badge badge-primary badge-pill">${exams.score}</span>
                                        </li>
                                    </#list>
                                </ul>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <#if name??>
                <button type="button" value="${dir.university.name}, ${dir.name}" class="btn btn-success" style="margin-left: 40%; margin-bottom: 3%;" onclick="apply(${dir.id}, value);">Подать заявление</button>
            </#if>
        </div>

    </#list>

    <#if directions?size != 0>
        <#if name??>
            <div class="card" style="width: 18rem;" id="dir-info">
                <div class="card-header" style="background-color: aliceblue;">
                    <h2 style="color: #ff5347;">Важная информация</h2>
                </div>
                <div class="card-body" >
                    <p class="card-text"><h3><span style="color: #56b8ff;">&bull;</span> Помните, что поданное заявление <u style="color: #ff5347;">невозможно отменить.</u></h3></p>
                    <p class="card-text"><h3><span style="color: #56b8ff;">&bull;</span> Вы можете подать максимум 3 заявления.</h3></p>
                    <p class="card-text"><h3><span style="color: #56b8ff;">&bull;</span> Вы уже подали <span id="decs-size" style="color: #2673cc;">${size}</span> из  3 заявлений.</h3></p>


                    <ul class="list-group list-group-flush" id="decs">
                        <#list declarations as dec>
                            <li class="list-group-item" style="color: #2673cc;">${dec.direction.university.name}, ${dec.direction.name}</li>
                        </#list>
                    </ul>
                </div>
            </div>
        <#else>
            <div class="card" style="width: 18rem;" id="dir-info">
                <div class="card-body" >
                        <p>Вы можете войти на сайт, чтобы посмотреть индивидуальный список направлений
                            для Вас и подать на них заявление.</p>
                </div>
            </div>
        </#if>

    </#if>

    <#if exams??>
        <div class="exams">
            <ul class="list-group">
                <li class="list-group-item d-flex justify-content-between align-items-center" style="font-weight: bold; font-size: 2em;">
                    Ваши результаты экзаменов
                </li>
                <#list exams as exam>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${exam.subject}
                        <span class="badge badge-primary badge-pill">${exam.score}</span>
                    </li>
                </#list>
            </ul>
        </div>

    </#if>

    <div id = "dir-message"></div>


    </body>




<script>

    <#if !name??>
    document.getElementById("sign-in-button").onclick = function () {
        return location.href = "/signIn";
    }



    <#else>


    function apply(dirId, value){
        if(!confirm("Вы действительно хотите подать заявление? Это действие невозможно отменить.\n"
        + value)) return;
        $.ajax({
            type: 'post',
            url: "/home",
            data: {
                dirId: dirId
            }
        }).done(function (data) {
            document.getElementById("dir-message").innerHTML =
                "<div class=\"alert alert-" + data.flag +  " alert-dismissible fade show\" role=\"alert\">\n" +
                data.message +
                "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                "    <span aria-hidden=\"true\">&times;</span>\n" +
                "  </button>\n" +
                "</div>";
            var content = "";
            for(var i = 0; i < data.declarations.length; i++){
                if(data.declarations[i] != undefined) content += "<li class=\"list-group-item\" style='color: #3872d1;'>"+data.declarations[i] +"</li>";
            }
            document.getElementById("decs").innerHTML = content;
            document.getElementById("decs-size").innerText = data.size;
        }).fail(function () {
            alert("Произошла ошибка.");
        });
    }


    </#if>


</script>
</html>