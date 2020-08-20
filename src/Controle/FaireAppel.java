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

import Modele.EnseiModuleGroupe;
import Modele.OperationEnseignent;
import Modele.Seance;

/**
 * Servlet implementation class FaireAppel
 */
@WebServlet("/FaireAppel")
public class FaireAppel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaireAppel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OperationEnseignent oe = new OperationEnseignent();
		HttpSession session = request.getSession();
		try {
			String choix = request.getParameter("choix");
			System.out.println(choix);
			String[] table = choix.split("-");
			int x =Integer.parseInt(table[0]);
			ArrayList<Seance> s = oe.getSeances(x, table[1], table[2]);
			session.setAttribute("Seances", s);
			session.setAttribute("groupe", table[0]);
			session.setAttribute("module", table[1]);
		}
		catch(Exception e) {
			System.out.println("error");
		}
		
		
		String username = (String) session.getAttribute("user");
		ArrayList<EnseiModuleGroupe> r = oe.enseiModuleGroupe(username);
		session.setAttribute("emg", r);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/FaireAppel.jsp");
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
