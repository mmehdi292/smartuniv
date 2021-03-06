package Modele;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class OperationChefDepartement {
	// get TOUTS les modules d'un deparement
	public ArrayList<Module> getModuleDeDepartement(String nomdepartemnt) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Module> modules = bd.getModuleDeDepartement(nomdepartemnt);
		bd.endConnection();
		return modules;
	}

	// get TOUTS les modules d'un deparement
	public ArrayList<String> getTypes(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<String> types = bd.getTypes(abrModule);
		bd.endConnection();
		return types;
	}

	// get TOUTS les groupe d'un module
	public ArrayList<Groupe> getGroupesDunModule(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Groupe> groupes = bd.getGroupesDunModule(abrModule);
		bd.endConnection();
		return groupes;
	}

	// affecter les seanses aux etudiant
	public boolean faireAffectation(String username, String abrModule, int idgroupe, String type) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.faireAffectation(username, abrModule, idgroupe, type);
		bd.endConnectionWithOutResult();
		return b;
	}

	// get les affectation d'un module
	public ArrayList<EnseiModuleGroupe> getAffectationDunModule(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<EnseiModuleGroupe> b = bd.getAffectationDunModule(abrModule);
		bd.endConnection();
		return b;
	}
	// delete les affectations d'un module
	public boolean deleteAffectataion(String abrModule) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			boolean b = bd.deleteAffectataion(abrModule);
			bd.endConnectionWithOutResult();
			return b;
	}
	// get les affectation d'un module
	public ArrayList<Groupe> getGroupeDeDepartement(String nomDepartement){
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Groupe> b = bd.getGroupeDeDepartement(nomDepartement);
			bd.endConnection();
			return b;
	}
	// get touts les etudiants d'un formation
	public ArrayList<Etudiant> getToutsLesEtudiants(String abrForamtion) {
				ConnectionBD bd = new ConnectionBD();
				bd.startConnection();
				ArrayList<Etudiant> b = bd.getToutsLesEtudiants(abrForamtion);
				bd.endConnection();
				return b;
	}
	////
	// delete les affectations d'un groupe
		public boolean suppAffectationEtudaint(int idgroupe) {
				ConnectionBD bd = new ConnectionBD();
				bd.startConnection();
				boolean b = bd.suppAffectationEtudaint(idgroupe);
				bd.endConnectionWithOutResult();
				return b;
		}
		// faire les affectations d'un groupes
		public boolean faireAffectationDesGroupeAuxEtudiant(String username,int idgroupe) {
				ConnectionBD bd = new ConnectionBD();
				bd.startConnection();
				boolean b = bd.faireAffectationDesGroupeAuxEtudiant(username,idgroupe);
				bd.endConnectionWithOutResult();
				return b;
		}
		// get les affectation d'un groupe
		public ArrayList<Etudiant> getAffectationDunGroupe(int idGroupe){
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Etudiant> b = bd.getAffectationDunGroupe(idGroupe);
			bd.endConnectionWithOutResult();
			return b;
		}
		// les statiques chef
		public ArrayList<Absence> toutsLesAbsenceParChef(String Departement){
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Absence> b = bd.toutsLesAbsenceChef(Departement);
			bd.endConnection();
			return b;
		}
		
		public String getNomDepartement(String usernameChef)  {
			ConnectionBD bd=new ConnectionBD();
			bd.startConnection();
			String ae = null;
			ae = bd.getNomDepartement(usernameChef);
			bd.endConnection();
			System.out.println("connection start ok");
			return ae;
		}
		
		public ArrayList<Absence> getInfoAbsencesPourListeExclusPourTroisAbsences(String departement){
			ArrayList<Absence> ae=new ArrayList<Absence>();
			ConnectionBD bd=new ConnectionBD();
			bd.startConnection();
			ae=bd.getInfoAbsencesPourListeExclusPourTroisAbsences(departement);
			bd.endConnection();
			System.out.println("connection start ok");
			return ae;
		}
		public ArrayList<Absence> getInfoAbsencesPourListeExclusTous(String departement){
			ArrayList<Absence> ae=new ArrayList<Absence>();
			ConnectionBD bd=new ConnectionBD();
			bd.startConnection();
			ae=bd.getInfoAbsencesPourListeExclusTous(departement);
			bd.endConnection();
			System.out.println("connection start ok");
			return ae;
		}

		public ArrayList<Seance> seanceModuleGroupeDep(String departement) {
			ArrayList<Seance> ae=new ArrayList<Seance>();
			ConnectionBD bd=new ConnectionBD();
			bd.startConnection();
			ae=bd.seanceModuleGroupeDep(departement);
			bd.endConnection();
			System.out.println("connection start ok");
			return ae;
		}
		public ArrayList<Seance> getSeances(int groupe, String module, String type) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Seance> s = bd.getSeances(groupe, module, type);
			bd.endConnection();
			return s;
		}

		public ArrayList<Absence> listeAbsencesJustifie(String departement) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Absence> b = bd.listeAbsencesJustifie(departement);
			bd.endConnection();
			return b;
		}

		public Absence getAbsence(int idAbsence) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Absence a = bd.getAbsence(idAbsence);
			bd.endConnection();
			return a;
		}

		public boolean accepterJustification(int idAbsence) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			boolean b = bd.accepterJustification(idAbsence);
			bd.endConnectionWithOutResult();
			return b;
		}
		public boolean refuserJustification(int idAbsence) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			boolean b = bd.refuserJustification(idAbsence);
			bd.endConnectionWithOutResult();
			return b;
		}

		public ArrayList<Absence> getAbsenceParNom(String nom, String prenom, String departement) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Absence>  b = bd.getAbsenceParNom(nom,prenom,departement);
			bd.endConnectionWithOutResult();
			return b;
		}
		//-------------------- gestion historique ------------------
		// conslutation
		public ArrayList<Historique> consulterHistoriqueChef(String departement) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Historique> his = bd.consulterHistoriqueChef(departement);
			bd.endConnection();
			return his;
		}
		public String getDepartementUtilisateur(String username) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			String his = bd.getDepartementUtilisateur(username);
			bd.endConnection();
			return his;
		}
		
		
		
		
}
