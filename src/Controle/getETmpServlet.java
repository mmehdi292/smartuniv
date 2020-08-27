package Controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.OperationEtudiant;
import Modele.Seance;

/**
 * Servlet implementation class getETmpServlet
 */
@WebServlet("/getETmpServlet")
public class getETmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public getETmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//****************  dayOfWeek *******
		//Calendar c = Calendar.getInstance();
		//int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		//////////////////////////////////////////////////////////////////
		
		OperationEtudiant op=new OperationEtudiant();
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("user");
		ArrayList<Seance> ars=new ArrayList<Seance>();
		ars=op.getETmp(username);
		System.out.println(username);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		

		String tab[]=new String[25];
		Calendar calendar = Calendar.getInstance();
		for(Seance s:ars){	
			calendar.setTime(s.getTemp());
			switch (calendar.get(Calendar.DAY_OF_WEEK)){
			case 1:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[0]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[1]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[2]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[3]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[4]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 2:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[5]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[6]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[7]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[8]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[9]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 3:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[10]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[11]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[12]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[13]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[14]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 4:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[15]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[16]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[17]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[18]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[19]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 5:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[20]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[21]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[22]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[23]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[24]=s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			default:System.out.println("default day of session");
			}
		} 
		for(int m=0;m<25;m++){
			if(tab[m]==null) tab[m]=" ";
		}
		
		session.setAttribute("listETmp", tab);
				
		RequestDispatcher dispatch=request.getRequestDispatcher("/WEB-INF/EspaceEtudiant/ConsulterETmp.jsp");
		dispatch.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
