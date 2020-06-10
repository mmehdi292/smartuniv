package Modele;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.util.converter.DateTimeStringConverter;

public class OperationAdministrateur {
	// voir touts les ensiengant
	public ArrayList<Enseignent> consulterEnseignant() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Enseignent> enseignents = bd.consulterEnseignant();
		bd.endConnection();
		return enseignents;
	}

	// ajouter un enseignant
	// ---------------------
	// add admin
	public boolean addAdmin(Administrateur e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.addAdmin(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// add res
	public boolean addChef(ChefDepartement e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.addchef(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// add chef
	public boolean addRes(ResponsableDeFormation e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.addres(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modifier ensignant
	public boolean modEns(Enseignent e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.modifierEns(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modifier admin
	public boolean modAdmin(Administrateur a) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.modifierAdmin(a);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modifier chef
	public boolean modChef(ChefDepartement c) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.modifierChef(c);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modifier responsable
	public boolean modRes(ResponsableDeFormation r) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		boolean b = bd.modifierRes(r);
		bd.endConnectionWithOutResult();
		return b;
	}

	// delete admin
	public boolean delectAdmin(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.deleteAdmin(username);
		bd.endConnectionWithOutResult();
		return b;
	}

	// delete chef
	public boolean delectChef(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.deleteChef(username);
		bd.endConnectionWithOutResult();
		return b;
	}

	// delete res
	public boolean delectRes(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.deleteRes(username);
		bd.endConnectionWithOutResult();
		return b;
	}

	// ------------------------------------------ Gestion etudiant
	// -----------------------------
	// voir touts les ensiengant
	public ArrayList<Etudiant> consulterEtudiant() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Etudiant> etudiants = bd.consulterEtudiant();
		bd.endConnection();
		return etudiants;
	}

	// ajouter etudiant
	public boolean ajouterEtudiant(Etudiant e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.ajouterEtudiant(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// supprimer etudtiant
	public boolean suppEtudiant(String username) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.suppEtudiant(username);
		bd.endConnectionWithOutResult();
		return b;
	}

	// supprimer etudtiant
	public boolean modifierEtudiant(Etudiant e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = false;
		if (e.getMotDePass().length() == 0) {
			b = bd.modifierEtudiantSansPass(e);
		} else {
			b = bd.modifierEtudiant(e);
		}
		bd.endConnectionWithOutResult();
		return b;
	}

	// ----------------------- gestion modules ---------------------
	// voir touts les ensiengant
	public ArrayList<Module> consulterModule() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Module> modules = bd.consulterModule();
		bd.endConnection();
		return modules;
	}

	// ajouter module
	public boolean ajouterModule(Module e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.ajouterModule(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// supprimer module
	public boolean suppModule(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.suppModule(abrModule);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modfier module
	public boolean modifierModule(Module e, String abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.modifierModule(e, abr);
		bd.endConnectionWithOutResult();
		return b;
	}

	// ------------------------ gestion formation -------------------
	// voir touts les formation
	public ArrayList<Formation> consulterFormation() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Formation> formations = bd.consulterFormation();
		bd.endConnection();
		return formations;
	}

	// ajouter formation
	public boolean ajouterFormation(Formation e) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.ajouterFormation(e);
		bd.endConnectionWithOutResult();
		return b;
	}

	// supprimer formation
	public boolean suppFormation(String abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.suppFormation(abr);
		bd.endConnectionWithOutResult();
		return b;
	}

	// modfier formation
	public boolean modifierFormation(Formation e, String abr) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.modifierFormation(e, abr);
		bd.endConnectionWithOutResult();
		return b;
	}
	//-------------------- gestion historique ------------------
	// conslutation
	public ArrayList<Historique> consulterHistorique() {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Historique> his = bd.consulterHistorique();
		bd.endConnection();
		return his;
	}
	//------------------------------Gestion seance-----------------------------
	//consultaion
		public ArrayList<Seance> consulterSeances() {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Seance> his = bd.consulterSeances();
			bd.endConnection();
			return his;
		}
		// ajouter seance
		public boolean ajouterSeance(Seance e,Seance e2,int total) {
			ConnectionBD bd = new ConnectionBD();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			bd.startConnection();
			ArrayList<Vacance> vs =bd.consulterVacances();
			Calendar cal = Calendar.getInstance();
			Boolean b = true;
			
			while(total != 0 && b) {
				Boolean add = true;
				if(e2 == null) {
					for(Vacance v : vs)
						if(sdf.format(v.getDate()).equals(sdf.format(e.getTemp())))
							add =false;
					if(add) {
						b = bd.ajouterSeance(e);
						total--;
					}
					cal.setTime(e.getTemp());
					cal.add(Calendar.DAY_OF_YEAR, 7);
					e.setTemp(cal.getTime());
				}
				else {
					for(Vacance v : vs)
						if(sdf.format(v.getDate()).equals(sdf.format(e.getTemp())))
							add =false;
					if(add) {
						b = bd.ajouterSeance(e);
						total--;
					}
					cal.setTime(e.getTemp());
					cal.add(Calendar.DAY_OF_YEAR, 7);
					e.setTemp(cal.getTime());
					add = true;
					if(total!=0) {
						for(Vacance v : vs)
							if(sdf.format(v.getDate()).equals(sdf.format(e2.getTemp())))
								add =false;
						if(add) {
							b = bd.ajouterSeance(e2);
							total--;
						}
						cal.setTime(e2.getTemp());
						cal.add(Calendar.DAY_OF_YEAR, 7);
						e2.setTemp(cal.getTime());
					}
				}
			}
			bd.endConnectionWithOutResult();
			return b;
		}
		// modfier seance
		public boolean modifierSeance(Seance e) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Boolean b = bd.modifierSeance(e);
			bd.endConnectionWithOutResult();
			return b;
		}
		// supprimer Seance
		public boolean suppSeance(int id) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Boolean b = bd.suppSeance(id);
			bd.endConnectionWithOutResult();
			return b;
		}
	//------------------------------Gestion Vacance-----------------------------
	//consultaion
		public ArrayList<Vacance> consulterVacances() {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Vacance> his = bd.consulterVacances();
			bd.endConnection();
			return his;
		}
		// ajouter formation
		public boolean ajouterVacances(String nom,Date dateD,Date dateF) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Date date = dateD;
			Date date1 = dateF;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//Getting the default zone id
			ZoneId defaultZoneId = ZoneId.systemDefault();
				
			//Converting the date to Instant
			Instant instant = date.toInstant();
			instant = date.toInstant();
				
			//Converting the Date to LocalDate
			LocalDate startDate = instant.atZone(defaultZoneId).toLocalDate();
			instant = date1.toInstant();
			LocalDate endDate = instant.atZone(defaultZoneId).toLocalDate();
			ArrayList<Vacance> vs =  consulterVacances();
			Boolean b = false;
			for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1))
			{
				Date res = Date.from(d.atStartOfDay(defaultZoneId).toInstant());
				Vacance v= new Vacance(res,nom);
				Boolean test = false;
				for(Vacance vac : vs)
					if(sdf.format(vac.getDate()).equals(sdf.format(v.getDate())))
						test=true;
				if(!test)
					b = bd.ajouterVacance(v);
				
				if(b==false)
					break;
			}
			bd.endConnectionWithOutResult();
			return b;
		}
		// modfier vacance
		public boolean modifierVacance(Vacance e) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Boolean b = bd.modifierVacance(e);
			bd.endConnectionWithOutResult();
			return b;
		}
		// supprimer vacance
		public boolean suppVacance(int id) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			Boolean b = bd.suppVacance(id);
			bd.endConnectionWithOutResult();
			return b;
		}
		// ----------------------- gestion groupe ---------------------
		// voir touts les groue
		public ArrayList<Groupe> consulterGroupe() {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			ArrayList<Groupe> groupes = bd.consulterGroupe();
			bd.endConnection();
			return groupes;
		}
	
}
