package Modele;

import java.io.InputStream;
import java.util.Date;

public class Administrateur extends Utilisateur {

	public Administrateur() {
		super();
	}

	public Administrateur(String nom, String prenom, String username, String email, Date dn, String ln, Sexe sexe,
			String adresse, SituationFamiliale situationFamiliale, InputStream photo) {
		super(nom, prenom, username, email, dn, ln, sexe, adresse, situationFamiliale, photo);
	}

	public Administrateur(String nom, String prenom, String username, String email, String motDePass, Date dn,
			String ln, Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream photo) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo);
	}

}
