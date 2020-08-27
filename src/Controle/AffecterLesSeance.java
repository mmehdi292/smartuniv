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
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.OperationChefDepartement;
import Modele.OperationGlobale;

/**
 * Servlet implementation class AffecterLesSeance
 */
@WebServlet("/AffecterLesSeance")
public class AffecterLesSeance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffecterLesSeance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String abrModuleSelected = null;
		HttpSession session = request.getSession();
		try{
			abrModuleSelected = request.getParameter("choix");
			session.setAttribute("abrModuleSelected", abrModuleSelected);
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
		ArrayList<Module> modules = oc.getModuleDeDepartement(nomdepartement);
		session.setAttribute("modules",modules);
		if(abrModuleSelected != null) {
			System.out.println("here");
			ArrayList<Enseignent> enseignents = oa.consulterEnseignant();
			ArrayList<String> types = oc.getTypes(abrModuleSelected);
			ArrayList<Groupe> groupes = oc.getGroupesDunModule(abrModuleSelected);
			ArrayList<EnseiModuleGroupe> dejaAffecter = oc.getAffectationDunModule(abrModuleSelected);
			session.setAttribute("enseignents",enseignents);
			session.setAttribute("types",types);
			session.setAttribute("groupes",groupes);
			session.setAttribute("dejaAffecter",dejaAffecter);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/affecterlesSeances.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OperationChefDepartement oc = new OperationChefDepartement();
		String abrModuleSelected = (String) session.getAttribute("abrModuleSelected");
		ArrayList<Groupe> groupes = oc.getGroupesDunModule(abrModuleSelected);
		ArrayList<String> types = oc.getTypes(abrModuleSelected);
		oc.deleteAffectataion(abrModuleSelected);
		for(Groupe g:groupes) {
			for(String type : types) {
				String username = request.getParameter(g.getIdGroupe()+"-"+type);
				if(!username.equals("")) {
					oc.faireAffectation(username, abrModuleSelected, g.getIdGroupe(), type);
				}
			}	
		}
		OperationAdministrateur oa = new OperationAdministrateur();
		ArrayList<Enseignent> enseignents = oa.consulterEnseignant();
		ArrayList<String> types1 = oc.getTypes(abrModuleSelected);
		ArrayList<Groupe> groupes1 = oc.getGroupesDunModule(abrModuleSelected);
		ArrayList<EnseiModuleGroupe> dejaAffecter = oc.getAffectationDunModule(abrModuleSelected);
		session.setAttribute("enseignents",enseignents);
		session.setAttribute("types",types1);
		session.setAttribute("groupes",groupes1);
		session.setAttribute("dejaAffecter",dejaAffecter);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/affecterlesSeances.jsp");
		rd.forward(request, response);
	}

}
