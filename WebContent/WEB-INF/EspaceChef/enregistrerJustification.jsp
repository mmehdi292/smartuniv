<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace chef département </title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/ChefSidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Enregistrer une Justification</h2>
		</div>
		<div class="tableDiv">
			<div class="row">
				<div class="col-md-5">
				<div class="titlePage">
					<h2>Par Séance</h2>
				</div>
			</div>
			<div class="col-md-7">
				<div class="titlePage">
					<h2>Par Nom et Prénom</h2>
				</div>
			</div>
			</div>
			<form action="EnregistrerJustificationParChef" method="get">
			<div class="row">
			<div class="col-md-4">
			<c:choose>
					<c:when test="${ empty sessionScope.emg}">
						<h2 style="text-align: center">Aucune absence non justifiée dans les groupes de votre département</h2>
						<h3 style="text-align: center">Vous pouvez seulement modifier les justifications précédentes</h3>
						
					</c:when>
					<c:otherwise>
						<Select name="choix">
						<c:forEach var="e" items="${sessionScope.emg}">
							<option  value="${e.getGroupe().getIdGroupe()}-${e.getModule().getAbrModule()}-${e.getType()}">Groupe ${e.getGroupe().getNumGroupe()} ${e.getGroupe().getFormation().getAbrFormation()} ${e.getType()} ${e.getModule().getAbrModule()}</option>
						</c:forEach>
						</Select>
			</div>
			<div class="col-md-1">
				<input type="submit" value="OK">
			</div>
					</c:otherwise>
			</c:choose>
			
			</form>
			
			<form action="EnregistrerJustificationParChef" method="post">
				<div class="col-md-3"><input type="text" name="nom" placeholder="Entrer le nom"></div>
				<div class="col-md-3"><input type="text" name="prenom" placeholder="Entrer le prénom"></div>
				<div class="col-md-1"><input type="submit" value="OK"></div>
				
			</form>
			</div>
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.Seances}">
						<h1 style="text-align: center"></h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>Id séance</th>
								<th>Type</th>
								<th>Date et heure de séance</th>
								<th>Salle</th>
								<th>Avoir Absence</th>
								<th>Opération</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.Seances}">
								<tr>
									<td><c:out value="${et.getIdSeance()}"></c:out></td>
									<td><c:out value="${et.getType()}"></c:out></td>
									<td><fmt:formatDate value="${et.getTemp()}" type="date" pattern="yyyy/MM/dd hh:mm"/></td>
									<td><c:out value="${et.getSalle()}"></c:out></td>
									<td><c:out value="${et.isAvoirAbs()}"></c:out></td>
									<td><a class="btn btn-success" href="EtudiantAbsencent?idgroupe=${sessionScope.groupe}&idseance=${et.getIdSeance()}&role=chef">Voir la liste des étudiants</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
				
			</div>
			<!--table end-->
		</div>
		
		<!--footer start-->
		<div class="footer">
			<p>Tous droits réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />