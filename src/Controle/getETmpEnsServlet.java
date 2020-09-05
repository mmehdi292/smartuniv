package Controle;

import java.io.IOException;
import java.text.ParseException;
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

import Modele.OperationEnseignent;
import Modele.Seance;


@WebServlet("/getETmpEnsServlet")
public class getETmpEnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public getETmpEnsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		OperationEnseignent op=new OperationEnseignent();
		String username=(String) session.getAttribute("user");
		ArrayList<Seance> ars=new ArrayList<Seance>();
		ars=op.getETmpEns(username);
		System.out.println("usernaem :"+username);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		
		
		String tab[]=new String[25];
		Calendar calendar = Calendar.getInstance();
		
		for(Seance s:ars){	
			calendar.setTime(s.getTemp());
			System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
			System.out.println("G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle());
			System.out.println("hour "+calendar.get(Calendar.HOUR_OF_DAY));
			switch (calendar.get(Calendar.DAY_OF_WEEK)){
			case 1:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[0]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[1]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[2]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[3]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[4]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 2:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[5]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[6]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[7]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[8]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[9]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 3:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[10]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[11]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[12]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[13]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[14]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 4:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[15]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[16]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[17]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[18]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[19]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			case 5:
				if(calendar.get(Calendar.HOUR_OF_DAY)==8&&calendar.get(Calendar.MINUTE)==30) {tab[20]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==10&&calendar.get(Calendar.MINUTE)==0) {tab[21]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==11&&calendar.get(Calendar.MINUTE)==30){tab[22]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==13&&calendar.get(Calendar.MINUTE)==0) {tab[23]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
				if(calendar.get(Calendar.HOUR_OF_DAY)==14&&calendar.get(Calendar.MINUTE)==30){tab[24]="G"+s.getGroupe().getIdGroupe()+" "+s.getType()+" "+s.getModule().getAbrModule()+" S"+s.getSalle();break;}
			default:System.out.println("default day of session");
			}
		} 
		for(int m=0;m<25;m++){
			System.out.println(tab[m]);
		}
		for(int m=0;m<25;m++){
			if(tab[m]==null) tab[m]=" ";
		}
		
		
		session.setAttribute("listETmp", tab);
		System.out.println("before dispatch");
		RequestDispatcher dispatch=request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/ConsulterETmpEns.jsp");
		dispatch.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
