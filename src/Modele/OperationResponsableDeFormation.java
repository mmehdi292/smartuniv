package Modele;

import java.util.ArrayList;

public class OperationResponsableDeFormation {
	
	public ArrayList<Absence> toutsLesAbsence(String abrFormation){
		ConnectionBD bd = new ConnectionBD();
		bd.startConnection();
		ArrayList<Absence> b = bd.toutsLesAbsence(abrFormation);
		bd.endConnection();
		return b;
	}

}
