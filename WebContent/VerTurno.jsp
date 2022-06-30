<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno"%>
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle de turnos</title>
</head>
<body>
<%!Turno turno = null;%>

<!-- MENU DE NAVEGACION Y LOGIN -->
<%! Usuario user = null; %>
<% if(session.getAttribute("login")!=null){ user= (Usuario)session.getAttribute("login");}else{user=null;} %>

<% if(user==null){%><jsp:include page="Menu.html"></jsp:include> <%} else { %><jsp:include page="MenuLog.html"></jsp:include> <% } %>
<% if(user!=null){%> 
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


<div class="container-fluid">
	<form action="servletNuevoTurno" method="get">
	<div class="row mx-1 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Detalle del turno</label>
	</div>
<%
if(request.getAttribute("turno")!=null){
	turno=(Turno)request.getAttribute("turno");
	%><input type="hidden" name="turnoId" value="<%= turno.getIdTurno()%>"><%
}
%>

<%if(user!=null){ 
	if(turno!=null){
		if(user.getIdUsuario() == turno.getMedico().getUsuario().getIdUsuario() || user.getTipo().getIdTipo()==1){
		 %>
	<div class="row mx-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del paciente</label>
			<h5>Nombre completo: <%=turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido() %></h5><br>
			<h5>DNI: <%= turno.getPaciente().getDni() %></h5><br>
			<h5>Nacionalidad: B<%= turno.getPaciente().getNacionalidad().getNombre() %></h5><br>
			<h5>Sexo: <%= turno.getPaciente().getSexo().getNombre() %></h5><br>
			<h5>Fecha de nacimiento: <%=turno.getPaciente().getFecha_nacimiento().toString() %></h5><br>
			<h5>Telefono: <%= turno.getPaciente().getTelefono() %></h5><br>
			<h5>Correo: <%= turno.getPaciente().getEmail() %></h5><br>
			<h5>Dirección: <%= turno.getPaciente().getDirecc().getCalleYNum() %></h5><br>
			<h5>Localidad: <%= turno.getPaciente().getDirecc().getLoc().getNombre() %></h5><br>
			<h5>Provincia: <%= turno.getPaciente().getDirecc().getLoc().getProvincia().getNombre() %></h5><br>
		</div>
		<div class="col mx-1 col-md-5 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del turno</label>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Medico: <%= turno.getMedico().getNombre() + " " + turno.getMedico().getApellido() %></h4>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Especialidad: <%= turno.getEspecialidad().getNombre() %> </h4><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Paciente: <%=turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido() %></h4><br>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Sexo: <%= turno.getPaciente().getSexo().getNombre() %></h4><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h5>Fecha: <%= turno.getDia().toString() %> - Hora: <%=turno.getHora() %>:00hs</h5><br>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h5>Estado del turno: <%= turno.getEstadoTurno().getNombre() %></h5><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-12 mh-2 d-flex justify-content-between p-2">
					<textarea rows="5" style="width: 100%; max-width: 100%;" disabled><%=turno.getObservacionConsulta() %></textarea>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
					<a href="servletNuevoTurno" class="btn btn-primary p-2 w-100">Nuevo turno</a>
				</div>
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
				<%	if(user.getTipo().getIdTipo()==3){%> <input type="submit" name="btnModificarTurno" class="btn btn-secondary p-2 w-100" value="Modificar turno" disabled> <%} 
					else {%><input type="submit" name="btnModificarTurno" class="btn btn-primary p-2 w-100" value="Modificar turno"><%}%>
				</div>
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
					<button type="button" class="btn btn-danger p-2 w-100" data-bs-toggle="modal" data-bs-target="#modalEliminarTurno"<%if(user.getTipo().getIdTipo()!=1){%> disabled <%}%>>Eliminar turno</button>
				</div>
			</div>
			
		</div>
		<div class="col col-md-3 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del médico</label>
			<h5>Nombre completo: <%= turno.getMedico().getNombre() + " " + turno.getMedico().getApellido() %></h5><br>
			<h5>Especialidad: <%= turno.getMedico().getEspecialidad().getNombre() %></h5><br>
			<h5>DNI: <%= turno.getMedico().getDni() %></h5><br>
			<h5>Legajo: <%= turno.getMedico().getIdMedico() %></h5><br>
			<h5>Fecha de nacimiento: <%= turno.getMedico().getFecha_nacimiento().toString() %> </h5><br>
			<h5>Telefono: <%= turno.getMedico().getTelefono() %></h5><br>
			<h5>Correo: <%= turno.getMedico().getEmail() %> </h5><br>
			<h5>Dirección: <%= turno.getMedico().getDirecc().getCalleYNum() %> </h5><br>
			<h5>Localidad: <%= turno.getMedico().getDirecc().getLoc().getNombre() %> </h5><br>
			<h5>Provincia: <%= turno.getMedico().getDirecc().getLoc().getProvincia().getNombre() %></h5><br>
		</div>
	</div>		
	<div class="row mx-1 mb-2 justify-content-center p-2">
	  	<a href="servletTurnos" name="btnVolver" class="btn btn-outline-primary p-2 w-25">Volver</a>
	</div>
	

  	<div class="modal fade" id="modalEliminarTurno" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Eliminar turno</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>¿Desea eliminar este turno?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      				<input type="submit" name="btnEliminarTurno" class="btn btn-outline-danger w-25" value="Eliminar">
				</div>
			</div>
		</div>
	</div>

	
</form>
</div>
			<!-- PASA OK - CODIGO DE PAGINA  -->
		<% } else { %>
			<div class="row mh-2 mb-2 justify-content-center p-2"><a href="servletHome" class="btn btn-outline-danger w-25 my-2">Permisos insuficientes - Volver a Home.</a></div>
		<% }	
	}else{ %>
		<div class="row mx-1 mb-2 justify-content-center p-2"><label class="subtitle w-100">NO HAY UN TURNO SELECCIONADO</label></div>
	<%} 
}else{%>
	<div class="row mh-2 mb-2 justify-content-center p-2"><a href="servletHome" class="btn btn-outline-danger w-25 my-2">No hay usuario logueado - Volver a Home.</a></div>
<%}%>	
	
</body>
</html>