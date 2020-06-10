package Controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.ConnectionBD;
import Modele.Enseignent;
import Modele.OperationAdministrateur;

/**
 * Servlet implementation class GestionEnseignant
 */
@WebServlet("/GestionEnseignant")
public class GestionEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionEnseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		OperationAdministrateur oa = new OperationAdministrateur();
		ArrayList<Enseignent> enseignents = oa .consulterEnseignant();
		session.setAttribute("enseignents", enseignents);
		rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/GestionEnseignants.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
