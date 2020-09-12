<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Etudiant </title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/etudiantSide.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>L'emploi de temps</h2>
		</div>
		<div class="tableDiv">

			<div class="table-responsive">
				
						<table class="table table-hover">
							<tr><td></td><th><c:out value="08h30-10h00"></c:out></th><th><c:out value="10h00-11h30"></c:out></th><td><c:out value="11h30-13h00"></c:out></th><th><c:out value="13h00-14h30"></c:out></th><th><c:out value="14h30-16h00"></c:out></th></tr>
			<tr><th><c:out value="Dimanche"></c:out></th><td><c:out value="${sessionScope.listETmp[0]}"></c:out></td><td><c:out value="${sessionScope.listETmp[1]}"></c:out></td><td><c:out value="${sessionScope.listETmp[2]}"></c:out></td><td><c:out value="${sessionScope.listETmp[3]}"></c:out></td><td><c:out value="${sessionScope.listETmp[4]}"></c:out></td></tr>
			<tr><th><c:out value="Lundi"></c:out></th><td><c:out value="${sessionScope.listETmp[5]}"></c:out></td><td><c:out value="${sessionScope.listETmp[6]}"></c:out></td><td><c:out value="${sessionScope.listETmp[7]}"></c:out></td><td><c:out value="${sessionScope.listETmp[8]}"></c:out></td><td><c:out value="${sessionScope.listETmp[9]}"></c:out></td></tr>
			<tr><th><c:out value="Mardi"></c:out></th><td><c:out value="${sessionScope.listETmp[10]}"></c:out></td><td><c:out value="${sessionScope.listETmp[11]}"></c:out></td><td><c:out value="${sessionScope.listETmp[12]}"></c:out></td><td><c:out value="${sessionScope.listETmp[13]}"></c:out></td><td><c:out value="${sessionScope.listETmp[14]}"></c:out></td></tr>
			<tr><th><c:out value="Mercredi"></c:out></th><td><c:out value="${sessionScope.listETmp[15]}"></c:out></td><td><c:out value="${sessionScope.listETmp[16]}"></c:out></td><td><c:out value="${sessionScope.listETmp[17]}"></c:out></td><td><c:out value="${sessionScope.listETmp[18]}"></c:out></td><td><c:out value="${sessionScope.listETmp[19]}"></c:out></td></tr>
			<tr><th><c:out value="Jeudi"></c:out></th><td><c:out value="${sessionScope.listETmp[20]}"></c:out></td><td><c:out value="${sessionScope.listETmp[21]}"></c:out></td><td><c:out value="${sessionScope.listETmp[22]}"></c:out></td><td><c:out value="${sessionScope.listETmp[23]}"></c:out></td><td><c:out value="${sessionScope.listETmp[24]}"></c:out></td></tr>
		
						</table>
	
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









		