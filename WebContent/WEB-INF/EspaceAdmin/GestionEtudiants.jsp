

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Gestion des Etudiants</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Gestion des Etudiants</h2>
			<button
				onclick="document.getElementById('id01').style.display='block'">
				<i class="fas fa-user-plus"></i>Ajouter un etudiant
			</button>
		</div>
		<!--title page with add button end-->
		<!--add modal start-->
		<div id="id01" class="modal">

			<form class="modal-content animate" action="AjouterEtudiant"
				method="post" enctype="multipart/form-data">
				<div class="container1">
					<h2>Ajouter un etudiant</h2>
					<label><b>Nom d'utilisateur</b></label> <input type="text"
						placeholder="Entrer le nom d'utilisateur" name="username" required> <label><b>Nom</b></label>
					<input type="text" placeholder="Entrer le nom" name="nom" required>

					<label><b>Prénom</b></label> <input type="text"
						placeholder="Entrer le prénom" name="prenom" required> <label><b>Email</b></label>
					<input type="email" placeholder="Entrer l'email" name="email"
						required> <label><b>Mot de passe</b></label> <input
						type="password" placeholder="Entrer le mot de passe"
						name="password" required> <label><b>Date de
							naissance</b></label> <input type="date" name="dn" required> <label><b>Lieu
							de naissance</b></label> <input type="text"
						placeholder="Entrer le lieu de naissance" name="ln" required>

					<label><b>Sexe</b></label> <select name="sexe" required>
						<option value="Homme">Homme</option>
						<option value="Femme">Femme</option>
					</select> <label><b>Addresse</b></label>
					<textarea name="addresse"></textarea>

					<label><b>Situation Familiale</b></label> <select name="sf">
						<option value="marié">marié</option>
						<option value="divorcé">divorcé</option>
						<option value="séparé">séparé</option>
						<option value="célibataire">célibataire</option>
						<option value="veuf">veuf</option>
					</select> <label><b>Photo</b></label> <input type="file" name="photo">
					<label for="formation">Formation</label>
					<select id="formation" name="formation">
						<c:forEach var="f" items="${sessionScope.Formations}">
							<option value="${f.getAbrFormation()}">${f.getAbrFormation()}</option>
						</c:forEach>
					</select>
					<input type="submit" value="Ajouter" class="submitbtn"> <input
						type="reset" value="Supprimer content" class="cancelbtn">
					<input type="button" value="Cancel"
						onclick="document.getElementById('id01').style.display='none'"
						class="cancelbtn">
				</div>


			</form>
		</div>
		<!--add modal start-->
		<!--table start-->
		<div class="tableDiv">
			<!--filterage option start-->
			<div class="text">
				<h3>Filtrer</h3>
				<input type="text" id="myInput" onkeyup="search()"
					placeholder="Recherche...">
				<div style="clear: both"></div>
				<div class="opt">
					<label>Sexe <select onselect="">
							<option value="">Aucan</option>
							<option value="Homme">Homme</option>
							<option value="Femme">Femme</option>
					</select>
					</label> <label>Situation Familiale <select onselect="">
							<option value="">Aucun</option>
							<option value="marié">marié</option>
							<option value="divorcé">divorcé</option>
							<option value="séparé">séparé</option>
							<option value="célibataire">célibataire</option>
							<option value="veuf">veuf</option>
					</select>
					</label> <label>Département <select onselect="">
							<option value="">Aucun</option>
							<option value="TLSI">TLSI</option>
							<option value="IFA">IFA</option>
							<option value="MI">MI</option>
					</select>
					</label> <label>Formation <select onselect="">
							<option value="">Aucun</option>
							<option value="GL">GL</option>
							<option value="MI">MI</option>
							<option value="SCI">SCI</option>
							<option value="SI">SI</option>
							<option value="TI">TI</option>
					</select>
					</label> <label>Module <select onselect="">
							<option value="">Aucun</option>
							<option value="GL">GL</option>
							<option value="MI">DAAW</option>
							<option value="SCI">TL</option>
							<option value="SI">POO</option>
							<option value="TI">ASD</option>
					</select>
					</label>
				</div>
			</div>
			<!--filterage option end-->
			<!--table  start-->
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.Etudiants}">
						<h1 style="text-align: center">la liste vide</h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Nom d'utilisateur</th>
								<th>Email</th>
								<th>Date de naissance</th>
								<th>Lieu de naissance</th>
								<th>Sexe</th>
								<th>Opération</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.Etudiants}">
								<tr>
									<td><c:out value="${et.getNom()}"></c:out></td>
									<td><c:out value="${et.getPrenom()}"></c:out></td>
									<td><c:out value="${et.getUsername()}"></c:out></td>
									<td><c:out value="${et.getEmail()}"></c:out></td>
									<td><c:out value="${et.getDn()}"></c:out></td>
									<td><c:out value="${et.getLn()}"></c:out></td>
									<td><c:out value="${et.getSexe()}"></c:out></td>
									<td><a
										href="ModifierActeur?type=etudiant&username=${et.getUsername()}"><i
											class="fas fa-user-edit fa-lg"></i></a> <a
										href="SupprimerActeur?type=etudiant&username=${et.getUsername()}"
										onclick="return(confirm('Etes-vous sûr de vouloir supprimer \n ${et.getNom()} ${et.getPrenom()}'));"><i
											class="fas fa-trash-alt fa-lg"></i></a></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
				<!--table end-->
			</div>
			<!--table end-->
			<!--pagination start-->
			<center>
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
				<!--pagination end-->
			</center>
		</div>
		<!--footer start-->
		<div class="footer">
			<p>Tous droits réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<script>
		var modal = document.getElementById('id01');
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<jsp:include page="/WEB-INF/template/footer.jsp" />