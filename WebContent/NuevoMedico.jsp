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

<title>Nuevo Médico</title>
</head>
<body>
<div class="container-fluid">
	<jsp:include page="Menu.html"></jsp:include>
	<div class="row mh-2 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo médico</label>
	</div>	

  	<div class="row justify-content-center">
  		<label class="subtitle w-75">Datos personales</label>
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
  			<label class="p-1">Documento</label>
	  		<input type="number" name="txfDocumento" class="w-100">
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=inpNacimiento class="w-100">
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nacionalidad</label>
	  		<input type="text" name="txfNacionalidad" class="w-100">
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
  	<div class="row justify-content-center border-bottom mb-3">
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
	  		<input type="text" name="txfDireccion" class="w-100">
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-6 mh-2 justify-content-center p-2">
  			<label class="subtitle w-100">Datos laborales</label>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="subtitle w-100">Cuenta de usuario</label>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Hoarios de atencion</label>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Domingo</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TIDomingo" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TEDomingo" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Lunes</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TILunes" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TELunes" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Martes</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TIMarteso" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TEMartes" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Miércoles</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TIMiercoles" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TEMiercoles" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Jueves</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TIJueves" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TEJueves" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Viernes</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TIViernes" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TEViernes" class="w-100">
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Sábado</label>
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TISabado" class="w-100">
  				</div>
  				<div class="col col-md-4">
  					<input type="time" name="TESabado" class="w-100">
  				</div>
  			</div>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2"> 
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Especialidad</label> 
	  			<select name="slcEsp" class="w-100">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Especialidad 1</option>
	  				<option value="3">Especialidad 2</option>
	  				<option value="4">Especialidad 3</option>
	  				<option value="5">Especialidad 4</option>
	  			</select>
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Correo Electrónico</label>
	  			<input type="email" name="txfCorreo" class="w-100">
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Telefono</label>
	  			<input type="text" name="txfTelefono" class="w-100">
	  		</div>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2"> 
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Nombre de usuario</label>
	  			<input type="text" name="txfUsername" class="w-100">	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Contraseña</label>
	  			<input type="password" name="txfPassword1" class="w-100">	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Confirme contraseña</label>
	  			<input type="password" name="txfPassword2" class="w-100">	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Tipo de cuenta</label>
	  			<select name="slcTipocuente" class="w-100">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Médico</option>
	  				<option value="3">Administrador</option>
	  			</select>	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Estado</label>
	  			<select name="slcEstado" class="w-100">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Activa</option>
	  				<option value="3">Inactiva</option>
	  			</select>		
	  		</div>
  		</div>
  	</div>
  	
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center mh-2 p-2">
		<form method="post" action=""><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
	  		<input type="submit" name="btnAceptar" class="btn btn-success p-3 w-100" value="Aceptar">
	  	</form>
	  	</div>	
	</div>	
</div>	
</body>
</html>