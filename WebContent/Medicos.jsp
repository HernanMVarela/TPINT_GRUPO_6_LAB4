<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#tabla_medicos').DataTable();
} );
</script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de médicos</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
  <div class="row mh-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Médico</label>
	</div>
  <div class="container-fluid">
  <div class="row mh-2 mb-2 justify-content-center p-2">
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
				<tr class="table-secondary"><th> ID Medico </th><th> Nombre y apellido</th><th> DNI </th> <th> Especialidad </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
			</thead>
			<tbody>
				<tr class="table-danger"><td> 1000 </td><td> Christian Sepulveda </td><td> 111111111 </td><td> Cirugía Plástica </td><td> Ocupado </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> 22222222 </td><td> Cardiología Clínica </td><td> Disponible </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> 33333333 </td><td> Neurología  </td><td> Disponible </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> 44444444 </td><td> Otorrinolaringología </td><td> Disponible </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
			</tbody>
			</table>
	  	</div> 
  	</div>
  	<div class="row mh-2 mb-2 justify-content-center p-2 border-bottom">
  		<div class="col-md-3 d-flex justify-content-center">
  			<form method="post" action="NuevoMedico.jsp" class="w-75">
				<input type="submit" class="btn btn-info w-100" name="btnAgregar" value="Agregar">
			</form>
		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info w-75" name="btnModificar" value="Modificar">
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<input type="submit" class="btn btn-danger w-75" name="btnBorrar" value="Eliminar">
  		</div>
  	</div>
  	
  </div><!-- FIN DE CONTAINER -->
</body>
</html>