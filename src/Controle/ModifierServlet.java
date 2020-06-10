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

import Modele.Cycle;
import Modele.Formation;
import Modele.Groupe;
import Modele.Module;
import Modele.OperationAdministrateur;
import Modele.Seance;
import Modele.TypeSeance;
import Modele.Vacance;
import Modele.Module;

/**
 * Servlet implementation class ModifierServlet
 */
@WebServlet("/ModifierServlet")
public class ModifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		OperationAdministrateur oa = new OperationAdministrateur();
		RequestDispatcher rd;
		String type = request.getParameter("type");
		switch (type) {
		case "module":
			Module m1 = (Module) session.getAttribute("Module");
			String abr = m1.getAbrModule();
			String nom = request.getParameter("nom");
			String abrModule = request.getParameter("abrModule");
			int semester = Integer.parseInt(request.getParameter("semester"));
			String formation = request.getParameter("formation");
			Module m = new Module(nom, abrModule, semester, new Formation(formation));
			if (oa.modifierModule(m, abr)) {
				session.setAttribute("message", "ajouter avec succes");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "ajouter echoue");
				session.setAttribute("etat", "échoué");
			}
			rd = request.getRequestDispatcher("GestionModule");
			rd.forward(request, response);
			break;
		case "formation":
			Formation f1 = (Formation) session.getAttribute("Formation");
			String abrFormation = f1.getAbrFormation();
			String nomfrm = request.getParameter("nom");
			String abrfrm = request.getParameter("abr");
			String spefrm = request.getParameter("spe");
			String c1 = request.getParameter("cycle");
			Cycle cycle = null;
			switch (c1) {
			case "licence":
				cycle = Cycle.licence;
				break;
			case "master":
				cycle = Cycle.master;
				break;
			}
			int annee = Integer.parseInt(request.getParameter("Annee"));
			String dep = request.getParameter("dep");
			Formation f = new Formation(nomfrm, abrfrm, spefrm, cycle, annee, dep);
			if (oa.modifierFormation(f, abrFormation)) {
				session.setAttribute("message", "ajouter avec succes");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "ajouter echoue");
				session.setAttribute("etat", "échoué");
			}
			rd = request.getRequestDispatcher("GestionFormation");
			rd.forward(request, response);
			break;
		case "vacance":
			Vacance v1 = (Vacance) session.getAttribute("Vacance");
			int id = v1.getId();
			String n = request.getParameter("nom");
			String d = request.getParameter("date");
			SimpleDateFormat fr = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = fr.parse(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Vacance v = new Vacance(id, date, n);
			if (oa.modifierVacance(v)) {
				session.setAttribute("message", "ajouter avec succes");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "ajouter echoue");
				session.setAttribute("etat", "échoué");
			}
			rd = request.getRequestDispatcher("GestionVacance");
			rd.forward(request, response);
			break;
		case "seance":
			Seance s1 = (Seance) session.getAttribute("Seance");
			int idSeance = s1.getIdSeance();
			String t = request.getParameter("types");
			TypeSeance type1 =null;
			switch(t) {
				case "COUR":type1 =TypeSeance.COUR; break;
				case "TD":type1 =TypeSeance.TD; break;
				case "TP":type1 =TypeSeance.TP; break;
			}
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date1 = null;
			try {
				date1 = form.parse(request.getParameter("date")+" "+request.getParameter("heur")+":"+request.getParameter("min"));
			} catch (ParseException e) {

				e.printStackTrace();
			}
			int salle = Integer.parseInt(request.getParameter("salle"));
			String abs = request.getParameter("abs");
			boolean b = true;
			if(abs.equals("NO")) {
				b=false;
			}
			String idm = request.getParameter("module");
			int idg = Integer.parseInt(request.getParameter("groupe"));
			Seance seance = new Seance(idSeance,type1,date1,salle,b,new Groupe(idg),new Module(idm));
			if (oa.modifierSeance(seance)) {
				session.setAttribute("message", "ajouter avec succes");
				session.setAttribute("etat", "Succès");
			} else {
				session.setAttribute("message", "ajouter echoue");
				session.setAttribute("etat", "échoué");
			}
			rd = request.getRequestDispatcher("GestionSeance");
			rd.forward(request, response);
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
