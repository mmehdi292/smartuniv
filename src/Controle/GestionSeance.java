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

import Modele.Formation;
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.OperationGlobale;
import Modele.Seance;

/**
 * Servlet implementation class GestionSeance
 */
@WebServlet("/GestionSeance")
public class GestionSeance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionSeance() {
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
		ArrayList<Seance> seances = oa.consulterSeances();
		ArrayList<Module> modules = oa.consulterModule();
		ArrayList<Groupe> groupes = oa.consulterGroupe();
		session.setAttribute("Seances", seances);
		session.setAttribute("Modules", modules);
		session.setAttribute("Groupes", groupes);
		rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/GestionSeance.jsp");
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
