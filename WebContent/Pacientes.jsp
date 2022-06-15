<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="bootstrap/StyleSheet.css"></jsp:include>
	<jsp:include page="bootstrap/css/bootstrap.min.css"></jsp:include>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de pacientes</title>
</head>
<body>
  <div class="container-fluid mh-2">
    <div class="d-flex flex-wrap align-items-left justify-content-left justify-content-md-between py-3 mb-4 border-bottom">

      	<ul class="nav col-md-4 mb-2 justify-content-left mb-md-0">
           	<li><a href="Home.jsp" class="nav-link px-2 link-secondary">Home</a></li>
        	<li><a href="Pacientes.jsp" class="nav-link px-2 link-dark">Pacientes</a></li>
        	<li><a href="Medicos.jsp" class="nav-link px-2 link-dark">Médicos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">Turnos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">About</a></li>
     	</ul>
     	<a href="Login.jsp" class="d-flex align-items-center col-md-2 mb-2 mb-md-0 text-dark text-decoration-none"></a>

			<div class="col-md-auto text-end pr-2">
				<label class="p-1">Emanuel Barreto</label> 
	  		</div>
		<div class="row align-items-right justify-content-end">
      		<div class="col-md-auto text-end pr-2">
       			<input type="submit" class="btn btn-outline-primary me-2" name="btnCerrarSesion" value="Cerrar sesión"> 	
      		</div>
     	 </div>
    </div>
  </div>
  
  <div class="container-fluid">
  <div class="row d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2">
	  		
	  		<div class="row text-start p-2">
	  			<label class="p-1">Buscar por nombre</label>
	  			<input type="text" name="txfBuscar">
	  		</div>
	  		<div class="row text-start p-2">
	  			<input type="submit" class="btn btn-info" name="btnAceptar" value="Aceptar">
	  		</div>
	  	</div>
	  	<div class="col-md-auto table-responsive w-75">
	  		<table border=2 class="table align-middle table-info table-hover th-lg">
				<tr class="table-secondary"><th> ID Paciente </th><th> DNI </th><th> Nombre y apellido </th><th> Fecha de nacimiento </th><th> Dirección </th>
				<th> Localidad </th><th> Nacionalidad </th><th> Email </th><th> Teléfono </th></tr>
				<tr>
				<td> 11000 </td><td> 111111111 </td><td> Julio Rodas </td><td> 01/01/2001 </td><td> Arturo Ilia 5532 </td><td> Ricardo Rojas </td><td> Argentino/a </td><td> algo@dominio.com </td><td> 46234634646 </td></tr>
				<tr><td> 11001 </td><td> 22222222 </td> <td> Teresa Pérez </td><td> 24/11/1999 </td><td> Roca 5532 </td><td> El Talar </td><td> Argentino/a </td><td> algo@dominio.com </td><td> 46765344646 </td></tr>
				<tr><td> 11002 </td><td> 33333333 </td> <td> Armando Sandrini </td><td> 07/10/1975  </td><td> Juan M. Rosas 4663 </td><td> Tortuguitas </td><td> Argentino/a </td><td> algo@dominio.com </td><td> 46233525346 </td></tr>
				<tr><td> 11003 </td><td> 44444444 </td> <td> Laura Ezpeleta </td><td> 18/06/1983 </td><td> Juana Azurduy 5532 </td><td> Grand Bourg </td><td> Argentino/a </td><td> algo@dominio.com </td><td> 4623253546 </td></tr>
			</table>
	  	</div> 
  	</div>
  	
  	<!-- La parte que sigue abajo es para asignarle un turno a un paciente -->
  	<div class="row d-flex flex-wrap align-middle justify-content-center py-3 mb-4 border-bottom">
  		
  	</div>
  	
  	
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2">
  			<div class="row justify-content-center p-2">
  				<label class="p-1">ID Turno: </label> 
	  			<label class="p-1">45335</label>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Paciente</label> 
	  			<select name="slcSexo">
	  				<option value="1">Julio Rodas</option>
	  				<option value="2">Teresa Pérez</option>
	  				<option value="3">Armando Sandrini</option>
	  				<option value="4">Laura Ezpeleta</option>
	  			</select>
  			</div>
  		</div>
  		
  		<div class="col col-md-3 mh-2">
  		  	<div class="row justify-content-center p-2">
  				<label class="p-1">Fecha</label> 
	  			<select name="slcEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2"> Datetime1</option>
	  				<option value="3">2</option>
	  				<option value="4">3</option>
	  				<option value="5"> 4</option>
	  			</select>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Horario de atención</label>
	  			<p><input type="time" name=inpHoraInc> a <input type="time" name=inpHoraInc></p>
  			</div>
  		</div>
  		
  		<div class="col col-md-3 mh-2">
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Estado</label> 
	  			<select name="slcEsp">
	  				<option value="1">Libre</option>
	  				<option value="2"> Ocupado</option>
	  				<option value="3">Ausente</option>
	  				<option value="4">Presente</option>
	  			</select>
  			</div>
  		</div>
  		
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info" name="btnAsignarTurno" value="Asignar a un turno">
  		</div>
  	</div>
  </div>
</body>
</html>