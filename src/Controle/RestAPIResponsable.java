package Controle;


import java.util.ArrayList;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Modele.Absence;
import Modele.OperationChefDepartement;
import Modele.OperationEnseignent;
import Modele.OperationResponsableDeFormation;
//http://localhost:8080/SMART_UNIV/api/Responable/AbsenceInfo/l3gl
//http://localhost:8080/SMART_UNIV/api/Responable/AbsenceInfoEnseignant/epayley12
//http://localhost:8080/SMART_UNIV/api/Responable/AbsenceInfoChef/TLSI
@Path("/Responable")
public class RestAPIResponsable {
	
	  @GET
	  @Path("/AbsenceInfo/{abrFormation}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Absence>  getStatistiques(@PathParam("abrFormation") String abrFormation) {
		  OperationResponsableDeFormation or = new OperationResponsableDeFormation();
		  ArrayList<Absence> a = or.toutsLesAbsence(abrFormation);
		  return a;
	  }
	  
	  @GET
	  @Path("/AbsenceInfoEnseignant/{username}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Absence>  getStatistiquesEnseignant(@PathParam("username") String username) {
		  OperationEnseignent or = new OperationEnseignent();
		  ArrayList<Absence> a = or.toutsLesAbsenceParEnsigenant(username);
		  return a;
	  }
	  @GET
	  @Path("/AbsenceInfoChef/{Departement}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Absence>  getStatistiquesChef(@PathParam("Departement") String Departement) {
		  OperationChefDepartement or = new OperationChefDepartement();
		  ArrayList<Absence> a = or.toutsLesAbsenceParChef(Departement);
		  return a;
	  }

}
