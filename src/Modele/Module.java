package Modele;

import java.util.ArrayList;

public class Module {
	private String nomModule;
	private String abrModule;
	private int semester;
	private Formation formation;
	private ArrayList<Enseignent> enseignents;
	
	public Module(String nomModule, String abrModule, int semester, Formation formation) {
		super();
		this.nomModule = nomModule;
		this.abrModule = abrModule;
		this.semester = semester;
		this.formation = formation;
	}
	
	public Module(String abrModule) {
		super();
		this.abrModule = abrModule;
	}

	public Module(String abrModule, int semester) {
		this.abrModule=abrModule;
		this.semester=semester;
	}

	public String getNomModule() {
		return nomModule;
	}
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}
	public String getAbrModule() {
		return abrModule;
	}
	public void setAbrModule(String abrModule) {
		this.abrModule = abrModule;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public ArrayList<Enseignent> getEnseignents() {
		return enseignents;
	}
	public void setEnseignents(ArrayList<Enseignent> enseignents) {
		this.enseignents = enseignents;
	}
	

}
