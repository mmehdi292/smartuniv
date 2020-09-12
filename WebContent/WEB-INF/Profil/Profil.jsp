<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Etudiant</title>
</head>

<body>
		<c:forEach var="r" items="${sessionScope.role.get(0)}">
				<c:choose>
					<c:when test="${ r eq 'Administrateur' }">
						<jsp:include page="/WEB-INF/template/sidebar.jsp" />
					</c:when>
					<c:when test="${ r eq 'Enseignent' }">
						<jsp:include page="/WEB-INF/template/ensSide.jsp" />
					</c:when>
					<c:when test="${ r eq 'ResponsableDeFormation' }">
						<jsp:include page="/WEB-INF/template/ResponsableSide.jsp" />
					</c:when>
					<c:when test="${ r eq 'ChefDepartement' }">
						<jsp:include page="/WEB-INF/template/ChefSidebar.jsp" />
					</c:when>
					
				</c:choose>
				
			
			</c:forEach>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modifier le profil</h2>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierProfileServlet" method="POST" id="mf" enctype="multipart/form-data">
				<h2>Profil de ${Enseignent.getNom()} ${Enseignent.getPrenom()}</h2>
				<div class="row">
					<div class="col-sm-6">
						<c:set var="image" value="${Enseignent.getPhoto()}"
							scope="session" />
						<img alt="profil" id="profil" src="getImage"> <label
							class="inputFile" for="photo">Modifier la photo</label> <input
							type="file" name="photo" id="photo" style="display: none">
					</div>
					<div class="col-sm-6">
						<h2>Les Roles:</h2>
						<c:forEach var="act" items="${sessionScope.userAction}">
							<label class="inputFile">${act}</label>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<label>Nom: </label> <input type="text"
							value="${Enseignent.getNom()}" class="modiferInput" name="nom">
					</div>
					<div class="col-sm-3">
						<label>Prénom: </label> <input type="text"
							value="${Enseignent.getPrenom()}" class="modiferInput"
							name="prenom">
					</div>
					<div class="col-sm-3">
						<label>Email: </label> <input type="text"
							value="${Enseignent.getEmail()}" class="modiferInput"
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
							value="${Enseignent.getDn()}" class="modiferInput" name="dn">
					</div>
					<div class="col-sm-3">
						<label>Lieu de naissance: </label> <input type="text"
							value="${Enseignent.getLn()}" class="modiferInput" name="ln">
					</div>
					<div class="col-sm-3">
						<label>Sexe: </label> <select name="sexe">
							<option value="${Enseignent.getSexe()}">${Enseignent.getSexe()}</option>
							<c:if test="${Enseignent.getSexe() eq 'Femme'}">
								<option value="Homme">Homme</option>
							</c:if>
							<c:if test="${Enseignent.getSexe() eq 'Homme'}">
								<option value="Femme">Femme</option>
							</c:if>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Adresse: </label>
						<textarea class="modiferInput" name="adresse">${Enseignent.getAdresse()}</textarea>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<label>Situation Familiale: </label> <select name="sf">
							<option value="${Enseignent.getSituationFamiliale()}">${Enseignent.getSituationFamiliale()}</option>
							<c:if test="${Enseignent.getSituationFamiliale() eq 'marié'}">
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Enseignent.getSituationFamiliale() eq 'divorcé'}">
								<option value="marié">marié</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Enseignent.getSituationFamiliale() eq 'séparé'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="célibataire">célibataire</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if
								test="${Enseignent.getSituationFamiliale() eq 'célibataire'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="veuf">veuf</option>
							</c:if>
							<c:if test="${Enseignent.getSituationFamiliale() eq 'veuf'}">
								<option value="marié">marié</option>
								<option value="divorcé">divorcé</option>
								<option value="séparé">séparé</option>
								<option value="célibataire">célibataire</option>
							</c:if>
						</select>
					</div>
					<div class="col-sm-6">
						<label>Grade: </label> <select name="grade">
							<option value="${Enseignent.getGarde()}">${Enseignent.getGarde()}</option>
							<c:if
								test="${Enseignent.getGarde() eq 'MaitreDeConférenceClasseA'}">
								<option value="MaitreDeConférenceClasseB">MaitreDeConférenceClasseB</option>
							</c:if>
							<c:if
								test="${Enseignent.getGarde() eq 'MaitreDeConférenceClasseB'}">
								<option value="MaitreDeConférenceClasseA">MaitreDeConférenceClasseA</option>
							</c:if>
						</select>
					</div>
				</div>
				<label>Les roles</label>
				<div class="row" align="center">
					<div class="col-sm-4">
					${Administrateur.getUsername() eq Enseignent.getUsername()}
						<label>administrateur
						<c:choose>
							
							<c:when test="${Administrateur.getUsername() eq Enseignent.getUsername()}">
								<input type="checkbox" value="admin" name="admin" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" value="admin" name="admin" >
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="col-sm-4">
						<label>chef département
							<c:choose>
							<c:when test="${ChefDepartement.getUsername() eq Enseignent.getUsername()}">
								<input type="checkbox" value="chef" name="chef" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" value="chef" name="chef" >
							</c:otherwise>
						</c:choose>
						</label>
					</div>
					<div class="col-sm-4">
						<label>Responsable
						<c:choose>
							<c:when test="${ResponsableDeFormation.getUsername() eq Enseignent.getUsername()}">
								<input type="checkbox" value="res" name="res" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" value="res" name="res">
							</c:otherwise>
						</c:choose>
						</label>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6" id="chefAction">
						<label><b>Nom de département</b></label> <select
							name="departement">
							<option value="TLSI">TLSI</option>
							<option value="IFA">IFA</option>
							<option value="MI">MI</option>
						</select>
					</div>
					<div class="col-sm-6" id="resAction">
						<label><b>Formation</b></label> <select name="formation">
							<option value="GL">GL</option>
							<option value="MI">MI</option>
							<option value="SCI">SCI</option>
							<option value="SI">SI</option>
							<option value="TI">TI</option>
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