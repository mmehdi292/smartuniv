package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Goto
 */
@WebServlet("/Goto")
public class Goto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Goto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = (String) request.getParameter("page");
		RequestDispatcher rd;
		switch(page) {
		case "Administrateur":
			rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceAdministrateur.jsp");
			rd.forward(request, response);
			break;
		case "ChefDepartement":
			rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceChefDepartement.jsp");
			rd.forward(request, response);
			break;
		case "Enseignent":
			rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceEnseignent.jsp");
			rd.forward(request, response);
			break;
		case "ResponsableDeFormation":
			rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceResponsableDeFormation.jsp");
			rd.forward(request, response);
			break;
		case "Etudiant":
			rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceEtudiant.jsp");
			rd.forward(request, response);
			break;
		case "Reset":
			rd = request.getRequestDispatcher("/WEB-INF/authentification/restpassword.jsp");
			rd.forward(request, response);
			break;
		case "ajouterEnseignent":
			rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ajouterEnseignant.jsp");
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
