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

import Modele.Administrateur;
import Modele.ChefDepartement;
import Modele.ConnectionBD;
import Modele.Enseignent;
import Modele.Etudiant;
import Modele.OperationAdministrateur;
import Modele.OperationGlobale;
import Modele.ResponsableDeFormation;

/**
 * Servlet implementation class ModifierActeur
 */
@WebServlet("/ModifierActeur")
public class ModifierActeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierActeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =request.getParameter("username");
		String type =request.getParameter("type");
		OperationGlobale og = new OperationGlobale();
		ConnectionBD bd = new ConnectionBD();
		HttpSession session = request.getSession();
		bd.startConnection();
		RequestDispatcher rd;
		switch (type) {
		case "enseignent":
			Enseignent e = bd.getEnseignent(username);
			System.out.println(e);
			if(e != null) {
				ArrayList<String> userAction = new ArrayList<String>();
				userAction.add("Enseignent");
				ChefDepartement c = bd.getChefDepartement(username);
				ResponsableDeFormation r =bd.getResponsableDeFormation(username);
				Administrateur a = bd.getAdministrateur(username);
				if(c != null) {
					userAction.add("ChefDepartement");
					session.setAttribute("ChefDepartement", c);
				}
				if(r != null) {
					userAction.add("ResponsableDeFormation");
					session.setAttribute("ResponsableDeFormation", r);
				}
				if(a != null) {
					userAction.add("Administrateur");
					session.setAttribute("Administrateur", a);
				}
				session.setAttribute("Enseignent", e);
				session.setAttribute("userAction", userAction);
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierActeur.jsp");
				rd.forward(request, response);
			}
			rd = request.getRequestDispatcher("GestionEnseignant");
			rd.forward(request, response);
			break;
		case "etudiant":
			Etudiant et = og.getEtudiant(username);
			if(et != null) {
				session.setAttribute("Etudiant", et);
				session.setAttribute("Formations", og.consulterFormation());
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierEtudiant.jsp");
				rd.forward(request, response);
			}
			rd = request.getRequestDispatcher("GestionEtudiant");
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
