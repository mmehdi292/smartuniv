<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Vacance</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modifier Vacance</h2>
			<a href="GestionVacance"> <i class="fas fa-users"></i>
				consulter les Vacances
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierServlet?type=vacance" method="POST" id="mf">
				<h2>Module de ${Vacance.getDescription()}</h2>
				<div class="row">
					<div class="col-sm-12">
						<label>Nom de Vacance: </label> <input type="text"
							value="${Vacance.getDescription()}" class="modiferInput" name="nom">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
					
						<label>Date de Vacance: </label>
						<input type="date" value="${Vacance.getDate()}" class="modiferInput" name="date">
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