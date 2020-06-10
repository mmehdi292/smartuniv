package Controle;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Modele.Administrateur;
import Modele.ChefDepartement;
import Modele.ConnectionBD;
import Modele.Enseignent;
import Modele.Formation;
import Modele.Grade;
import Modele.ResponsableDeFormation;
import Modele.Sexe;
import Modele.SituationFamiliale;

/**
 * Servlet implementation class AjouterEnseignant
 */
@WebServlet("/AjouterEnseignant")
@MultipartConfig(maxFileSize = 16177216)
public class AjouterEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterEnseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dn = request.getParameter("dn");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-hh").parse(dn);
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
		String g = request.getParameter("grade");
		Grade grade = null;
		switch (g) {
		case "MaitreDeConférenceClasseA":
			grade = Grade.MaitreDeConférenceClasseA;
			break;
		case "MaitreDeConférenceClasseB":
			grade = Grade.MaitreDeConférenceClasseB;
			break;
		}
		String admin = request.getParameter("admin");
		String chef = request.getParameter("chef");
		String res = request.getParameter("res");
		String departement = request.getParameter("departement");
		String formation = request.getParameter("formation");
		HttpSession session = request.getSession();
		String message = nom + " " + prenom + " actuellement est : ";
		int i = 0;
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Enseignent en = new Enseignent(nom, prenom, username, email, password, date, ln, sexe, addresse, sf,
				photoProfil, grade);
		boolean okens = bd.addEns(en);
		if (okens) {
			i++;
			message += "Enseignent";

			if (admin != null) {
				Administrateur ad = new Administrateur(nom, prenom, username, email, password, date, ln, sexe, addresse,
						sf, photoProfil);
				boolean okadmin = bd.addAdmin(ad);
				if (okadmin) {
					i++;
					message += " ,Administrateur";
				}

			}
			if (chef != null) {
				ChefDepartement ch = new ChefDepartement(nom, prenom, username, email, password, date, ln, sexe,
						addresse, sf, photoProfil, grade, departement);
				boolean okchef = bd.addchef(ch);
				if (okchef) {
					i++;
					message += " ,ChefDepartement";
				}

			}
			if (res != null) {
				ResponsableDeFormation re = new ResponsableDeFormation(nom, prenom, username, email, password, date, ln,
						sexe, addresse, sf, photoProfil, grade, new Formation(formation));
				boolean okres = bd.addres(re);
				if (okres) {
					i++;
					message += " ,ResponsableDeFormation";
				}
			}
		}

		if (i != 0) {
			session.setAttribute("message", message+".");
			session.setAttribute("etat","Succès");
		} else {
			session.setAttribute("message", "l'ajout échoué");
			session.setAttribute("etat","échoué");
		}
		bd.endConnectionWithOutResult();
		RequestDispatcher rd = request.getRequestDispatcher("GestionEnseignant");
		rd.forward(request, response);
	}

}
