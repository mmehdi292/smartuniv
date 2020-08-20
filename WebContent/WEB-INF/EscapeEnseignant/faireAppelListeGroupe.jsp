<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Enseignent </title>
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
			<h2>Faire l'appel | liste de groupe</h2>
		</div>
		<div class="tableDiv">
			<div class="titlePage">
			<h2>Note: coches les presents</h2>
			</div>
			
			<div class="table-responsive">
				<c:choose>
					<c:when test="${ empty sessionScope.list}">
						<h1 style="text-align: center">la liste vide</h1>
					</c:when>
					<c:otherwise>
					<form action="appeleSeance" method="POST">
						<table class="table table-hover">
							<tr>
								<th>Nom</th>
								<th>Prenom</th>
								<th>action</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.list}">
								<tr>
									<td><c:out value="${et.getNom()}"></c:out></td>
									<td><c:out value="${et.getPrenom()}"></c:out></td>
									<c:choose>
										<c:when test="${empty sessionScope.absence}">
											<td><input type="checkbox" name="${et.getUsername()}" ></td>
										</c:when>
										<c:otherwise>
											<% boolean b = false; %>
											<c:forEach var="ab" items="${sessionScope.absence}">
												<c:if test="${ab.getEtudiants().getUsername() eq et.getUsername()}">
													<td><input type="checkbox" name="${et.getUsername()}" ></td>
													<% b= true; %>
												</c:if>
											</c:forEach>
											<% if(b == false) {%>
												<td><input type="checkbox" name="${et.getUsername()}" checked="checked" ></td>
											<%} %>
										</c:otherwise>
									</c:choose>
									
									
									
									

									
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
				<input type="submit"  value="Enrigster les absences">
				</form>
			</div>
			<!--table end-->
		</div>
		
		<!--footer start-->
		<div class="footer">
			<p>tous les droits sont réservés © 2020</p>
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