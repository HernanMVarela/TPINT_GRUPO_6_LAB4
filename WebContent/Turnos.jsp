<%@page import="servlets.servletTurnos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Turno"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.Especialidad"%>
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
<%! List<Turno> listaTurnos = null; %>

<jsp:include page="Menu.html"></jsp:include>

<div class="container-fluid">
<form action="servletNuevoTurno" method="get">
<div class="row mx-2 mb-2 justify-content-center p-2">
  	<label class="subtitle w-100">Turnos</label>
</div>	
 <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
 	<div class="col-md-auto table-responsive w-100">
 		<table id="tabla_admins" border=2 class="display table align-middle table-info table-hover th-lg">
 			<thead>
				<tr class="table-secondary"><th width="10%"> ID Turno </th><th> Medico</th><th> Especialidad </th> 
				<th> Paciente </th><th> DNI Paciente </th> <th> Sexo </th> <th> Horario </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
			</thead>
			<tbody>
			<% if(request.getAttribute("listaTurnos")!=null){
				listaTurnos = (ArrayList<Turno>)request.getAttribute("listaTurnos");
  					if(!listaTurnos.isEmpty()) {
  						for (Turno x: listaTurnos){
  							%>
  								<tr <% if(!x.isEstado()){%> class="table-danger" <%} %>>
  								<td> <%= x.getIdTurno() %> </td>
  								<td> <%= x.getMedico().getNombre() + " " + x.getMedico().getApellido() %> </td> 
  								<td> <%= x.getEspecialidad().getNombre() %>  </td>
								<td> <%= x.getPaciente().getNombre() + " " + x.getPaciente().getApellido() %> </td>
								<td> <%= x.getPaciente().getDni() %>  </td>
								<td> <%= x.getPaciente().getSexo().getNombre() %>  </td>
								<td> <%= x.getDia() + " " + x.getHora()+":00hs" %>  </td>
								<td <% if(x.getEstadoTurno().getIdEstado()==1){%> class="table-info" <%}%>
									<% if(x.getEstadoTurno().getIdEstado()==2){%> class="table-warning" <%}%>
									<% if(x.getEstadoTurno().getIdEstado()==3){%> class="table-danger" <%}%>
									<% if(x.getEstadoTurno().getIdEstado()==4){%> class="table-success" <%}%>
								> <%= x.getEstadoTurno().getNombre() %> </td>
								<td align="center"> <input type="radio" name="radSelect" value="<%=x.getIdTurno() %>"></td>
  							<%
  						}   					
  					}
  				}
	  		%>
			</tbody>
		</table>
 	</div> 
</div>
<div class="row mx-2 mb-2 justify-content-center p-2">
	<div class="col-md-3 d-flex justify-content-center">
		<input type="submit" name="btnVerTurno" class="btn btn-info w-75" value="Ver detalle del turno">
	</div>
	<div class="col-md-3 d-flex justify-content-center">
		<a href="servletNuevoTurno" class="btn btn-info w-75">Agregar nuevo turno</a>
	</div>
	<div class="col-md-3 d-flex justify-content-center">
		<input type="submit" class="btn btn-info w-75" name="btnModificarTurno" value="Modificar">
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
					<input type="submit" name="btnEliminarTurno" class="btn btn-outline-danger w-25" value="Eliminar">
				</div>
			</div>
		</div>
	</div>
 </form>
</div> <!-- Fin del container -->
</body>
</html>