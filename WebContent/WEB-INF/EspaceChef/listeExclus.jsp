<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Chef Departement</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/ChefSidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="tableDiv">
		
		<div class="titlePage">
			<h2>La liste des exclus</h2>
		</div>
			<c:choose>
				<c:when test="${ empty sessionScope.listExclus}">
					<h1 style="text-align: center">la liste vide</h1>
				</c:when>
				<c:otherwise>
		
						<table class="table table-hover">
							<tr><th>N°</th>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Type de séance/Module</th>
								<th>Groupe</th>
								<th>Formation</th></tr>
								
							<c:set var="i" value="${1}"></c:set>
							<c:forEach var="list" items="${sessionScope.listExclus}">
								<tr><td><c:out value="${i}"></c:out></td>
									<td><c:out value="${list.getEtudiants().getNom()}"></c:out></td>
									<td><c:out value="${list.getEtudiants().getPrenom()}"></c:out></td>
									<td><c:out value="${list.getSeance().getType()} "></c:out>
										<c:out value="${list.getSeance().getModule().getAbrModule()}"></c:out></td>
									<td><c:out value="${list.getSeance().getGroupe().getIdGroupe()}"></c:out></td>
									<td><c:out value="${list.getEtudiants().getFormation().getAbrFormation()}"></c:out></td></tr>
									
								<c:set var="i" value="${i+1}"></c:set>	
							</c:forEach>	
						
						</table>
				</c:otherwise>
			</c:choose>
			<!--table end-->
		</div>
		
		
		<!--footer start-->
		<div class="footer">
			<p>tous les droits sont réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />