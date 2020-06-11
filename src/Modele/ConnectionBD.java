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
import java.util.Date;

public class ConnectionBD {
	public Connection cn;
	public Statement state;
	public ResultSet result;
	public String user = "root";
	public String pass = "root";
	public String nameDatabase = "smartuniv";

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
			String sql = "DELETE FROM Responsable WHERE username like \"" + username + "\";";
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
		int res = 0;
		try {
			String sql = "DELETE FROM etudiant WHERE username like \"" + username + "\";";
			res = state.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL");
		}
		return res == 1;
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

	// ---------------------------- gestion historique -----------------
	// consulter historique
	public ArrayList<Historique> consulterHistorique() {
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
					Seance seance = new Seance(id,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule));
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
				int id =result.getInt(1);
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
				return new Seance(id,type,date,salle,avoirAbs,new Groupe(idGroupe),new Module(abrModule));
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
						pre.setBoolean(5,e.isAvoirAbs());
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
					Vacance vacance = new Vacance(id,date,t);
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
				PreparedStatement pre = cn.prepareStatement(
						"INSERT INTO vacance (date,description) VALUES(?,?);");
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
				Date date =result.getDate(2);
				String description = result.getString(3);
				return new Vacance(id,date,description);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		// modifier vacance
		public boolean modifierVacance(Vacance e) {
			try {
				PreparedStatement pre = cn.prepareStatement(
						"UPDATE vacance SET date = ?,description  =? WHERE id = ?;");
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
				PreparedStatement pre = cn.prepareStatement(
						"INSERT INTO groupe (numGroupe,section,abrFormation) VALUES(?,?,?);");
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
						int id =result.getInt(1);
						int num =result.getInt(2);
						int sec =result.getInt(3);
						String abrFormation = result.getString(4);
						return new Groupe(id,num,sec,new Formation(abrFormation));
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
}