<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="style.css">
  <title>Cadastro de Transação</title>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center mt-5 ">
      <div class="col-lg-4 col-md-6">
        <h1 class="text-center">CADASTRO DE TRANSAÇÃO</h1>
        <form action="TrasactionCadastroServlet" method="POST" class="mt-5" id="transactionForm">
          <div class="form-group">
            <label for="title">Título *</label>
            <input
              type="text"
              required
              class="form-control"
              name="title"
              placeholder="Digite o título"
            />
          </div>
          <div class="form-group">
            <label for="description">Descrição (opcional)</label>
            <textarea
              class="form-control"
              name="description"
              placeholder="Digite a descrição"
            ></textarea>
          </div>
          <div class="form-group">
            <label for="value">Valor *</label>
            <input
              type="number"
              required
              class="form-control"
              name="value"
              placeholder="Digite o valor"
            />
          </div>
          <div class="form-group">
            <label for="category">Selecione uma categoria *</label>
            <select
              class="form-control"
              name="category"
              required
            >
              <option value="" disabled selected>Escolha uma categoria</option>
              
              	<c:if test="${not empty categories}">
			        <!-- Itera sobre as categorias apenas se a lista não estiver vazia -->
			        <c:forEach var="category" items="${categories}">
			            <option value="${category.id}">${category.name}</option>
			        </c:forEach>
			    </c:if>
            </select>
          </div>
          <div class="form-group text-center mt-3">
            <button
              type="submit"
              class="btn btn-block green-button"
              id="submitTransactionBtn"
            >
              CADASTRAR TRANSAÇÃO
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
