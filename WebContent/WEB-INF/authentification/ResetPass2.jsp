<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Récupération de mot de passe</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">

</head>

<body>
		<div class="centerdiv">
			<div class="card-group">
				<div class="card">
					<img class="img"
						src="${pageContext.request.contextPath}/IMG-Template/logo.png"
						alt="logo">
					<h5 class="card-title center">Université Constantine 2</h5>
					<h5 class="card-title center">ABD EL HAMID MAHRI</h5>
					<h5 class="card-title center">Système De Gestion des Absences</h5>
					<p class="card-text center">Tous droits réservés © 2020</p>
				</div>
				<div class="card colorA">
					<form>
						<div class="form-group">
							<select id="language" class="form-control">
								<option value="Française" selected>Française</option>
								<option value="Anglaise">Anglais</option>
								<option value="Arabe">Arabe</option>
							</select>
						</div>
					</form>
					<h1 class="card-title">Récupération de mot de passe</h1>
					<form action="Reset" method="POST">
						<div class="form-group">
							<label
								for="Password">Votre mot de passe</label> <input type="password"
								id="Password" name="password" class="form-control"
								placeholder="Entrez votre mot de passe">
							<label
								for="Password1">Confirmer votre mot de passe</label> <input type="password"
								id="Password1"  class="form-control"
								placeholder="Entrez la confirmation votre mot de passe">
							<button type="submit" class="btn btn-primary  btn-1">Récupérer</button>
						</div>
					</form>
				</div>
		</div>
	</div>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
</body>

</html>