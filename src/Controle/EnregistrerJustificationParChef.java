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

import Modele.Absence;
import Modele.EnseiModuleGroupe;
import Modele.OperationChefDepartement;
import Modele.OperationEnseignent;
import Modele.Seance;

/**
 * Servlet implementation class EnregisterJustification
 */
@WebServlet("/EnregistrerJustificationParChef")
public class EnregistrerJustificationParChef extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerJustificationParChef() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OperationChefDepartement op = new OperationChefDepartement();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String nomDepartement=op.getNomDepartement(username);

		try {
			String choix = request.getParameter("choix");
			System.out.println(choix);
			String[] table = choix.split("-");
			int x =Integer.parseInt(table[0]);
			ArrayList<Seance> s = op.getSeances(x, table[1], table[2]);
			session.setAttribute("Seances", s);
			session.setAttribute("groupe", table[0]);
			session.setAttribute("module", table[1]);
		}
		catch(Exception e) {
			System.out.println("error");
		}
		
		ArrayList<Seance> r = op.seanceModuleGroupeDep(nomDepartement);
		System.out.println("size: "+r.size());
		session.setAttribute("emg", r);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/enregistrerJustification.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OperationChefDepartement oc = new OperationChefDepartement();
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String username = (String) session.getAttribute("user");
		System.out.println("servlet test username="+username);
		String departement=oc.getNomDepartement(username);
		ArrayList<Absence> absences = oc.getAbsenceParNom(nom, prenom, departement);
		session.setAttribute("absences", absences);
		session.setAttribute("nom", nom);
		session.setAttribute("prenom", prenom);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/ListeEtudiantParNom.jsp");
		rd.forward(request, response);
	}

}
