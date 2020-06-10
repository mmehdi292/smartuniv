package Controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.ConnectionBD;
import Modele.ResetMotDePass;

/**
 * Servlet implementation class Reset
 */
@WebServlet("/Reset")
public class Reset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("password");
		HttpSession session = request.getSession();
		ResetMotDePass reset = (ResetMotDePass) session.getAttribute("reset");
		ConnectionBD bd = new ConnectionBD("root", "root", "smartuniv");
		bd.startConnection();
		System.out.println("Done init ");
		boolean res = bd.MAJpassword(reset, password);
		System.out.println("MAJ is "+res);
		if(res) {
			session.setAttribute("message", "le mot de pass est modifier");
		}
		else 
		{
			session.setAttribute("message", "il y a un erreur");
		}
		bd.endConnectionWithOutResult();
		System.out.println("end connection without");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
		rd.forward(request, response);
		
	}

}
