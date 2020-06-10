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

import Modele.Etudiant;
import Modele.Module;
import Modele.Formation;
import Modele.OperationAdministrateur;
import Modele.OperationGlobale;

/**
 * Servlet implementation class GestionModule
 */
@WebServlet("/GestionModule")
public class GestionModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		OperationAdministrateur oa = new OperationAdministrateur();
		OperationGlobale og = new OperationGlobale();
		ArrayList<Module> modules = oa.consulterModule();
		ArrayList<Formation> formations = og.consulterFormation();
		session.setAttribute("Modules", modules);
		session.setAttribute("Formations", formations);
		rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/GestionModules.jsp");
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
