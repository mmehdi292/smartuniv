<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Justification</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/ChefSidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>La justification</h2>
		</div>
		<div class="tableDiv">
				<div class="row">
					<div class="col-sm-6">
						<c:set var="image" value="${abs.getJustification()}" scope="session" />
						<img alt="Justification" id="profil" src="getImage"> 
						<td><a class="btn btn-success" href="accepterRefuserJustification?action=accepter&idAbsence=${idAbsence}">Accepter</a></td>
						<td><a class="btn btn-success" href="accepterRefuserJustification?action=refuser&idAbsence=${idAbsence}">Refuser</a></td>
					
					</div>
				</div>
		</div>
		<!--footer start-->
		<div class="footer">
			<p>Tous droits réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />












































<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
  //cette page recupérer from la servlet (detailleJustification)  les information d'absence choisi dans l'attribute (abs)
  //
  //Afficher la justification + ces détailles  et 2 boutons (accepter et refuser) 
  // créer 2 variables string:  String var1=accepter, String var2=refuser
  // si le chef clique sur accepter  envoyé var1  sinon var2
  
  //la servlet(accepterRefuserJustification) recupérer le string(action) -----
%>



</body>
</html>
























