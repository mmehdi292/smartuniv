package Controle;


import java.util.ArrayList;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Modele.Absence;
import Modele.OperationResponsableDeFormation;
//http://localhost:8080/SMART_UNIV/api/Responable/AbsenceInfo/l3gl
@Path("/Responable")
public class RestAPIResponsable {
	
	  @GET
	  @Path("/AbsenceInfo/{abrFormation}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Absence>  getStatistiques(@PathParam("abrFormation") String abrFormation) {
		  OperationResponsableDeFormation or = new OperationResponsableDeFormation();
		  System.out.println(abrFormation);
		  System.out.println("----");
		  ArrayList<Absence> a = or.toutsLesAbsence(abrFormation);
		  for(Absence as: a ) {
			  System.out.println(as.getIdAbsence());
		  }
		  return a;
	  }

}
