package Controle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Modele.Absence;

import Modele.OperationEnseignent;

/**
 * Servlet implementation class EtudiantAbsencent
 */
@WebServlet("/EtudiantAbsencent")
@MultipartConfig(maxFileSize = 16177216)
public class EtudiantAbsencent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantAbsencent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idgroupe = Integer.parseInt(request.getParameter("idgroupe"));
		int idseance = Integer.parseInt(request.getParameter("idseance"));
		String role=request.getParameter("role");
		OperationEnseignent oe = new OperationEnseignent();
		ArrayList<Absence> absences = oe.getAbsencesDunSeanceAvecEtudiant(idseance);
		HttpSession session = request.getSession();
		session.setAttribute("idseance", idseance);
		session.setAttribute("idgroupe", idgroupe);
		session.setAttribute("absences", absences);
		System.out.println("le role est : "+role);
		if(role==null) {
			System.out.println("test null");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/listeEtudiantAbsent.jsp");
			rd.forward(request, response);
		}
		if(role.equals("chef")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/listeEtudiantAbsent.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role=request.getParameter("role");
		int idseance = (int) session.getAttribute("idseance");
		OperationEnseignent oe = new OperationEnseignent();
		ArrayList<Absence> absences = oe.getAbsencesDunSeanceAvecEtudiant(idseance);
		session.setAttribute("message","L'ajout est échoué");
		session.setAttribute("etat","échoué");
		for(Absence a : absences) {
			Part photo = request.getPart(a.getIdAbsence()+"");
			if(photo!=null) {
				InputStream justification = photo.getInputStream();
				oe.insertJustification(a.getIdAbsence(), justification);
				session.setAttribute("message","ajouter avec succès");
				session.setAttribute("etat","Succès");
			}
		}
		if(role==null) {
			System.out.println("test null");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/listeSeancePourEnrJustification.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/enregistrerJustification.jsp");
			rd.forward(request, response);
		}

	}
}
