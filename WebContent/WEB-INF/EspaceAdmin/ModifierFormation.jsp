<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Formation</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modifier Formation</h2>
			<a href="GestionFormation"> <i class="fas fa-users"></i>
				consulter les Formation
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierServlet?type=formation" method="POST" id="mf">
				<h2>Formation de ${Formation.getNomFormation()}</h2>
				<div class="row">
					<div class="col-sm-6">
						<label>Nom de Formation: </label> <input type="text"
							value="${Formation.getNomFormation()}" class="modiferInput" name="nom">
					</div>
					<div class="col-sm-6">
						<label>abrivation de Formation: </label> <input type="text"
							value="${Formation.getAbrFormation()}" class="modiferInput"
							name="abr">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
								<label for="spe">specialite</label>
						<select id="spe" name="spe">
							<c:choose>
								<c:when test="${Formation.getSpecialite() eq 'GL'}">
									<option value="GL" selected>GL</option>
									<option value="MI">MI</option>
									<option value="SCI">SCI</option>
									<option value="SI">SI</option>
									<option value="TI">TI</option>
								</c:when>
								<c:when test="${Formation.getSpecialite() eq 'MI'}">
									<option value="GL" >GL</option>
									<option value="MI" selected>MI</option>
									<option value="SCI">SCI</option>
									<option value="SI">SI</option>
									<option value="TI">TI</option>
								</c:when>
								<c:when test="${Formation.getSpecialite() eq 'SCI'}">
									<option value="GL">GL</option>
									<option value="MI">MI</option>
									<option value="SCI" selected>SCI</option>
									<option value="SI">SI</option>
									<option value="TI">TI</option>
								</c:when>
								<c:when test="${Formation.getSpecialite() eq 'SI'}">
									<option value="GL">GL</option>
									<option value="MI">MI</option>
									<option value="SCI">SCI</option>
									<option value="SI" selected>SI</option>
									<option value="TI">TI</option>
								</c:when>
								<c:when test="${Formation.getSpecialite() eq 'TI'}">
									<option value="GL">GL</option>
									<option value="MI">MI</option>
									<option value="SCI">SCI</option>
									<option value="SI">SI</option>
									<option value="TI"selected>TI</option>
								</c:when>
							</c:choose>
						</select>
					</div>
					<div class="col-sm-6">
						<label for="cycle"><b>Cycle</b></label>
					<select id="cycle" name="cycle">
						<c:choose>
							<c:when test="${Formation.getCycle() eq 'licence'}">
									<option value="licence" selected>licence</option>
									<option value="master">master</option>
							</c:when>
							<c:when test="${Formation.getCycle() eq 'master'}">
									<option value="licence">licence</option>
									<option value="master" selected>master</option>
								</c:when>
						</c:choose>	
					</select>
					</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
						<label>Annee: </label> <input type="number"
							value="${Formation.getAnnee()}" min="1" max="5" class="modiferInput"
							name="Annee">
					</div>
					<div class="col-sm-6">
					<label for="dep">Departement</label>
					<select id="dep" name="dep">
							<c:choose>
								<c:when test="${Formation.getDepartement() eq 'TLSI'}">
									<option value="TLSI" selected>TLSI</option>
									<option value="IFA">IFA</option>
									<option value="MI">MI</option>
								</c:when>
								<c:when test="${Formation.getDepartement() eq 'IFA'}">
									<option value="TLSI">TLSI</option>
									<option value="IFA" selected>IFA</option>
									<option value="MI">MI</option>
								</c:when>
								<c:when test="${Formation.getDepartement() eq 'MI'}">
									<option value="TLSI">TLSI</option>
									<option value="IFA">IFA</option>
									<option value="MI" selected>MI</option>
								</c:when>
							</c:choose>
							
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