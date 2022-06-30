<%@page import="servlets.servletPacientes"%>
<%@page import="servlets.servletNuevoPaciente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.Usuario"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="servlets.servletHome"%>

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
<%! String mensaje = null; %>

<!-- MENU DE NAVEGACION Y LOGIN -->
<%! Usuario user = null; %>
<% if(session.getAttribute("login")!=null){ user= (Usuario)session.getAttribute("login");}else{user=null;} %>

<% if(user==null){%><jsp:include page="Menu.html"></jsp:include> <%} else { %><jsp:include page="MenuLog.html"></jsp:include> <% } %>
<% if(user!=null){%> 


<%if(request.getAttribute("Mensaje")!=null){ mensaje = (String)request.getAttribute("Mensaje");
	if(mensaje.equals("SELECT")){%><script>alert("No hay elemento seleccionado");</script><%}
	if(mensaje.equals("AGROK")){%><script>alert("Paciente agregado correctamente");</script><%}
	if(mensaje.equals("MODIOK")){%><script>alert("Paciente modificado correctamente");</script><%}
	if(mensaje.equals("ELIMOK")){%><script>alert("Paciente eliminado correctamente");</script><%}
	if(mensaje.equals("ERROR")){%><script>alert("No se pudo completar la operación");</script><%}
}%>


<div class="d-flex flex-row bd-highlight">

	<div class="px-2 flex-grow-1 bd-highlight"></div>
 	 <div class="px-6 mx-4 bd-highlight">
 		<h5>Usuario actual: <%=user.getUser() %></h5>
 	</div>
</div>
<% } else {
%> 
<div class="d-flex flex-row bd-highlight">
	<div class="px-2 flex-grow-1 bd-highlight"></div>
	<div class="px-6 mx-4 bd-highlight">
		<h5>Inicie sesión para navegar</h5>
	</div>
</div>
<%
} %>

<div class="row mx-2 mb-2 justify-content-center w-auto p-2">
  	<label class="subtitle w-100">Pacientes</label>
</div>	

<%if(user!=null){ %>
<div class="container-fluid w-auto">
<form action="servletNuevoPaciente" method="get">
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
  			<a href="servletNuevoPaciente" class="btn btn-info w-75 ">Agregar Paciente</a>
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<input type="submit" name="btnModificarPaciente" class="btn btn-info w-75 " value="Modificar Seleccionado">
  		</div>
  		<div class="col-md-3 d-flex justify-content-center">
  			<button type="button" class="btn btn-danger w-75" data-bs-toggle="modal" data-bs-target="#modalEliminarUsuario"
  			<%if(user.getTipo().getIdTipo()!=1){%> disabled<%} %>>Eliminar Seleccionado</button>
  		</div>
  	</div>

	<div class="modal fade" id="modalEliminarUsuario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar Usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>¿Desea eliminar este usuario?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      				<input type="submit" name="btnEliminarUsuario" class="btn btn-outline-danger w-100" value="Eliminar">
				</div>
			</div>
		</div>
	</div>
</form>
</div><!-- FIN DE CONTAINER -->
<%}else{ %>
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<a href="servletHome" class="btn btn-outline-danger w-25 my-2">No hay usuario logueado - Volver a Home.</a>
	</div>
<%} %>	

</body>
</html>