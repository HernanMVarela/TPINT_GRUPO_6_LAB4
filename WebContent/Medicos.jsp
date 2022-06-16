<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="bootstrap/StyleSheet.css"></jsp:include>
	<jsp:include page="bootstrap/css/bootstrap.min.css"></jsp:include>
</style>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_medicos').DataTable();
} );
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de médicos</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
  
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
	  		<table id="tabla_medicos" border=2 class="table align-middle table-info table-hover th-lg">
	  		<thead>
				<tr class="table-secondary"><th> ID Medico </th><th> Nombre y apellido</th><th> DNI </th> <th> Especialidad </th><th> Estado </th></tr>
			</thead>
			<tbody>
				<tr class="table-danger"><td> 1000 </td><td> Christian Sepulveda </td><td> 111111111 </td><td> Cirugía Plástica </td><td> Ocupado </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> 22222222 </td><td> Cardiología Clínica </td><td> Disponible </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> 33333333 </td><td> Neurología  </td><td> Disponible </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> 44444444 </td><td> Otorrinolaringología </td><td> Disponible </td></tr>
			</tbody>
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
  		<label class="subtitle">Datos personales</label>
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
	<div class="row justify-content-center ms-3">
		<div class="col col-md-3">
			<input type="submit" class="btn btn-info w-100" name="btnAgregar" value="Agregar">
		</div>
 	</div>
 	<div class="row justify-content-center border-bottom m-3">
 	</div>
  </div><!-- FIN DE CONTAINER -->
</body>
</html>