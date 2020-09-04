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
		<div class="titlePage">
			<h2>Affecter les groupes aux etudiants</h2>
		</div>
		
		<div class="tableDiv">
			<div class="titlePage">
				<h2>Choose le le groupe</h2>
			</div>
			<form action="AffecterLesEtudiants" method="get">
			<div class="row">
			<div class="col-md-10">
				<Select name="choix">
				<option value="">Select</option>
				<c:forEach var="e" items="${sessionScope.groupes}">
					<option  value="${e.getIdGroupe()}-${e.getFormation().getAbrFormation()}">G${e.getNumGroupe()} ${e.getFormation().getAbrFormation()}</option>
				</c:forEach>
				</Select>
			</div>
			<div class="col-md-2">
				<input type="submit" value="OK">
			</div>
			</div>
			</form>
			<div class="table-responsive">
					<form action="AffecterLesEtudiants" method="POST">
					<div class="titlePage">
						<h2>Faire les affectations</h2>
					</div>
						<table class="table table-hover">
							<tr>
								<th>num</th>
								<th>Etudiant</th>
								
							</tr>
							<c:forEach var = "i" begin = "1" end = "30">
							<tr>
								<td><c:out value="${i}" /></td>
								<td>
								<select name="${i}">
									<option value="">Aucan</option>
									<c:forEach var="e" items="${sessionScope.etudiants}">
										<option value="${e.getUsername()}">${e.getNom()} ${e.getPrenom()}</option>
									</c:forEach>
								</select>
								</td>
							</tr>
							</c:forEach>
						</table>
						<input type="submit" value="Enregsiter">
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
	<jsp:include page="/WEB-INF/template/footer.jsp" />