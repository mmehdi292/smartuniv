package Controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Absence;
import Modele.Etudiant;
import Modele.Formation;
import Modele.Groupe;
import Modele.Module;
import Modele.OperationChefDepartement;
import Modele.Seance;
import Modele.TypeSeance;


@WebServlet("/listeAbsencesJustifie")
public class listeAbsencesJustifie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public listeAbsencesJustifie() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OperationChefDepartement op=new OperationChefDepartement();
		ArrayList<Absence> ae=new ArrayList<Absence>();
		HttpSession session=request.getSession();
		
		String username=(String) session.getAttribute("user");
		String departement=op.getNomDepartement(username);
		ae=op.listeAbsencesJustifie(departement);
		
		session.setAttribute("listAbsencesJustifie",ae );
		
		RequestDispatcher disp=request.getRequestDispatcher("/WEB-INF/EspaceChef/listeAbsencesJustifie.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
