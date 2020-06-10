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

import Modele.OperationGlobale;
import Modele.Seance;
import Modele.Vacance;
import Modele.Formation;
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;

/**
 * Servlet implementation class Modifier
 */
@WebServlet("/Modifier")
public class Modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifier() {
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
		OperationGlobale og = new OperationGlobale();
		OperationAdministrateur oa = new OperationAdministrateur();
		RequestDispatcher rd;
		switch (type) {
			case "module":
				Module m = og.getModule(abr);
				session.setAttribute("Module", m);
				session.setAttribute("Formations", og.consulterFormation());
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierModule.jsp");
				rd.forward(request, response);
			break;
			case "formation":
				Formation f = og.getFormation(abr);
				session.setAttribute("Formation", f);
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierFormation.jsp");
				rd.forward(request, response);
			break;
			case "vacance":
				int i = Integer.parseInt(abr);
				Vacance v = og.getVacance(i);
				session.setAttribute("Vacance", v);
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierVacance.jsp");
				rd.forward(request, response);
			break;
			case "seance":
				int id = Integer.parseInt(abr);
				Seance s = og.getSeance(id);
				ArrayList<Module> modules = oa.consulterModule();
				ArrayList<Groupe> groupes = oa.consulterGroupe();
				session.setAttribute("Seance", s);
				session.setAttribute("Modules", modules);
				session.setAttribute("Groupes", groupes);
				rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ModifierSeance.jsp");
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
