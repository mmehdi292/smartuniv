<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Enseignant </title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/ensSide.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Faire l'appel</h2>
		</div>
		<div class="tableDiv">
			<form method="get">
			<div class="row">
			<div class="col-md-10">
				<Select name="choix">
				<c:forEach var="e" items="${sessionScope.emg}">
					<option  value="${e.getGroupe().getIdGroupe()}-${e.getModule().getAbrModule()}-${e.getType()}">${e.getModule().getAbrModule()}|Groupe ${e.getGroupe().getNumGroupe()} ${e.getGroupe().getFormation().getAbrFormation()} ${e.getType()}</option>
				</c:forEach>
				</Select>
			</div>
			<div class="col-md-2">
				<input type="submit" value="OK">
			</div>
			</div>
			</form>
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.Seances}">
						<h1 style="text-align: center">La liste vide</h1>
					</c:when>
					<c:otherwise>

						<table class="table table-hover">
							<tr>
								<th>Id séance</th>
								<th>type</th>
								<th>temp</th>
								<th>salle</th>
								<th>avoir Absence</th>
								<th>Operation</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.Seances}">
								<tr>
									<td><c:out value="${et.getIdSeance()}"></c:out></td>
									<td><c:out value="${et.getType()}"></c:out></td>
									<td><fmt:formatDate value="${et.getTemp()}" type="date" pattern="yyyy/MM/dd hh:mm"/></td>
									<td><c:out value="${et.getSalle()}"></c:out></td>
									<td><c:out value="${et.isAvoirAbs()}"></c:out></td>
									<jsp:useBean id="now" class="java.util.Date"/>
									<fmt:formatDate var="date" value="${now}" pattern="yyyy/MM/dd" />
									<fmt:formatDate var="date1" value="${et.getTemp()}" pattern="yyyy/MM/dd" />
									<c:choose>
										<c:when test="${date1 lt date}">
											<td><a class="btn btn-success" href="ModiferReleve?idgroupe=${sessionScope.groupe}&idseance=${et.getIdSeance()}">Modifier l'releve d'absences</a></td>
										</c:when>
										<c:otherwise>
											<td><a class="btn btn-success" href="appeleSeance?idgroupe=${sessionScope.groupe}&idseance=${et.getIdSeance()}">Faire l'appel</a></td>
										</c:otherwise>
									</c:choose>
									
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