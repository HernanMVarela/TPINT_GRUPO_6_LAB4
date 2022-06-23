<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>Nuevo Paciente</title>
</head>
<body>
<div class="container-fluid">
	<jsp:include page="Menu.html"></jsp:include>
	<div class="row mh-2 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo paciente</label>
	</div>	

  	<div class="row justify-content-center">
  		<label class="subtitle w-75">Datos personales</label>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nombre</label>
	  		<input type="text" name="txfNombre" class="w-100" required>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Apellido</label>
	  		<input type="text" name="txfApellido" class="w-100" required>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Documento</label>
	  		<input type="number" name="txfDocumento" class="w-100" required>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=inpNacimiento class="w-100" required>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nacionalidad</label>
	  		<select name="slcNacionalidad" class="w-100">
  				<option value="1">Seleccione opción</option>
  				<option value="2">Aregentina</option>
  				<option value="3">Bolivia</option>
  				<option value="3">Uruguay</option>
  				<option value="3">Ecuador</option>
  				<option value="3">Brasil</option>
  			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Sexo</label> 
  			<select name="slcSexo" class="w-100">
  				<option value="1">Seleccione opción</option>
  				<option value="2">Masculino</option>
  				<option value="3">Femenino</option>
  			</select>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Provincia</label> 
  			<select name="slcEsp" class="w-100">
  				<option value="1">Seleccione opción</option>
  				<option value="2">Provincia 1</option>
  				<option value="3">Provincia 2</option>
  				<option value="4">Provincia 3</option>
  				<option value="5">Provincia 4</option>
  			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Localidad</label> 
  			<select name="slcEsp" class="w-100">
  				<option value="1">Seleccione opción</option>
  				<option value="2">Localidad 1</option>
  				<option value="3">Localidad 2</option>
  				<option value="4">Localidad 3</option>
  				<option value="5">Localidad 4</option>
  			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Dirección</label>
	  		<input type="text" name="txfDireccion" class="w-100" required>
  		</div>
  	</div>
	<div class="row justify-content-center border-bottom mb-3">
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Correo Electrónico</label>
	  		<input type="email" name="txfCorreo" class="w-100" required>
  		</div>
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Telefono</label>
	  		<input type="text" name="txfTelefono" class="w-100" required>
  		</div>
	</div>
  	
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center mh-2 p-2">
	  		<input type="submit" name="btnAceptar" class="btn btn-success p-3 w-100" value="Aceptar">
	  	</div>	
	</div>	
</div>	
</body>
</html>