package Modele;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

public class Enseignent extends Utilisateur {
	private Grade garde;
	private Map<Module,Groupe> Enseignentment;
	public Enseignent() {}
	public Enseignent(String nom, String prenom, String username, String email, String motDePass, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream  photo, Grade garde) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo);
		this.garde = garde;
	}
	public Enseignent(String nom, String prenom, String username, String email, String motDePass, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream  photo, Grade garde,
			Map<Module, Groupe> enseignentment) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo);
		this.garde = garde;
		Enseignentment = enseignentment;
	}
	public Enseignent(String nom, String prenom, String username, String email, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream  photo, Grade garde) {
		super(nom, prenom, username, email, dn, ln, sexe, adresse, situationFamiliale, photo);
		this.garde = garde;
	}
	public Enseignent(String nom, String prenom, String username, String email, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream  photo, Grade garde,
			Map<Module, Groupe> enseignentment) {
		super(nom, prenom, username, email, dn, ln, sexe, adresse, situationFamiliale, photo);
		this.garde = garde;
		Enseignentment = enseignentment;
	}
	public Grade getGarde() {
		return garde;
	}
	public Map<Module, Groupe> getEnseignentment() {
		return Enseignentment;
	}
	
}
