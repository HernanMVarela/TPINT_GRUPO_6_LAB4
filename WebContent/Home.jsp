<%@page import="servlets.servletNuevoUsuario"%>
<%@page import="entidad.Usuario"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="servlets.servletHome"%>
<%@page import="servlets.servletPanelAdministrador"%>
<%@page import="servlets.servletMedicos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
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
	<div class="row mx-2 mb-2 justify-content-center p-2">
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoTurnoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Turnos</h4>
					<p class="card-text">Vista de turnos</p>
					<% if(user!=null){%> 
						<a href="servletTurnos" class="btn btn-primary w-100 my-2">Listado</a>
						<a href="servletNuevoTurno" class="btn btn-success w-100 my-2">Sacar turno</a>
					<% } else { %> 	
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Listado</a>
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Sacar turno</a>
					<% } %>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoPacienteFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Pacientes</h4>
					<p class="card-text">Listado de pacientes</p>
					<% if(user!=null){%> 
						<a href="servletPacientes" class="btn btn-primary w-100 my-2">Listado</a>
						<a href="servletNuevoPaciente" class="btn btn-primary w-100 my-2">Agregar</a>
					<% } else { %> 	
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Listado</a>
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Agregar</a>
					<% } %>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoMedicoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Medicos</h4>
					<p class="card-text">Listado de médicos</p>
					<% if(user!=null){%> 
						<a href="servletMedicos" class="btn btn-primary w-100 my-2">Listado</a>
						<% if(user.getTipo().getIdTipo()==1){ %> 
							<a href="servletNuevoMedico" class="btn btn-primary w-100 my-2">Agregar</a>
						<% }else{ %>
							<a href="servletHome" class="btn btn-secondary w-100 my-2">Agregar</a>
						<% } %>				
					<% } else { %> 	
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Listado</a>
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Agregar</a>
					<% } %>

				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoAdminFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Administración</h4>
					<p class="card-text">Panel de administración del sitio.</p>
					<% if(user!=null){%> 
						<% if(user.getTipo().getIdTipo()==1){ %> 
							<a href="servletPanelAdministrador" class="btn btn-primary w-100 my-2">Administración</a>
							<a href="servletNuevoUsuario" class="btn btn-primary w-100 my-2">Nuevo usuario</a>
						<% }else{ %>
							<a href="servletHome" class="btn btn-secondary w-100 my-2">Administración</a>
							<a href="servletHome" class="btn btn-secondary w-100 my-2">Nuevo usuario</a>
						<% } %>				
					<% } else { %> 	
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Administración</a>
						<a href="servletHome" class="btn btn-secondary w-100 my-2">Nuevo usuario</a>
					<% } %>
				</div>
			</div>
	</div>
	<%if(user!=null) { %> 
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Pacientes</label><br>
	  		<h4 class="p-2">Pacientes totales: 543</h4>
	  		<h4 class="p-2">Total de consultas: 927</h4>
	  		<h4 class="p-2">Otro dato: ---</h4>
	  		<h4 class="p-2">Otro dato mas: ---</h4>
	  	</div>
	  	<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Medicos</label>
	  		<h4 class="p-2">Médicos: 64</h4>
	  		<h4 class="p-2">Especialidades: 15</h4>
	  		<h4 class="p-2">Otro dato: ---</h4>
	  		<h4 class="p-2">Otro dato mas: ---</h4>
	  	</div>
	  	<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Turnos</label>
	  		<h4 class="p-2">Totales totales: 1203</h4>
	  		<h4 class="p-2">Turnos atendidos: 927</h4>
	  		<h4 class="p-2">Turnos cancelados: 130</h4>
	  		<h4 class="p-2">Turnos pendientes: 146</h4>
	  	</div>
	</div>
	<%} %>
</div><!-- FIN DEL CONTAINER -->
</body>
</html>