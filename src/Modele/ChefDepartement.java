package Modele;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class ChefDepartement extends Enseignent {
	private String nomDepartement;
	private ArrayList<Formation> formation;
	public ChefDepartement(String nom, String prenom, String username, String email, String motDePass, Date dn,
			String ln, Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream photo, Grade garde,
			String nomDepartement) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo, garde);
		this.nomDepartement = nomDepartement;
	}
	public String getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	public ArrayList<Formation> getFormation() {
		return formation;
	}
	public void setFormation(ArrayList<Formation> formation) {
		this.formation = formation;
	}
	public ChefDepartement(String nom, String prenom, String username, String email, Date dn, String ln, Sexe sexe,
			String adresse, SituationFamiliale situationFamiliale, InputStream photo, Grade garde,
			String nomDepartement) {
		super(nom, prenom, username, email, dn, ln, sexe, adresse, situationFamiliale, photo, garde);
		this.nomDepartement = nomDepartement;
	}
	
	
}
