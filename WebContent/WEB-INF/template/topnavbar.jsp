<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="topnav" id="myTopnav">
			<div class="dropdown1">
				<button class="dropbtn">
					<p><c:out value="bienvenue, ${sessionScope.user}"/></p>
					<c:set var="profil" value="${sessionScope.profil}" scope="session" />
					<img alt="profil img" src="getImage">
				</button>
				<div class="dropdown1-content">
					<c:choose>
						<c:when test="${sessionScope.role.get(0) eq 'Etudiant'}">
							<a href="ConsulterProfile?type=etudiant"><i class="fas fa-id-card-alt"></i><br>Profil(voir/modifier)</a>
						</c:when>
						<c:when test="${sessionScope.role.get(0) eq 'Enseignent'}">
							<a href="ConsulterProfile?type=enseignent"><i class="fas fa-id-card-alt"></i><br>Profil(voir/modifier)</a>
						</c:when>
					</c:choose>
					<a href="logout"><i class="fas fa-sign-out-alt"></i><br>Déconnecter</a>
				</div>
			</div>
			<c:forEach var="r" items="${sessionScope.role}">
				<c:choose>
					<c:when test="${ r eq 'Administrateur' }">
						<a href="Goto?page=Administrateur" >Espace administrateur</a>
					</c:when>
					<c:when test="${ r eq 'Enseignent' }">
						<a href="Goto?page=Enseignent">Espace enseignant</a>
					</c:when>
					<c:when test="${ r eq 'ResponsableDeFormation' }">
						<a href="Goto?page=ResponsableDeFormation">Espace responsable de formation</a>
					</c:when>
					<c:when test="${ r eq 'ChefDepartement' }">
						<a href="Goto?page=ChefDepartement">Espace chef département</a>
					</c:when>
					<c:when test="${ r eq 'Etudiant' }">
						<a href="Goto?page=Etudiant">Espace étudiant</a>
					</c:when>
				</c:choose>
				
			
			</c:forEach>
			
		</div>