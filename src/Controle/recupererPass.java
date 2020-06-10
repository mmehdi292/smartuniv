package Controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.ConnectionBD;
import Modele.Email;
import Modele.ResetMotDePass;

/**
 * Servlet implementation class recupererPass
 */
@WebServlet("/recupererPass")
public class recupererPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public recupererPass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static String getURLWithContextPath(HttpServletRequest request) {
		   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String token = (String) request.getParameter("token");
		HttpSession session = request.getSession();
		ConnectionBD bd = new ConnectionBD("root", "root", "smartuniv");
		bd.startConnection();
		System.out.println("Start conncetion S1");
		if (token == null) {
			System.out.println("token null");
			if (bd.existEmail(email)) {
				System.out.println("email here");
				token = UUID.randomUUID().toString().replace("-", "");
				ResetMotDePass reset = new ResetMotDePass(token, email);
				if (!bd.existEmailInReset(email) && bd.insertToken(reset)) {
					System.out.println("insert is ok");
					Email e_mail = new Email();
					e_mail.sendCode(token, email,getURLWithContextPath(request));
					System.out.println("Send message ok");
					session.setAttribute("message", "verifier votre email");
					bd.endConnection();
					System.out.println("end connection ");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
					rd.forward(request, response);
				} else {
					System.out.println("insert false");
					session.setAttribute("message", "le message deja envoyer");
					bd.endConnection();
					System.out.println("end connection ");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
					rd.forward(request, response);
				}
			} else {
				System.out.println("email fasle");
				session.setAttribute("message", "il y a un erreur");
				bd.endConnection();
				System.out.println("end connection ");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/login.jsp");
				rd.forward(request, response);
			}
		} else {
			System.out.println("token not null");
			ResetMotDePass reset = bd.existTokenInReset(token);
			System.out.println("object created");
			if (reset == null) {
				System.out.println("object null");
				bd.endConnection();
				System.out.println("end connection ");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/template/erreur.jsp");
				rd.forward(request, response);
			} else {
				session.setAttribute("reset", reset);
				System.out.println("object not null ");
				bd.endConnection();
				System.out.println("end connection ");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/authentification/ResetPass2.jsp");
				rd.forward(request, response);
			}
		}

	}

}
