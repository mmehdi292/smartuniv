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
import Modele.EnseiModuleGroupe;
import Modele.OperationEnseignent;

/**
 * Servlet implementation class EtudiantAbsencentParNom
 */
@WebServlet("/EtudiantAbsencentParNom")
@MultipartConfig(maxFileSize = 16177216)
public class EtudiantAbsencentParNom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantAbsencentParNom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role =request.getParameter("role");
		System.out.println("username :"+(String) session.getAttribute("user"));
		String nom = (String) session.getAttribute("nom");
		String prenom = (String) session.getAttribute("prenom");
		OperationEnseignent oe = new OperationEnseignent();
		ArrayList<Absence> absences = oe.getAbsenceParNom(nom, prenom);
		session.setAttribute("message","L'ajout est échoué");
		session.setAttribute("etat","échoué");
		for(Absence a : absences) {
			Part photo = request.getPart(a.getIdAbsence()+"");
			if(photo!=null) {
				InputStream justification = photo.getInputStream();
				oe.insertJustification(a.getIdAbsence(), justification);
				session.setAttribute("message","Ajouter avec success");
				session.setAttribute("etat","Succès");
			}
		}
		String username = (String) session.getAttribute("user");
		ArrayList<EnseiModuleGroupe> r = oe.enseiModuleGroupe(username);
		session.setAttribute("emg", r);
		if(role==null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/listeSeancePourEnrJustification.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceChef/enregistrerJustification.jsp");
			rd.forward(request, response);
		}
	}

}
