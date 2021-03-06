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
<%!boolean verEsp = true;%>
<%!boolean verPac = true;%>
<%!boolean verFec = true;%>
<%!boolean verMed = true;%>


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
		<h5>Inicie sesi?n para navegar</h5>
	</div>
</div>
<%
} %>

<%if(user!=null){ %>
<div class="container-fluid">

<%
if(request.getAttribute("elegirespecialidad")!=null){
	verEsp =(boolean)request.getAttribute("elegirespecialidad");
}
if(request.getAttribute("elegirpaciente")!=null){
	verPac=(boolean)request.getAttribute("elegirpaciente");
}
if(request.getAttribute("elegirfecha")!=null){
	verFec=(boolean)request.getAttribute("elegirfecha");
}
if(request.getAttribute("elegirmedico")!=null){
	verMed=(boolean)request.getAttribute("elegirmedico");
}

%>
<form action="servletNuevoTurno" method="get">
<%
if(request.getAttribute("preturno")!=null){
	turno=(Turno)request.getAttribute("preturno");
	%><input type="hidden" name="turnoId" value="<%= 0%>"><%
}

if(request.getAttribute("fullturno")!=null){
	turno=(Turno)request.getAttribute("fullturno");
	%><input type="hidden" name="turnoId" value="<%= turno.getIdTurno() %>"><%
}

if(request.getAttribute("fullturno")==null && request.getAttribute("preturno")==null){
	turno = null;
}

%>
	<div class="row mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo turno</label>
	</div>	
	<div class="row mb-2 justify-content-center p-2">
		<div class="col col-md-6 p-2">
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between  p-2">
					<label class="p-1">Paciente</label>
					<select name="slcPaciente" class="w-75 <%if(!verPac){%> btn-danger <%}else{%>btn-light<%}%>">
	  				<option value="0" disabled selected>Seleccione opci?n</option>
	  				<% if(request.getAttribute("listaPacientes")!=null){
	  					listaPacientes = (ArrayList<Paciente>)request.getAttribute("listaPacientes");
	  					if(!listaPacientes.isEmpty()) {
	  						for (Paciente x: listaPacientes){
	  							if(x.isEstado()){
	  							%><option value="<%= x.getIdPaciente() %>"<%
	  								if(turno!=null){if(x.getIdPaciente()==turno.getPaciente().getIdPaciente()){ %> selected <%}}%>>
	  							<%= x.getNombre() + " " + x.getApellido() %></option><%
	  							}
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
					<label class="p-1">Medico  </label>
					<select name="slcMedico" class="w-75 <%if(!verMed){%> btn-danger <%}else{%>btn-light<%}%>">
	  				<option value="0" disabled selected>Seleccione opci?n</option>
	  				<% if(request.getAttribute("listaMedicos")!=null){
	  					listaMedicos = (ArrayList<Medico>)request.getAttribute("listaMedicos");
	  					if(!listaMedicos.isEmpty()) {
	  						for (Medico x: listaMedicos){
	  							if(x.isEstado()){
	  							%><option value="<%= x.getIdMedico() %>"<%
	  								if(turno!=null){if(x.getIdMedico()==turno.getMedico().getIdMedico()){ %> selected <%}}%>><%= x.getEspecialidad().getNombre() + " | " + x.getNombre() + " " + x.getApellido() %></option><%
	  							}
	  						} 
	  					}
	  				}%>
	  				</select>
				</div>
				<div class="col col-md-4 justify-content-center p-2">
					<%if(user.getTipo().getIdTipo()!=1){%><button class="btn btn-info w-100 px-2" disabled>Agregar nuevo m?dico</button> 
					<% }else { %> <a href="servletNuevoMedico" class="btn btn-info w-100 px-2">Agregar nuevo m?dico</a>  <% } %>
					
				</div>
			</div>
			
			<div class="row justify-content-center align-items-center">
				<div class="col col-md-6 d-flex justify-content-between  p-2">
					<label class="p-1">Seleccione una fecha</label>
					
					<input type="date" name="inpFechaTurno" class="w-50 <%if(!verFec){%> btn-danger <%}else{%>btn-light<%}%>" min="2022-01-01"
					<%if(turno!=null){%>value="<%=turno.getDia()%>" <%} %>
					>
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
					<select name="slcHoras" class="w-50 btn-light" <%if(horasDisponibles!=null) {%>required<%} %>>
						<option value="0" disabled selected>Seleccione opci?n</option>
						<% if(request.getAttribute("horasDisponibles")!=null){
							horasDisponibles = (ArrayList<Integer>)request.getAttribute("horasDisponibles");
		  					if(!horasDisponibles.isEmpty()) {
		  						for (Integer x: horasDisponibles){
		  							%><option value="<%= x %>"
		  							<%if(request.getAttribute("fullturno")!=null && turno.getHora()==x){%>selected<%}%>><%=x+":00hs"%></option><%
		  						} 
		  					}
		  				}%>
					</select>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 d-flex justify-content-between  p-2">
					<label class="p-1">Observaciones</label>
					<textarea name="txfObservacion" rows="5" cols="40" <%if(horasDisponibles!=null) {%>required<%} %>>
					<%if(request.getAttribute("fullturno")!=null && !turno.getObservacionConsulta().isEmpty()){%><%=turno.getObservacionConsulta().trim() %><%}%>
					</textarea>
				</div>
			</div>
			<div class="row justify-content-center w-100">
				<div class="col col-md-8 d-flex justify-content-between  p-2">
					<label class="p-1">Estado</label>						
						<select name="slcEstadoTurno" class="w-50 btn-light"  
						<%if(request.getAttribute("fullturno")==null){%> onchange="this.value = '2'" <%}%>
						<%if(request.getAttribute("fullturno")!=null && turno.getEstadoTurno().getIdEstado()==1 ){%> onchange="this.value = '1'" <%}%>>
						
						<option value="0" disabled> Seleccione opci?n</option>
						<option value="1" <%if(request.getAttribute("fullturno")!=null && turno.getEstadoTurno().getIdEstado()==1 ){%>selected<%} else {%>disabled<%}%>> LIBRE </option>
						<option value="2" <%if(request.getAttribute("fullturno")==null){%>selected<%} else if(user.getTipo().getIdTipo()==3){%>disabled<%} %>> OCUPADO </option>
						<option value="3"> AUSENTE </option>
						<option value="4" <%if(request.getAttribute("fullturno")!=null && user.getTipo().getIdTipo()==3){%>selected<%}%> > PRESENTE </option>
						
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

<%}else{ %>
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<a href="servletHome" class="btn btn-outline-danger w-25 my-2">No hay usuario logueado - Volver a Home.</a>
	</div>
<%}%>	

</body>
</html>