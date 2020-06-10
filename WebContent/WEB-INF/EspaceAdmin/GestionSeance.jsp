

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Gestion Seances</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<div class="titlePage">
		<div class="alert alert-info">
						<strong>Faites attention!</strong> Attention tu dois m'assurer d'inclure tous les jours fériés, les vacons et les evenments tout au long de l'année <a href="GestionVacance">Gestion des jours fériés</a>			
					</div>
					</div>
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Gestion des Seances</h2>
			<button
				onclick="document.getElementById('id01').style.display='block'">
				<i class="fas fa-user-plus"></i>Ajouter Seances
			</button>
		</div>
		<!--title page with add button end-->
		<!--add modal start-->
		<div id="id01" class="modal">

			<form class="modal-content animate" action="AjouterSeance"
				method="post">
				<div class="container1">
					<h2>Ajouter Seances</h2>
					<label for="type"><b>Type :</b></label>
					<select id="type" name="type">
						<option value="TD">TD</option>
						<option value="TP">TP</option>
						<option value="COUR">COUR</option>
					</select>
					
					<label for="nbr"><b>Nomber des seance par semain :</b></label>
					<select id="nbr" name="nbr">
						<option value="1" selected>1</option>
						<option value="2">2</option>
					</select>
					
					<label for="total"><b>Nombre des seance totale: </b></label>
					<input type="number" id="total" name="total" >
					
					<fieldset>
						<legend id = "s1Title">Seance 1</legend>
					<label for="date"><b>Date: </b></label>
					<input type="Date" id="date" name="date" required>
					
					<label for="heur"><b>Heur: </b></label>
					<input type="number" id="heur" name="heur" min="0" max="23" required>
					
					<label for="min"><b>min: </b></label>
					<input type="number" id="min" name="min" min="0" max="59" required>
					
					<label for="salle"><b>salle: </b></label>
					<input type="number" id="salle" name="salle" required>
					</fieldset>
					<fieldset id="s2">
						<legend >Seance 2</legend>
					<label for="date2"><b>Date: </b></label>
					<input type="Date" id="date2" name="date2" >
					
					<label for="heur2"><b>Heur: </b></label>
					<input type="number" id="heur2" name="heur2" min="0" max="23">
					
					<label for="min2"><b>min: </b></label>
					<input type="number" id="min2" name="min2" min="0" max="59">
					
					<label for="salle2"><b>salle: </b></label>
					<input type="number" id="salle2" name="salle2">
					</fieldset>
					<label for="abs">Avoir Absence</label>
					<select id="abs" name="abs">
						<option value="OUI" selected>OUI</option>
						<option value="NO">NO</option>
					</select>
					
					<label for="module">Module:</label>
					<select id="module" name="module">
						<c:forEach var="f" items="${sessionScope.Modules}">
							<option value="${f.getAbrModule()}">${f.getAbrModule()}</option>
						</c:forEach>
					</select>
					
					<label for="groupe">Groupe:</label>
					<select id="groupe" name="groupe">
						<c:forEach var="f" items="${sessionScope.Groupes}">
							<option value="${f.getIdGroupe()}">${f.getIdGroupe()} ${f.getNumGroupe()} ${f.getFormation().getAbrFormation()}</option>
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
				</div>
			</div>
			<!--filterage option end-->
			<!--table  start-->
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.Seances}">
						<h1 style="text-align: center">la liste vide</h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>idSeance</th>
								<th>type</th>
								<th>temp</th>
								<th>salle</th>
								<th>avoirAbs</th>
								<th>idGroupe</th>
								<th>abrModule</th>
								<th>Operation</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.Seances}">
								<tr>
									<td><c:out value="${et.getIdSeance()}"></c:out></td>
									<td><c:out value="${et.getType()}"></c:out></td>
									<td><fmt:formatDate type="time" value="${et.getTemp()}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td><c:out value="${et.getSalle()}"></c:out></td>
									<td><c:out value="${et.isAvoirAbs()}"></c:out></td>
									<td><c:out value="${et.getGroupe().getIdGroupe()}"></c:out></td>
									<td><c:out value="${et.getModule().getAbrModule()}"></c:out></td>
									<td><a
										href="Modifier?type=seance&abr=${et.getIdSeance()}"><i
											class="fas fa-user-edit fa-lg"></i></a> <a
										href="Supprimer?type=seance&abr=${et.getIdSeance()}"
										onclick="return(confirm('Etes-vous sûr de vouloir supprimer \n ${et.getIdSeance()}'));"><i
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
		var titleS1 = document.getElementById("s1Title");
		var s2 = document.getElementById("s2");
		titleS1.style.display='none';
		s2.style.display='none';
		document.getElementById('nbr').addEventListener('change', nbrSeance);
		function nbrSeance(){
			if(document.getElementById('nbr').value == "2"){
				titleS1.style.display='block';
				s2.style.display='block';
			}
			else{
				titleS1.style.display='none';
				s2.style.display='none';
			}
		}
		var modal = document.getElementById('id01');
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<jsp:include page="/WEB-INF/template/footer.jsp" />