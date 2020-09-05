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
 * Servlet implementation class ModiferProfileEtudaintServlet
 */
@WebServlet("/ModiferProfileEtudaintServlet")
@MultipartConfig(maxFileSize = 16177216)
public class ModiferProfileEtudaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiferProfileEtudaintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etudiant en = (Etudiant) session.getAttribute("Etudiant");
		String username = en.getUsername();
		System.out.println(username);
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dn = request.getParameter("dn");
		System.out.println(dn);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-hh").parse(dn);
		} catch (ParseException e) {
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
		String abrForamtion = request.getParameter("formation");
		Etudiant e = new Etudiant(nom, prenom, username, email, password, date, ln, sexe, addresse, sf, photoProfil, new Formation(abrForamtion));
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.modifierEtudiant(e)) {
			session.setAttribute("message","modifiction avec succes");
			session.setAttribute("etat","Succès");
		}
		else{
			session.setAttribute("message","modifiction echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("Goto?page=Etudiant");
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
