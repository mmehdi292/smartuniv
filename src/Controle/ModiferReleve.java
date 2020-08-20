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
import Modele.Etudiant;
import Modele.OperationEnseignent;

/**
 * Servlet implementation class ModiferReleve
 */
@WebServlet("/ModiferReleve")
public class ModiferReleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiferReleve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idgroupe = Integer.parseInt(request.getParameter("idgroupe"));
		int idseance = Integer.parseInt(request.getParameter("idseance"));
		OperationEnseignent oe = new OperationEnseignent();
		ArrayList<Etudiant> et = oe.getEtudiantGroupe(idgroupe);
		ArrayList<Absence> absences = oe.getAbsencesDunSeance(idseance);
		for(Absence a : absences) {
			System.out.println(a.getIdAbsence());
		}
		HttpSession session = request.getSession();
		session.setAttribute("list", et);
		session.setAttribute("idseance", idseance);
		session.setAttribute("idgroupe", idgroupe);
		session.setAttribute("absence", absences);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/ModifierReleve.jsp");
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
