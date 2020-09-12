package Modele;


import java.io.InputStream;

public class Absence {
	private int idAbsence;
	private boolean justifier;
	private Seance seance;
	private InputStream justification;
	private Etudiant etudiants;
	
	public Absence(int idAbsence, boolean justifier, Seance seance, InputStream justification, Etudiant etudiants) {
		super();
		this.idAbsence = idAbsence;
		this.justifier = justifier;
		this.seance = seance;
		this.justification = justification;
		this.etudiants = etudiants;
	}
	public Absence(Seance seance, Etudiant etudiant) {
		this.seance=seance;this.etudiants=etudiant;
	}
	public Absence(int idAbsence, Seance seance, Etudiant etudiant) {
		this.idAbsence=idAbsence;this.seance=seance;this.etudiants=etudiant;
	}
	public Absence(int idAbsence, InputStream justification, Seance seance, Etudiant e1) {
		this.idAbsence = idAbsence;
		this.seance = seance;
		this.justification = justification;
		this.etudiants = e1;
		
	}
	public int getIdAbsence() {
		return idAbsence;
	}
	public boolean isJustifier() {
		return justifier;
	}
	public Seance getSeance() {
		return seance;
	}
	public InputStream getJustification() {
		return justification;
	}
	public Etudiant getEtudiants() {
		return etudiants;
	}
	
}
