<%@page import="servlets.servletMedicos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Especialidad"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
	
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

<%! List<Medico> listaDeMedicos = null; %>
<%! List<Especialidad> listaEsp = null; %>

<jsp:include page="Menu.html"></jsp:include>
  <div class="row mx-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Médico</label>
	</div>
  <div class="container-fluid">
  <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2 border-right">
	  		<div class="row text-start p-2">
	  			<label class="p-1">Filtra por especialidad</label> 
	  			<select name="slcFiltoEsp" class="w-100">
	  				<option value="0">Seleccione opción</option>
	  				
	  				<% if(request.getAttribute("listaDeEsp")!=null){
	  					listaEsp = (ArrayList<Especialidad>)request.getAttribute("listaDeEsp");
	  					if(!listaEsp.isEmpty()) {
	  						for (Especialidad x: listaEsp){
	  							%><option value="<%= x.getIdEspecialidad() %>"><%= x.getNombre() %></option><%
	  						} 
	  					}
	  				}%>
	  				
	  				</select>
	  		</div>
	  		<div class="row text-start p-2">
	  			<label class="p-1">Buscar por nombre</label>
	  			<input type="text" name="txfBuscar" class="w-100">
	  		</div>
	  		<div class="row text-start p-2">
	  			<input type="submit" class="btn btn-info w-100" name="btnAceptar" value="Aceptar">
	  		</div>
	  	</div>
	  	<div class="col-md-10 table-responsive w-75">
	  		<table id="tabla_medicos" border=2 class="table align-middle table-info table-hover th-lg">
	  		<thead>
				<tr class="table-secondary"><th> ID Medico </th><th> Nombre y apellido</th><th> DNI </th> <th> Especialidad </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
			</thead>
			<tbody>
			<% if(request.getAttribute("listaMedicos")!=null){
  					listaDeMedicos = (ArrayList<Medico>)request.getAttribute("listaMedicos");
  					if(!listaDeMedicos.isEmpty()) {
  						for (Medico x: listaDeMedicos){
  							%>
  								<tr><td> <%= x.getIdMedico() %> </td>
  								<td> <%= x.getNombre() + " " + x.getApellido() %> </td> 
  								<td> <%= x.getDni() %>  </td>
								<td> <%= x.getEspecialidad().getNombre() %> </td>
								<td> <%= x.isEstado()? "Activo":"Inactivo"%> </td>
								<td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
  							<%
  						}   					
  					}
  				}
	  		%>
				
			</tbody>
			</table>
	  	</div> 
  	</div>
  	<div class="row mx-2 mb-2 justify-content-center p-2 border-bottom">
  		<div class="col-md-3 d-flex justify-content-center">
  			<form method="post" action="NuevoMedico.jsp" class="w-75">
				<input type="submit" class="btn btn-info w-100" name="btnAgregar" value="Agregar">
			</form>
		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info w-75" name="btnModificar" value="Modificar">
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<button type="button" class="btn btn-danger w-75" data-bs-toggle="modal" data-bs-target="#modalEliminar">Eliminar Seleccionado</button>
  		</div>
  	</div>
  	
  </div><!-- FIN DE CONTAINER -->
  
  	<div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar Médico</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>¿Desea eliminar este médico?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      				<form method="post" action="">
      					<input type="submit" name="btnEliminarMedico" class="btn btn-outline-danger w-100" value="Eliminar">
      				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>