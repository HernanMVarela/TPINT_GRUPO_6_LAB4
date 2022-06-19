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
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

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
				<tr class="table-secondary"><th> ID Usuario </th><th> Nombre y apellido</th><th> DNI </th> <th> Tipo Usuario </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
				</thead>
				<tbody>
				<tr><td> 1000 </td><td> Christian Sepulveda </td><td> 111111111 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> 22222222 </td><td> Médico </td><td class="table-danger"> Inactivo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> 33333333 </td><td> Médico </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> 44444444 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1004 </td><td> Crescencia Bello </td><td> 55555555 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1005 </td><td> Celeste Sosa </td> <td> 66666666 </td><td> Médico </td><td class="table-danger"> Inactivo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1006 </td><td> Gloria Gaspar </td> <td> 77777777 </td><td> Médico </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1007 </td><td> María Velásquez </td> <td> 88888888 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1008 </td><td> Tulio Jiménez </td><td> 99999999 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1009 </td><td> Fidel Valencia </td> <td> 10101010 </td><td> Médico </td><td class="table-danger"> Inactivo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1010 </td><td> Ada Antonio </td> <td> 11000000 </td><td> Médico </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1011 </td><td> Isaura Varela </td> <td> 12000000 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1012 </td><td> Covadonga Zambrano </td><td> 13000000 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1013 </td><td> Marisol Valencia </td> <td> 14000000 </td><td> Médico </td><td class="table-danger"> Inactivo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1014 </td><td> Patrocinio Quijada </td> <td> 15000000 </td><td> Médico </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1015 </td><td> Gloria Rey </td> <td> 16000000 </td><td> Administrador </td><td> Activo </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				</tbody>
			</table>
	  	</div> 
  	</div>
  <!-- TERMINA CUADRO -->
   	<div class="row mh-2 mb-2 justify-content-center p-2 border-bottom">
  		<div class="col-md-4 d-flex justify-content-center">
  			<form method="post" action="NuevoUsuario.jsp" class="w-75">
  			<input type="submit" class="btn btn-info w-100" name="btnAgregar" value="Agregar">
  			</form>
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info w-75" name="btnModificar" value="Modificar">
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-danger w-75" name="btnBorrar" value="Eliminar">
  		</div>
  	</div>
  <!-- TERMINA BOTONES -->
  </div><!-- FIN DE CONTAINER -->
  
</body>
</html>