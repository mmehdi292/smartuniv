package Modele;

import java.io.InputStream;
import java.util.Date;

public class ResponsableDeFormation extends Enseignent{
	private Formation formation;

	public ResponsableDeFormation(String nom, String prenom, String username, String email, String motDePass, Date dn,
			String ln, Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream photo, Grade garde,
			Formation formation) {
		super(nom, prenom, username, email, motDePass, dn, ln, sexe, adresse, situationFamiliale, photo, garde);
		this.formation = formation;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public ResponsableDeFormation(String nom, String prenom, String username, String email, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream photo, Grade garde,
			Formation formation) {
		super(nom, prenom, username, email, dn, ln, sexe, adresse, situationFamiliale, photo, garde);
		this.formation = formation;
	}
	
	

}
