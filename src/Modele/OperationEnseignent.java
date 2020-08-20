package Modele;

import java.io.InputStream;
import java.util.ArrayList;

public class OperationEnseignent {
	// les groupes d'un ensiengnent
	public ArrayList<EnseiModuleGroupe> enseiModuleGroupe(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<EnseiModuleGroupe> m = bd.enseiModuleGroupe(username);
		bd.endConnection();
		return m;
	}

	// les groupes d'un ensiengnent
	public ArrayList<Seance> getSeances(int groupe, String module, String type) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Seance> s = bd.getSeances(groupe, module, type);
		bd.endConnection();
		return s;
	}

	// les etudiants d'un groupe
	public ArrayList<Etudiant> getEtudiantGroupe(int groupe) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Etudiant> s = bd.getEtudiantGroupe(groupe);
		bd.endConnection();
		return s;
	}

	// ajoutes les absences
	public boolean addAbsences(int idseance, ArrayList<String> username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();

		Boolean b = bd.deleteAbsence(idseance);
		for (String s : username) {
			if (!bd.verifieAbsence(idseance, s)) {
				b = bd.addAbsence(idseance, s);
			}
			if (!b) {
				System.out.println("il y a un erreur");
			}
		}
		bd.endConnectionWithOutResult();
		return b;
	}

	// ajoutes les absences
	public ArrayList<Absence> getAbsencesDunSeance(int idseance) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Absence> b = bd.getAbsencesDunSeance(idseance);
		bd.endConnection();
		return b;
	}
	// ajoutes les absences
		public ArrayList<Absence> getAbsencesDunSeanceAvacEtudaint(int idseance) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Absence> b = bd.getAbsencesDunSeanceAvacEtudaint(idseance);
			bd.endConnection();
			return b;
		}

	//
	public boolean insertJustification(int idabsence, InputStream photo) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.insertJustification(idabsence,photo);
		bd.endConnectionWithOutResult();
		return b;
	}
	//
	public ArrayList<Absence> getAbsenceParNom(String nom,String prenom) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Absence>  b = bd.getAbsenceParNom(nom,prenom);
		bd.endConnectionWithOutResult();
		return b;
	}
}
