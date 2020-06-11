package Modele;

import java.util.ArrayList;

public class OperationGlobale {
	// la liste des formation
	public ArrayList<Formation> consulterFormation() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Formation> formations = bd.consulterFormation();
		bd.endConnection();
		return formations;
	}

	// get etudiant
	public Etudiant getEtudiant(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Etudiant etudiant = bd.getEtudiant(username);
		bd.endConnection();
		return etudiant;
	}

	// get module
	public Module getModule(String abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Module module = bd.getModule(abr);
		bd.endConnection();
		return module;
	}

	// get Formation
	public Formation getFormation(String abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Formation formation = bd.getFormation(abr);
		bd.endConnection();
		return formation;
	}

	// get Vacance
	public Vacance getVacance(int abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Vacance v = bd.getVacance(abr);
		bd.endConnection();
		return v;
	}

	// get seance
	public Seance getSeance(int abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Seance v = bd.getSeance(abr);
		bd.endConnection();
		return v;
	}
	// get Groupe
		public Groupe getGroupe(int abr) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Groupe v = bd.getGroupe(abr);
			bd.endConnection();
			return v;
		}

}
