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
					<form method="post" action="Turnos.jsp" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Listado">
					</form>
					<form method="post" action="NuevoTurno.jsp" class="p-2 w-100">
						<input type="submit" class="btn btn-success w-100" value="Sacar turno">
					</form>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoPacienteFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Pacientes</h4>
					<p class="card-text">Listado de pacientes</p>
					<form method="post" action="Pacientes.jsp" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Listado">
					</form>
					<form method="post" action="NuevoPaciente.jsp" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Agregar">
					</form>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoMedicoFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Medicos</h4>
					<p class="card-text">Listado de médicos</p>
					<form action="servletMedicos" method="get" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Listado">
					</form>
					<form method="post" action="NuevoMedico.jsp" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Agregar">
					</form>
				</div>
			</div>
		
			<div class="cuadro_uno btn-outline-secondary mx-2" style="width: 18rem;">
				<img src="image/LogoAdminFB.png" class="card-img-top" alt="S/I">
				<div class="card-body">
					<h4 class="card-title">Administración</h4>
					<p class="card-text">Panel de administración del sitio.</p>
					<form  action="servletPanelAdministrador" method="get" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Administración">
					</form>
					<form action="servletNuevoUsuario" method="get" class="p-2 w-100">
						<input type="submit" class="btn btn-primary w-100" value="Nuevo usuario">
					</form>
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