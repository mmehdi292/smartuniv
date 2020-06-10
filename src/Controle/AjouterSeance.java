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
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.Seance;
import Modele.TypeSeance;

/**
 * Servlet implementation class AjouterSeance
 */
@WebServlet("/AjouterSeance")
public class AjouterSeance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterSeance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String t = request.getParameter("type");
		TypeSeance type =null;
		switch(t) {
			case "COUR":type =TypeSeance.COUR; break;
			case "TD":type =TypeSeance.TD; break;
			case "TP":type =TypeSeance.TP; break;
		}
		int nbrS = Integer.parseInt(request.getParameter("nbr"));
		int total = Integer.parseInt(request.getParameter("total"));
		String d = request.getParameter("date");
		int heur = Integer.parseInt(request.getParameter("heur"));
		int min = Integer.parseInt(request.getParameter("min"));
		Date date = null;
		try {
			date = frmt.parse(d+" "+heur+":"+min);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		int salle = Integer.parseInt(request.getParameter("salle"));
		
		String abs = request.getParameter("abs");
		boolean b = true;
		if(abs.equals("NO")) {
			b=false;
		}
		String module = request.getParameter("module");
		int groupe = Integer.parseInt(request.getParameter("groupe"));
		Seance m2 = null;
		if(nbrS==2) {
			String d2 = request.getParameter("date2");
			int heur2 = Integer.parseInt(request.getParameter("heur2"));
			int min2 = Integer.parseInt(request.getParameter("min2"));
			Date date2 = null;
			try {
				date2 = frmt.parse(d2+" "+heur2+":"+min2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int salle2 = Integer.parseInt(request.getParameter("salle2"));
			m2 = new Seance(type,date2,salle2,b,new Groupe(groupe),new Module(module));
		}
		Seance m = new Seance(type,date,salle,b,new Groupe(groupe),new Module(module));
		
		OperationAdministrateur oa = new OperationAdministrateur();
		if(oa.ajouterSeance(m,m2,total)) {
			session.setAttribute("message","ajouter avec succes");
			session.setAttribute("etat","Succès");
		}
		else {
			session.setAttribute("message","ajouter echoue");
			session.setAttribute("etat","échoué");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GestionSeance");
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
