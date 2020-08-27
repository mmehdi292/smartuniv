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
import Modele.OperationEtudiant;

/**
 * Servlet implementation class EtudiantAbsenceParUsername
 */
@WebServlet("/EtudiantAbsenceParUsername")
@MultipartConfig(maxFileSize = 16177216)
public class EtudiantAbsenceParUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantAbsenceParUsername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		OperationEtudiant oe = new OperationEtudiant();
		OperationEnseignent oe1 = new OperationEnseignent();
		ArrayList<Absence> absences = oe.getAbsenceParUsername(username);
		session.setAttribute("message","ajouter echoue");
		session.setAttribute("etat","échoué");
		for(Absence a : absences) {
			Part photo = request.getPart(a.getIdAbsence()+"");
			if(photo!=null) {
				InputStream justification = photo.getInputStream();
				oe1.insertJustification(a.getIdAbsence(), justification);
				System.out.println("ok________________1");
				session.setAttribute("message","ajouter avec succes");
				session.setAttribute("etat","Succès");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("AbsenceJustification");
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
