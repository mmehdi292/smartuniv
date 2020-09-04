<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/template/header.jsp" />
<title>Espace Responsable de formation</title>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/template/ResponsableSide.jsp" />
	<!--content start-->
	<div class="content">
		<jsp:include page="/WEB-INF/template/topnavbar.jsp" />
		<jsp:include page="/WEB-INF/template/message.jsp" />
		
		<!--top navbar end-->

		<!--title page with add button start-->
		<div class="titlePage">
			<h2>Espace Responsable de formation</h2>
		</div>
		<div class="tableDiv">
		<div class="row">
			<div id="pargroupe" class="col-md-5" style="height: 300px;"></div>
			<div class="col-md-1"></div>
			<div id="parSexe" class="col-md-5" style="height: 300px;"></div>
		</div>
		<div class="row">
			<div id="parmodule" class="col-md-5" style="height: 300px;"></div>
			<div class="col-md-1"></div>
			<div id="partemp" class="col-md-5" style="height: 300px;"></div>
		</div>
			
				
		</div>
		<!--table end-->
		</div>
		<!--footer start-->
		<div class="footer">
			<p>tous les droits sont réservés © 2020</p>
		</div>
		<!--sidebar end-->
		<script type="text/javascript">

				window.onload = function () {
					
					var groupe = [];
					var groupeName = [];
				    var absence = [];
				    var homme =0;
				    var femme =0;
				    var modules = [];
				    var modulesFois = [];
				    var dates = [];
				    var t1 = 0;
				    var t2 = 0;
				    var t3 = 0;
				    var t4 = 0;
				    var t5 = 0;
					
					fetch('http://localhost:8080/SMART_UNIV/api/Responable/AbsenceInfo/${abrForamtion}').then(
							function(response) {
							      if (!response.ok) {
							        throw new Error("HTTP error, status = " + response.status);
							      }
							      return response.json();
							   }
					).then(
							
							function(json) {
							      
							      json.forEach((absence) => {
							    	  var g = absence.seance.groupe.idGroupe;
							    	  groupe.push(g);
							    	  modules.push(absence.seance.module.abrModule);
							    	  var sexe = absence.etudiants.sexe;
							    	  if(sexe == "Homme"){
							    		  homme++;
							    	  }
							    	  else{
							    		  femme++;
							    	  }
							  
							      });
							      groupe = Array.from(new Set(groupe));
							      modules = Array.from(new Set(modules));
							      groupe.forEach((g)=>{
							    	  var x = 0;
							    	  var num = 0;
							    	  json.forEach((f)=>{
							    		  
							    		  if(f.seance.groupe.idGroupe == g){
							    			  num = f.seance.groupe.numGroupe;
							    			  x++;
							    		  }
							    			  
							    	  });
							    	  absence.push(x);
							    	  groupeName.push(num);
							      });
							      modules.forEach((g)=>{
							    	  var x = 0;
							    	  json.forEach((f)=>{
							    		  
							    		  if(f.seance.module.abrModule == g){
							    			  x++;
							    		  }
							    		  
							    			  
							    	  });
							    	  modulesFois.push(x);
							      });
							      
							      console.log(modules);
							      console.log(modulesFois);
							
					
					
					
					
					var infoGroupe = [];
					for (var k in groupeName){
					
						infoGroupe.push(
							{
								label:groupeName[k],
								y:absence[k]
							}
						);
					}
					var infoModule = [];
					for (var k in modules){
					
						infoModule.push(
							{
								label: modules[k],
								y: modulesFois[k]
							}
						);
					}
					
					
					
					json.forEach((absence) => {
				    	  var date = absence.seance.temp;
				    	  var tab = date.split('Z[UTC]');
				    	  var t = tab[0].split('T');
				    	  switch (t[1]) {
				    	 	 case "8:30:00":
				    	   		 	t1++;
				    	   		 	break;
				    	  	case "08:30:00":
				    	   			 t1++;
				    	   		 	break;
					    	  case "10:00:00":
						    	    t2++;
						    	    break;
					    	  case "11:30:00":
						    	    t3++;
						    	    break;
					    	  case "13:00:00":
						    	    t4++;
						    	    break;
					    	  case "14:30:00":
						    	    t5++;
						    	    break;
				    	 	 default:
				    	 		console.log(t);
				    	}
				  
				      });
					
					
					
					
					
					var chart = new CanvasJS.Chart("pargroupe", {
						title:{
							text: "Les groupes les plus absentes"              
						},
						data: [              
						{
							
							type: "column",
							dataPoints: infoGroupe
						}
						]
					});
					chart.render();
					var chart = new CanvasJS.Chart("parSexe", {
						title:{
							text: "les plus absents par sexe"              
						},
						data: [              
						{
							
							type: "column",
							dataPoints: [
								{ label: "Homme",  y: homme  },
								{ label: "Femme", y: femme  }
							]
						}
						]
					});
					chart.render();
					var chart = new CanvasJS.Chart("parmodule", {
						title:{
							text: "La plupart des modules que les étudiants ne assiste pas"              
						},
						data: [              
						{
							
							type: "column",
							dataPoints: infoModule
						}
						]
					});
					chart.render();
					var chart = new CanvasJS.Chart("partemp", {
						title:{
							text: "Le temps pendant lequel l'étudiants est absent"              
						},
						data: [              
						{
							
							type: "column",
							dataPoints: [
								{ label: "8:30 - 10:00",  y: t1  },
								{ label: "10:00 - 11:30", y: t2  },
								{ label: "11:30 - 13:00", y: t3  },
								{ label: "13:00 - 14:30",  y: t4  },
								{ label: "14:30 - 16:00",  y: t5  }
							]
						}
						]
					});
					chart.render();
					
					
							});
				}
		</script>
	</div>
	<!--sidebar end-->
	<jsp:include page="/WEB-INF/template/footer.jsp" />