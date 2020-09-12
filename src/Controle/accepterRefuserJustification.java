package Controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.OperationChefDepartement;


@WebServlet("/accepterRefuserJustification")
public class accepterRefuserJustification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public accepterRefuserJustification() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int idAbsence=Integer.parseInt(request.getParameter("idAbsence"));
		String action=request.getParameter("action");
		System.out.println("action: "+action);
		System.out.println("idAbsence: "+idAbsence);
		OperationChefDepartement op=new OperationChefDepartement();
		boolean b = false;
		if(action.equals("accepter"))
			b = op.accepterJustification(idAbsence);
		else
			b = op.refuserJustification(idAbsence);
		
			RequestDispatcher dispatch = request.getRequestDispatcher("listeAbsencesJustifie");
			dispatch.forward(request, response);

		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
