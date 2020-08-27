package Modele;


import java.util.ArrayList;


public class OperationEtudiant {
	// get TOUTS les absences d'etudiant
		public  ArrayList<Absence> getAbsenceParUsername(String username){
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Absence> absences = bd.getAbsenceParUsername(username);
			bd.endConnection();
			return absences;
		}
		//
		public ArrayList<Seance> getETmp(String username) {
			ConnectionBD bd=new ConnectionBD();
			bd.startConnection();
			ArrayList<Seance> ars=new ArrayList<Seance>();
			ars=bd.getETmp(username);
			bd.endConnection();
			return ars;
		}

}
