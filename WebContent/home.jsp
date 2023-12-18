<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="lista.css">
<title>Home</title>
</head>

<body>
	<div class="container">
		<div class="row justify-content-center mt-9 ">
			<div class="col-lg-4 col-md-6">

				<button type="submit" class="btn btn-block green-button"
					id="loginBtn">Transações</button>
				<h3 class="text-center">
					SALDO TOTAL: <span classe="span">R$ ${saldoTotal}</span>
				</h3>


				<div class="container text-center">
					<div class="row">
						<div class="col">Titulo</div>
						<div class="col">Descrição</div>
						<div class="col">Valor</div>
						<div class="col">Categoria</div>
					</div>
					<c:if test="${not empty transactions}">
						<c:forEach var="transaction" items="${transactions}">
							<div class="row">
								<div class="col">${transaction.title}</div>
								<div class="col">${transaction.description}</div>
								<div class="col">${transaction.value}</div>
								<div class="col">${transaction.category.name}</div>
							</div>
						</c:forEach>
					</c:if>
					
				</div>
				<form method="post" action="CategoryServlet" >
					<button type="submit" class="btn btn-block green-button"
						id="loginBtn">ADICIONAR TRANSAÇÃO</button>
				</form>

			</div>

		</div>

	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>