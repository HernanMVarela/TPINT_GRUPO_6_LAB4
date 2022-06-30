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
						<button class="btn btn-primary w-100 my-2" disabled>Listado</button>
						<button class="btn btn-primary w-100 my-2" disabled>Sacar turno</button>
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
						<button class="btn btn-primary w-100 my-2" disabled>Listado</button>
						<button class="btn btn-primary w-100 my-2" disabled>Agregar</button>
					<% } %>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoMedicoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Médicos</h4>
					<p class="card-text">Listado de médicos</p>
					<% if(user!=null){%> 
						<a href="servletMedicos" class="btn btn-primary w-100 my-2">Listado</a>
						<% if(user.getTipo().getIdTipo()==1){ %> 
							<a href="servletNuevoMedico" class="btn btn-primary w-100 my-2">Agregar</a>
						<% }else{ %>
							<button class="btn btn-primary w-100 my-2" disabled>Agregar</button>
						<% } %>				
					<% } else { %> 	
						<button class="btn btn-primary w-100 my-2" disabled>Listado</button>
						<button class="btn btn-primary w-100 my-2" disabled>Agregar</button>
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
							<button class="btn btn-primary w-100 my-2" disabled>Administración</button>
							<button class="btn btn-primary w-100 my-2" disabled>Nuevo usuario</button>
						<% } %>				
					<% } else { %> 	
						<button class="btn btn-primary w-100 my-2" disabled>Administración</button>
						<button class="btn btn-primary w-100 my-2" disabled>Nuevo usuario</button>
					<% } %>
				</div>
			</div>
	</div>
	<%if(user!=null) { %> 
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<%! int pacientes = 0; %>
		<%! String turnosporpac = "0"; %>
		<%! int consultasrealizadas = 0; %>
		<%! int consultastotales = 0; %>
		
		<%! int turnosocupados = 0; %>
		<%! int turnosatendidos = 0;%>
		<%! int turnoscancelados = 0;%>
		
		<%! int medicostotales = 0; %>
		<%! int especialidades = 0;%>
		<%! String mejormedico = "";%>
		<%! int turnosmejormedico = 0;%>
		
		<%	if(request.getAttribute("totalpacientes")!=null){pacientes=(int)request.getAttribute("totalpacientes");}
			if(request.getAttribute("consultas")!=null){consultasrealizadas=(int)request.getAttribute("consultas");}
			if(request.getAttribute("turnosporpac")!=null){turnosporpac=request.getAttribute("turnosporpac").toString() ;}
			if(request.getAttribute("totalturnos")!=null){consultastotales=(int)request.getAttribute("totalturnos");}
			if(request.getAttribute("turnosocupados")!=null){turnosocupados=(int)request.getAttribute("turnosocupados");}
			if(request.getAttribute("turnosatendidos")!=null){turnosatendidos=(int)request.getAttribute("turnosatendidos");}
			if(request.getAttribute("turnoscancelados")!=null){turnoscancelados=(int)request.getAttribute("turnoscancelados");}
			if(request.getAttribute("medicostotales")!=null){medicostotales=(int)request.getAttribute("medicostotales");}
			if(request.getAttribute("especialidades")!=null){especialidades=(int)request.getAttribute("especialidades");}
			if(request.getAttribute("mejormedico")!=null){mejormedico=request.getAttribute("mejormedico").toString();}
			if(request.getAttribute("turnosmejormedico")!=null){turnosmejormedico=(int)request.getAttribute("turnosmejormedico");}
		%>
		
		<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Pacientes</label><br>
	  		
	  		<h4 class="p-2">Pacientes totales:<%=pacientes %> </h4>
	  		<h4 class="p-2">Total de consultas: <%=consultastotales %></h4>
	  		<h4 class="p-2">Pacientes atendidos: <%=consultasrealizadas %></h4>
	  		<h4 class="p-2">Turnos por paciente: <%=turnosporpac %></h4>
	  	</div>
	  	<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Medicos</label>
	  		<h4 class="p-2">Médicos: <%=medicostotales %></h4>
	  		<h4 class="p-2">Especialidades: <%=especialidades %></h4>
	  		<h4 class="p-2">Mejor médico: <%=mejormedico %></h4>
	  		<h4 class="p-2">Con <%=turnosmejormedico %> turnos atendidos</h4>
	  	</div>
	  	<div class="col col-md-3 mx-2 btn-outline-info justify-content-center p-2 cuadro_uno">
	  		<label class="subtitle w-100">Turnos</label>
	  		<h4 class="p-2">Totales totales: <%=consultastotales %></h4>
	  		<h4 class="p-2">Turnos atendidos: <%=turnosatendidos %></h4>
	  		<h4 class="p-2">Turnos cancelados: <%=turnoscancelados %></h4>
	  		<h4 class="p-2">Turnos pendientes: <%=turnosocupados %></h4>
	  	</div>
	</div>
	<%} %>
</div><!-- FIN DEL CONTAINER -->

</body>
</html>