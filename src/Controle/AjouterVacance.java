package Controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import Modele.Vacance;

/**
 * Servlet implementation class AjouterVacance
 */
@WebServlet("/AjouterVacance")
public class AjouterVacance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterVacance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom");
		String debut = request.getParameter("dateD");
		String fin = request.getParameter("dateF");
		Date date = null;
		Date dateD = null;
		Date dateF = null;
		try {
			dateD = new SimpleDateFormat("yyyy-MM-dd").parse(debut);
			dateF = new SimpleDateFormat("yyyy-MM-dd").parse(fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.ajouterVacances(nom,dateD,dateF)) {
			session.setAttribute("message","ajouter avec succes");
			session.setAttribute("etat","Succès");
		}
		else {
			session.setAttribute("message","ajouter echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GestionVacance");
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
