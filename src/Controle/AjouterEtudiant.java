package Controle;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Modele.Etudiant;
import Modele.Formation;
import Modele.OperationAdministrateur;
import Modele.Sexe;
import Modele.SituationFamiliale;

/**
 * Servlet implementation class AjouterEtudiant
 */
@WebServlet("/AjouterEtudiant")
@MultipartConfig(maxFileSize = 16177216)
public class AjouterEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dn = request.getParameter("dn");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dn);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ln = request.getParameter("ln");
		String s1 = request.getParameter("sexe");
		Sexe sexe = null;
		switch (s1) {
		case "Homme":
			sexe = Sexe.Homme;
			break;
		case "Femme":
			sexe = Sexe.Femme;
			break;
		}
		String addresse = request.getParameter("addresse");
		String sf1 = request.getParameter("sf");
		SituationFamiliale sf = null;
		switch (sf1) {
		case "célibataire":
			sf = SituationFamiliale.célibataire;
			break;
		case "divorcé":
			sf = SituationFamiliale.divorcé;
			break;
		case "marié":
			sf = SituationFamiliale.marié;
			break;
		case "séparé":
			sf = SituationFamiliale.séparé;
			break;
		case "veuf":
			sf = SituationFamiliale.veuf;
			break;
		}
		Part photo = request.getPart("photo");
		InputStream photoProfil = photo.getInputStream();
		String abrFormation = request.getParameter("formation");
		Etudiant e = new Etudiant(nom,prenom,username,email,password,date,ln,sexe,addresse,sf,photoProfil,new Formation(abrFormation));
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.ajouterEtudiant(e)) {
			session.setAttribute("message","ajouter avec succes");
			session.setAttribute("etat","Succès");
		}
		else {
			session.setAttribute("message","ajouter echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GestionEtudiant");
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
