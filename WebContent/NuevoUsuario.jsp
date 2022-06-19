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

<title>Nuevo usuario</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>

<div class="row mh-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Nuevo Usuario</label>
</div>
<div class="container-fluid">
	<div class="row justify-content-center">
	  	<div class="col col-md-6 mh-2 justify-content-center p-2">
	  		<label class="subtitle w-100">Datos personales</label>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="subtitle w-100">Cuenta de usuario</label>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Nombre</label>
		  	<input type="text" name="txfNombre" class="w-100">
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Apellido</label>
		  	<input type="text" name="txfApellido" class="w-100">
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Nombre de usuario</label>
		  	<input type="text" name="txfUsername" class="w-100">
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Documento</label>
		  	<input type="number" name="txfDocumento" class="w-100">
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Nacionalidad</label>
		  	<input type="text" name="txfNacionalidad" class="w-100">
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Contraseña</label>
		  	<input type="password" name="txfPassword" class="w-100">
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
		  	<label class="p-1">Sexo</label> 
			<select name="slcSexo" class="w-100">
				<option value="1">Seleccione opción</option>
				<option value="2">Masculino</option>
				<option value="3">Femenino</option>
			</select>
	    </div>
	     <div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Fecha de nacimiento</label>
		  	<input type="date" name="txfFechaNac" class="w-100">
	    </div>
	     <div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Verifique su contraseña</label>
		  	<input type="password" name="txfPassword" class="w-100">
	    </div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
	  		<label class="p-1">Provincia</label> 
			<select name="slcEsp" class="w-100">
				<option value="1">Seleccione opción</option>
				<option value="2">Provincia 1</option>
				<option value="3">Provincia 2</option>
				<option value="4">Provincia 3</option>
				<option value="5">Provincia 4</option>
			</select>
	  	</div>
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
	  		<label class="p-1">Localidad</label> 
			<select name="slcEsp" class="w-100">
				<option value="1">Seleccione opción</option>
				<option value="2">Localidad 1</option>
				<option value="3">Localidad 2</option>
				<option value="4">Localidad 3</option>
				<option value="5">Localidad 4</option>
			</select>
	  	</div>
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
	 		<label class="p-1">Dirección</label>
		  	<input type="text" name="txfDireccion" class="w-100">
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Tipo de usuario</label> 
			<select name="slcEsp" class="w-100">
				<option value="1">Seleccione opción</option>
				<option value="2">Médico</option>
				<option value="3">Administrador</option>
			</select>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  	
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  	
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  	  	<label class="p-1">Estado de cuenta</label> 
			<select name="slcEsp" class="w-100">
				<option value="1">Seleccione opción</option>
				<option value="2">Activo</option>
				<option value="3">Inactivo</option>
			</select>
	  	</div>
	  </div>
		<div class="row justify-content-center m-3">
			<div class="col col-md-3">
				<input type="submit" class="btn btn-info w-100" name="btnAgregar" value="Agregar">
			</div>
	 	</div>
  </div><!-- FIN DE CONTAINER -->
  
</body>
</html>