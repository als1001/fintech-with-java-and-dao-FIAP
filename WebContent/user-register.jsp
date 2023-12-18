<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
  <link rel="stylesheet" href="style.css">
  <title>Cadastro</title>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center mt-5">
      <div class="col-lg-4 col-md-6">
        <h1 class="text-center">CADASTRE-SE</h1>
       
        <form method="post" action="user" class="mt-5" id="registerForm">
          <div class="form-group">
            <label for="fullName">Nome Completo *</label>
            <input
              type="text"
              required
              class="form-control"
              name="name"
              placeholder="Digite seu nome completo"
            />
          </div>
          <div class="form-group">
            <label for="email">E-mail *</label>
            <input
              type="email"
              required
              class="form-control"
              name="email"
              placeholder="Digite seu e-mail"
            />
          </div>
          <div class="form-group">
            <label for="password">Senha *</label>
            <input
              type="password"
              required
              class="form-control"
              name="password"
              placeholder="Digite sua senha"
            />
          </div>
          <a href="./login.jsp">Possui uma conta?</a>
          <div class="form-group text-center mt-3">
            <button
              type="submit"
              class="btn btn-block green-button"
              id="registerBtn"
            >
              CADASTRAR
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
