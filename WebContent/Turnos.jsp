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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>

<div class="container-fluid">
<div class="row mh-2 mb-2 justify-content-center p-2">
  	<label class="subtitle w-100">Turnos</label>
</div>	
<div class="row mh-2 mb-2 justify-content-center p-2">
 	<div class="col-md-auto table-responsive w-100">
 		<table id="tabla_admins" border=2 class="display table align-middle table-info table-hover th-lg">
 			<thead>
				<tr class="table-secondary"><th> ID Turno </th><th> Medico</th><th> Especialidad </th> <th> Paciente </th><th> Fecha </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
			</thead>
			<tbody>
				<tr><td> 1000 </td><td> Christian Sepulveda </td><td> Cardiología </td><td> Josefina Suarez </td><td> 10-07-2022 14:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1001 </td><td> Emanuel Barreto </td> <td> Pediatría </td><td> Javier Caballero </td><td> 05-06-2022 13:00hs </td>
				<td class="table-danger"> AUSENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1002 </td><td> Hernán Varela </td> <td> Dermatología </td><td> Paulina Guerrero </td><td> 15-07-2022 08:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1003 </td><td> Bruno Molteni </td> <td> Neumología </td><td> Juana Reyes </td><td> 25-06-2022 17:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1004 </td><td> Crescencia Bello </td><td> Oftalmología </td><td> Natalia Domínquez </td><td> 03-07-2022 10:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1005 </td><td> Celeste Sosa </td> <td> Ortopedia </td><td> Benjamín Dávila </td><td> 08-06-2022 14:00hs </td>
				<td class="table-success"> PRESENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1006 </td><td> Gloria Gaspar </td> <td> Pediatría </td><td> Hipólito Espinoza </td><td> 13-07-2022 18:00hs</td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1007 </td><td> María Velásquez </td> <td> Neumología </td><td> Rodrigo Pascual </td><td> 15-07-2022 13:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1008 </td><td> Tulio Jiménez </td><td> Psiquiatría General </td><td> Javier Velasco </td><td> 10-06-2022 16:00hs </td>
				<td class="table-success"> PRESENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1009 </td><td> Fidel Valencia </td> <td> Neurología </td><td> Rebeca Dominguez </td><td> 17-05-2022 12:00hs </td>
				<td class="table-danger"> AUSENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1010 </td><td> Ada Antonio </td> <td> Urología </td><td> Simón Dominguez </td><td> 19-05-2022 19:00hs </td>
				<td class="table-success"> PRESENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1011 </td><td> Isaura Varela </td> <td> Hematología </td><td> Luna Carmona </td><td> 23-05-2022 10:00hs </td>
				<td class="table-success"> PRESENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1012 </td><td> Covadonga Zambrano </td><td> Oncología </td><td> Constanza Marquez </td><td> 30-07-2022 11:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1013 </td><td> Marisol Valencia </td> <td> Dermatología </td><td> Nadia Peña </td><td> 26-05-2022 10:00hs </td>
				<td class="table-danger"> AUSENTE </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1014 </td><td> Patrocinio Quijada </td> <td> Psiquiatría </td><td> Martina Cano </td><td> 21-07-2022 16:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
				<tr><td> 1015 </td><td> Gloria Rey </td> <td> Cardiología </td><td> Bruno Vargas </td><td> 20-07-2022 10:00hs </td>
				<td class="table-warning"> OCUPADO </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
			</tbody>
		</table>
 	</div> 
</div>
<div class="row mh-2 mb-2 justify-content-center p-2">
	<div class="col-md-3 d-flex justify-content-center">
		<form method="post" action="VerTurno.jsp">	
		<input type="submit" name="btnVerDetalle" class="btn btn-info p-3 w-75" value="Ver detalle del turno">
		</form>
	</div>
	<div class="col-md-3 d-flex justify-content-center">
		<form method="post" action="NuevoTurno.jsp" class="w-75">
		<input type="submit" name="btnAgregarTurno" class="btn btn-info p-3 w-100" value="Agregar nuevo turno">
		</form>
	</div>
	<div class="col-md-3 d-flex justify-content-center">
		<form method="post" action="NuevoTurno.jsp" class="w-75">
			<input type="submit" name="btnModificarTurno" class="btn btn-info p-3 w-75" value="Modificar turno">
		</form>
	</div>
	
	<div class="col-md-3 d-flex justify-content-center">
		<button type="button" class="btn btn-danger w-75" data-bs-toggle="modal" data-bs-target="#modalEliminarTurno">Eliminar turno</button>
	</div>
</div>
 
	<div class="modal fade" id="modalEliminarTurno" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar Turno</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>¿Desea eliminar este turno?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	     				<form method="post" action="">
	     					<input type="submit" name="btnEliminarTurno" class="btn btn-outline-danger w-100" value="Eliminar">
	     				</form>
				</div>
			</div>
		</div>
	</div>
 
</div> <!-- Fin del container -->
</body>
</html>