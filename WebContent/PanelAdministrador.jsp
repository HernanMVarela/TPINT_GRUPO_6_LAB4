<%@page import="servlets.servletNuevoUsuario"%>
<%@page import="servlets.servletPanelAdministrador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Administrador"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Tipo"%>
<%@page import="entidad.Usuario"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="servlets.servletHome"%>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel de control - Administrador</title>
</head>

<body>
<%!ArrayList<Administrador> listaadmin = null;%>
<%!String mensaje = null; %>

<!-- MENU DE NAVEGACION Y LOGIN -->
<%! Usuario user = null; %>
<% if(session.getAttribute("login")!=null){ user= (Usuario)session.getAttribute("login");}else{user=null;} %>

<% if(user==null){%><jsp:include page="Menu.html"></jsp:include> <%} else { %><jsp:include page="MenuLog.html"></jsp:include> <% } %>
<% if(user!=null){%> 

<%if(request.getAttribute("Mensaje")!=null){ mensaje = (String)request.getAttribute("Mensaje");
	if(mensaje.equals("SELECT")){%><script>alert("No hay elemento seleccionado");</script><%}
	if(mensaje.equals("AGROK")){%><script>alert("Administrador agregado correctamente");</script><%}
	if(mensaje.equals("MODIOK")){%><script>alert("Administrador modificado correctamente");</script><%}
	if(mensaje.equals("ELIMOK")){%><script>alert("Administrador eliminado correctamente");</script><%}
	if(mensaje.equals("ERROR")){%><script>alert("No se pudo completar la operaci?n");</script><%}
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
		<h5>Inicie sesi?n para navegar</h5>
	</div>
</div>
<%
} %>

<div class="row mx-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Panel de control - Usuarios</label>
</div>

<%if(user!=null){ if(user.getTipo().getIdTipo() == 1){ %>

<form method="get" action="servletNuevoUsuario"><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
  <div class="container-fluid">

   <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
	  	<!-- TERMINA FILTROS -->
	  	<div class="col-md-auto table-responsive w-100">
	  		<table id="tabla_admins" border=2 class="display table align-middle table-info table-hover th-lg">
	  			<thead>
				<tr class="table-secondary"><th> ID Usuario </th><th> Nombre y apellido</th><th> DNI</th><th> Email</th>
				<th> Usuario </th> <th> Tipo Usuario </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
				</thead>
				<tbody>
				<%
				if(request.getAttribute("listaadmin")!=null){
					listaadmin = (ArrayList<Administrador>)request.getAttribute("listaadmin");
					if(!listaadmin.isEmpty()){
						for(Administrador admin : listaadmin){
						%>
						<tr <% if(!admin.isEstado()){%> class="table-danger" <%} %>>
						<td> <%=admin.getUsuario().getIdUsuario() %> </td>
						<td> <%=admin.getNombre() +" "+ admin.getApellido()%> </td>
						<td> <%=admin.getDni()%> </td>
						<td> <%=admin.getEmail() %> </td>
						<td> <%=admin.getUsuario().getUser() %> </td>
						<td> <%=admin.getUsuario().getTipo().getNombre() %> </td>
						<td> <%=admin.isEstado()? "Activo":"Inactivo"%> </td>
						<td align="center"> <input type="radio" name="radSelect" value="<%=admin.getUsuario().getIdUsuario()%>"></td>
						</tr>
						<%
						}
					}
				}
				%>
				</tbody>
			</table>
	  	</div> 
  	</div>
  <!-- TERMINA CUADRO -->
   	<div class="row mx-2 mb-2 justify-content-center p-2 border-bottom">
  		<div class="col-md-4 d-flex justify-content-center">
  			<a href="servletNuevoUsuario" class="btn btn-info w-75">Agregar</a>
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<input type="submit" class="btn btn-info w-75" name="btnModificarUsuario" value="Modificar">
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<button type="button" class="btn btn-danger w-75" data-bs-toggle="modal" data-bs-target="#modalEliminarUsuario">Eliminar</button>
  		</div>
  	</div>
  <!-- TERMINA BOTONES -->
  </div><!-- FIN DE CONTAINER -->
  
  	<div class="modal fade" id="modalEliminarUsuario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar Usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>?Desea eliminar este usuario?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	     			<input type="submit" name="btnEliminarUsuario" class="btn btn-outline-danger w-25" value="Eliminar">
				</div>
			</div>
		</div>
	</div>
</form>
<%}else{ %>
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<a href="servletHome" class="btn btn-outline-danger w-25 my-2">Permisos insuficientes - Volver a Home.</a>
	</div>
<%} 
}else{%>
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<a href="servletHome" class="btn btn-outline-danger w-25 my-2">No hay usuario logueado - Volver a Home.</a>
	</div>
<%}%>	
	

</body>
</html>