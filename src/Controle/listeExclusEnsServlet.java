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
import Modele.OperationEnseignent;
import Modele.Seance;
import Modele.TypeSeance;


@WebServlet("/listeExclusEnsServlet")
public class listeExclusEnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public listeExclusEnsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servleeeeeeeeet");
		OperationEnseignent op=new OperationEnseignent();
		ArrayList<Absence> ae=new ArrayList<Absence>();
		ArrayList<Absence> ae2=new ArrayList<Absence>();
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("user");	
		System.out.println("user servlet "+username);
		ae=op.getInfoAbsencesPourListeExclusPourTroisAbsencesEns(username);
		ae2=op.getInfoAbsencesPourListeExclusTousEns(username);

		ArrayList<Absence> aret=new ArrayList<Absence>();
		ArrayList<Absence> aret2=new ArrayList<Absence>();

//------------------------------------------------------------------------------------------------
		int co=0;
		for(Absence a:ae) System.out.println(a.getEtudiants().getUsername());
		for(int i1=0;i1<ae2.size();i1++){
			Absence abs=ae2.get(i1);
			for(int i2=i1;i2<ae2.size();i2++){
				
				if((abs!=null)&&(ae2.get(i2)!=null) && (abs.getEtudiants().getUsername().equals(ae2.get(i2).getEtudiants().getUsername())) && (abs.getSeance().getModule().getAbrModule().equals(ae2.get(i2).getSeance().getModule().getAbrModule())) && (abs.getSeance().getType().equals(ae2.get(i2).getSeance().getType())))
				{	co++;
					ae2.set(i2, null);
				}
			}
			if((co>=5 && abs.getSeance().isAvoirAbs()==true)){
				System.out.println("co>=5");
				String user=abs.getEtudiants().getUsername();
				String module=abs.getSeance().getModule().getAbrModule();
				TypeSeance type=abs.getSeance().getType();
				String nom=abs.getEtudiants().getNom();
				String prenom=abs.getEtudiants().getPrenom();
				int groupe=abs.getSeance().getGroupe().getIdGroupe();
				String formation=abs.getEtudiants().getFormation().getAbrFormation();
				Absence absence=new Absence(new Seance(type,new Groupe(groupe),new Module(module)),new Etudiant(user,nom,prenom,new Formation(formation)));
				aret2.add(absence);
				//aret.
			}
			co=0;
		}
//-------------------------------------------------------------------------------------------------------
		for(int i1=0;i1<ae.size();i1++){
			Absence abs=ae.get(i1);
			for(int i2=i1;i2<ae.size();i2++){
				if((abs!=null)&&(ae.get(i2)!=null) && (abs.getEtudiants().getUsername().equals(ae.get(i2).getEtudiants().getUsername())) && (abs.getSeance().getModule().getAbrModule().equals(ae.get(i2).getSeance().getModule().getAbrModule())) && (abs.getSeance().getType().equals(ae.get(i2).getSeance().getType())))
				{	co++;
					ae.set(i2, null);
				}
			}
			if((co>=3 && abs.getSeance().isAvoirAbs()==true)){
				System.out.println("co>=3");
				String user=abs.getEtudiants().getUsername();
				String module=abs.getSeance().getModule().getAbrModule();
				TypeSeance type=abs.getSeance().getType();
				String nom=abs.getEtudiants().getNom();
				String prenom=abs.getEtudiants().getPrenom();
				int groupe=abs.getSeance().getGroupe().getIdGroupe();
				String formation=abs.getEtudiants().getFormation().getAbrFormation();				
				Absence absence=new Absence(new Seance(type,new Groupe(groupe),new Module(module)),new Etudiant(user,nom,prenom,new Formation(formation)));
				boolean b=false;
				for(Absence a:aret2) {
					if(a.getEtudiants().getUsername().equals(abs.getEtudiants().getUsername())) {
						b=true;break;
					}
				}
				if(!b) aret.add(absence);
			}
			co=0;
		}
		
		for(Absence a:aret2) {
			aret.add(a);
		}
		
		session.setAttribute("listExclusEns",aret );
		
		RequestDispatcher disp=request.getRequestDispatcher("/WEB-INF/EscapeEnseignant/listeExclusEns.jsp");
		disp.forward(request, response);

	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
