<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.Estado"%>
<%@page import="entidad.Especialidad"%>
<%@page import="servlets.servletNuevoTurno"%>
<%@page import="servlets.servletTurnos"%>
<%@page import="java.util.Date"%>

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
<%!ArrayList<Especialidad> listaEsp = null;%>
<%!ArrayList<Estado> listaEstadoTurno = null;%>
<%!ArrayList<Integer> horasDisponibles = null; %>

<%!Turno turno = null;%>

<jsp:include page="Menu.html"></jsp:include>
<div class="container-fluid">

<form action="servletNuevoTurno" method="get">
	<div class="row mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo turno</label>
	</div>	
	<div class="row mb-2 justify-content-center p-2">
		<div class="col col-md-6 p-2">
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between  p-2">
					<label class="p-1">Paciente</label>
					<select name="slcPaciente" class="w-75">
	  				<option value="0" disabled>Seleccione opción</option>
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
				<div class="col col-md-4 justify-content-center p-2">
					<a href="Pacientes.jsp" class="btn btn-info w-100 px-2">Agregar nuevo Paciente</a>
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between p-2">
					<label class="p-1">Especialidad  </label>
					<select name=slcEsp class="w-75" required>
						<option value="0" disabled>Seleccione opción</option>
						<%
						if(request.getAttribute("listaEsp")!=null){
							listaEsp = (ArrayList<Especialidad>)request.getAttribute("listaEsp");
							if(!listaEsp.isEmpty()){
								for(Especialidad esp : listaEsp){
									%>
									<option value="<%=esp.getIdEspecialidad()%>" 
									<%
						  			if(turno!=null && turno.getEspecialidad().getIdEspecialidad() == esp.getIdEspecialidad()){%>selected<%;}
									%>
									><%=esp.getNombre() %></option>	
									<%
								}
							}
						}
						%>
					</select>	
				</div>
				<div class="col col-md-4 justify-content-center p-2">
					
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between p-2">
					<label class="p-1">Medico  </label>
					<select name="slcMedico" class="w-75">
	  				<option value="0" disabled>Seleccione opción</option>
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
				<div class="col col-md-4 justify-content-center p-2">
					<a href="servletNuevoMedico" class="btn btn-info w-100 px-2">Agregar nuevo médico</a>
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between  p-2">
					<label class="p-1">Seleccione una fecha</label>
					
					<input type="date" name="inpFechaTurno" class="w-50" min="2022-01-01">
				</div>
				<div class="col col-md-4 justify-content-center p-2">
					
				</div>
			</div>
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-4 justify-content-center p-2">
					<input type="submit" name="btnFiltrarDatos" class="btn btn-info w-75 px-2" value="Siguiente">
				</div>
			</div>
		</div>
		<div class="col col-md-6 p-2">
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 d-flex justify-content-between  p-2">
					<label class="p-1">Horarios disponibles</label>
					<select name="slcHoras" class="w-50" <%if(horasDisponibles!=null) {%>required<%} %>>
						<option value="0" disabled>Seleccione opción</option>
						<% if(request.getAttribute("horasDisponibles")!=null){
							horasDisponibles = (ArrayList<Integer>)request.getAttribute("horasDisponibles");
		  					if(!horasDisponibles.isEmpty()) {
		  						for (Integer x: horasDisponibles){
		  							%><option value="<%= x %>"><%=x+":00hs"%></option><%
		  						} 
		  					}
		  				}%>
					</select>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 d-flex justify-content-between  p-2">
					<label class="p-1">Observaciones</label>
					<textarea name="txfObservacion" rows="5" cols="40"></textarea>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 d-flex justify-content-between  p-2">
					<label class="p-1">Estado</label>
						<select name="slcEstadoTurno" class="w-50" required>
						<option value="0" disabled>Seleccione opción</option>
						<%
						if(request.getAttribute("listaEstadoTurno")!=null){
							listaEstadoTurno = (ArrayList<Estado>)request.getAttribute("listaEstadoTurno");
							if(!listaEstadoTurno.isEmpty()){
								for(Estado est : listaEstadoTurno){
									%>
									<option value="<%=est.getIdEstado()%>" 
									<%
						  			if(turno!=null && turno.getEstadoTurno().getIdEstado() == est.getIdEstado()){%>selected<%;}
									%>
									><%=est.getNombre() %></option>	
									<%
								}
							}
						}
						%>
					</select>	
				</div>
			</div>
		</div>
	</div>
	<div class="row mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center mh-2 p-2">
			<input name="btnConfirmarTurno" type="submit" class="btn btn-success w-100 p-3 w-100" value="Confirmar turno">
	  	</div>	
	</div>
	
</form>
</div>	
</body>
</html>