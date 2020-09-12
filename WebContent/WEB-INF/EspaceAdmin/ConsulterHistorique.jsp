

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Consulter l'Historique</title>
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
			<h2>Consultation de l'historique</h2>
		</div>
		<!--title page with add button end-->
		<!--table start-->
		<div class="tableDiv">
			<!--filterage option start-->
			<div class="text">
				<h3>Filtrer</h3>
				<input type="text" id="myInput" onkeyup="search()"
					placeholder="Recherche...">
				<div style="clear: both"></div>
				<div class="opt">
					<label>sexe <select onselect="">
							<option value="">Aucun</option>
							<option value="Homme">Homme</option>
							<option value="Femme">Femme</option>
					</select>
					</label> <label>situation Familiale <select onselect="">
							<option value="">Aucun</option>
							<option value="marié">marié</option>
							<option value="divorcé">divorcé</option>
							<option value="séparé">séparé</option>
							<option value="célibataire">célibataire</option>
							<option value="veuf">veuf</option>
					</select>
					</label> <label>Grade <select onselect="">
							<option value="">Aucun</option>
							<option value="MaitreDeConférenceClasseA">MaitreDeConférenceClasseA</option>
							<option value="MaitreDeConférenceClasseB">MaitreDeConférenceClasseB</option>
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
					</label> <label>Role <select onselect="">
							<option value="">Aucun</option>
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
					<c:when test="${ empty sessionScope.Historiques}">
						<h1 style="text-align: center">La liste vide</h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>Id Historique</th>
								<th>Nom d'utilisateur</th>
								<th>Date</th>
								<th>Action</th>
							</tr>

							<c:forEach var="ens" items="${sessionScope.Historiques}">
								<tr>
									<td><c:out value="${ens.getIdHistorique()}"></c:out></td>
									<td><c:out value="${ens.getUsername()}"></c:out></td>
									<td><fmt:formatDate type="time" value="${ens.getDate()}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									<td><c:out value="${ens.getAction()}"></c:out></td>
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