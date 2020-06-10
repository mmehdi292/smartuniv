package Modele;

import java.util.Date;

public class Vacance {
	private int id;
	private Date date;
	private String description;
	public Vacance(int id, Date date, String description) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
	}
	
	public Vacance(Date date, String description) {
		super();
		this.date = date;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public String getDescription() {
		return description;
	}
	
}
