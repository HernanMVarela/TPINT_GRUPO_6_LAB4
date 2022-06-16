<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_admins').DataTable();
} );
</script>

<style type="text/css">
	<jsp:include page="bootstrap/StyleSheet.css"></jsp:include>
	<jsp:include page="bootstrap/css/bootstrap.min.css"></jsp:include>
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de control - Administrador</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
<div class="row mh-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Panel de control - Usuarios</label>
</div>
  <div class="container-fluid">
   <div class="row d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2">
	  		<div class="row text-start p-2">
	  			<label class="p-1">Filtra por estado</label> 
	  			<select name="slcFiltoEsp">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Activo</option>
	  				<option value="3">Inactivo</option>
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
	  	<!-- TERMINA FILTROS -->
	  	<div class="col-md-auto table-responsive w-75">
	  		<table id="tabla_admins" border=2 class="display table align-middle table-info table-hover th-lg">
	  			<thead>
				<tr class="table-secondary"><th> ID Usuario </th><th> Nombre y apellido</th><th> DNI </th> <th> Tipo Usuario </th><th> Estado </th></tr>
				</thead>
				<tbody>
				<tr><td> 1000 </td><td> Christian Sepulveda </td><td> 111111111 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> 22222222 </td><td> Médico </td><td class="table-danger"> Inactivo </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> 33333333 </td><td> Médico </td><td> Activo </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> 44444444 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1004 </td><td> Crescencia Bello </td><td> 55555555 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1005 </td><td> Celeste Sosa </td> <td> 66666666 </td><td> Médico </td><td class="table-danger"> Inactivo </td></tr>
				<tr><td> 1006 </td><td> Gloria Gaspar </td> <td> 77777777 </td><td> Médico </td><td> Activo </td></tr>
				<tr><td> 1007 </td><td> María Velásquez </td> <td> 88888888 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1008 </td><td> Tulio Jiménez </td><td> 99999999 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1009 </td><td> Fidel Valencia </td> <td> 10101010 </td><td> Médico </td><td class="table-danger"> Inactivo </td></tr>
				<tr><td> 1010 </td><td> Ada Antonio </td> <td> 11000000 </td><td> Médico </td><td> Activo </td></tr>
				<tr><td> 1011 </td><td> Isaura Varela </td> <td> 12000000 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1012 </td><td> Covadonga Zambrano </td><td> 13000000 </td><td> Administrador </td><td> Activo </td></tr>
				<tr><td> 1013 </td><td> Marisol Valencia </td> <td> 14000000 </td><td> Médico </td><td class="table-danger"> Inactivo </td></tr>
				<tr><td> 1014 </td><td> Patrocinio Quijada </td> <td> 15000000 </td><td> Médico </td><td> Activo </td></tr>
				<tr><td> 1015 </td><td> Gloria Rey </td> <td> 16000000 </td><td> Administrador </td><td> Activo </td></tr>
				</tbody>
			</table>
	  	</div> 
  	</div>
  <!-- TERMINA CUADRO -->
   	<div class="row d-flex flex-wrap align-middle justify-content-center py-3 mb-4 border-bottom">
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info" name="btnModificar" value="Modificar">
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info" name="btnBorrar" value="Eliminar">
  		</div>
  	</div>
  <!-- TERMINA BOTONES -->
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