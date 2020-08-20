package Modele;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class Etudiant extends Utilisateur {
	public Etudiant(String nom, String prenom, String username, String email, String motDePass, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream photo, Formation formation) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo);
		this.formation = formation;
	}
	
	
	public Etudiant(String username) {
		super(username);
	}
	


	public Etudiant(String nom, String prenom, String username) {
		super(nom, prenom, username);
	}



	private Formation formation;
	private ArrayList<Module> modules; //Dans le cas o� l'�tudiant a des modules pour le retudier (progression)
	private ArrayList<Groupe> groupes;//Dans le cas de plusieurs groupe(groupe simple , groupe progression , groupe atlier)
	private ArrayList<Absence> absences;
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public ArrayList<Module> getModules() {
		return modules;
	}
	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}
	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(ArrayList<Groupe> groupes) {
		this.groupes = groupes;
	}
	public ArrayList<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(ArrayList<Absence> absences) {
		this.absences = absences;
	}
	
}
