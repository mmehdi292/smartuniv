<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h2>Enregistrer justification | La liste des absences</h2>
		</div>
		<div class="tableDiv">
			<div class="titlePage">
			<h2>Les étudiants absents</h2>
			</div>
			
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.absences}">
						<h1 style="text-align: center">La liste vide</h1>
					</c:when>
					<c:otherwise>
					<form action="EtudiantAbsencent" method="POST"  enctype="multipart/form-data">
						<table class="table table-hover">
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Télécharger une justification</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.absences}">
								<tr>
									<td><c:out value="${et.getEtudiants().getNom()}"></c:out></td>
									<td><c:out value="${et.getEtudiants().getPrenom()}"></c:out></td>
									<td><input type="file" name="${et.getIdAbsence()}"></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
					</c:choose>
					
					<input type="submit"  value="Enregistrer">
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
	<jsp:include page="/WEB-INF/template/footer.jsp" /><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>