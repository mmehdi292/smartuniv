<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Chef département </title>
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
			<h2>Enregistrer justification | La liste des absences</h2>
		</div>
		<div class="tableDiv">
			
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.absences}">
						<h1 style="text-align: center">La liste vide</h1>
					</c:when>
					<c:otherwise>
					<form action="EtudiantAbsencentParNom?role=chef" method="POST"  enctype="multipart/form-data">
						<table class="table table-hover">
							<tr>
								<th>Id Absence</th>
								<th>Module</th>
								<th>Type</th>
								<th>Date et heure de séance</th>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Justifier</th>
								<th>Etat de justification</th>
								<th>Action</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.absences}">
								<tr>
									<td><c:out value="${et.getIdAbsence()}"></c:out></td>
									<td><c:out value="${et.getSeance().getModule().getAbrModule()}"></c:out></td>
									<td><c:out value="${et.getSeance().getType()} "></c:out></td>
									<td><fmt:formatDate value="${et.getSeance().getTemp()}" type="date" pattern="yyyy/MM/dd hh:mm"/></td>
									<td><c:out value="${et.getEtudiants().getNom()}"></c:out></td>
									<td><c:out value="${et.getEtudiants().getPrenom()}"></c:out></td>
									<c:choose>
										<c:when test="${et.getJustification() eq null}"><td><c:out value="Pas de justification"></c:out></td></c:when>
										<c:otherwise><td><c:out value="il y a une justification"></c:out></td></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${et.isJustifier() eq true}"><td><c:out value="justification accepter"></c:out></td></c:when>
										<c:when test="${et.getJustification() eq null}"><td><c:out value="Pas de justification"></c:out></td></c:when>
										<c:otherwise><td><c:out value="en cours de vérification"></c:out></td></c:otherwise>
									</c:choose>
									<td><input type="file" name="${et.getIdAbsence()}"></td>
								</tr>
							</c:forEach>
						</table>
						<input type="submit"  value="Enregistrer les justifications">
					</c:otherwise>
					</c:choose>
					
				</form>
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