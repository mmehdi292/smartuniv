package Modele;

import java.util.ArrayList;

public class OperationChefDepartement {
	// get TOUTS les modules d'un deparement
	public ArrayList<Module> getModuleDeDepartement(String nomdepartemnt) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Module> modules = bd.getModuleDeDepartement(nomdepartemnt);
		bd.endConnection();
		return modules;
	}

	// get TOUTS les modules d'un deparement
	public ArrayList<String> getTypes(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<String> types = bd.getTypes(abrModule);
		bd.endConnection();
		return types;
	}

	// get TOUTS les groupe d'un module
	public ArrayList<Groupe> getGroupesDunModule(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Groupe> groupes = bd.getGroupesDunModule(abrModule);
		bd.endConnection();
		return groupes;
	}

	// affecter les seanses aux etudiant
	public boolean faireAffectation(String username, String abrModule, int idgroupe, String type) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		Boolean b = bd.faireAffectation(username, abrModule, idgroupe, type);
		bd.endConnectionWithOutResult();
		return b;
	}

	// get les affectation d'un module
	public ArrayList<EnseiModuleGroupe> getAffectationDunModule(String abrModule) {
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<EnseiModuleGroupe> b = bd.getAffectationDunModule(abrModule);
		bd.endConnection();
		return b;
	}
	// delete les affectations d'un module
	public boolean deleteAffectataion(String abrModule) {
			ConnectionBD bd = new ConnectionBD();
			bd.startConnection();
			boolean b = bd.deleteAffectataion(abrModule);
			bd.endConnectionWithOutResult();
			return b;
		}
}
