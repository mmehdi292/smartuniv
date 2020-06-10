package Modele;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String username;
	private String email;
	private String motDePass;
	private Date dn;
	private String ln;
	private Sexe sexe;
	private String adresse;
	private SituationFamiliale situationFamiliale;
	private InputStream photo;
	public Utilisateur() {}
	public Utilisateur(String nom, String prenom, String username, String email, String motDePass, Date dn, String ln,
			Sexe sexe, String adresse, SituationFamiliale situationFamiliale, InputStream  photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.motDePass = motDePass;
		this.dn = dn;
		this.ln = ln;
		this.sexe = sexe;
		this.adresse = adresse;
		this.situationFamiliale = situationFamiliale;
		this.photo = photo;
	}
	public Utilisateur(String nom, String prenom, String username, String email, Date dn, String ln, Sexe sexe,
			String adresse, SituationFamiliale situationFamiliale, InputStream  photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.dn = dn;
		this.ln = ln;
		this.sexe = sexe;
		this.adresse = adresse;
		this.situationFamiliale = situationFamiliale;
		this.photo = photo;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getMotDePass() {
		return motDePass;
	}
	public Date getDn() {
		return dn;
	}
	public String getLn() {
		return ln;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public String getAdresse() {
		return adresse;
	}
	public SituationFamiliale getSituationFamiliale() {
		return situationFamiliale;
	}
	public InputStream  getPhoto() {
		return photo;
	}
	public void setMotDePass(String motDePass) {
		this.motDePass = motDePass;
	}
	
}
