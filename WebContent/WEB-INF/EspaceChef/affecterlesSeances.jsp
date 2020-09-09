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
			<h2>Affecter les seances aux ensignants</h2>
		</div>
		
		<div class="tableDiv">
			<div class="titlePage">
				<h2>Choose le module</h2>
			</div>
			<form action="AffecterLesSeance" method="get">
			<div class="row">
			<div class="col-md-10">
				<Select name="choix">
				<option value="">Select</option>
				<c:forEach var="e" items="${sessionScope.modules}">
					<option  value="${e.getAbrModule()}">${e.getAbrModule()} ${e.getFormation().getAbrFormation()}</option>
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
					<c:when test="${ empty sessionScope.groupes or empty sessionScope.types or empty sessionScope.enseignents  }">
						<h1 style="text-align: center">la liste vide</h1>
					</c:when>
					<c:otherwise>
					<form action="AffecterLesSeance" method="POST">
					<div class="titlePage">
						<h2>Faire les affectations</h2>
					</div>
						<table class="table table-hover">
							<tr>
								<th>Groupe</th>
								<th>type</th>
								<th>ensignents</th>
							</tr>

							<c:forEach var="et" items="${sessionScope.groupes}">
								<tr>
									<td rowspan="${sessionScope.types.size()}">G${et.getNumGroupe()} ${et.getFormation().getAbrFormation()}</td>
									<c:forEach var="type" items="${sessionScope.types}">
										<td><c:out value="${type}" /></td>
										<td>
										<select name="${et.getIdGroupe()}-${type}">
											<option value="">Aucun</option>
											<c:forEach var="enseignent" items="${sessionScope.enseignents}">
												<% boolean b = false; %>
												<c:forEach var="deja" items="${sessionScope.dejaAffecter}">
													<c:if test="${et.getIdGroupe() eq deja.getGroupe().getIdGroupe() and enseignent.getUsername() eq deja.getEnsiegnent().getUsername() and type eq deja.getType()}">
														<option value="${enseignent.getUsername()}" selected>${enseignent.getNom()} ${enseignent.getPrenom()}</option>
													</c:if>
												</c:forEach>
												<option value="${enseignent.getUsername()}">${enseignent.getNom()} ${enseignent.getPrenom()}</option>
											</c:forEach>
										</select>
										</td>
										<tr>
									</c:forEach>
									
								</tr>
							</c:forEach>
						</table>
						<input type="submit" value="Enregsiter">
						</form>
					</c:otherwise>
				</c:choose>
				
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