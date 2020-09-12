<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Etudiant</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modification d'Etudiant</h2>
			<a href="GestionEtudiant"> <i class="fas fa-users"></i>
				consulter les Etudiants
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierEtudiant" method="POST" id="mf" enctype="multipart/form-data">
				<h2>Profil de ${Etudiant.getNom()} ${Etudiant.getPrenom()} (${Etudiant.getUsername()})</h2>
				<div class="row">
					<div class="col-sm-6">
						<c:set var="image" value="${Etudiant.getPhoto()}"
							scope="session" />
						<img alt="profil" id="profil" src="getImage"> <label
							class="inputFile" for="photo">Modifier Photo</label> <input
							type="file" name="photo" id="photo" value="${Etudiant.getPhoto()}" style="display: none">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<label>Nom: </label> <input type="text"
							value="${Etudiant.getNom()}" class="modiferInput" name="nom">
					</div>
					<div class="col-sm-3">
						<label>Prénom: </label> <input type="text"
							value="${Etudiant.getPrenom()}" class="modiferInput"
							name="prenom">
					</div>
					<div class="col-sm-3">
						<label>Email: </label> <input type="text"
							value="${Etudiant.getEmail()}" class="modiferInput"
							name="email">
					</div>
					<div class="col-sm-3">
						<label>Mot de passe: </label> <input type="Password"
							class="modiferInput" name="password">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-3">
						<label>Date de naissance: </label> <input type="date"
							value="${Etudiant.getDn()}" class="modiferInput" name="dn">
					</div>
					<div class="col-sm-3">
						<label>Lieu de naissance: </label> <input type="text"
							value="${Etudiant.getLn()}" class="modiferInput" name="ln">
					</div>
					<div class="col-sm-3">
						<label>Sexe: </label> <select name="sexe">
							<option value="${Etudiant.getSexe()}">${Etudiant.getSexe()}</option>
							<c:if test="${Etudiant.getSexe() eq 'Femme'}">
								<option value="Homme">Homme</option>
							</c:if>
							<c:if test="${Etudiant.getSexe() eq 'Homme'}">
								<option value="Femme">Femme</option>
							</c:if>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Adresse: </label>
						<textarea class="modiferInput" name="adresse">${Etudiant.getAdresse()}</textarea>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<label>Situation Familiale: </label> <select name="sf">
							<option value="${Etudiant.getSituationFamiliale()}">${Etudiant.getSituationFamiliale()}</option>
							<c:if test="${Etudiant.getSituationFamiliale() eq 'marié'}">
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Etudiant.getSituationFamiliale() eq 'divorcé'}">
								<option value="marié">marié</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Etudiant.getSituationFamiliale() eq 'séparé'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if
								test="${Etudiant.getSituationFamiliale() eq 'célibataire'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Etudiant.getSituationFamiliale() eq 'veuf'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
							</c:if>
						</select>
					</div>
					<div class="col-sm-6">
						<label for="formation">Formation</label>
					<select id="formation" name="formation">
					
						<c:forEach var="f" items="${sessionScope.Formations}">
							<c:choose>
								<c:when test="${Etudiant.getFormation().getAbrFormation() eq f.getAbrFormation()}">
									<option value="${f.getAbrFormation()}" selected>${f.getAbrFormation()}</option>
								</c:when>
								<c:otherwise>
									<option value="${f.getAbrFormation()}">${f.getAbrFormation()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="row" align="center">
					<div class="col-sm-12">
						<input class="mbtn" type="submit" value="modifier">
					</div>
				</div>

			</form>
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
		var chef = document.getElementById('chefAction');
		var res = document.getElementById('resAction');
		document.getElementById('chef').addEventListener('click', checkChef);
		document.getElementById('res').addEventListener('click', checkRes);
		document.getElementById('photo').addEventListener('click', changePhoto);
		
		function changePhoto() {
			var x = document.getElementById('profil');
			var y = document.getElementById('photo');
			if (x.getAttribute("src") == null) {
				x.setAttribute("src", 'https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260');
			}
			if (y.getAttribute("value") != null) {
				x.setAttribute("src", y.value);
			}
		}
		function checkChef() {
			if (document.getElementById('chef').checked) {
				chef.style.display = "block";
			} else {
				chef.style.display = "none";
			}
		}
		function checkRes() {
			if (document.getElementById('res').checked) {
				res.style.display = "block";
			} else {
				res.style.display = "none";
			}
		}
	</script>
	<jsp:include page="/WEB-INF/template/footer.jsp" />