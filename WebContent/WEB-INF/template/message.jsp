<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="titlePage">
		<c:if test="${sessionScope.message != null}">
			<c:choose>
				<c:when test="${sessionScope.etat eq 'Succès'}">
				<div class="alert alert-success">
						<strong>${sessionScope.etat}!</strong> ${sessionScope.message}
					</div>
				</c:when>
				<c:otherwise>
				<div class="alert alert-danger">
						<strong>${sessionScope.etat}!</strong> ${sessionScope.message}
					</div>
				
				</c:otherwise>
			</c:choose>
		</c:if>
		<% 
		session.removeAttribute("message");
		session.removeAttribute("etat");
		%>
		</div>