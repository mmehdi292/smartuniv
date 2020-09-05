package Controle;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Administrateur;
import Modele.ConnectionBD;
import Modele.ResponsableDeFormation;
import Modele.blocked;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int error=0;
		try {
			 error=(int) session.getAttribute("error");
		}
		catch(Exception e) {
			session.setAttribute("error", 1);
		}
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		ConnectionBD bd = new ConnectionBD("root","root","smartuniv");
		bd.startConnection();
		ArrayList<String> role = new ArrayList<String>() ;
		if(error<2) {
			
			if(bd.isEnseignentByEmail(user, password)||bd.isEnseignentByUsername(user, password)) {
				role.add("Enseignent");
			}
			if(bd.isAdministrateurByEmail(user, password)||bd.isAdministrateurByUsername(user, password)) {
				role.add("Administrateur");
				Administrateur a = bd.getAdministrateur(user);
				InputStream profil = a.getPhoto();
				System.out.println(profil.toString());
				session.setAttribute("profil",profil);
			}
			if(bd.isChefDepartementByEmail(user, password)||bd.isChefDepartementByUsername(user, password)) {
				role.add("ChefDepartement");
			}
			
			if(bd.isResponsableDeFormationByEmail(user, password)||bd.isResponsableDeFormationByUsername(user, password)) {
				role.add("ResponsableDeFormation");
				ResponsableDeFormation r = bd.getResponsableDeFormation(user);
				String abr = r.getFormation().getAbrFormation();
				InputStream photo = r.getPhoto();
				System.out.println("----------------");
				System.out.println(r.getUsername());
				session.setAttribute("abrForamtion",abr);
				session.setAttribute("photo",photo);
			}
			if(bd.isEtudiantByEmail(user, password)||bd.isEtudiantByUsername(user, password)) {
				role.add("Etudiant");
			}
				bd.endConnection();
			if(!role.isEmpty()) {
				session.setAttribute("user", user);
				session.setAttribute("role", role);
				if(role.size()==1) {
					RequestDispatcher rd;
					switch(role.get(0)) {
					case "Administrateur":
						rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceAdministrateur.jsp");
						rd.forward(request, response);
						break;
					case "ChefDepartement":
						rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceChefDepartement.jsp");
						rd.forward(request, response);
						break;
					case "Enseignent":
						rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceEnseignent.jsp");
						rd.forward(request, response);
						break;
					case "ResponsableDeFormation":
						rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceResponsableDeFormation.jsp");
						rd.forward(request, response);
						break;
					case "Etudiant":
						rd = request.getRequestDispatcher("/WEB-INF/Espaces/EspaceEtudiant.jsp");
						rd.forward(request, response);
						break;
					}
				}
				else {
					//acess page
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/accesDisponible.jsp");
					rd.forward(request, response);
				}
			}
			else {
				//login page
				session.setAttribute("error", ++error);
				session.setAttribute("message", "errer "+error+" fois");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
				rd.forward(request, response);
			}
		}
		else {
			//blocked
			bd.startConnection();
			String MAC = blocked.getMacAddress();
			System.out.println(MAC);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			System.out.println("o");
			boolean b = bd.addBlockedMAC(MAC,format.format(date));
			System.out.println("o");
			if(b) {
				System.out.println("inset ok ok ");
			}
			bd.endConnectionWithOutResult();
			session.setAttribute("message", "tu est bloque 30 min");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/blocked.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
