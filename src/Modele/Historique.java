package Modele;

import java.util.Date;

public class Historique {
	private int idHistorique;
	private String username;
	private Date date;
	private String action;
	public Historique(int idHistorique, String username, Date date, String action) {
		super();
		this.idHistorique = idHistorique;
		this.username = username;
		this.date = date;
		this.action = action;
	}
	public int getIdHistorique() {
		return idHistorique;
	}
	public String getUsername() {
		return username;
	}
	public Date getDate() {
		return date;
	}
	public String getAction() {
		return action;
	}
	
}
