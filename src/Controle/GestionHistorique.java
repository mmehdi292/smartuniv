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
import Modele.Historique;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.OperationChefDepartement;
import Modele.OperationGlobale;

/**
 * Servlet implementation class GestionHistorique
 */
@WebServlet("/GestionHistorique")
public class GestionHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionHistorique() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		String username=(String) session.getAttribute("user");
		System.out.println("useeeeeeeeeeeer servleeet"+username);
		String role=request.getParameter("role");
		OperationAdministrateur oa = new OperationAdministrateur();
		OperationChefDepartement og = new OperationChefDepartement();
		if(role==null) {
			System.out.println("servlet admin");
			ArrayList<Historique> historiques = oa.consulterHistorique();
			session.setAttribute("Historiques", historiques);
			rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin/ConsulterHistorique.jsp");
			rd.forward(request, response);
		}
		else {
			System.out.println("servlet chef");
			String departement=og.getNomDepartement(username);
			System.out.println("departement =="+departement);
			ArrayList<Historique> ar=new ArrayList<Historique>();
			ArrayList<Historique> historiquesChef = og.consulterHistoriqueChef(departement);
			for(Historique h:historiquesChef) {
				String depart=og.getDepartementUtilisateur(h.getUsername());
				if(depart!=null) {
					if(depart.equals(departement)) ar.add(h);
				}
			}
			session.setAttribute("HistoriquesChef", ar);
			rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/ConsulterHistorique.jsp");
			rd.forward(request, response);
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
