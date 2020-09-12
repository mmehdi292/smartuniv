package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Absence;
import Modele.OperationChefDepartement;


@WebServlet("/detailleJustification")
public class detailleJustification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public detailleJustification() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int idAbsence=Integer.parseInt(request.getParameter("idAbsence"));
		OperationChefDepartement op=new OperationChefDepartement();
		Absence a=op.getAbsence(idAbsence);
		
		HttpSession session=request.getSession();
		session.setAttribute("abs",a );
		session.setAttribute("idAbsence",idAbsence );

		
		RequestDispatcher disp=request.getRequestDispatcher("/WEB-INF/EspaceChef/detailleJustification.jsp");
			disp.forward(request, response);
	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
