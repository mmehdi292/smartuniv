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

import Modele.ChefDepartement;
import Modele.EnseiModuleGroupe;
import Modele.Enseignent;
import Modele.Etudiant;
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.OperationChefDepartement;
import Modele.OperationGlobale;

/**
 * Servlet implementation class AffecterLesEtudiants
 */
@WebServlet("/AffecterLesEtudiants")
public class AffecterLesEtudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffecterLesEtudiants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String abrModuleSelected = null;
		HttpSession session = request.getSession();
		String [] tab =new String[2];
		try{
			abrModuleSelected = request.getParameter("choix");
			tab = abrModuleSelected.split("-");
			session.setAttribute("groupe", tab[0]);
			session.setAttribute("formation", tab[1]);
			System.out.println(tab[0]+"  -- "+tab[1]);
			}
		catch(Exception e) {
			System.out.println("ERROR CHOIX");
		}
		OperationChefDepartement oc = new OperationChefDepartement();
		OperationGlobale og = new OperationGlobale();
		OperationAdministrateur oa = new OperationAdministrateur();
		
		String useraname = (String) session.getAttribute("user");
		ChefDepartement c = og.getChefDepartemnt(useraname);
		String nomdepartement = c.getNomDepartement();
		System.out.println(nomdepartement);
		ArrayList<Groupe> groupes = oc.getGroupeDeDepartement(nomdepartement);
		session.setAttribute("groupes",groupes);
		if(abrModuleSelected != null) {
			ArrayList<Etudiant> etudiants = oc.getToutsLesEtudiants(tab[1]);
			int idgroupe = Integer.parseInt(tab[0]);
			ArrayList<Etudiant> etudiantsAffecter = oc.getAffectationDunGroupe(idgroupe);
			session.setAttribute("etudiants", etudiants);
			session.setAttribute("etudiantsAffecter", etudiantsAffecter);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/affecterlesgroupes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OperationChefDepartement oc = new OperationChefDepartement();
		int idGroupe = Integer.parseInt((String) session.getAttribute("groupe"));
		oc.suppAffectationEtudaint(idGroupe);
		String message = "";
		session.setAttribute("etat","Succès");
		for(int i=1;i<=30;i++) {
			String username = request.getParameter(i+"");
			if(!username.equals("")) {
				if(oc.faireAffectationDesGroupeAuxEtudiant(username, idGroupe)) {
					
				}
				else {
					message += username+" ";
					session.setAttribute("etat","échoué");
				}
			}
		}
		session.setAttribute("message",message+" deja affecter");
		ArrayList<Etudiant> etudiantsAffecter = oc.getAffectationDunGroupe(idGroupe);
		session.setAttribute("etudiantsAffecter", etudiantsAffecter);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/affecterlesgroupes.jsp");
		rd.forward(request, response);
	}

}
