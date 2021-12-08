<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <link href="assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/css/login.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Iniciar Sesion Agencia de Viajes</title>
</head>
<body class="text-center">
    
<main class="form-signin">
  <form action="/tierramedia/login" method="post">
    <img class="mb-4" src="assets/img/logo.jpg" alt="" width="90" height="95">
    <h1 class="h3 mb-3 fw-normal">Inicie Sesion</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" name="username" placeholder="name@example.com">
      <label for="floatingInput">Email</label>
    </div>
    <div class="form-floating">
      <input type="password" name= "password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Contrase�a</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Iniciar Sesion</button>
  </form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>