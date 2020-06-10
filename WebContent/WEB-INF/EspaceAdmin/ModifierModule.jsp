<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Module</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modifier Module</h2>
			<a href="GestionModule"> <i class="fas fa-users"></i>
				consulter les Modules
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierServlet?type=module" method="POST" id="mf">
				<h2>Module de ${Module.getNomModule()}</h2>
				<div class="row">
					<div class="col-sm-6">
						<label>Nom de module: </label> <input type="text"
							value="${Module.getNomModule()}" class="modiferInput" name="nom">
					</div>
					<div class="col-sm-6">
						<label>abrivation de module: </label> <input type="text"
							value="${Module.getAbrModule()}" class="modiferInput"
							name="abrModule">
					</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
						<label>semater: </label> <input type="number"
							value="${Module.getSemester()}" min="1" max="6" class="modiferInput"
							name="semester">
					</div>
					<div class="col-sm-6">
						<label for="formation">Formation</label>
					<select id="formation" name="formation">
					
						<c:forEach var="f" items="${sessionScope.Formations}">
							<c:choose>
								<c:when test="${Module.getFormation().getAbrFormation() eq f.getAbrFormation()}">
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
			<p>tous les droits sont réservés © 2020</p>
		</div>
		<!--sidebar end-->
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />