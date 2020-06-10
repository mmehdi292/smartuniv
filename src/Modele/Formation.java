package Modele;

import java.util.ArrayList;

public class Formation {
	private String nomFormation;
	private String abrFormation;
	private String specialite;
	private Cycle cycle;
	private int annee;
	private String Departement;
	private ResponsableDeFormation responsable;
	private ArrayList<Module> modules;
	public Formation(String abrFormation) {
		super();
		this.abrFormation = abrFormation;
	}
	
	public Formation(String nomFormation, String abrFormation, String specialite, Cycle cycle, int annee,
			String departement) {
		super();
		this.nomFormation = nomFormation;
		this.abrFormation = abrFormation;
		this.specialite = specialite;
		this.cycle = cycle;
		this.annee = annee;
		Departement = departement;
	}

	public String getNomFormation() {
		return nomFormation;
	}
	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}
	public String getAbrFormation() {
		return abrFormation;
	}
	public void setAbrFormation(String abrFormation) {
		this.abrFormation = abrFormation;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public Cycle getCycle() {
		return cycle;
	}
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getDepartement() {
		return Departement;
	}
	public void setDepartement(String departement) {
		Departement = departement;
	}
	public ResponsableDeFormation getResponsable() {
		return responsable;
	}
	public void setResponsable(ResponsableDeFormation responsable) {
		this.responsable = responsable;
	}
	public ArrayList<Module> getModules() {
		return modules;
	}
	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}
	
}
