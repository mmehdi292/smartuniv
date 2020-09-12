package Modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ConnectionBD {
	public Connection cn;
	public Statement state;
	public ResultSet result;
	public String user = "root";
	public String pass = "ahmed555";
	public String nameDatabase = "ates";

	public ConnectionBD(String user, String pass, String nameDatabase) {
		this.user = user;
		this.pass = pass;
		this.nameDatabase = nameDatabase;
	}

	public ConnectionBD() {

	}

	// initialisation de la connection BD
	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/" + nameDatabase;
			cn = DriverManager.getConnection(url, user, pass);
			state = cn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		} catch (SQLException e) {
			System.out.println("SQLException");
		}
	}

	// fermer la connection
	public void endConnection() {
		try {
			result.close();
			state.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// fermer la connection (utiliser avec executeUpdate() cause d'absence de
	// ResultSet)
	public void endConnectionWithOutResult() {
		try {
			state.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean isEtudiantByUsername(String user, String pass) {
		try {
			result = state.executeQuery("select username,motDePass from Etudiant where username = '" + user
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isResponsableDeFormationByUsername(String user, String pass) {
		try {
			result = state.executeQuery("select username,motDePass from Responsable where username = '" + user
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isEnseignentByUsername(String user, String pass) {
		try {
			result = state.executeQuery("select username,motDePass from Enseignent where username = '" + user
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isChefDepartementByUsername(String user, String pass) {
		try {
			result = state.executeQuery("select username,motDePass from Chef where username = '" + user
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isAdministrateurByUsername(String user, String pass) {
		try {
			result = state.executeQuery("select username,motDePass from Administrateur where username = '" + user
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isEtudiantByEmail(String email, String pass) {
		try {
			result = state.executeQuery("select email,motDePass from Etudiant where email = '" + email
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isResponsableDeFormationByEmail(String email, String pass) {
		try {
			result = state.executeQuery("select email,motDePass from Responsable where email = '" + email
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isEnseignentByEmail(String email, String pass) {
		try {
			result = state.executeQuery("select email,motDePass from Enseignent where email = '" + email
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isChefDepartementByEmail(String email, String pass) {
		try {
			result = state.executeQuery("select email,motDePass from Chef where email = '" + email
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isAdministrateurByEmail(String email, String pass) {
		try {
			result = state.executeQuery("select email,motDePass from Administrateur where email = '" + email
					+ "' and motDePass = MD5('" + pass + "');");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existEmail(String email) {
		try {
			result = state.executeQuery("select email from Etudiant where email = '" + email + "';");
			if (result.next() == true)
				return true;
			result = state.executeQuery("select email from Enseignent where email = '" + email + "';");
			if (result.next() == true)
				return true;
			result = state.executeQuery("select email from Responsable where email = '" + email + "';");
			if (result.next() == true)
				return true;
			result = state.executeQuery("select email from Chef where email = '" + email + "';");
			if (result.next() == true)
				return true;
			result = state.executeQuery("select email from Administrateur where email = '" + email + "';");
			if (result.next() == true)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean insertToken(ResetMotDePass reset) {
		try {
			int res = state.executeUpdate("insert into ResetMotDePass (ResetCode,email) values ('"
					+ reset.getResetCode() + "','" + reset.getEmail() + "'); ");
			return res == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existEmailInReset(String email) {
		try {
			result = state.executeQuery("select email from ResetMotDePass where email = '" + email + "';");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ResetMotDePass existTokenInReset(String token) {
		try {
			result = state.executeQuery("select * from ResetMotDePass where ResetCode = '" + token + "';");
			result.next();
			int idReset = result.getInt(1);
			String resetCode = result.getString(2);
			String email = result.getString(3);
			ResetMotDePass r = new ResetMotDePass(idReset, resetCode, email);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean MAJpassword(ResetMotDePass reset, String password) {
		try {
			int r = state.executeUpdate("UPDATE Administrateur SET motDePass = md5('" + password + "') WHERE email ='"
					+ reset.getEmail() + "'; ");
			r = state.executeUpdate("UPDATE Enseignent SET motDePass = md5('" + password + "') WHERE email ='"
					+ reset.getEmail() + "'; ");
			r = state.executeUpdate(
					"UPDATE chef SET motDePass = md5('" + password + "') WHERE email ='" + reset.getEmail() + "'; ");
			r = state.executeUpdate("UPDATE Responsable SET motDePass = md5('" + password + "') WHERE email ='"
					+ reset.getEmail() + "'; ");
			r = state.executeUpdate("UPDATE Etudiant SET motDePass = md5('" + password + "') WHERE email ='"
					+ reset.getEmail() + "'; ");
			r = state.executeUpdate("DELETE FROM ResetMotDePass WHERE idReset ='" + reset.getIdReset() + "';");
			return r == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addHisto(String username, String action) {
		int res = 0;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			res = state.executeUpdate("insert into historique (username,date,action) values ('" + username + "','"
					+ format.format(date) + "','" + action + "'); ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1;
	}

	public boolean addBlockedMAC(String MAC, String date) {
		int res = 0;
		try {
			res = state.executeUpdate("insert into blocked (MacAdresse,date) values ('" + MAC + "','" + date + "'); ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1;
	}

	public boolean isBlocked(String MAC) {
		try {
			result = state.executeQuery("select * from blocked where MacAdresse  = '" + MAC + "';");
			return result.next() == true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deblockMAC() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String actuleTime = format.format(date);
		try {
			state.executeUpdate("DELETE FROM blocked WHERE TIMEDIFF('" + actuleTime + "',date) > '00:30:00';");
			System.out.println("Done");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Enseignent> consulterEnseignant() {
		ArrayList<Enseignent> enseignents = new ArrayList<Enseignent>();
		System.out.println(enseignents);
		InputStream f = null;
		try {
			result = state.executeQuery("SELECT * FROM enseignent;");
			while (result.next()) {
				String username = result.getString(1);
				String nom = result.getString(2);
				String prenom = result.getString(3);
				String email = result.getString(4);
				// String pass = result.getString(5);
				Date dn = result.getDate(6);
				String ln = result.getString(7);
				String sexe = result.getString(8);
				Sexe e = Sexe.Femme;
				if (sexe.equalsIgnoreCase("Homme")) {
					e = Sexe.Homme;
				}
				String adresse = result.getString(9);
				String situationFamiliale = result.getString(10);
				SituationFamiliale s = SituationFamiliale.célibataire;
				switch (situationFamiliale) {
				case "célibataire":
					s = SituationFamiliale.célibataire;
					break;
				case "divorcé":
					s = SituationFamiliale.divorcé;
					break;
				case "marié":
					s = SituationFamiliale.marié;
					break;
				case "séparé":
					s = SituationFamiliale.séparé;
					break;
				case "veuf":
					s = SituationFamiliale.veuf;
					break;
				}
				Blob photo = result.getBlob(11);
				String grade = result.getString(12);
				Grade g = Grade.MaitreDeConférenceClasseB;
				if (grade.equalsIgnoreCase("MaitreDeConférenceClasseA"))
					g = Grade.MaitreDeConférenceClasseA;
				Enseignent en = new Enseignent(nom, prenom, username, email, dn, ln, e, adresse, s, f, g);
				enseignents.add(en);
			}
			return enseignents;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean suppEnseignant(String username) {
		int res = 0;
		try {
			String sql = "DELETE FROM enseignentmodulesgroupe WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
			sql = "DELETE FROM Responsable WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
			sql = "DELETE FROM chef WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
			sql = "DELETE FROM Administrateur WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
			sql = "DELETE FROM enseignent WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);

			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}

	public boolean modifierEnseignant(Enseignent en) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE enseignent SET email = ? ,motDePass = md5(?) , adresse = ? , situationFamiliale = ? , photo = ? , grade = ? WHERE username = ?");
			pre.setString(1, en.getEmail());
			pre.setString(2, en.getMotDePass());
			pre.setString(3, en.getAdresse());
			pre.setString(4, en.getSituationFamiliale().toString());
			InputStream blob = en.getPhoto();
			pre.setBinaryStream(5, blob);
			pre.setString(6, en.getGarde().toString());
			pre.setString(7, en.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// get Enseignent
	public Enseignent getEnseignent(String username) {
		try {
			result = state.executeQuery("SELECT * FROM enseignent WHERE username = '" + username + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nom = result.getString(2);
			String prenom = result.getString(3);
			String email = result.getString(4);
			String pass = result.getString(5);
			Date dn = result.getDate(6);
			String ln = result.getString(7);
			String sexe = result.getString(8);
			Sexe e = Sexe.Femme;
			if (sexe.equalsIgnoreCase("Homme")) {
				e = Sexe.Homme;
			}
			String adresse = result.getString(9);
			String situationFamiliale = result.getString(10);
			SituationFamiliale s = SituationFamiliale.célibataire;
			switch (situationFamiliale) {
			case "célibataire":
				s = SituationFamiliale.célibataire;
				break;
			case "divorcé":
				s = SituationFamiliale.divorcé;
				break;
			case "marié":
				s = SituationFamiliale.marié;
				break;
			case "séparé":
				s = SituationFamiliale.séparé;
				break;
			case "veuf":
				s = SituationFamiliale.veuf;
				break;
			}
			InputStream photo = result.getBinaryStream(11);
			String grade = result.getString(12);
			Grade g = Grade.MaitreDeConférenceClasseB;
			if (grade.equalsIgnoreCase("MaitreDeConférenceClasseA"))
				g = Grade.MaitreDeConférenceClasseA;
			return new Enseignent(nom, prenom, username, email, pass, dn, ln, e, adresse, s, photo, g);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ChefDepartement getChefDepartement(String username) {
		try {
			result = state.executeQuery("SELECT * FROM chef WHERE username = '" + username + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nom = result.getString(2);
			String prenom = result.getString(3);
			String email = result.getString(4);
			// String pass = result.getString(5);
			Date dn = result.getDate(6);
			String ln = result.getString(7);
			String sexe = result.getString(8);
			Sexe e = Sexe.Femme;
			if (sexe.equalsIgnoreCase("Homme")) {
				e = Sexe.Homme;
			}
			String adresse = result.getString(9);
			String situationFamiliale = result.getString(10);
			SituationFamiliale s = SituationFamiliale.célibataire;
			switch (situationFamiliale) {
			case "célibataire":
				s = SituationFamiliale.célibataire;
				break;
			case "divorcé":
				s = SituationFamiliale.divorcé;
				break;
			case "marié":
				s = SituationFamiliale.marié;
				break;
			case "séparé":
				s = SituationFamiliale.séparé;
				break;
			case "veuf":
				s = SituationFamiliale.veuf;
				break;
			}
			InputStream photo = result.getBinaryStream(11);
			String grade = result.getString(12);
			Grade g = Grade.MaitreDeConférenceClasseB;
			if (grade.equalsIgnoreCase("MaitreDeConférenceClasseA"))
				g = Grade.MaitreDeConférenceClasseA;
			String departement = result.getString(13);
			return new ChefDepartement(nom, prenom, username, email, dn, ln, e, adresse, s, photo, g, departement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponsableDeFormation getResponsableDeFormation(String username) {
		try {
			result = state.executeQuery("SELECT * FROM Responsable WHERE username = '" + username + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nom = result.getString(2);
			String prenom = result.getString(3);
			String email = result.getString(4);
			// String pass = result.getString(5);
			Date dn = result.getDate(6);
			String ln = result.getString(7);
			String sexe = result.getString(8);
			Sexe e = Sexe.Femme;
			if (sexe.equalsIgnoreCase("Homme")) {
				e = Sexe.Homme;
			}
			String adresse = result.getString(9);
			String situationFamiliale = result.getString(10);
			System.out.println(situationFamiliale+"   kokokok");
			SituationFamiliale s = SituationFamiliale.célibataire;
			switch (situationFamiliale) {
			case "célibataire":
				s = SituationFamiliale.célibataire;
				break;
			case "divorcé":
				s = SituationFamiliale.divorcé;
				break;
			case "marié":
				s = SituationFamiliale.marié;
				break;
			case "séparé":
				s = SituationFamiliale.séparé;
				break;
			case "veuf":
				s = SituationFamiliale.veuf;
				break;
			}
			InputStream photo = result.getBinaryStream(11);
			String grade = result.getString(12);
			Grade g = Grade.MaitreDeConférenceClasseB;
			if (grade.equalsIgnoreCase("MaitreDeConférenceClasseA"))
				g = Grade.MaitreDeConférenceClasseA;
			String abrFormation = result.getString(13);
			return new ResponsableDeFormation(nom, prenom, username, email, dn, ln, e, adresse, s, photo, g,
					new Formation(abrFormation));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Administrateur getAdministrateur(String username) {
		try {
			result = state.executeQuery("SELECT * FROM Administrateur WHERE username = '" + username + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nom = result.getString(2);
			String prenom = result.getString(3);
			String email = result.getString(4);
			// String pass = result.getString(5);
			Date dn = result.getDate(6);
			String ln = result.getString(7);
			String sexe = result.getString(8);
			Sexe e = Sexe.Femme;
			if (sexe.equalsIgnoreCase("Homme")) {
				e = Sexe.Homme;
			}
			String adresse = result.getString(9);
			String situationFamiliale = result.getString(10);
			SituationFamiliale s = SituationFamiliale.célibataire;
			switch (situationFamiliale) {
			case "célibataire":
				s = SituationFamiliale.célibataire;
				break;
			case "divorcé":
				s = SituationFamiliale.divorcé;
				break;
			case "marié":
				s = SituationFamiliale.marié;
				break;
			case "séparé":
				s = SituationFamiliale.séparé;
				break;
			case "veuf":
				s = SituationFamiliale.veuf;
				break;
			}
			InputStream photo = result.getBinaryStream(11);
			return new Administrateur(nom, prenom, username, email, dn, ln, e, adresse, s, photo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// -------------------------------------------------top--------

	// add administrator
	public boolean addAdmin(Administrateur admin) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO Administrateur(username,nom,prenom,email,motDePass,dn,ln,sexe,adresse,situationFamiliale,photo) VALUES(?,?,?,?,md5(?),?,?,?,?,?,?);");
			pre.setString(1, admin.getUsername());
			pre.setString(2, admin.getNom());
			pre.setString(3, admin.getPrenom());
			pre.setString(4, admin.getEmail());
			pre.setString(5, admin.getMotDePass());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(admin.getDn());
			pre.setString(6, date);
			pre.setString(7, admin.getLn());
			pre.setString(8, admin.getSexe().toString());
			pre.setString(9, admin.getAdresse());
			pre.setString(10, admin.getSituationFamiliale().toString());
			pre.setBlob(11, admin.getPhoto());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// add Enseignent
	public boolean addEns(Enseignent ens) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO Enseignent(username,nom,prenom,email,motDePass,dn,ln,sexe,adresse,situationFamiliale,photo,grade) VALUES(?,?,?,?,md5(?),?,?,?,?,?,?,?);");
			pre.setString(1, ens.getUsername());
			pre.setString(2, ens.getNom());
			pre.setString(3, ens.getPrenom());
			pre.setString(4, ens.getEmail());
			pre.setString(5, ens.getMotDePass());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(ens.getDn());
			pre.setString(6, date);
			pre.setString(7, ens.getLn());
			pre.setString(8, ens.getSexe().toString());
			pre.setString(9, ens.getAdresse());
			pre.setString(10, ens.getSituationFamiliale().toString());
			pre.setBlob(11, ens.getPhoto());
			pre.setString(12, ens.getGarde().toString());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// add chef
	public boolean addchef(ChefDepartement chef) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO chef(username,nom,prenom,email,motDePass,dn,ln,sexe,adresse,situationFamiliale,photo,grade,nomDepartement) VALUES(?,?,?,?,md5(?),?,?,?,?,?,?,?,?);");
			pre.setString(1, chef.getUsername());
			pre.setString(2, chef.getNom());
			pre.setString(3, chef.getPrenom());
			pre.setString(4, chef.getEmail());
			pre.setString(5, chef.getMotDePass());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(chef.getDn());
			pre.setString(6, date);
			pre.setString(7, chef.getLn());
			pre.setString(8, chef.getSexe().toString());
			pre.setString(9, chef.getAdresse());
			pre.setString(10, chef.getSituationFamiliale().toString());
			pre.setBlob(11, chef.getPhoto());
			pre.setString(12, chef.getGarde().toString());
			pre.setString(13, chef.getNomDepartement());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// add ResponsableDeFormation
	public boolean addres(ResponsableDeFormation res) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO Responsable(username,nom,prenom,email,motDePass,dn,ln,sexe,adresse,situationFamiliale,photo,grade,abrFormation) VALUES(?,?,?,?,md5(?),?,?,?,?,?,?,?,?);");
			pre.setString(1, res.getUsername());
			pre.setString(2, res.getNom());
			pre.setString(3, res.getPrenom());
			pre.setString(4, res.getEmail());
			pre.setString(5, res.getMotDePass());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(res.getDn());
			pre.setString(6, date);
			pre.setString(7, res.getLn());
			pre.setString(8, res.getSexe().toString());
			pre.setString(9, res.getAdresse());
			pre.setString(10, res.getSituationFamiliale().toString());
			pre.setBlob(11, res.getPhoto());
			pre.setString(12, res.getGarde().toString());
			pre.setString(13, res.getFormation().getAbrFormation());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteChef(String username) {
		int res = 0;
		try {
			res = state.executeUpdate("DELETE FROM chef WHERE username = '" + username + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1;
	}

	public boolean deleteRes(String username) {
		int res = 0;
		try {
			res = state.executeUpdate("DELETE FROM responsable WHERE username = '" + username + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1;
	}

	public boolean deleteAdmin(String username) {
		int res = 0;
		try {
			res = state.executeUpdate("DELETE FROM administrateur WHERE username = '" + username + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res == 1;
	}

	// modifier en
	public boolean modifierEns(Enseignent en) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE enseignent SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ? , grade = ? WHERE username = ?;");
			pre.setString(1, en.getUsername());
			System.out.println(en.getUsername());
			pre.setString(2, en.getNom());
			pre.setString(3, en.getPrenom());
			pre.setString(4, en.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(en.getDn());
			pre.setString(5, date);
			pre.setString(6, en.getLn());
			pre.setString(7, en.getSexe().toString());
			pre.setString(8, en.getAdresse());
			System.out.println("aaaaaaaaaa " + en.getAdresse());
			pre.setString(9, en.getSituationFamiliale().toString());
			InputStream blob = en.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, en.getGarde().toString());
			pre.setString(12, en.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// modifier admin
	public boolean modifierAdmin(Administrateur en) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE administrateur SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ?  WHERE username = ?;");
			pre.setString(1, en.getUsername());
			pre.setString(2, en.getNom());
			pre.setString(3, en.getPrenom());
			pre.setString(4, en.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(en.getDn());
			pre.setString(5, date);
			pre.setString(6, en.getLn());
			pre.setString(7, en.getSexe().toString());
			pre.setString(8, en.getAdresse());
			pre.setString(9, en.getSituationFamiliale().toString());
			InputStream blob = en.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, en.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// modifer chef
	public boolean modifierChef(ChefDepartement en) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE chef SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ? , grade = ?,nomDepartement=? WHERE username = ?;");
			pre.setString(1, en.getUsername());
			pre.setString(2, en.getNom());
			pre.setString(3, en.getPrenom());
			pre.setString(4, en.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(en.getDn());
			pre.setString(5, date);
			pre.setString(6, en.getLn());
			pre.setString(7, en.getSexe().toString());
			pre.setString(8, en.getAdresse());
			pre.setString(9, en.getSituationFamiliale().toString());
			InputStream blob = en.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, en.getGarde().toString());
			pre.setString(12, en.getNomDepartement());
			pre.setString(13, en.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// modifer res
	public boolean modifierRes(ResponsableDeFormation en) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE responsable SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ? , grade = ? , abrFormation=? WHERE username = ?;");
			pre.setString(1, en.getUsername());
			pre.setString(2, en.getNom());
			pre.setString(3, en.getPrenom());
			pre.setString(4, en.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(en.getDn());
			pre.setString(5, date);
			pre.setString(6, en.getLn());
			pre.setString(7, en.getSexe().toString());
			pre.setString(8, en.getAdresse());
			pre.setString(9, en.getSituationFamiliale().toString());
			InputStream blob = en.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, en.getGarde().toString());
			pre.setString(12, en.getFormation().getAbrFormation());
			pre.setString(13, en.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// consulter etudiants
	public ArrayList<Etudiant> consulterEtudiant() {
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		try {
			result = state.executeQuery("SELECT * FROM etudiant;");
			while (result.next()) {
				String username = result.getString(1);
				String nom = result.getString(2);
				String prenom = result.getString(3);
				String email = result.getString(4);
				String pass = result.getString(5);
				Date dn = result.getDate(6);
				String ln = result.getString(7);
				String sexe = result.getString(8);
				Sexe e = Sexe.Femme;
				if (sexe.equalsIgnoreCase("Homme")) {
					e = Sexe.Homme;
				}
				String adresse = result.getString(9);
				String situationFamiliale = result.getString(10);
				SituationFamiliale s = SituationFamiliale.célibataire;
				switch (situationFamiliale) {
				case "célibataire":
					s = SituationFamiliale.célibataire;
					break;
				case "divorcé":
					s = SituationFamiliale.divorcé;
					break;
				case "marié":
					s = SituationFamiliale.marié;
					break;
				case "séparé":
					s = SituationFamiliale.séparé;
					break;
				case "veuf":
					s = SituationFamiliale.veuf;
					break;
				}
				InputStream f = result.getBinaryStream(11);
				String abr = result.getString(12);
				Etudiant en = new Etudiant(nom, prenom, username, email, pass, dn, ln, e, adresse, s, f,
						new Formation(abr));
				etudiants.add(en);
			}
			return etudiants;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// ---------------------------- gestion des etudiant ----------------------
	// consultaion

	// ajouter etudiant
	public boolean ajouterEtudiant(Etudiant e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO etudiant (username,nom,prenom,email,motDePass,dn,ln,sexe,adresse,situationFamiliale,photo,abrFormation) VALUES(?,?,?,?,md5(?),?,?,?,?,?,?,?);");
			pre.setString(1, e.getUsername());
			pre.setString(2, e.getNom());
			pre.setString(3, e.getPrenom());
			pre.setString(4, e.getEmail());
			pre.setString(5, e.getMotDePass());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(e.getDn());
			pre.setString(6, date);
			pre.setString(7, e.getLn());
			pre.setString(8, e.getSexe().toString());
			pre.setString(9, e.getAdresse());
			pre.setString(10, e.getSituationFamiliale().toString());
			pre.setBlob(11, e.getPhoto());
			pre.setString(12, e.getFormation().getAbrFormation());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// supp etudiant
	public boolean suppEtudiant(String username) {
		int res = 0,res2 = 0,res3 = 0;
		try {
			String sql = "DELETE FROM absence WHERE username like \"" + username + "\";";
			res3 = state.executeUpdate(sql);
			sql = "DELETE FROM groupeetudiant WHERE username like \"" + username + "\";";
			res2 = state.executeUpdate(sql);
			sql = "DELETE FROM etudiant WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return (res == 1)&&(res2 == 1)&&(res3 == 1);
	}
	// get Enseignent
	public Etudiant getEtudiant(String username) {
		try {
			result = state.executeQuery("SELECT * FROM etudiant WHERE username = '" + username + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nom = result.getString(2);
			String prenom = result.getString(3);
			String email = result.getString(4);
			String pass = result.getString(5);
			Date dn = result.getDate(6);
			String ln = result.getString(7);
			String sexe = result.getString(8);
			Sexe e = Sexe.Femme;
			if (sexe.equalsIgnoreCase("Homme")) {
				e = Sexe.Homme;
			}
			String adresse = result.getString(9);
			String situationFamiliale = result.getString(10);
			SituationFamiliale s = SituationFamiliale.célibataire;
			switch (situationFamiliale) {
			case "célibataire":
				s = SituationFamiliale.célibataire;
				break;
			case "divorcé":
				s = SituationFamiliale.divorcé;
				break;
			case "marié":
				s = SituationFamiliale.marié;
				break;
			case "séparé":
				s = SituationFamiliale.séparé;
				break;
			case "veuf":
				s = SituationFamiliale.veuf;
				break;
			}
			InputStream photo = result.getBinaryStream(11);
			String abrFormtion = result.getString(12);
			return new Etudiant(nom, prenom, username, email, pass, dn, ln, e, adresse, s, photo,
					new Formation(abrFormtion));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier etudiant with password
	public boolean modifierEtudiant(Etudiant e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE etudiant SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ? , MotDePass = md5(?) , abrFormation=? WHERE username = ?;");
			pre.setString(1, e.getUsername());
			pre.setString(2, e.getNom());
			pre.setString(3, e.getPrenom());
			pre.setString(4, e.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(e.getDn());
			pre.setString(5, date);
			pre.setString(6, e.getLn());
			pre.setString(7, e.getSexe().toString());
			pre.setString(8, e.getAdresse());
			pre.setString(9, e.getSituationFamiliale().toString());
			InputStream blob = e.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, e.getMotDePass());
			pre.setString(12, e.getFormation().getAbrFormation());
			pre.setString(13, e.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// modifier etudiant without password
	public boolean modifierEtudiantSansPass(Etudiant e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE etudiant SET username=?, nom=?, prenom=?, email = ?,dn=?, ln=?, sexe=?, adresse = ? , situationFamiliale = ? , photo = ? , abrFormation=? WHERE username = ?;");
			pre.setString(1, e.getUsername());
			pre.setString(2, e.getNom());
			pre.setString(3, e.getPrenom());
			pre.setString(4, e.getEmail());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(e.getDn());
			pre.setString(5, date);
			pre.setString(6, e.getLn());
			pre.setString(7, e.getSexe().toString());
			pre.setString(8, e.getAdresse());
			pre.setString(9, e.getSituationFamiliale().toString());
			InputStream blob = e.getPhoto();
			pre.setBinaryStream(10, blob);
			pre.setString(11, e.getFormation().getAbrFormation());
			pre.setString(12, e.getUsername());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// ---------------------------- gestion des modules ----------------------
	// consultaion
	public ArrayList<Module> consulterModule() {
		ArrayList<Module> modules = new ArrayList<Module>();
		try {
			result = state.executeQuery("SELECT * FROM module;");
			while (result.next()) {
				String nomModule = result.getString(1);
				String abrModule = result.getString(2);
				int semester = result.getInt(3);
				String abrFormation = result.getString(4);
				Module mdl = new Module(nomModule, abrModule, semester, new Formation(abrFormation));
				modules.add(mdl);
			}
			return modules;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// ajouter module
	public boolean ajouterModule(Module e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO module (NomModule,AbrModule,Semester,AbrFormation) VALUES(?,?,?,?);");
			pre.setString(1, e.getNomModule());
			pre.setString(2, e.getAbrModule());
			pre.setInt(3, e.getSemester());
			pre.setString(4, e.getFormation().getAbrFormation());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// supp module
	public boolean suppModule(String abr) {
		int res = 0;
		try {
			String sql = "DELETE FROM Module WHERE AbrModule like \"" + abr + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}

	// get module
	public Module getModule(String abr) {
		try {
			result = state.executeQuery("SELECT * FROM Module WHERE AbrModule = '" + abr + "' ;");
			if (result.next() == false) {
				return null;
			}
			String NomModule = result.getString(1);
			String AbrModule = result.getString(2);
			int Semester = result.getInt(3);
			String abrFormation = result.getString(4);
			return new Module(NomModule, AbrModule, Semester, new Formation(abrFormation));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier module
	public boolean modifierModule(Module e, String abr) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE Module SET NomModule = ?,AbrModule =?,Semester =?,AbrFormation=? WHERE AbrModule = ?;");
			pre.setString(1, e.getNomModule());
			pre.setString(2, e.getAbrModule());
			pre.setInt(3, e.getSemester());
			pre.setString(4, e.getFormation().getAbrFormation());
			pre.setString(5, abr);
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// -------------------- gestion des formation ----------------
	// consultaion
	public ArrayList<Formation> consulterFormation() {
		ArrayList<Formation> formations = new ArrayList<Formation>();
		try {
			result = state.executeQuery("SELECT * FROM formation;");
			while (result.next()) {
				String nomFormation = result.getString(1);
				String abrFormation = result.getString(2);
				String specialite = result.getString(3);
				String c1 = result.getString(4);
				Cycle cycle = null;
				switch (c1) {
				case "licence":
					cycle = Cycle.licence;
					break;
				case "master":
					cycle = Cycle.master;
					break;
				}
				int annee = result.getInt(5);
				String departement = result.getString(6);
				Formation frm = new Formation(nomFormation, abrFormation, specialite, cycle, annee, departement);
				formations.add(frm);
			}
			return formations;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ajouter formation
	public boolean ajouterFormation(Formation e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO formation (NomFormation,AbrFormation,Specialite,Cycle,Annee,Departement) VALUES(?,?,?,?,?,?);");
			pre.setString(1, e.getNomFormation());
			pre.setString(2, e.getAbrFormation());
			pre.setString(3, e.getSpecialite());
			pre.setString(4, e.getCycle().toString());
			pre.setInt(5, e.getAnnee());
			pre.setString(6, e.getDepartement());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// supp module
	public boolean suppFormation(String abr) {
		int res = 0;
		try {
			String sql = "DELETE FROM formation WHERE abrFormation like \"" + abr + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}

	// get Formation
	public Formation getFormation(String abr) {
		try {
			result = state.executeQuery("SELECT * FROM formation WHERE AbrFormation = '" + abr + "' ;");
			if (result.next() == false) {
				return null;
			}
			String nomFormation = result.getString(1);
			String abrFormation = result.getString(2);
			String specialite = result.getString(3);
			String c1 = result.getString(4);
			Cycle cycle = null;
			switch (c1) {
			case "licence":
				cycle = Cycle.licence;
				break;
			case "master":
				cycle = Cycle.master;
				break;
			}
			int annee = result.getInt(5);
			String departement = result.getString(6);
			return new Formation(nomFormation, abrFormation, specialite, cycle, annee, departement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier formation
	public boolean modifierFormation(Formation e, String abr) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE formation SET nomFormation = ?,abrFormation =?,specialite =?,cycle=? ,annee=? ,departement=? WHERE abrFormation = ?;");
			pre.setString(1, e.getNomFormation());
			pre.setString(2, e.getAbrFormation());
			pre.setString(3, e.getSpecialite());
			pre.setString(4, e.getCycle().toString());
			pre.setInt(5, e.getAnnee());
			pre.setString(6, e.getDepartement());
			pre.setString(7, abr);
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	//-------------------------------
	public String getDepartementUtilisateur(String username) {
		String nomDepartement=null;
		try {
			result=state.executeQuery("select distinct departement from formation f,ENSEIGNENT e,enseignentmodulesgroupe emg,groupe g where f.abrformation=g.abrformation and g.idgroupe=emg.idgroupe and emg.username=e.username and e.username=\""+username+"\";");
			if(result.next() == true) {System.out.println(result.getString(1)); return result.getString(1);}
			result=state.executeQuery("select departement from formation,etudiant where etudiant.abrformation=formation.abrformation and username=\""+username+"\";");
			if(result.next() == true) { System.out.println(result.getString(1));return result.getString(1);}
			
		} catch (SQLException e) {
			System.out.println("catch bd getDeparementUtilisateur");
			e.printStackTrace();
		}
		return nomDepartement;
	}
	
	
	

	// ---------------------------- gestion historique -----------------
	// consulter historique
	public ArrayList<Historique> consulterHistoriqueChef(String departement) {
		ArrayList<Historique> historiques = new ArrayList<Historique>();
		try {
			result = state.executeQuery("SELECT * FROM historique;");
			while (result.next()) {
				int id = result.getInt(1);
				String username = result.getString(2);
				System.out.println("utilisateur:"+username);
				Date date = result.getTimestamp(3);
				String action = result.getString(4);
				Historique his = new Historique(id, username, date, action);
				historiques.add(his);
			}
			return historiques;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ---------------------------- gestion historique -----------------
		// consulter historique
		public ArrayList<Historique> consulterHistorique() {
			System.out.println("BD admin historique");
			ArrayList<Historique> historiques = new ArrayList<Historique>();
			try {
				result = state.executeQuery("SELECT * FROM historique;");
				while (result.next()) {
					int id = result.getInt(1);
					String username = result.getString(2);
					Date date = result.getTimestamp(3);
					String action = result.getString(4);
					Historique his = new Historique(id, username, date, action);
					historiques.add(his);
				}
				return historiques;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	// ---------------------------- gestion Seance -----------------
	// consulter seance
	public ArrayList<Seance> consulterSeances() {
		ArrayList<Seance> seances = new ArrayList<Seance>();
		try {
			result = state.executeQuery("SELECT * FROM seance;");
			while (result.next()) {
				int id = result.getInt(1);
				String t = result.getString(2);
				TypeSeance type = null;
				switch (t) {
				case "COUR":
					type = TypeSeance.COUR;
					break;
				case "TD":
					type = TypeSeance.TD;
					break;
				case "TP":
					type = TypeSeance.TP;
					break;
				}
				Date date = result.getTimestamp(3);
				int salle = result.getInt(4);
				Boolean avoirAbs = result.getBoolean(5);
				int idGroupe = result.getInt(6);
				String abrModule = result.getString(7);
				Seance seance = new Seance(id, type, date, salle, avoirAbs, new Groupe(idGroupe),
						new Module(abrModule));
				seances.add(seance);
			}
			return seances;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ajouter seacne
	public boolean ajouterSeance(Seance e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"INSERT INTO seance (type,temp,salle,avoirAbs,idGroupe,abrModule) VALUES(?,?,?,?,?,?);");
			pre.setString(1, e.getType().toString());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = s.format(e.getTemp());
			pre.setString(2, date);
			pre.setInt(3, e.getSalle());
			pre.setBoolean(4, e.isAvoirAbs());
			pre.setInt(5, e.getGroupe().getIdGroupe());
			pre.setString(6, e.getModule().getAbrModule());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// get Seance
	public Seance getSeance(int abr) {
		try {
			result = state.executeQuery("SELECT * FROM seance WHERE idSeance  = '" + abr + "' ;");
			if (result.next() == false) {
				return null;
			}
			int id = result.getInt(1);
			String t = result.getString(2);
			TypeSeance type = null;
			switch (t) {
			case "COUR":
				type = TypeSeance.COUR;
				break;
			case "TD":
				type = TypeSeance.TD;
				break;
			case "TP":
				type = TypeSeance.TP;
				break;
			}
			Date date = result.getTimestamp(3);
			int salle = result.getInt(4);
			Boolean avoirAbs = result.getBoolean(5);
			int idGroupe = result.getInt(6);
			String abrModule = result.getString(7);
			return new Seance(id, type, date, salle, avoirAbs, new Groupe(idGroupe), new Module(abrModule));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier seance
	public boolean modifierSeance(Seance e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE seance SET idSeance =? , type =? , temp =? , salle =?, avoirAbs=?, idGroupe =? , abrModule =? WHERE idSeance = ?;");
			pre.setInt(1, e.getIdSeance());
			System.out.println(e.getType().toString());
			pre.setString(2, e.getType().toString());
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = s.format(e.getTemp());
			pre.setString(3, date);
			pre.setInt(4, e.getSalle());
			pre.setBoolean(5, e.isAvoirAbs());
			pre.setInt(6, e.getGroupe().getIdGroupe());
			pre.setString(7, e.getModule().getAbrModule());
			pre.setInt(8, e.getIdSeance());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// supp seance
	public boolean suppSeance(int id) {
		int res = 0;
		try {
			String sql = "DELETE FROM seance WHERE idSeance  like \"" + id + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}

	// ---------------------------- gestion vacances -----------------
	// consulter Vacances
	public ArrayList<Vacance> consulterVacances() {
		ArrayList<Vacance> vacances = new ArrayList<Vacance>();
		try {
			result = state.executeQuery("SELECT * FROM Vacance;");
			while (result.next()) {
				int id = result.getInt(1);
				String t = result.getString(3);
				Date date = result.getDate(2);
				Vacance vacance = new Vacance(id, date, t);
				vacances.add(vacance);
			}
			return vacances;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ajouter vacance
	public boolean ajouterVacance(Vacance e) {
		try {
			PreparedStatement pre = cn.prepareStatement("INSERT INTO vacance (date,description) VALUES(?,?);");
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(e.getDate());
			pre.setString(1, date);
			pre.setString(2, e.getDescription());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// get Vacance
	public Vacance getVacance(int abr) {
		try {
			result = state.executeQuery("SELECT * FROM vacance WHERE id = '" + abr + "' ;");
			if (result.next() == false) {
				return null;
			}
			int id = result.getInt(1);
			Date date = result.getDate(2);
			String description = result.getString(3);
			return new Vacance(id, date, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier vacance
	public boolean modifierVacance(Vacance e) {
		try {
			PreparedStatement pre = cn.prepareStatement("UPDATE vacance SET date = ?,description  =? WHERE id = ?;");
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String date = s.format(e.getDate());
			pre.setString(1, date);
			pre.setString(2, e.getDescription());
			pre.setInt(3, e.getId());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// supp vacance
	public boolean suppVacance(int id) {
		int res = 0;
		try {
			String sql = "DELETE FROM vacance WHERE id like \"" + id + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}

	// ---------------------------- gestion des Groupe ----------------------
	// consultaion
	public ArrayList<Groupe> consulterGroupe() {
		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
		try {
			result = state.executeQuery("SELECT * FROM Groupe;");
			while (result.next()) {
				int id = result.getInt(1);
				int num = result.getInt(2);
				int section = result.getInt(3);
				String abrFromtion = result.getString(4);

				Groupe grp = new Groupe(id, num, section, new Formation(abrFromtion));
				groupes.add(grp);
			}
			return groupes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// ajouter Groupe
	public boolean ajouterGroupe(Groupe e) {
		try {
			PreparedStatement pre = cn
					.prepareStatement("INSERT INTO groupe (numGroupe,section,abrFormation) VALUES(?,?,?);");
			pre.setInt(1, e.getNumGroupe());
			pre.setInt(2, e.getSection());
			pre.setString(3, e.getFormation().getAbrFormation());
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}

	// get Groupe
	public Groupe getGroupe(int abr) {
		try {
			result = state.executeQuery("SELECT * FROM groupe WHERE idGroupe  = '" + abr + "' ;");
			if (result.next() == false) {
				return null;
			}
			int id = result.getInt(1);
			int num = result.getInt(2);
			int sec = result.getInt(3);
			String abrFormation = result.getString(4);
			return new Groupe(id, num, sec, new Formation(abrFormation));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modifier Groupe
	public boolean modifierGroupe(Groupe e) {
		try {
			PreparedStatement pre = cn.prepareStatement(
					"UPDATE groupe SET numGroupe =? , section =? , abrFormation =? WHERE idGroupe = ?;");
			pre.setInt(1, e.getNumGroupe());
			pre.setInt(2, e.getSection());
			pre.setString(3, e.getFormation().getAbrFormation());
			pre.setInt(4, e.getIdGroupe());
			int x = pre.executeUpdate();
			return x == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// supp Groupe
	public boolean suppGroupe(int id) {
		int res = 0;
		try {
			String sql = "DELETE FROM groupe WHERE idGroupe  like \"" + id + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
	}


	// get touts les seances d'un modules d'un groupe
	public ArrayList<Seance> getSeances(int groupe, String abr, String t) {
		ArrayList<Seance> s = new ArrayList<Seance>();
		try {
			//result = state.executeQuery("SELECT * FROM seance,absence WHERE seance.idseance=absence.idseance and justifier=0 and abrModule = '"+abr+"' and type='"+t+"' and idgroupe = '"+groupe+"' group by seance.idseance;");
			result = state.executeQuery("SELECT * FROM seance,absence WHERE seance.idseance=absence.idseance and justifier=0 and abrModule = '"+abr+"' and type='"+t+"' and idgroupe = '"+groupe+"' group by seance.idseance;");
			while (result.next()) {
				int idSeance = result.getInt("idSeance");
				InputStream justification = result.getBinaryStream("justification");
				Date date = result.getTimestamp("temp");
				int salle = result.getInt("salle");
				boolean avoirAbs = result.getBoolean("avoirAbs");
				TypeSeance type = TypeSeance.COUR;
				switch (t) {
				case "TD":
					type = TypeSeance.TD;
					break;
				case "TP":
					type = TypeSeance.TP;
					break;
				}
				Groupe g = new Groupe(groupe);
				Module m = new Module(abr);
				Seance seance = new Seance(idSeance,type,date,salle,avoirAbs,g,m);
				if(justification==null)
				s.add(seance);
			}
			System.out.println("KO");
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	 public ArrayList<Seance> getSeancesPourFaireAppel(int groupe, String abr, String t){
		 ArrayList<Seance> s = new ArrayList<Seance>();
			try {
				result = state.executeQuery("SELECT * FROM seance WHERE abrModule = '"+abr+"' and type='"+t+"' and idgroupe = '"+groupe+"';");
				while (result.next()) {
					int idSeance = result.getInt("idSeance");
					
					Date date = result.getTimestamp("temp");
					int salle = result.getInt("salle");
					boolean avoirAbs = result.getBoolean("avoirAbs");
					TypeSeance type = TypeSeance.COUR;
					switch (t) {
					case "TD":
						type = TypeSeance.TD;
						break;
					case "TP":
						type = TypeSeance.TP;
						break;
					}
					Groupe g = new Groupe(groupe);
					Module m = new Module(abr);
					Seance seance = new Seance(idSeance,type,date,salle,avoirAbs,g,m);
					s.add(seance);
				}
				System.out.println("KO");
				return s;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
	 }
	// touts les etudiants d'un groupe
	public ArrayList<Etudiant> getEtudiantGroupe(int idgroupe){
		ArrayList<Etudiant> s = new ArrayList<Etudiant>();
		try {
			result = state.executeQuery("SELECT * FROM groupeetudiant g, etudiant e WHERE g.idgroupe='"+idgroupe+"' and g.username = e.username ;");
			while (result.next()) {
				String username = result.getString("username");
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				String email = result.getString("email");
				Date dn = result.getTimestamp("dn");
				String ln = result.getString("ln");
				String sexe = result.getString("sexe");
				Sexe e = Sexe.Femme;
				if (sexe.equalsIgnoreCase("Homme")) {
					e = Sexe.Homme;
				}
				String adress = result.getString("adresse");
				String situationFamiliale = result.getString("situationFamiliale");
				SituationFamiliale sf = SituationFamiliale.célibataire;
				switch (situationFamiliale) {
				case "célibataire":
					sf = SituationFamiliale.célibataire;
					break;
				case "divorcé":
					sf = SituationFamiliale.divorcé;
					break;
				case "marié":
					sf = SituationFamiliale.marié;
					break;
				case "séparé":
					sf = SituationFamiliale.séparé;
					break;
				case "veuf":
					sf = SituationFamiliale.veuf;
					break;
				}
				InputStream photo = result.getBinaryStream("photo");
				String abrFormation = result.getString("abrFormation");
				Etudiant et = new Etudiant(nom,prenom,username,email,null,dn,ln,e,adress,sf,photo,new Formation(abrFormation));
				s.add(et);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public boolean addAbsence(int idseance, String username) {
		try {
			PreparedStatement pre = cn
					.prepareStatement("INSERT INTO absence (justifier,idSeance,justification,username) VALUES(?,?,?,?);");
			pre.setBoolean(1, false);
			pre.setInt(2, idseance);
			pre.setBinaryStream(3, null);
			pre.setString(4, username);
			int i = pre.executeUpdate();
			return i == 1;
		} catch (SQLException ex) {
			System.out.println("SQL Error");
		}
		return false;
	}
	public  ArrayList<Absence> getAbsencesDunSeance(int idseance){
		ArrayList<Absence> s = new ArrayList<Absence>();
		try {
			result = state.executeQuery("SELECT * FROM absence,seance  WHERE absence.idseance=seance.idseance and justification!=null and seance.idSeance = "+idseance+" ;");
			while (result.next()) {
				int idAbsence = result.getInt("idAbsence");
				boolean justifier = result.getBoolean("justifier");
				String username = result.getString("username");
				
				InputStream photo = result.getBinaryStream("justification");
				Absence a = new Absence(idAbsence,justifier,new Seance(idseance),photo,new Etudiant(username));
				System.out.println("photo :"+photo);
				//if(photo==null)
				s.add(a);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public  ArrayList<Absence> getAbsencesDunSeanceAvecEtudiant(int idseance){
		ArrayList<Absence> s = new ArrayList<Absence>();
		try {
			result = state.executeQuery("SELECT * FROM absence a, Etudiant e,seance s WHERE s.idseance=a.idseance and a.idSeance = "+idseance+" AND justifier=0 and e.username = a.username;");
			System.out.println("I am here");
			while (result.next()) {
				int idAbsence = result.getInt("idAbsence");
				boolean justifier = result.getBoolean("justifier");
				InputStream photo = result.getBinaryStream("justification");
				String username = result.getString("username");
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				Absence a = new Absence(idAbsence,justifier,new Seance(idseance),photo,new Etudiant(nom,prenom,username));
				System.out.println(a.getEtudiants().getUsername());
				if(photo==null)
				s.add(a);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public boolean verifieAbsence(int idseance, String username) {
		try {
			result = state.executeQuery("SELECT * FROM absence WHERE idSeance = "+idseance+" AND username= '"+username+"';");
			return result.next();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;	
	}
	public boolean deleteAbsence(int idSeance) {
		try {
			int res = state.executeUpdate("DELETE FROM absence WHERE idseance = "+idSeance+" AND justification is null  ;");
			return res==1;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public boolean insertJustification(int idabsence,InputStream photo) {
		try {
				PreparedStatement pre = cn.prepareStatement("UPDATE absence SET justification = ? WHERE idAbsence = ?");
				pre.setBinaryStream(1, photo);
				pre.setInt(2, idabsence);
			
				int i = pre.executeUpdate();
			return i==1;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public  ArrayList<Absence> getAbsenceParUsername(String username){
		ArrayList<Absence> s = new ArrayList<Absence>();
		try {
			result = state.executeQuery("SELECT * FROM absence a, Etudiant e, seance s WHERE a.username='"+username+"' AND e.username = a.username AND s.idseance = a.idseance ;");
			while (result.next()) {
				int idAbsence = result.getInt("idAbsence");
				int idseance = result.getInt("idseance");
				boolean justifier = result.getBoolean("justifier");
				InputStream photo = result.getBinaryStream("justification");
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				String t = result.getString("type");
				TypeSeance type = TypeSeance.COUR;
				switch (t) {
				case "TD":
					type = TypeSeance.TD;
					break;
				case "TP":
					type = TypeSeance.TP;
					break;
				}
				Date date = result.getTimestamp("temp");
				int salle = result.getInt("salle");
				boolean avoirAbs = result.getBoolean("avoirAbs");
				int idGroupe = result.getInt("idGroupe");
				String abrModule = result.getString("abrModule");

			
				Absence a = new Absence(idAbsence,justifier,new Seance(idseance,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule)),photo,new Etudiant(nom,prenom,username));
				s.add(a);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//touts les module d'in departement
	public ArrayList<Module> getModuleDeDepartement(String nomDepartement){
		ArrayList<Module> s = new ArrayList<Module>();
		try {
			result = state.executeQuery("SELECT * FROM module m, formation f WHERE m.abrFormation = f.abrFormation AND Departement = '"+nomDepartement+"';");
			while (result.next()) {
				String nomModule = result.getString("nomModule");
				String abrModule = result.getString("abrModule");
				int semester = result.getInt("semester");
				String abrFormation = result.getString("abrFormation");
				String nomFormation = result.getString("nomFormation");
				String specialite = result.getString("specialite");
				String c = result.getString("cycle");
				Cycle cycle = Cycle.licence;
				switch (c) {
				case "master":
					cycle = Cycle.master;
					break;
				}
				int annee = result.getInt("annee");
				Module m = new Module(nomModule,abrModule,semester,new Formation(nomFormation,abrFormation,specialite,cycle,annee,nomDepartement));
				s.add(m);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//get types d'un module
	public ArrayList<String> getTypes(String abrModule){
		ArrayList<String> s = new ArrayList<String>();
		try {
			result = state.executeQuery("SELECT distinct type FROM module m,seance s WHERE m.abrModule = s.abrModule AND s.abrModule='"+abrModule+"';");
			while (result.next()) {
				String type = result.getString("type");
				s.add(type);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//get all groupes for one module
	public ArrayList<Groupe> getGroupesDunModule(String abrModule){
		ArrayList<Groupe> s = new ArrayList<Groupe>();
		try {
			result = state.executeQuery("SELECT * FROM groupe g,formation f, Module m WHERE g.abrFormation=f.abrFormation AND f.abrFormation = m.abrFormAtion AND m.abrModule = '"+abrModule+"';");
			while (result.next()) {
				int idGroupe = result.getInt("idGroupe");
				int numGroupe = result.getInt("numGroupe");
				int section = result.getInt("section");
				String abrFormation = result.getString("abrFormation");
				Groupe g = new Groupe(idGroupe,numGroupe,section,new Formation(abrFormation));
				s.add(g);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//faire affectations 
	public boolean faireAffectation(String username,String abrModule,int idgroupe,String type) {
		try {
			PreparedStatement pre = cn.prepareStatement("INSERT INTO enseignentmodulesgroupe values(?,?,?,?);");
			pre.setString(1, username);
			pre.setString(2, abrModule);
			pre.setInt(3, idgroupe);
			pre.setString(4, type);
			int i = pre.executeUpdate();
			return i==1;
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public  ArrayList<Absence> getAbsenceParNom(String nom,String prenom){
		ArrayList<Absence> s = new ArrayList<Absence>();
		try {
			result = state.executeQuery("SELECT * FROM absence a, Etudiant e, seance s WHERE e.nom='"+nom+"' AND e.prenom='"+prenom+"' AND e.username = a.username AND s.idseance = a.idseance and justifier=0 ;");
			while (result.next()) {
				int idAbsence = result.getInt("idAbsence");
				int idseance = result.getInt("idseance");
				boolean justifier = result.getBoolean("justifier");
				InputStream photo = result.getBinaryStream("justification");
				String username = result.getString("username");
				String t = result.getString("type");
				TypeSeance type = TypeSeance.COUR;
				switch (t) {
				case "TD":
					type = TypeSeance.TD;
					break;
				case "TP":
					type = TypeSeance.TP;
					break;
				}
				Date date = result.getTimestamp("temp");
				int salle = result.getInt("salle");
				boolean avoirAbs = result.getBoolean("avoirAbs");
				int idGroupe = result.getInt("idGroupe");
				String abrModule = result.getString("abrModule");
				Absence a = new Absence(idAbsence,justifier,new Seance(idseance,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule)),photo,new Etudiant(nom,prenom,username));
				s.add(a);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//les ensignant deja affecter
	public ArrayList<EnseiModuleGroupe> getAffectationDunModule(String abrModule){
		ArrayList<EnseiModuleGroupe> s = new ArrayList<EnseiModuleGroupe>();
		try {
			result = state.executeQuery("SELECT * FROM enseignentmodulesgroupe WHERE abrModule = '"+abrModule+"';");
			while (result.next()) {
				int idGroupe = result.getInt("idGroupe");
				String username = result.getString("username");
				String t = result.getString("type");
				t=t.toUpperCase();
				TypeSeance type = TypeSeance.COUR;
				switch (t) {
				case "TD":
					type = TypeSeance.TD;
					break;
				case "TP":
					type = TypeSeance.TP;
					break;
				}
				EnseiModuleGroupe e = new EnseiModuleGroupe(new Enseignent(username) ,new Module(abrModule),new Groupe(idGroupe),type);
				s.add(e);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//delete les affectattion d'un modules
	public boolean deleteAffectataion(String abrModule) {
		try {
			int res = state.executeUpdate("DELETE FROM enseignentmodulesgroupe WHERE abrModule = '"+abrModule+"'; ");
			return res==1;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	//
	public ArrayList<Seance> getETmp(String username)  {
		ArrayList<Seance> ars=new ArrayList<Seance>();
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println("dayOfWeek : "+dayOfWeek+" --  hourOfDay: "+ hourOfDay);
		String startDate = "", endDate = "";			
		Calendar c = GregorianCalendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		if(dayOfWeek==6||dayOfWeek==7||(dayOfWeek==5&&hourOfDay>=16)){
			if(dayOfWeek==5&&hourOfDay>=16) c.add(Calendar.DATE, 3);
			if(dayOfWeek==6) c.add(Calendar.DATE, 2);
			if(dayOfWeek==7) c.add(Calendar.DATE, 1);
			startDate = df.format(c.getTime());
			c.add(Calendar.DATE, 4);
			endDate = df.format(c.getTime());
		}
		else {
			if(dayOfWeek==2) c.add(Calendar.DATE, -1);
			if(dayOfWeek==3) c.add(Calendar.DATE, -2);
			if(dayOfWeek==4) c.add(Calendar.DATE, -3);
			if(dayOfWeek==5) c.add(Calendar.DATE, -4);

			startDate = df.format(c.getTime());
			c.add(Calendar.DATE, 4);
			endDate = df.format(c.getTime());
			System.out.println("Start Date = " + startDate);
			System.out.println("End Date = " + endDate);}
		
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date d=null;
			TypeSeance ty = null;
		try {
			result=state.executeQuery("select type,temp,salle,abrModule from seance,etudiant,groupe,groupeetudiant where etudiant.username=groupeetudiant.username and groupeetudiant.idGroupe=groupe.idGroupe and groupe.idGroupe=seance.idGroupe and etudiant.username=\""+username+"\" and temp between \'"+startDate+" 08:30:00\' and \'"+endDate+" 14:30:00\';");
			while(result.next()==true) {
				String t=result.getString(1);
				String temp=result.getString(2);
				d=sdf.parse(temp);
				int salle=result.getInt(3);
				String abrModule=result.getString(4);
				switch (t) {
				case "COUR":
					ty = TypeSeance.COUR;
				case "TD":
					ty = TypeSeance.TD;
					break;
				case "TP":
					ty = TypeSeance.TP;
					break;
				}
				ars.add(new Seance(ty,d,salle,new Module(abrModule)));
			}
		} catch (SQLException e) {
			System.out.println("sqlException");
		} catch (ParseException e) {
			System.out.println("parseException");
			e.printStackTrace();
		}			
		return ars;
	}
	//

	public ArrayList<Seance> getETmpEns(String username)  {
		ArrayList<Seance> ars=new ArrayList<Seance>();
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println("dayOfWeek : "+dayOfWeek+" --  hourOfDay: "+ hourOfDay);
		String startDate = "", endDate = "";			
		Calendar c = GregorianCalendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		if(dayOfWeek==6||dayOfWeek==7||(dayOfWeek==5&&hourOfDay>=16)){
			if(dayOfWeek==5&&hourOfDay>=16) c.add(Calendar.DATE, 3);
			if(dayOfWeek==6) c.add(Calendar.DATE, 2);
			if(dayOfWeek==7) c.add(Calendar.DATE, 1);
			startDate = df.format(c.getTime());
			c.add(Calendar.DATE, 4);
			endDate = df.format(c.getTime());
			System.out.println("Start Date = " + startDate);
			System.out.println("End Date = " + endDate);
		}
		else {
			if(dayOfWeek==2) c.add(Calendar.DATE, -1);
			if(dayOfWeek==3) c.add(Calendar.DATE, -2);
			if(dayOfWeek==4) c.add(Calendar.DATE, -3);
			if(dayOfWeek==5) c.add(Calendar.DATE, -4);

			startDate = df.format(c.getTime());
			c.add(Calendar.DATE, 4);
			endDate = df.format(c.getTime());
			System.out.println("Start Date = " + startDate);
			System.out.println("End Date = " + endDate);}
		
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date d=null;
			TypeSeance ty = null;

		try {
			result=state.executeQuery("select enseignentmodulesgroupe.type,temp,salle,module.abrmodule,numgroupe from seance,groupe,module,enseignent,enseignentmodulesgroupe where enseignent.username=enseignentmodulesgroupe.username and module.abrmodule=enseignentmodulesgroupe.abrmodule and groupe.idgroupe=enseignentmodulesgroupe.idgroupe and seance.idgroupe=groupe.idgroupe and module.abrmodule=seance.abrmodule and enseignent.username=\""+username+"\" and temp between \'"+startDate+" 08:30:00\' and \'"+endDate+" 14:30:00\';");
			while(result.next()==true) {
				String t=result.getString(1);
				String temp=result.getString(2);
				try {
					d=sdf.parse(temp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int salle=result.getInt(3);
				String abrModule=result.getString(4);
				int numGroupe=result.getInt(5);
				switch (t) {
				case "COUR":
					ty = TypeSeance.COUR;
				case "TD":
					ty = TypeSeance.TD;
					break;
				case "TP":
					ty = TypeSeance.TP;
					break;
				}
				System.out.println("numGroupe");
				Seance s = new Seance(ty,d,salle,new Module(abrModule),new Groupe(numGroupe));
				ars.add(s);
			}
		} catch (SQLException e) {
			System.out.println("sqlException");
		}
		return ars;
	}
	//les groupe d'un departement
	public ArrayList<Groupe> getGroupeDeDepartement(String nomDepartement){
		ArrayList<Groupe> s = new ArrayList<Groupe>();
		try {
			result = state.executeQuery("select * from groupe g, formation f where f.abrFormation = g.abrFormation AND f.Departement = '"+nomDepartement+"';");
			while (result.next()) {
				int idGroupe = result.getInt("idGroupe");
				int numGroupe = result.getInt("numGroupe");
				int section = result.getInt("section");
				String abrFormation = result.getString("abrFormation");
				String nomFormation = result.getString("nomFormation");
				String specialite = result.getString("specialite");
				String c = result.getString("cycle");
				Cycle cycle = Cycle.licence;
				switch (c) {
				case "master":
					cycle = Cycle.master;
					break;
				}
				int annee = result.getInt("annee");
				Groupe m = new Groupe(idGroupe,numGroupe,section,new Formation(nomFormation,abrFormation,specialite,cycle,annee,nomDepartement));
				s.add(m);
			}
			return s;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	// get touts les etudiants d'un groupe
		public ArrayList<Etudiant> getToutsLesEtudiants(String abrForamtion) {
			ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
			try {
				result = state.executeQuery("SELECT * FROM etudiant e, formation f WHERE f.abrFormation = e.abrFormation AND e.abrFormation = '"+abrForamtion+"';");
				while (result.next()) {
					String username = result.getString(1);
					String nom = result.getString(2);
					String prenom = result.getString(3);
					String email = result.getString(4);
					String pass = result.getString(5);
					Date dn = result.getDate(6);
					String ln = result.getString(7);
					String sexe = result.getString(8);
					Sexe e = Sexe.Femme;
					if (sexe.equalsIgnoreCase("Homme")) {
						e = Sexe.Homme;
					}
					String adresse = result.getString(9);
					String situationFamiliale = result.getString(10);
					SituationFamiliale s = SituationFamiliale.célibataire;
					switch (situationFamiliale) {
					case "célibataire":
						s = SituationFamiliale.célibataire;
						break;
					case "divorcé":
						s = SituationFamiliale.divorcé;
						break;
					case "marié":
						s = SituationFamiliale.marié;
						break;
					case "séparé":
						s = SituationFamiliale.séparé;
						break;
					case "veuf":
						s = SituationFamiliale.veuf;
						break;
					}
					InputStream f = result.getBinaryStream(11);
					String abr = result.getString(12);
					Etudiant en = new Etudiant(nom, prenom, username, email, pass, dn, ln, e, adresse, s, f,
							new Formation(abr));
					etudiants.add(en);
				}
				return etudiants;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		// affecter les etudiants
		public boolean faireAffectationDesGroupeAuxEtudiant(String username,int idgroupe) {
			try {
				PreparedStatement pre = cn.prepareStatement("INSERT INTO groupeetudiant values(?,?);");
				pre.setString(1, username);
				pre.setInt(2, idgroupe);
				int i = pre.executeUpdate();
				return i==1;
				
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			return false;
		}
		// supp affectations les etudiants d un groupe
		public boolean suppAffectationEtudaint(int idgroupe) {
					try {
						PreparedStatement pre = cn.prepareStatement("DELETE FROM groupeetudiant WHERE idGroupe = ? ;");
						pre.setInt(1, idgroupe);
						int i = pre.executeUpdate();
						return i==1;
						
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					return false;
				}
		//les etudiants deja affecter
		public ArrayList<Etudiant> getAffectationDunGroupe(int idGroupe){
			ArrayList<Etudiant> s = new ArrayList<Etudiant>();
			try {
				result = state.executeQuery("SELECT * FROM groupeetudiant g , Eutdiant e WHERE g.username = e.username and idGroupe = '"+idGroupe+"';");
				while (result.next()) {
					
					String username = result.getString("username");
					String nom = result.getString("nom");
					String prenom = result.getString("prenom");
					Etudiant e = new Etudiant(nom,prenom,username);
					s.add(e);
				}
				return s;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		}
		// les statistiques responsable de formation
		public ArrayList<Absence> toutsLesAbsence(String abrFormation){
			ArrayList<Absence> s = new ArrayList<Absence>();
			try {
				result = state.executeQuery("SELECT * FROM absence a, etudiant e, groupe g, seance s, module m, formation f WHERE a.idseance = s.idseance AND a.username = e.username AND g.idgroupe = s.idgroupe AND s.abrModule = m.abrModule AND m.abrFormation=f.abrFormation AND f.abrFormation = '"+abrFormation+"';");
				while (result.next()) {
					int idAbsence = result.getInt("idAbsence");
					Boolean justifier = result.getBoolean("justifier");
					int idSeance = result.getInt("idSeance");
					
					String t = result.getString("type");
					TypeSeance type = TypeSeance.COUR;
					switch (t) {
					case "TD":
						type = TypeSeance.TD;
						break;
					case "TP":
						type = TypeSeance.TP;
						break;
					}
					Date date = result.getTimestamp("temp");
					int salle = result.getInt("salle");
					boolean avoirAbs = result.getBoolean("avoirAbs");
					int idGroupe = result.getInt("idGroupe");
					
					
					int numGroupe = result.getInt("numGroupe");
					int section = result.getInt("section");
					
					
					//String abrFormation = result.getString("abrFormation");
					String nomFormation = result.getString("nomFormation");
					String specialite = result.getString("specialite");
					String c = result.getString("cycle");
					Cycle cycle = Cycle.licence;
					switch (c) {
					case "master":
						cycle = Cycle.master;
						break;
					}
					int annee = result.getInt("annee");
					String Departement = result.getString("Departement");
					Formation f = new Formation(nomFormation,abrFormation,specialite,cycle,annee,Departement);
					Groupe g = new Groupe(idGroupe,numGroupe,section,f);
					String nomModule = result.getString("nomModule");
					String abrModule = result.getString("abrModule");
					int semester = result.getInt("semester");
					Module m = new Module(nomModule,abrModule,semester,f);
					
					
					
					
					
					InputStream justification = result.getBinaryStream("justification");
					String username = result.getString("username");
					
					String nom = result.getString("nom");
					String prenom = result.getString("prenom");
					String email = result.getString("email");
					
					Date dn = result.getDate("dn");
					String ln = result.getString("ln");
					String sexe = result.getString("sexe");
					Sexe e = Sexe.Femme;
					if (sexe.equalsIgnoreCase("Homme")) {
						e = Sexe.Homme;
					}
					String adresse = result.getString("adresse");
					String situationFamiliale = result.getString("situationFamiliale");
					SituationFamiliale s1 = SituationFamiliale.célibataire;
					switch (situationFamiliale) {
					case "célibataire":
						s1 = SituationFamiliale.célibataire;
						break;
					case "divorcé":
						s1 = SituationFamiliale.divorcé;
						break;
					case "marié":
						s1 = SituationFamiliale.marié;
						break;
					case "séparé":
						s1 = SituationFamiliale.séparé;
						break;
					case "veuf":
						s1 = SituationFamiliale.veuf;
						break;
					}
					InputStream photo = result.getBinaryStream("photo");
					
					Etudiant e1 = new Etudiant(nom,prenom,username,email,null,dn,ln,e,adresse,s1,photo,f);
					
					
					
					
					Absence a = new Absence(idAbsence,justifier,new Seance(idSeance,type,date,salle,avoirAbs,g,m),justification,e1);
					s.add(a);
				}
				return s;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		}
		// les statistiques enseignant
				public ArrayList<Absence> toutsLesAbsenceParEnsigenant(String user){
					ArrayList<Absence> s = new ArrayList<Absence>();
					try {
						result = state.executeQuery("SELECT * FROM absence a, etudiant e, groupe g, seance s, module m, formation f,enseignentmodulesgroupe en,enseignent ens WHERE a.idseance = s.idseance AND a.username = e.username AND g.idgroupe = s.idgroupe AND s.abrModule = m.abrModule AND en.abrModule = m.abrModule AND en.username = ens.username AND m.abrFormation=f.abrFormation AND ens.username= '"+user+"';");
						while (result.next()) {
							int idAbsence = result.getInt("idAbsence");
							Boolean justifier = result.getBoolean("justifier");
							int idSeance = result.getInt("idSeance");
							
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Date date = result.getTimestamp("temp");
							int salle = result.getInt("salle");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							
							
							int numGroupe = result.getInt("numGroupe");
							int section = result.getInt("section");
							String abrFormation = result.getString("abrFormation");
							
							//String abrFormation = result.getString("abrFormation");
							String nomFormation = result.getString("nomFormation");
							String specialite = result.getString("specialite");
							String c = result.getString("cycle");
							Cycle cycle = Cycle.licence;
							switch (c) {
							case "master":
								cycle = Cycle.master;
								break;
							}
							int annee = result.getInt("annee");
							String Departement = result.getString("Departement");
							Formation f = new Formation(nomFormation,abrFormation,specialite,cycle,annee,Departement);
							Groupe g = new Groupe(idGroupe,numGroupe,section,f);
							String nomModule = result.getString("nomModule");
							String abrModule = result.getString("abrModule");
							int semester = result.getInt("semester");
							Module m = new Module(nomModule,abrModule,semester,f);
							
							
							
							
							
							InputStream justification = result.getBinaryStream("justification");
							String username = result.getString("username");
							
							String nom = result.getString("nom");
							String prenom = result.getString("prenom");
							String email = result.getString("email");
							
							Date dn = result.getDate("dn");
							String ln = result.getString("ln");
							String sexe = result.getString("sexe");
							Sexe e = Sexe.Femme;
							if (sexe.equalsIgnoreCase("Homme")) {
								e = Sexe.Homme;
							}
							String adresse = result.getString("adresse");
							String situationFamiliale = result.getString("situationFamiliale");
							SituationFamiliale s1 = SituationFamiliale.célibataire;
							switch (situationFamiliale) {
							case "célibataire":
								s1 = SituationFamiliale.célibataire;
								break;
							case "divorcé":
								s1 = SituationFamiliale.divorcé;
								break;
							case "marié":
								s1 = SituationFamiliale.marié;
								break;
							case "séparé":
								s1 = SituationFamiliale.séparé;
								break;
							case "veuf":
								s1 = SituationFamiliale.veuf;
								break;
							}
							InputStream photo = result.getBinaryStream("photo");
							
							Etudiant e1 = new Etudiant(nom,prenom,username,email,null,dn,ln,e,adresse,s1,photo,f);
							
							
							
							
							Absence a = new Absence(idAbsence,justifier,new Seance(idSeance,type,date,salle,avoirAbs,g,m),justification,e1);
							s.add(a);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				// les statistiques chef
				public ArrayList<Absence> toutsLesAbsenceChef(String Departement){
					ArrayList<Absence> s = new ArrayList<Absence>();
					try {
						result = state.executeQuery("SELECT * FROM absence a, etudiant e, groupe g, seance s, module m, formation f WHERE a.idseance = s.idseance AND a.username = e.username AND g.idgroupe = s.idgroupe AND s.abrModule = m.abrModule AND m.abrFormation=f.abrFormation AND f.Departement = '"+Departement+"';");
						while (result.next()) {
							int idAbsence = result.getInt("idAbsence");
							Boolean justifier = result.getBoolean("justifier");
							int idSeance = result.getInt("idSeance");
							
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Date date = result.getTimestamp("temp");
							int salle = result.getInt("salle");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							
							
							int numGroupe = result.getInt("numGroupe");
							int section = result.getInt("section");
							
							
							//String abrFormation = result.getString("abrFormation");
							String nomFormation = result.getString("nomFormation");
							String specialite = result.getString("specialite");
							String c = result.getString("cycle");
							Cycle cycle = Cycle.licence;
							switch (c) {
							case "master":
								cycle = Cycle.master;
								break;
							}
							int annee = result.getInt("annee");
							String abrFormation = result.getString("abrFormation");
							Formation f = new Formation(nomFormation,abrFormation,specialite,cycle,annee,Departement);
							Groupe g = new Groupe(idGroupe,numGroupe,section,f);
							String nomModule = result.getString("nomModule");
							String abrModule = result.getString("abrModule");
							int semester = result.getInt("semester");
							Module m = new Module(nomModule,abrModule,semester,f);
							
							
							
							
							
							InputStream justification = result.getBinaryStream("justification");
							String username = result.getString("username");
							
							String nom = result.getString("nom");
							String prenom = result.getString("prenom");
							String email = result.getString("email");
							
							Date dn = result.getDate("dn");
							String ln = result.getString("ln");
							String sexe = result.getString("sexe");
							Sexe e = Sexe.Femme;
							if (sexe.equalsIgnoreCase("Homme")) {
								e = Sexe.Homme;
							}
							String adresse = result.getString("adresse");
							String situationFamiliale = result.getString("situationFamiliale");
							SituationFamiliale s1 = SituationFamiliale.célibataire;
							switch (situationFamiliale) {
							case "célibataire":
								s1 = SituationFamiliale.célibataire;
								break;
							case "divorcé":
								s1 = SituationFamiliale.divorcé;
								break;
							case "marié":
								s1 = SituationFamiliale.marié;
								break;
							case "séparé":
								s1 = SituationFamiliale.séparé;
								break;
							case "veuf":
								s1 = SituationFamiliale.veuf;
								break;
							}
							InputStream photo = result.getBinaryStream("photo");
							
							Etudiant e1 = new Etudiant(nom,prenom,username,email,null,dn,ln,e,adresse,s1,photo,f);
							
							
							
							
							Absence a = new Absence(idAbsence,justifier,new Seance(idSeance,type,date,salle,avoirAbs,g,m),justification,e1);
							s.add(a);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				
				public String getNomDepartement(String usernameChef) {
					String nomDepartement=null;
					try {
						result=state.executeQuery("select nomDepartement from chef where username=\""+usernameChef+"\"");
					
					while(result.next()) {
						nomDepartement=result.getString(1);
						System.out.println("nomDepartement BD :: "+nomDepartement);
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return nomDepartement;
				}
				
				public ArrayList<Absence> getInfoAbsencesPourListeExclusPourTroisAbsences(String departement){
					ArrayList<Absence> ae=new ArrayList<Absence>();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date d=null;
					try {
					result=state.executeQuery("select idAbsence,etudiant.username,nom,prenom,seance.idseance,type,temp,avoirabs,abrmodule,formation.abrformation,numGroupe from etudiant,formation,absence,seance,groupe where groupe.idGroupe=seance.idGroupe and etudiant.abrformation=formation.abrformation and etudiant.username=absence.username and seance.idseance=absence.idseance and departement=\""+departement+"\" and justifier=0 group by idAbsence;");
					if(!result.next()) { System.out.println("Aucun absence non justifiée");return ae;}
					else {boolean b=true;
						do {
							int idAbsence=result.getInt(1);
							String username=result.getString(2);
							String nom=result.getString(3);
							String prenom=result.getString(4);
							int idSeance=result.getInt(5);
							String type=result.getString(6);
							TypeSeance ty = TypeSeance.COUR;
							switch (type) {
							case "TD":
								ty = TypeSeance.TD;
								break;
							case "TP":
								ty = TypeSeance.TP;
								break;
							}
							String temp=result.getString(7);
							
							d=sdf.parse(temp);							
							int avoirAbs=result.getInt(8);
							boolean ab=false;
							if(avoirAbs==1) ab=true;
							String abrModule=result.getString(9);
							String abrFormation=result.getString(10);
							int numGroupe=result.getInt(11);
							b=result.next();
							ae.add(new Absence(idAbsence,new Seance(idSeance,ty,d,ab,new Groupe(numGroupe),new Module(abrModule)),new Etudiant(username,nom,prenom,new Formation(abrFormation))));
						}while(b);		
					}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					return ae;
				}
				
				public ArrayList<Absence> getInfoAbsencesPourListeExclusTous(String departement){
					ArrayList<Absence> ae=new ArrayList<Absence>();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date d=null;
					try {
						result=state.executeQuery("select idAbsence,etudiant.username,nom,prenom,seance.idseance,type,temp,avoirabs,abrmodule,formation.abrformation,numGroupe from etudiant,formation,absence,seance,groupe where groupe.idGroupe=seance.idGroupe and etudiant.abrformation=formation.abrformation and etudiant.username=absence.username and seance.idseance=absence.idseance and departement=\""+departement+"\" group by idAbsence;");					
					if(!result.next()) { System.out.println("Aucun absence non justifiée");return ae;}
					else {boolean b=true;
						do {
							int idAbsence=result.getInt(1);
							String username=result.getString(2);
							String nom=result.getString(3);
							String prenom=result.getString(4);
							int idSeance=result.getInt(5);
							String type=result.getString(6);
							TypeSeance ty = TypeSeance.COUR;
							switch (type) {
							case "TD":
								ty = TypeSeance.TD;
								break;
							case "TP":
								ty = TypeSeance.TP;
								break;
							}
							String temp=result.getString(7);
							
							d=sdf.parse(temp);
							int avoirAbs=result.getInt(8);
							boolean ab=false;
							if(avoirAbs==1) ab=true;
							String abrModule=result.getString(9);
							System.out.println(abrModule+" :!!");
							String abrFormation=result.getString(10);
							int numGroupe=result.getInt(11);
							
							Groupe g=new Groupe(numGroupe);
							Module m=new Module(abrModule);
							Formation f=new Formation(abrFormation);
							Etudiant e=new Etudiant(username,nom,prenom,f);
							Seance s=new Seance(idSeance,ty,d,ab,g,m);
							Absence a=new Absence(idAbsence,s,e);
							b=result.next();
							ae.add(a);
						}while(b);		
					}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return ae;
				}
				
				public ArrayList<Absence> getInfoAbsencesPourListeExclusPourTroisAbsencesEns(String user){
					ArrayList<Absence> ae=new ArrayList<Absence>();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date d=null;
					System.out.println("user first "+user);
					try {
						result=state.executeQuery("select idAbsence,etudiant.username,etudiant.nom,etudiant.prenom,seance.idseance,seance.type,temp,avoirabs,seance.abrmodule,formation.abrformation,numGroupe from enseignent,etudiant,formation,absence,seance,groupe,enseignentmodulesgroupe where enseignent.username=enseignentmodulesgroupe.username and groupe.idGroupe=enseignentmodulesgroupe.idgroupe and groupe.idGroupe=seance.idGroupe and etudiant.abrformation=formation.abrformation and etudiant.username=absence.username and seance.idseance=absence.idseance and enseignent.username=\""+user+"\" and justifier=0 group by idAbsence;");					
					if(!result.next()) { System.out.println("Aucun absence non justifiée tr");return ae;}
					else {boolean b=true;
						do {
							int idAbsence=result.getInt(1);
							String username=result.getString(2);
							String nom=result.getString(3);
							String prenom=result.getString(4);
							int idSeance=result.getInt(5);
							String type=result.getString(6);
							TypeSeance ty = TypeSeance.COUR;
							switch (type) {
							case "TD":
								ty = TypeSeance.TD;
								break;
							case "TP":
								ty = TypeSeance.TP;
								break;
							}
							String temp=result.getString(7);
							
							d=sdf.parse(temp);							
							int avoirAbs=result.getInt(8);
							boolean ab=false;
							if(avoirAbs==1) ab=true;
							String abrModule=result.getString(9);
							String abrFormation=result.getString(10);
							int numGroupe=result.getInt(11);
							b=result.next();
							ae.add(new Absence(idAbsence,new Seance(idSeance,ty,d,ab,new Groupe(numGroupe),new Module(abrModule)),new Etudiant(username,nom,prenom,new Formation(abrFormation))));
						}while(b);		
					}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					return ae;
				}
				
				public ArrayList<Absence> getInfoAbsencesPourListeExclusTousEns(String user){
					ArrayList<Absence> ae=new ArrayList<Absence>();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date d=null;
					System.out.println("user second "+user);

					try {
						result=state.executeQuery("select idAbsence,etudiant.username,etudiant.nom,etudiant.prenom,seance.idseance,seance.type,temp,avoirabs,seance.abrmodule,formation.abrformation,numGroupe from enseignent,etudiant,formation,absence,seance,groupe,enseignentmodulesgroupe where enseignent.username=enseignentmodulesgroupe.username and groupe.idGroupe=enseignentmodulesgroupe.idgroupe and groupe.idGroupe=seance.idGroupe and etudiant.abrformation=formation.abrformation and etudiant.username=absence.username and seance.idseance=absence.idseance and enseignent.username=\""+user+"\" group by idAbsence;");					
					if(!result.next()) { System.out.println("Aucun absence non justifiée ts");return ae;}
					else {boolean b=true;
						do {
							int idAbsence=result.getInt(1);
							String username=result.getString(2);
							String nom=result.getString(3);
							String prenom=result.getString(4);
							int idSeance=result.getInt(5);
							String type=result.getString(6);
							TypeSeance ty = TypeSeance.COUR;
							switch (type) {
							case "TD":
								ty = TypeSeance.TD;
								break;
							case "TP":
								ty = TypeSeance.TP;
								break;
							}
							String temp=result.getString(7);
							
							d=sdf.parse(temp);
							int avoirAbs=result.getInt(8);
							boolean ab=false;
							if(avoirAbs==1) ab=true;
							String abrModule=result.getString(9);
							System.out.println(abrModule+" :!!");
							String abrFormation=result.getString(10);
							int numGroupe=result.getInt(11);
							
							Groupe g=new Groupe(numGroupe);
							Module m=new Module(abrModule);
							Formation f=new Formation(abrFormation);

							Etudiant e=new Etudiant(username,nom,prenom,f);
							Seance s=new Seance(idSeance,ty,d,ab,g,m);
							Absence a=new Absence(idAbsence,s,e);
							b=result.next();
							ae.add(a);
						}while(b);		
					}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return ae;
				}
				
				
				public ArrayList<Seance> seanceModuleGroupeDep(String departement) {
					ArrayList<Seance> s = new ArrayList<Seance>();
					try {
						result = state.executeQuery("SELECT DISTINCT justification,s.abrmodule,g.numgroupe, s.idGroupe,type,f.abrformation FROM groupe g, module m,seance s,absence a, formation f WHERE a.idseance=s.idseance and f.departement = '"+ departement + "' and s.idgroupe=g.idgroupe and g.abrformation = f.abrformation and m.abrFormation=f.abrFormation and justifier=0 order by numGroupe;");						
						while (result.next()) {
							String abr = result.getString("abrModule");
							int numGroupe = result.getInt("numGroupe");
							String abrFormation = result.getString("abrFormation");
							String t = result.getString("type");
							int idGroupe = result.getInt("idGroupe");

							TypeSeance type = TypeSeance.COUR;

							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							InputStream justification = result.getBinaryStream("justification");
							Groupe g = new Groupe(idGroupe,new Formation(abrFormation),numGroupe);
							Module m = new Module(abr);
							Seance se=new Seance(type,g,m);
							if(justification==null)
							s.add(se);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				
				public ArrayList<Absence> listeAbsencesJustifie(String Departement){
					ArrayList<Absence> s = new ArrayList<Absence>();
					try {
						result = state.executeQuery("SELECT * FROM absence a, etudiant e, groupe g, seance s, module m, formation f WHERE a.idseance = s.idseance AND a.username = e.username AND g.idgroupe = s.idgroupe AND s.abrModule = m.abrModule AND m.abrFormation=f.abrFormation AND justifier=0 and f.Departement = '"+Departement+"' order by e.username;");
						while (result.next()) {
							int idAbsence = result.getInt("idAbsence");
							int idSeance = result.getInt("idSeance");							
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Date date = result.getTimestamp("temp");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							int numGroupe = result.getInt("numGroupe");
							String abrFormation = result.getString("abrFormation");
							String abrModule = result.getString("abrModule");
							InputStream justification = result.getBinaryStream("justification");
							String username = result.getString("username");
							String nom = result.getString("nom");
							String prenom = result.getString("prenom");
							
							Formation f = new Formation(abrFormation);
							Module m = new Module(abrModule);
							Groupe g = new Groupe(idGroupe,numGroupe);
							Etudiant e1 = new Etudiant(username,nom,prenom,f);
							
							Absence a = new Absence(idAbsence,justification,new Seance(idSeance,type,date,avoirAbs,g,m),e1);
							s.add(a);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				
				
				public Absence getAbsence(int idAbsence){
					Absence a=null;
					try {
						result = state.executeQuery("SELECT * FROM absence a, etudiant e, groupe g, seance s, module m WHERE a.idseance = s.idseance AND a.username = e.username AND g.idgroupe = s.idgroupe AND s.abrModule = m.abrModule and a.idAbsence = '"+idAbsence+"' ;");
						if (result.next()) {
							int idSeance = result.getInt("idSeance");							
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Date date = result.getTimestamp("temp");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							int numGroupe = result.getInt("numGroupe");
							String abrFormation = result.getString("abrFormation");
							String abrModule = result.getString("abrModule");
							InputStream justification = result.getBinaryStream("justification");
							String username = result.getString("username");
							String nom = result.getString("nom");
							String prenom = result.getString("prenom");
							
							Formation f = new Formation(abrFormation);
							Module m = new Module(abrModule);
							Groupe g = new Groupe(idGroupe,numGroupe);
							Etudiant e1 = new Etudiant(nom,prenom,username,f);
							
							a = new Absence(idAbsence,justification,new Seance(idSeance,type,date,avoirAbs,g,m),e1);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return a;
				}
				
				public boolean accepterJustification(int idAbsence){
					int result=0;
					try {
						result=state.executeUpdate("update absence set justifier=1 where idAbsence=\""+idAbsence+"\" ;");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("result = "+result);
					if(result==1) 
					System.out.println("envoie email :la justification de l'absence idAbsence=... a été acceptée");
					
					return result==1;
				}
				
				public boolean refuserJustification(int idAbsence){
					int result=0;
					try {
						result=state.executeUpdate("update absence set justifier=0,justification=null where idAbsence=\""+idAbsence+"\" ;");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("result = "+result);
					if(result==1) 
					System.out.println("envoie email la justification de l'absence idAbsence=... a été refusée");
					
					return result==1;
				}
				public ArrayList<Absence> getAbsenceParNom(String nom, String prenom, String departement) {
					ArrayList<Absence> s = new ArrayList<Absence>();
					try {			
						result = state.executeQuery("SELECT * FROM absence a, Etudiant e, seance s, formation f WHERE f.abrformation=e.abrformation and f.departement='"+departement+"' and e.nom='"+nom+"' AND e.prenom='"+prenom+"' AND e.username = a.username AND s.idseance = a.idseance and justifier=0 ;");
						while (result.next()) {
							int idAbsence = result.getInt("idAbsence");
							int idseance = result.getInt("idseance");
							boolean justifier = result.getBoolean("justifier");
							InputStream photo = result.getBinaryStream("justification");
							String username = result.getString("username");
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Date date = result.getTimestamp("temp");
							int salle = result.getInt("salle");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							String abrModule = result.getString("abrModule");
							Absence a = new Absence(idAbsence,justifier,new Seance(idseance,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule)),photo,new Etudiant(nom,prenom,username));
							s.add(a);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				
				public ArrayList<Integer> getIdGroupesDUnEnseignant(String username) {
					ArrayList<Integer> groupes=new ArrayList<Integer>();
					try {
						result=state.executeQuery("select g.idgroupe from groupe g,enseignentmodulesgroupe emg,enseignent e where g.idgroupe=emg.idgroupe and e.username=emg.username and e.username='"+username+"';");
					while (result.next()) {
						int idGroupe = result.getInt(1);
						groupes.add(idGroupe);
					}
					return groupes;
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
					return null;	
				}
				
				public  ArrayList<Absence> getAbsenceParNom(String nom,String prenom,ArrayList<Integer> arg){
					ArrayList<Absence> s = new ArrayList<Absence>();
					try {			
						result = state.executeQuery("SELECT * FROM absence a, Etudiant e, seance s WHERE e.nom='"+nom+"' AND e.prenom='"+prenom+"' AND e.username = a.username AND s.idseance = a.idseance and justifier=0 ;");
						while (result.next()) {
							int idAbsence = result.getInt("idAbsence");
							int idseance = result.getInt("idseance");
							boolean justifier = result.getBoolean("justifier");
							InputStream photo = result.getBinaryStream("justification");
							String username = result.getString("username");
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							System.out.println("nom:"+nom);
							System.out.println("prenom:"+prenom);
							System.out.println("username:"+username);
							Date date = result.getTimestamp("temp");
							int salle = result.getInt("salle");
							boolean avoirAbs = result.getBoolean("avoirAbs");
							int idGroupe = result.getInt("idGroupe");
							String abrModule = result.getString("abrModule");
							Absence a = new Absence(idAbsence,justifier,new Seance(idseance,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule)),photo,new Etudiant(nom,prenom,username));
							for(int i:arg) {
								if(idGroupe==i) {
									s.add(a);break;
								}
							}
							
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				
				// get touts les seances d'un enseignant qui ont des absences
				public ArrayList<EnseiModuleGroupe> enseiModuleGroupe(String username) {
					ArrayList<EnseiModuleGroupe> s = new ArrayList<EnseiModuleGroupe>();
					try {
						result = state.executeQuery("SELECT distinct justification,s.idgroupe, g.numgroupe,g.abrformation,s.type,s.abrmodule FROM enseignentmodulesgroupe e, groupe g, module m, seance s, absence a WHERE e.username = '"+ username + "' and g.idGroupe = e.idGroupe and m.abrModule = e.abrModule and s.idgroupe=g.idgroupe and s.idseance=a.idseance and justifier=0;");
						while (result.next()) {
							String abr = result.getString("abrModule");
							int idGroupe = result.getInt("idGroupe");
							int numGroupe = result.getInt("numGroupe");
							InputStream justification = result.getBinaryStream("justification");
							//int section = result.getInt("section");
							String abrFormation = result.getString("abrFormation");
							//String nomModule = result.getString("nomModule");
							//int semester = result.getInt("semester");
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;

							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Groupe g = new Groupe(idGroupe, new Formation(abrFormation),numGroupe);
							Module m = new Module(abr);
							EnseiModuleGroupe en = new EnseiModuleGroupe(m, g, type);
							if(justification==null)
							s.add(en);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}
				// get touts les seances d'un enseignant
				public ArrayList<EnseiModuleGroupe> enseiModuleGroupePourFaireAppel(String username) {
					ArrayList<EnseiModuleGroupe> s = new ArrayList<EnseiModuleGroupe>();
					try {
						result = state.executeQuery("SELECT * FROM enseignentmodulesgroupe e, groupe g, module m WHERE username = '"+ username + "' and g.idGroupe = e.idGroupe and m.abrModule = e.abrModule;");
						while (result.next()) {
							String abr = result.getString("abrModule");
							int idGroupe = result.getInt("idGroupe");
							int numGroupe = result.getInt("numGroupe");
							int section = result.getInt("section");
							String abrFormation = result.getString("abrFormation");
							String nomModule = result.getString("nomModule");
							int semester = result.getInt("semester");
							String t = result.getString("type");
							TypeSeance type = TypeSeance.COUR;
							switch (t) {
							case "TD":
								type = TypeSeance.TD;
								break;
							case "TP":
								type = TypeSeance.TP;
								break;
							}
							Groupe g = new Groupe(idGroupe, numGroupe, section, new Formation(abrFormation));
							Module m = new Module(nomModule, abr, semester, new Formation(abrFormation));
							EnseiModuleGroupe en = new EnseiModuleGroupe(m, g, type);
							s.add(en);
						}
						return s;
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					return null;
				}


}
