package Modele;

import java.util.ArrayList;

public class Groupe {
	private int idGroupe;
	private int numGroupe;
	private int section;
	private Formation formation;
	private ArrayList<Etudiant> etudiants;
	private ArrayList<Seance> seances;
	public Groupe(int idGroupe) {
		super();
		this.idGroupe = idGroupe;
	}
	
	public Groupe(int idGroupe, int numGroupe, int section, Formation formation) {
		super();
		this.idGroupe = idGroupe;
		this.numGroupe = numGroupe;
		this.section = section;
		this.formation = formation;
	}

	public int getIdGroupe() {
		return idGroupe;
	}
	public int getNumGroupe() {
		return numGroupe;
	}
	public int getSection() {
		return section;
	}
	public Formation getFormation() {
		return formation;
	}
	public ArrayList<Etudiant> getEtudiants() {
		return etudiants;
	}
	public ArrayList<Seance> getSeances() {
		return seances;
	}
	

}
