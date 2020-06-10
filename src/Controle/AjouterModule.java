package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Formation;
import Modele.Module;
import Modele.OperationAdministrateur;

/**
 * Servlet implementation class AjouterModule
 */
@WebServlet("/AjouterModule")
public class AjouterModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterModule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom");
		String abrModule = request.getParameter("abrModule");
		int semester = Integer.parseInt(request.getParameter("semester"));
		String formation = request.getParameter("formation");
		Module m = new Module(nom,abrModule,semester,new Formation(formation));
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.ajouterModule(m)) {
			session.setAttribute("message","ajouter avec succes");
			session.setAttribute("etat","Succès");
		}
		else {
			session.setAttribute("message","ajouter echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GestionModule");
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
