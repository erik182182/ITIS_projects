<!DOCTYPE html>
<html lang="en">
<head>
	<title>Wellness - лучший сервис для похудения! Регистрация</title>
	<meta name="keywords" content="Похудение, wellness, здоровье, питание, еда, сервис"> 
	<meta name="description" content="Wellness - лучший сервис для похудения! С его помощью вы быстро и легко достигните своей цели!">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link href="https://fonts.googleapis.com/css?family=Damion|Dancing+Script" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz|Oswald|Gabriela" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/css/styles.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="reg-page">
<div class="container">
	<div class="row auth justify-content-center">
		<a class="navbar-brand" href="${springMacroRequestContext.contextPath}/articles" >
    	<img src="${springMacroRequestContext.contextPath}/server.img/logo.png" width="80" height="70" class="d-inline-block align-top mt-5" alt="Wellness" >
    	<span class="brand "> Wellness </span>
 	 </a>
	</div>
	<div class="row justify-content-center">
		<div class="card auth-card ">
			  <div class="card-header">
			 	 Регистрация
			  </div>
			  <div class="card-body">
			    <form method="post">
					  <div class="form-group">
					    <label for="login">Введите почту:</label>
					    <input type="email" name="email" class="form-control" id="login" aria-describedby="emailHelp" placeholder="E-mail">
					  </div>
					  <div class="row justify-content-between ">
					    <div class="col">
					    <label for="first-name">Введите имя:</label>
					      <input type="text" id="first-name" class="form-control" name="first_name" placeholder="Имя">
					    </div>
					    <div class="col">
					    <label for="last-name">Введите фамилию:</label>
					      <input type="text" id="last-name" name="last_name" class="form-control" placeholder="Фамилия">
					    </div>
					  </div>
						<div class="row justify-content-between">
                            <div class="form-check col">
                                <input class="form-check-input" type="radio" name="sex" id="male" value="male">
                                <label class="form-check-label" for="male">
                                    Мужской
                                </label>
                            </div>
                            <div class="form-check col">
                                <input class="form-check-input" type="radio" name="sex" id="female" value="female">
                                <label class="form-check-label" for="female">
                                    Женский
                                </label>
                            </div>
						</div>
					  <div class="row justify-content-between">
						  <div class="col">
                              <div class="form-group ">
                                  <label for="password">Введите пароль:</label>
                                  <input type="password" name="password" class="form-control" id="password" placeholder="Пароль">
                              </div>
						  </div>
						  <div class="col">
                              <div class="form-group ">
                                  <label for="r-password">Повторите пароль:</label>
                                  <input type="password" name="repeat_password" class="form-control" id="r-password" placeholder="Пароль">
                              </div>
						  </div>
					  </div>

					  <div class="row justify-content-center form-group">
					    <div class="form-check">
					      <input class="form-check-input" type="checkbox" name="consent_data" id="gridCheck" required="true">
					      <label class="form-check-label" for="gridCheck">
					        Согласие на обработку персональных данных
					      </label>
					    </div>
					  </div>
					  <div class="row justify-content-center form-group">
					    <div class="form-check">
					      <input class="form-check-input" type="checkbox" name="consent_emails" id="emailCheck">
					      <label class="form-check-label" for="emailCheck">
					        Присылать уведомления на почту
					      </label>
					    </div>
					  </div>
					  <div class="row justify-content-center">
					  <small class="form-text text-muted">Уже зарегистрированы? Вы можете <a href="${springMacroRequestContext.contextPath}/signin">войти</a>. </small>
					  </div>
					  <div class="row justify-content-center">
  						  <button type="submit" class="btn btn-success ">Зарегистрироваться</button>
					  </div>
				</form>			 
			   </div>
		</div>
	</div>
</div>


</body>
</html>