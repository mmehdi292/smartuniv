package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Cycle;
import Modele.Formation;

import Modele.OperationAdministrateur;

/**
 * Servlet implementation class AjouterFormation
 */
@WebServlet("/AjouterFormation")
public class AjouterFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom");
		String abr = request.getParameter("abr");
		String spe = request.getParameter("spe");
		String c1 = request.getParameter("cycle");
		Cycle cycle = null;
		switch(c1) {
		case "licence": cycle=Cycle.licence; break;
		case "master": cycle=Cycle.master;  break;
		}
		int annee = Integer.parseInt(request.getParameter("Annee"));
		String dep = request.getParameter("dep");
		Formation m = new Formation(nom,abr,spe,cycle,annee,dep);
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.ajouterFormation(m)) {
			session.setAttribute("message","ajouter avec succes");
			session.setAttribute("etat","Succès");
		}
		else {
			session.setAttribute("message","ajouter echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GestionFormation");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
