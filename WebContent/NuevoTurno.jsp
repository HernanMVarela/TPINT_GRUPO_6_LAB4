<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Paciente"%>
<%@page import="servlets.servletNuevoTurno"%>
<%@page import="servlets.servletTurnos"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>Nuevo Turno</title>
</head>
<body>

<%!ArrayList<Paciente> listaPacientes = null;%>
<%!ArrayList<Medico> listaMedicos = null;%>

<jsp:include page="Menu.html"></jsp:include>
<div class="container-fluid">
<div class="row mh-2 mb-2 justify-content-center p-2">
  	<label class="subtitle w-100">Nuevo turno</label>
</div>	
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-6 mh-2 p-2">
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Paciente</label>
					<select name="slcPaciente" class="w-100">
	  				<option value="0">Seleccione opción</option>
	  				<% if(request.getAttribute("listaPacientes")!=null){
	  					listaPacientes = (ArrayList<Paciente>)request.getAttribute("listaPacientes");
	  					if(!listaPacientes.isEmpty()) {
	  						for (Paciente x: listaPacientes){
	  							%><option value="<%= x.getIdPaciente() %>"><%= x.getNombre() + " " + x.getApellido() %></option><%
	  						} 
	  					}
	  				}%>
	  				</select>
				</div>
				<div class="col col-md-4 mh-3 justify-content-center p-2">
					<a href="Pacientes.jsp" class="btn btn-info w-100 px-2">Agregar nuevo Paciente</a>
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<label class="p-1">Especialidad</label>
					<select name="slcEspecialidad" class="w-75">
						<option value="0">Seleccione opción</option>
						<option value="1">Especialidad 1</option>
						<option value="2">Especialidad 2</option>
						<option value="3">Especialidad 3</option>
					</select>
				</div>
				<div class="col col-md-4 mh-3 justify-content-center p-2">
					
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Medico</label>
					<select name="slcMedico" class="w-100">
	  				<option value="0">Seleccione opción</option>
	  				<% if(request.getAttribute("listaMedicos")!=null){
	  					listaMedicos = (ArrayList<Medico>)request.getAttribute("listaMedicos");
	  					if(!listaMedicos.isEmpty()) {
	  						for (Medico x: listaMedicos){
	  							%><option value="<%= x.getIdMedico() %>"><%= x.getNombre() + " " + x.getApellido() %></option><%
	  						} 
	  					}
	  				}%>
	  				</select>
				</div>
				<div class="col col-md-4 mh-3 justify-content-center p-2">
					<a href="Medicos.jsp" class="btn btn-info w-100 px-2">Agregar nuevo médico</a>
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Seleccione una fecha</label>
					<input type="date" name="inpFechaTurno" class="w-50">
				</div>
				<div class="col col-md-4 mh-3 justify-content-center p-2">
					
				</div>
			</div>
		</div>
		<div class="col col-md-6 mh-2 p-2">
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Horarios disponibles</label>
					<select name="slcHorario" class="w-50">
						<option value="0">Seleccione opción</option>
						<option value="1">8:00</option>
						<option value="2">9:00</option>
						<option value="3">12:00</option>
						<option value="4">13:00</option>
						<option value="5">14:00</option>
						<option value="6">16:00</option>
						<option value="7">17:00</option>
					</select>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Observaciones</label>
					<textarea name="txfObservacion" rows="5" cols="40"></textarea>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 mh-2 d-flex justify-content-between  p-2">
					<label class="p-1">Estado</label>
					<select name="slcEstadoTurno" class="w-50">
						<option value="0">Seleccione opción</option>
						<option value="1">AUSENTE</option>
						<option value="2">PRESENTE</option>
						<option value="3">OCUPADO</option>
						<option value="4">LIBRE</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row mh-2 mb-2 justify-content-center p-2">
	<div class="col col-md-3 justify-content-center mh-2 p-2">
		<a href="NuevoTurno.jsp" class="btn btn-success w-100 p-3 w-100">Confirmar turno</a><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
  	</div>	
</div>	
</body>
</html>