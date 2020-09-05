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

import Modele.Administrateur;
import Modele.ChefDepartement;
import Modele.Enseignent;
import Modele.Formation;
import Modele.Grade;
import Modele.OperationAdministrateur;
import Modele.ResponsableDeFormation;
import Modele.Sexe;
import Modele.SituationFamiliale;

/**
 * Servlet implementation class ModifierProfileServlet
 */
@WebServlet("/ModifierProfileServlet")
@MultipartConfig(maxFileSize = 16177216)
public class ModifierProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Enseignent en = (Enseignent) session.getAttribute("Enseignent");
		String username = en.getUsername();
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean modifierPass = true;
		if(password == null) {
			password = en.getMotDePass();
			modifierPass = false;
		}
		System.out.println(nom);
		String dn = request.getParameter("dn");
		System.out.println(dn);
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
		String addresse = request.getParameter("adresse");
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
		System.out.println(" lllllllllll "+admin);
		String chef = request.getParameter("chef");
		String res = request.getParameter("res");
		String departement = request.getParameter("departement");
		String formation = request.getParameter("formation");
		OperationAdministrateur oa = new OperationAdministrateur();
		if(admin == null) {
			oa.delectAdmin(username);
		}
		else {
			Administrateur a = new Administrateur(nom,prenom,username,email,password,date,ln,sexe,addresse,sf,photoProfil);
			if(oa.modAdmin(a) == false) {
				oa.addAdmin(a);
			}
		}
		if(chef == null) {
			oa.delectChef(username);
		}
		else {
			ChefDepartement c = new ChefDepartement(nom,prenom,username,email,password,date,ln,sexe,addresse,sf,photoProfil,grade,departement);
			if(oa.modChef(c) == false) {
				oa.addChef(c);
			}
		}
		if(res == null) {
			oa.delectRes(username);
		}
		else{
			ResponsableDeFormation r = new ResponsableDeFormation(nom,prenom,username,email,password,date,ln,sexe,addresse,sf,photoProfil,grade,new Formation(formation));
			if(oa.modRes(r) == false) {
				oa.addRes(r);
			}
		}
		Enseignent e = new Enseignent(nom,prenom,username,email,password,date,ln,sexe,addresse,sf,photoProfil,grade);
		System.out.println(e.getUsername());
		boolean a = oa.modEns(e);
		System.out.println("------------------ "+a);
		RequestDispatcher rd = request.getRequestDispatcher("Goto?page=Enseignent");
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
