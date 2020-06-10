

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Gestion Enseignants</title>
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
			<h2>Gestion des enseignants</h2>
			<button
				onclick="document.getElementById('id01').style.display='block'">
				<i class="fas fa-user-plus"></i>Ajouter enseignant
			</button>
		</div>
		<!--title page with add button end-->
		<!--add modal start-->
		<div id="id01" class="modal">

			<form class="modal-content animate" action="AjouterEnseignant"
				method="post" enctype="multipart/form-data">
				<div class="container1">
					<h2>Ajouter enseignant</h2>
					<label><b>Username</b></label> <input type="text"
						placeholder="Entrer Username" name="username" required> <label><b>Nom</b></label>
					<input type="text" placeholder="Entrer le nom" name="nom" required>

					<label><b>Prenom</b></label> <input type="text"
						placeholder="Entrer le prenom" name="prenom" required> <label><b>Email</b></label>
					<input type="email" placeholder="Entrer email" name="email"
						required> <label><b>Mot de pass</b></label> <input
						type="password" placeholder="Entrer le mot de pass"
						name="password" required> <label><b>Date de
							naissance</b></label> <input type="date" name="dn" required> <label><b>liue
							de naissance</b></label> <input type="text"
						placeholder="Entrer le liue de naissance" name="ln" required>

					<label><b>Sexe</b></label> <select name="sexe" required>
						<option value="Homme">Homme</option>
						<option value="Femme">Femme</option>
					</select> <label><b>Addresse</b></label>
					<textarea name="addresse"></textarea>

					<label><b>situation Familiale</b></label> <select name="sf">
						<option value="marié">marié</option>
						<option value="divorcé">divorcé</option>
						<option value="séparé">séparé</option>
						<option value="célibataire">célibataire</option>
						<option value="veuf">veuf</option>
					</select> <label><b>Photo</b></label> <input type="file" name="photo">

					<label><b>Grade</b></label> <select name="grade" required>
						<option value="MaitreDeConférenceClasseA">MaitreDeConférenceClasseA</option>
						<option value="MaitreDeConférenceClasseB">MaitreDeConférenceClasseB</option>
					</select>
					<center>
						<label><b>administrateur</b><input type="checkbox"
							value="admin" name="admin"></label> <label><b>chef de
								departemet</b><input type="checkbox" value="chef" name="chef"
							id="chef" onclick="checkRes"></label> <label><b>Responsable</b><input
							type="checkbox" value="res" name="res" id="res"></label>
					</center>
					<div id="chefAction">
						<label><b>Nom de departement </b></label> <select
							name="departement">
							<option value="TLSI">TLSI</option>
							<option value="IFA">IFA</option>
							<option value="MI">MI</option>
						</select>
					</div>

					<div id="resAction">
						<label><b>Formation</b></label> <select name="formation">
							<option value="GL">GL</option>
							<option value="MI">MI</option>
							<option value="SCI">SCI</option>
							<option value="SI">SI</option>
							<option value="TI">TI</option>
						</select>
					</div>


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
				<h3>les opetions de filtrage</h3>
				<input type="text" id="myInput" onkeyup="search()"
					placeholder="Recherche...">
				<div style="clear: both"></div>
				<div class="opt">
					<label>sexe <select onselect="">
							<option value="">Aucan</option>
							<option value="Homme">Homme</option>
							<option value="Femme">Femme</option>
					</select>
					</label> <label>situation Familiale <select onselect="">
							<option value="">Aucan</option>
							<option value="marié">marié</option>
							<option value="divorcé">divorcé</option>
							<option value="séparé">séparé</option>
							<option value="célibataire">célibataire</option>
							<option value="veuf">veuf</option>
					</select>
					</label> <label>Grade <select onselect="">
							<option value="">Aucan</option>
							<option value="MaitreDeConférenceClasseA">MaitreDeConférenceClasseA</option>
							<option value="MaitreDeConférenceClasseB">MaitreDeConférenceClasseB</option>
					</select>
					</label> <label>Departement <select onselect="">
							<option value="">Aucan</option>
							<option value="TLSI">TLSI</option>
							<option value="IFA">IFA</option>
							<option value="MI">MI</option>
					</select>
					</label> <label>Formation <select onselect="">
							<option value="">Aucan</option>
							<option value="GL">GL</option>
							<option value="MI">MI</option>
							<option value="SCI">SCI</option>
							<option value="SI">SI</option>
							<option value="TI">TI</option>
					</select>
					</label> <label>Module <select onselect="">
							<option value="">Aucan</option>
							<option value="GL">GL</option>
							<option value="MI">DAAW</option>
							<option value="SCI">TL</option>
							<option value="SI">POO</option>
							<option value="TI">ASD</option>
					</select>
					</label>
					</label> <label>Role <select onselect="">
							<option value="">Aucan</option>
							<option value="GL">Admin</option>
							<option value="MI">ENS</option>
							<option value="SCI">RES</option>
							<option value="SI">Chef</option>
					</select>
					</label>
				</div>
			</div>
			<!--filterage option end-->
			<!--table  start-->
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.enseignents}">
						<h1 style="text-align: center">la liste vide</h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>nom</th>
								<th>prenom</th>
								<th>username</th>
								<th>email</th>
								<th>date de naissance</th>
								<th>lieu de naissance</th>
								<th>sexe</th>
								<th>operation</th>
							</tr>

							<c:forEach var="ens" items="${sessionScope.enseignents}">
								<tr>
									<td><c:out value="${ens.getNom()}"></c:out></td>
									<td><c:out value="${ens.getPrenom()}"></c:out></td>
									<td><c:out value="${ens.getUsername()}"></c:out></td>
									<td><c:out value="${ens.getEmail()}"></c:out></td>
									<td><c:out value="${ens.getDn()}"></c:out></td>
									<td><c:out value="${ens.getLn()}"></c:out></td>
									<td><c:out value="${ens.getSexe()}"></c:out></td>
									<td><a
										href="ModifierActeur?type=enseignent&username=${ens.getUsername()}"><i
											class="fas fa-user-edit fa-lg"></i></a> <a
										href="SupprimerActeur?type=enseignent&username=${ens.getUsername()}"
										onclick="return(confirm('Etes-vous sûr de vouloir supprimer \n ${ens.getNom()} ${ens.getPrenom()}'));"><i
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
			<p>tous les droits sont réservés © 2020</p>
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
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<jsp:include page="/WEB-INF/template/footer.jsp" />