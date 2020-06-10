<jsp:include page="/WEB-INF/template/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>S'authentifier</title>
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
					<p class="card-text center">© 2020 tous les droites sont
						reserver</p>
				</div>
				<div class="card colorA">
					<form>
						<div class="form-group">
							<select id="language" class="form-control">
								<option value="Française" selected>Française</option>
								<option value="Anglaise">Anglaise</option>
								<option value="Arabe">Arabe</option>
							</select>
						</div>
					</form>
					<h1 class="card-title">S'authentifier</h1>
					<form action="Login" method="POST">
						<div class="form-group">
							<label class="text-center" for="user">Votre nom d'utilisateur ou e-mail</label> <input
								type="text" id="user" name="user" class="form-control"
								placeholder="Entrez votre nom d'utilisateur ou email"> <label
								for="Password">Votre mot de passe</label> <input type="password"
								id="Password" name="password" class="form-control"
								placeholder="Entrez votre mot de passe">
							<p class="card-text right"><a href="Goto?page=Reset">Mot de passe oublié?</a></p>
							<button type="submit" class="btn btn-primary  btn-1">se
								connecter</button>
						</div>
					</form>
				</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/template/footer.jsp" />