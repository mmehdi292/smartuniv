<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Modifier Seance</title>
</head>

<body>
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />

		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Modifier Seance</h2>
			<a href="GestionSeance"> <i class="fas fa-users"></i>
				consulter les Seances
			</a>
		</div>
		<div class="tableDiv">
			<form id="frm1" action="ModifierServlet?type=seance" method="POST" id="mf">
				<h2>Seance de ${Seance.getIdSeance()}</h2>
				<div class="row">
				<div class="col-sm-12">
					<label for="type"><b>Type :</b></label>
					<select id="type" name="types">
						<option value="${Seance.getType()}" selected>${Seance.getType()}</option>
						<option value="TD">TD</option>
						<option value="TP">TP</option>
						<option value="COUR">COUR</option>
					</select>
				</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
					<label for="date"><b>Date: </b></label>
					<input type="Date" id="date"  value="<fmt:formatDate type="time" value="${Seance.getTemp()}" pattern="yyyy-MM-dd"/>" name="date" required>
					</div>
					<div class="col-sm-4">
					<label for="heur"><b>Heur: </b></label>
					<input type="number" id="heur" value="<fmt:formatDate type="time" value="${Seance.getTemp()}" pattern="HH"/>" name="heur" min="0" max="23" required>
					</div>
					<div class="col-sm-4">
					<label for="min"><b>min: </b></label>
					<input type="number" id="min" value="<fmt:formatDate type="time" value="${Seance.getTemp()}" pattern="mm"/>" name="min" min="0" max="59" required>
					</div>
				</div>	
				<div class="row">
					<div class="col-sm-6">
					<label for="salle"><b>salle: </b></label>
					<input type="number" id="salle" value="${Seance.getSalle()}" name="salle" required>
					</div>
					<div>
					<div class="col-sm-6">
					<label for="abs">Avoir Absence</label>
					<select id="abs" name="abs">
						<c:choose>
							<c:when test="${Seance.isAvoirAbs() eq true}">
								<option value="OUI" selected>OUI</option>
								<option value="NO">NO</option>
							</c:when>
							<c:otherwise>
								<option value="OUI" >OUI</option>
								<option value="NO" selected>NO</option>
							</c:otherwise>
						</c:choose>	
					</select>
					</div>
					</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
					<label for="module">Module:</label>
					<select id="module" name="module">
						<c:forEach var="f" items="${sessionScope.Modules}">
							<c:choose>
								<c:when test="${f.getAbrModule() eq Seance.getModule().getAbrModule()}">
									<option value="${f.getAbrModule()}" selected>${f.getAbrModule()}</option>
								</c:when>
								<c:otherwise>
									<option value="${f.getAbrModule()}">${f.getAbrModule()}</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-6">
					<label for="groupe">Groupe:</label>
					<select id="groupe" name="groupe">
						<c:forEach var="f" items="${sessionScope.Groupes}">
							<c:choose>
								<c:when test="${f.getIdGroupe() eq Seance.getGroupe().getIdGroupe()}">
								<option value="${f.getIdGroupe()}" selected>${f.getIdGroupe()} ${f.getNumGroupe()} ${f.getFormation().getAbrFormation()}</option>
								</c:when>
								<c:otherwise>
								<option value="${f.getIdGroupe()}">${f.getIdGroupe()} ${f.getNumGroupe()} ${f.getFormation().getAbrFormation()}</option>
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