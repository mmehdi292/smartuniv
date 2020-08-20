package Modele;

public class EnseiModuleGroupe {
	Enseignent ensiegnent;
	Module module;
	Groupe groupe;
	TypeSeance type;
	public EnseiModuleGroupe(Enseignent ensiegnent, Module module, Groupe groupe,TypeSeance type) {
		super();
		this.ensiegnent = ensiegnent;
		this.module = module;
		this.groupe = groupe;
		this.type=type;
	}
	public EnseiModuleGroupe(Module module, Groupe groupe,TypeSeance type) {
		super();
		this.module = module;
		this.groupe = groupe;
		this.type=type;
	}
	public Enseignent getEnsiegnent() {
		return ensiegnent;
	}
	public Module getModule() {
		return module;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public TypeSeance getType() {
		return type;
	}
	
	
}
