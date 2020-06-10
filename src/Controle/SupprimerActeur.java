package Controle;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.ConnectionBD;
import Modele.OperationAdministrateur;

/**
 * Servlet implementation class SupprimerActeur
 */
@WebServlet("/SupprimerActeur")
public class SupprimerActeur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerActeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String type = (String) request.getParameter("type");
		OperationAdministrateur oa = new OperationAdministrateur();
		ConnectionBD bd = new ConnectionBD("root", "root", "smartuniv");
		HttpSession session = request.getSession();
		bd.startConnection();
		RequestDispatcher rd;
		switch (type) {
		case "enseignent":
			boolean b = bd.suppEnseignant(username);
			if (b) {
				session.setAttribute("message", "Suppression succès");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "Suppression échoué");
				session.setAttribute("etat", "échoué");
			}
			bd.endConnectionWithOutResult();
			rd = request.getRequestDispatcher("GestionEnseignant");
			rd.forward(request, response);
			break;
		case "etudiant":
			if(oa.suppEtudiant(username)) {
				session.setAttribute("message", "Suppression succès");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "Suppression échoué");
				session.setAttribute("etat", "échoué");
			}
			rd = request.getRequestDispatcher("GestionEtudiant");
			rd.forward(request, response);
			break;
		}
		

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
