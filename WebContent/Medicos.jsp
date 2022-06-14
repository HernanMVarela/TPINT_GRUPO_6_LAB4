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
<title>Panel de médicos</title>
</head>
<body>
  <div class="container-fluid mh-2">
    <div class="d-flex flex-wrap align-items-left justify-content-left justify-content-md-between py-3 mb-4 border-bottom">

      	<ul class="nav col-md-4 mb-2 justify-content-left mb-md-0">
        	<li><a href="Home.jsp" class="nav-link px-2 link-secondary">Home</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">Pacientes</a></li>
        	<li><a href="Medicos.jsp" class="nav-link px-2 link-dark">Médicos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">Turnos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">About</a></li>
     	</ul>
     	<a href="Login.jsp" class="d-flex align-items-center col-md-2 mb-2 mb-md-0 text-dark text-decoration-none"></a>

		<div class="row align-items-right justify-content-end">
      		<div class="col-md-auto text-end pr-2">
       			<input type="submit" class="btn btn-outline-primary me-2" name="btnLogin" value="Login"> 	
      		</div>
      		<div class="col-md-auto text-end pr-2">
				<input type="text" name="txfUsername" placeholder="Usuario" class="form-control" required>
	  		</div>
	  		<div class="col-md-auto text-end pr-2">
				<input type="Password" name="txfPassword" placeholder="Contraseña" class="form-control" required>
     		</div>
     	 </div>
    </div>
  </div>
  
  <div class="container-fluid">
  <div class="row d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2">
	  		<div class="row text-start p-2">
	  			<label class="p-1">Filtra por especialidad</label> 
	  			<select name="slcFiltoEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Especialidad 1</option>
	  				<option value="3">Especialidad 2</option>
	  				<option value="4">Especialidad 3</option>
	  				<option value="5">Especialidad 4</option>
	  			</select>
	  		</div>
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
				<tr class="table-secondary"><th> ID Medico </th><th> Nombre y apellido</th><th> DNI </th> <th> Especialidad </th><th> Estado </th></tr>
				<tr class="table-danger"><td> 1000 </td><td> Christian Sepulveda </td><td> 111111111 </td><td> Cirugía Plástica </td><td> Ocupado </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> 22222222 </td><td> Cardiología Clínica </td><td> Disponible </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> 33333333 </td><td> Neurología  </td><td> Disponible </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> 44444444 </td><td> Otorrinolaringología </td><td> Disponible </td></tr>
			</table>
	  	</div> 
  	</div>
  	<div class="row d-flex flex-wrap align-middle justify-content-center py-3 mb-4 border-bottom">
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info" name="btnModificar" value="Modificar">
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info" name="btnBorrar" value="Eliminar">
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2">
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Nombre</label>
	  			<input type="text" name="txfNombre">
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Apellido</label>
	  			<input type="text" name="txfApellido">
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Documento</label>
	  			<input type="text" name="txfDocumento">
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Sexo</label> 
	  			<select name="slcSexo">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Masculino</option>
	  				<option value="3">Femenino</option>
	  			</select>
  			</div>
  		</div>
  		<div class="col col-md-3 mh-2">
  		  	<div class="row justify-content-center p-2">
  				<label class="p-1">Especialidad</label> 
	  			<select name="slcEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Especialidad 1</option>
	  				<option value="3">Especialidad 2</option>
	  				<option value="4">Especialidad 3</option>
	  				<option value="5">Especialidad 4</option>
	  			</select>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=inpNacimiento>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Nacionalidad</label>
	  			<input type="text" name="txfNacionalidad">
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Correo electrónico</label>
	  			<input type="text" name="txfCorreo">
  			</div>
  			<div class="row justify-content-center p-2">
	  			<input type="submit" class="btn btn-info" name="btnAgregar" value="Agregar">
  			</div>
  		</div>
  		<div class="col col-md-3 mh-2">
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Dirección</label>
	  			<input type="text" name="txfDireccion">
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Provincia</label> 
	  			<select name="slcEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Provincia 1</option>
	  				<option value="3">Provincia 2</option>
	  				<option value="4">Provincia 3</option>
	  				<option value="5">Provincia 4</option>
	  			</select>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Localidad</label> 
	  			<select name="slcEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Localidad 1</option>
	  				<option value="3">Localidad 2</option>
	  				<option value="4">Localidad 3</option>
	  				<option value="5">Localidad 4</option>
	  			</select>
  			</div>
  			<div class="row justify-content-center p-2">
  				<label class="p-1">Horario de atención</label>
	  			<p><input type="time" name=inpHoraInc> a <input type="time" name=inpHoraInc></p>
  			</div>
  		</div>
  	</div>
  </div>
</body>
</html>