package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.OperationGlobale;

/**
 * Servlet implementation class Supprimer
 */
@WebServlet("/Supprimer")
public class Supprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supprimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String abr =request.getParameter("abr");
		String type =request.getParameter("type");
		HttpSession session = request.getSession();
		OperationAdministrateur oa = new OperationAdministrateur();
		RequestDispatcher rd;
		switch (type) {
			case "module":
				if(oa.suppModule(abr)) {
					session.setAttribute("message","suppression avec succès");
					session.setAttribute("etat","Succès");
				}
				else {
					session.setAttribute("message","suppression échoué");
					session.setAttribute("etat","échoué");
				}
				rd = request.getRequestDispatcher("GestionModule");
				rd.forward(request, response);
			break;
			case "formation":
				if(oa.suppFormation(abr)) {
					session.setAttribute("message","suppression avec succès");
					session.setAttribute("etat","Succès");
				}
				else {
					session.setAttribute("message","suppression échoué, il y a des étudiants qui étudient cette formation");
					session.setAttribute("etat","échoué");
				}
				rd = request.getRequestDispatcher("GestionFormation");
				rd.forward(request, response);
			break;
			case "vacance":
				int id = Integer.parseInt(abr);
				if(oa.suppVacance(id)) {
					session.setAttribute("message","suppression avec succès");
					session.setAttribute("etat","Succès");
				}
				else {
					session.setAttribute("message","suppression échoué, il y a des étudiants qui étudient dans cette formation");
					session.setAttribute("etat","échoué");
				}
				rd = request.getRequestDispatcher("GestionVacance");
				rd.forward(request, response);
			break;
			case "seance":
				int idSeance = Integer.parseInt(abr);
				if(oa.suppSeance(idSeance)) {
					session.setAttribute("message","suppression avec succès");
					session.setAttribute("etat","Succès");
				}
				else {
					session.setAttribute("message","suppression échoué, il y a des étudiants qui étudient ce module");
					session.setAttribute("etat","échoué");
				}
				rd = request.getRequestDispatcher("GestionSeance");
				rd.forward(request, response);
			break;
			case "groupe":
				int idGroupe = Integer.parseInt(abr);
				if(oa.suppGroupe(idGroupe)) {
					session.setAttribute("message","suppression avec succès");
					session.setAttribute("etat","Succès");
				}
				else {
					session.setAttribute("message","suppression échoué, il y a des étudiants qui étudient dans ce groupe");
					session.setAttribute("etat","échoué");
				}
				rd = request.getRequestDispatcher("GestionGroupe");
				rd.forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
