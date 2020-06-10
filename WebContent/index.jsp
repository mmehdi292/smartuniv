<jsp:include page="/WEB-INF/template/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Modele.ConnectionBD,Modele.blocked"%>
<title>index</title>
</head>
<body>
	<%/*
		ConnectionBD bd = new ConnectionBD("root", "root", "smartuniv");
		bd.startConnection();
		
		bd.deblockMAC();
		String MAC = blocked.getMacAddress();
		if (bd.isBlocked(MAC)) {
			bd.endConnection();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/blocked.jsp");
			rd.forward(request, response);
		}
		
		bd.endConnection();*/
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
		rd.forward(request, response);
	%>

</body>
</html>