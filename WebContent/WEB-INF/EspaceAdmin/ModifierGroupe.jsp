<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Groupe</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modification de Groupe</h2>
			<a href="GestionGroupe"> <i class="fas fa-users"></i>
				Consulter les Groupes
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierServlet?type=groupe" method="POST" id="mf">
				<h2>Groupe de id :  ${Groupe.getIdGroupe()}</h2>
				<div class="row">
					<div class="col-sm-12">
						<label>Numéro de Groupe: </label> <input type="number"
							value="${Groupe.getNumGroupe()}" class="modiferInput"
							name="num">
					</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
						<label>Section: </label> <input type="number"
							value="${Groupe.getSection()}"class="modiferInput"
							name="section">
					</div>
					<div class="col-sm-6">
						<label for="formation">Formation</label>
					<select id="formation" name="formation">
					
						<c:forEach var="f" items="${sessionScope.Formations}">
							<c:choose>
								<c:when test="${Groupe.getFormation().getAbrFormation() eq f.getAbrFormation()}">
									<option value="${f.getAbrFormation()}" selected>${f.getAbrFormation()}</option>
								</c:when>
								<c:otherwise>
									<option value="${f.getAbrFormation()}">${f.getAbrFormation()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="row" align="center">
					<div class="col-sm-12">
						<input class="mbtn" type="submit" value="modifier">
					</div>
				</div>

			</form>
		</div>
		<!--footer start-->
		<div class="footer">
			<p>Tous droits réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />