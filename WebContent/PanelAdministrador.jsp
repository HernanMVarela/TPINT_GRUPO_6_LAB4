<%@page import="servlets.servletNuevoUsuario"%>
<%@page import="servlets.servletPanelAdministrador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Administrador"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Tipo"%>

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
<%!ArrayList<Medico> listamedic = null;%>

<jsp:include page="Menu.html"></jsp:include>

<div class="row mx-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Panel de control - Usuarios</label>
</div>
  <div class="container-fluid">
   <div class="row mx-2 d-flex flex-wrap align-middle justify-content-evenly">
	  	<div class="col-md-2 border-right">
	  		<div class="row text-start p-2">
	  			<label class="p-1">Filtra por estado</label> 
	  			<select name="slcFiltoEsp" class="w-100">
	  				<option value="1">Seleccione opción</option>
	  				<option value="2">Activo</option>
	  				<option value="3">Inactivo</option>
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
	  	<!-- TERMINA FILTROS -->
	  	<div class="col-md-10 table-responsive w-75">
	  		<table id="tabla_admins" border=2 class="display table align-middle table-info table-hover th-lg">
	  			<thead>
				<tr class="table-secondary"><th> ID Usuario </th><th> Nombre y apellido</th><th> Usuario </th> <th> Tipo Usuario </th><th> Estado </th><th width="5%"> Seleccionar </th></tr>
				</thead>
				<tbody>
				<%
				if(request.getAttribute("listaadmin")!=null){
					listaadmin = (ArrayList<Administrador>)request.getAttribute("listaadmin");
					if(!listaadmin.isEmpty()){
						for(Administrador admin : listaadmin){
						%>
						<tr><td> <%=admin.getUsuario().getIdUsuario() %> </td><td> <%=admin.getNombre() +" "+ admin.getApellido()%> </td>
						<td> <%=admin.getUsuario().getUser() %> </td><td> <%=admin.getUsuario().getTipo().getNombre() %> </td><td> <%=admin.isEstado() %> </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
						<%
						}
					}
				}
				if(request.getAttribute("listamedic")!=null){
					listamedic = (ArrayList<Medico>)request.getAttribute("listamedic");
					if(!listamedic.isEmpty()){
						for(Medico medic : listamedic){
						%>
						<tr><td> <%=medic.getUsuario().getIdUsuario() %> </td><td> <%=medic.getNombre() +" "+ medic.getApellido()%> </td>
						<td> <%=medic.getUsuario().getUser() %> </td><td> <%=medic.getUsuario().getTipo().getNombre() %> </td><td> <%=medic.isEstado() %> </td><td align="center"> <input type="radio" name="radSelect" value=""> </td></tr>
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
  			<a href="NuevoUsuario.jsp" class="btn btn-info w-75">Agregar</a><!-- REEMPLAZAR RUTA POR SERVLET -->
  		</div>
  		<div class="col-md-4 d-flex justify-content-center">
  			<form action="" method="get" class="w-75"><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
  				<input type="submit" class="btn btn-info w-100" name="btnModificar" value="Modificar">
  			</form>
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
					<p>¿Desea eliminar este usuario?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	     				<form method="post" action=""><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
	     					<input type="submit" name="btnEliminarUsuario" class="btn btn-outline-danger w-100" value="Eliminar">
	     				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>