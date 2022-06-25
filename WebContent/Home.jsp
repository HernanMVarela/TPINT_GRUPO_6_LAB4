<%@page import="servlets.servletNuevoUsuario"%>
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
<jsp:include page="Menu.html"></jsp:include>

<div class="container-fluid">
	<div class="row mx-2 mb-2 justify-content-center p-2">
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoTurnoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Turnos</h4>
					<p class="card-text">Vista de turnos</p>
					<a href="Turnos.jsp" class="btn btn-primary w-100 my-2">Listado</a><!-- REEMPLAZAR RUTA POR SERVLET -->
					<a href="NuevoTurno.jsp" class="btn btn-success w-100 my-2">Sacar turno</a><!-- REEMPLAZAR RUTA POR SERVLET -->
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoPacienteFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Pacientes</h4>
					<p class="card-text">Listado de pacientes</p>
					<a href="Pacientes.jsp" class="btn btn-primary w-100 my-2">Listado</a><!-- REEMPLAZAR RUTA POR SERVLET -->
					<a href="NuevoPaciente.jsp" class="btn btn-primary w-100 my-2">Agregar</a><!-- REEMPLAZAR RUTA POR SERVLET -->
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoMedicoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Medicos</h4>
					<p class="card-text">Listado de médicos</p>
					<a href="servletMedicos" class="btn btn-primary w-100 my-2">Listado</a>
					<a href="NuevoMedico.jsp" class="btn btn-primary w-100 my-2">Agregar</a><!-- REEMPLAZAR RUTA POR SERVLET -->
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoAdminFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Administración</h4>
					<p class="card-text">Panel de administración del sitio.</p>
					<a href="servletPanelAdministrador" class="btn btn-primary w-100 my-2">Administración</a>
					<a href="servletNuevoUsuario" class="btn btn-primary w-100 my-2">Nuevo usuario</a>
				</div>
			</div>
	</div>
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
	
</div><!-- FIN DEL CONTAINER -->
</body>
</html>