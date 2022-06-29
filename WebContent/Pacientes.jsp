<%@page import="servlets.servletPacientes"%>
<%@page import="servlets.servletNuevoPaciente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Paciente"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>

</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

$(document).ready( function () {
    $('#tabla_pacientes').DataTable();
} );
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de pacientes</title>
</head>
<body>
<%! List<Paciente> listaPacientes = null; %>

<jsp:include page="Menu.html"></jsp:include>

<div class="row mx-2 mb-2 justify-content-center w-auto p-2">
  	<label class="subtitle w-100">Pacientes</label>
</div>	
<div class="container-fluid w-auto">
	
   <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2 border-right">
	  		<div class="row text-start p-2">
	  			<label class="p-1">Buscar por nombre</label>
	  			<input type="text" name="txfBuscar" class="w-100">
	  		</div>
	  		<div class="row text-start p-2">
	  			<input type="submit" class="btn btn-info w-100" name="btnAceptar" value="Aceptar">
	  		</div>
	  	</div>
	  	<div class="col-md-10 table-responsive w-75">
	  		<table id="tabla_pacientes" border=2 class="table align-middle table-info table-hover th-lg">
	  		<thead>
				<tr class="table-secondary"><th width="10%"> ID Paciente </th><th> Nombre y apellido </th><th> DNI </th><th> Sexo </th>
				<th> Fecha de nacimiento </th><th> Dirección </th><th> Nacionalidad </th><th width="5%"> Seleccionar </th></tr>
			</thead>
			<% if(request.getAttribute("listaPacientes")!=null){
					listaPacientes = (ArrayList<Paciente>)request.getAttribute("listaPacientes");
  					if(!listaPacientes.isEmpty()) {
  						for (Paciente x: listaPacientes){
  							%>
  								<tr <% if(!x.isEstado()){%> class="table-danger" <%} %>>
  								<td> <%= x.getIdPaciente() %> </td>
  								<td> <%= x.getNombre() + " " + x.getApellido() %> </td> 
  								<td> <%= x.getDni() %>  </td>
								<td> <%= x.getSexo().getNombre() %> </td>
								<td> <%= x.getFecha_nacimiento().toString() %> </td>
								<td> <%= x.getDirecc().getCalleYNum() %> </td>
								<td> <%= x.getNacionalidad().getNombre() %> </td>
								<td align="center"> <input type="radio" name="radSelect" value="<%=x.getIdPaciente() %>"></td>
  							<%
  						}   					
  					}
  				}
	  		%>
			</table>
	  	</div> 
  	</div>
 
  	 <div class="row mx-2 mb-2 justify-content-center p-2 border-bottom">
  		<div class="col-md-3 d-flex justify-content-center">
  			<a href="NuevoPaciente.jsp" class="btn btn-info w-75 ">Agregar Paciente</a>
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<a href="NuevoPaciente.jsp" class="btn btn-info w-75 ">Modificar Seleccionado</a><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<button type="button" class="btn btn-danger w-75" data-bs-toggle="modal" data-bs-target="#modalEliminarPaciente">Eliminar Seleccionado</button>
  		</div>
  	</div>
  </div><!-- FIN DE CONTAINER -->
  
	<div class="modal fade" id="modalEliminarPaciente" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar Paciente</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>¿Desea eliminar este paciente?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      				<form method="post" action=""><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
      					<input type="submit" name="btnEliminarPaciente" class="btn btn-outline-danger w-100" value="Eliminar">
      				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>