package Modele;

import java.util.ArrayList;
import java.util.Date;

public class Seance {
	private int idSeance;
	private TypeSeance type;
	private Date temp;
	private int salle;
	private boolean avoirAbs;
	private Groupe groupe;
	private Module module;
	
	public Seance(int idSeance) {
		super();
		this.idSeance = idSeance;
	}
	public Seance(int idSeance, TypeSeance type, Date temp, int salle, boolean avoirAbs, Groupe groupe, Module module) {
		super();
		this.idSeance = idSeance;
		this.type = type;
		this.temp = temp;
		this.salle = salle;
		this.avoirAbs = avoirAbs;
		this.groupe = groupe;
		this.module = module;
	}
	public Seance(TypeSeance type, Date temp, int salle, boolean avoirAbs, Groupe groupe, Module module) {
		super();
		this.type = type;
		this.temp = temp;
		this.salle = salle;
		this.avoirAbs = avoirAbs;
		this.groupe = groupe;
		this.module = module;
	}
	public Seance(TypeSeance ty, Date temp, int salle, Module module, Groupe groupe) {
		this.type = ty;
		this.temp = temp;
		this.salle = salle;
		this.module = module;
		this.groupe=groupe;
	}
	public Seance(TypeSeance type, Date temp, int salle, Module module) {
		super();
		this.type = type;
		this.temp = temp;
		this.salle = salle;
		this.module = module;
	}
	public Seance(TypeSeance type, Groupe groupe, Module module) {
		this.type=type;this.groupe=groupe;this.module=module;
	}
	public Seance(int idSeance, TypeSeance ty, Date d, boolean ab, Groupe groupe, Module module) {
		this.idSeance=idSeance;
		this.type=ty;
		this.temp=d;
		this.avoirAbs=ab;
		this.groupe=groupe;
		this.module=module;
	}
	public int getIdSeance() {
		return idSeance;
	}
	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}
	public TypeSeance getType() {
		return type;
	}
	public void setType(TypeSeance type) {
		this.type = type;
	}
	public Date getTemp() {
		return temp;
	}
	public void setTemp(Date temp) {
		this.temp = temp;
	}
	public int getSalle() {
		return salle;
	}
	public void setSalle(int salle) {
		this.salle = salle;
	}
	public boolean isAvoirAbs() {
		return avoirAbs;
	}
	public void setAvoirAbs(boolean avoirAbs) {
		this.avoirAbs = avoirAbs;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	
}
