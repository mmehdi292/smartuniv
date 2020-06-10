<jsp:include page="/WEB-INF/template/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>EspaceEnseignent</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">EspaceEnseignent</a>
			</div>
			<ul class="nav navbar-nav">
				<ul>
					<a href="#">Gerer les enseignants</a>
					<li><a href="#">Faire l'appele</a></li>
					<li><a href="#">Modifier le releve d'absence</a></li>
					<li><a href="#">Enrgistre un justification</a></li>
				</ul>
				<li><a href="#">Etablir la liste des exclus</a></li>
				<li><a href="#">Consulter l'emploi des temps</a></li>
				<li><a href="#">Consulter les statistiques</a></li>
				<li><a href="#">Consulter le profil</a></li>
				<li><a href="logout">Deconneter</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>