<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Tipo"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Horario"%>
<%@page import="servlets.servletNuevoMedico"%>
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

<title>Nuevo Médico</title>
</head>
<body>

<%!ArrayList<Provincia> listaProvincias = null;%>
<%!ArrayList<Localidad> listaLocalidades = null;%>
<%!ArrayList<Sexo> listasexos = null;%>
<%!ArrayList<Tipo> listaTipos = null;%>
<%!ArrayList<Pais> listapaises = null;%>
<%!ArrayList<Especialidad> listaesp = null;%>
<%!Medico medic = null;%>
<%
	if(request.getAttribute("medic")!=null){
		medic = (Medico)request.getAttribute("medic");
	}else{
		medic = null;
	}
%>

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
<%if(user!=null){ if(user.getTipo().getIdTipo() == 1){ %>
<div class="container-fluid">
	<form action="servletNuevoMedico" method="get" ><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
	<%
	if(medic != null) {
		%>
		<input type="hidden" name="medicId" value="<%=medic.getIdMedico()%>">
		<%
		}
	%>
	<div class="row mh-2 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo médico</label>
	</div>	

  	<div class="row justify-content-center">
  		<label class="subtitle w-75">Datos personales</label>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nombre</label>
	  		<input type="text" name="txfNombre" class="w-100" required placeholder="Nombre" <% if(medic!=null){%>value="<%=medic.getNombre()%>"<%;}%>>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Apellido</label>
	  		<input type="text" name="txfApellido" class="w-100" required placeholder="Apellido"
	  		<% 	if(medic!=null){%>value="<%=medic.getApellido()%>"<%;} 	%>>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Documento</label>
	  		<input type="number" name="txfDocumento" class="w-100" required placeholder="Documento"
	  		<%if(medic!=null){%>value="<%=medic.getDni()%>" readonly<%;}%>>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=txfFechaNacPersona class="w-100" required
	  		<% 	if(medic!=null){%>value="<%=medic.getFecha_nacimiento()%>"<%;} 	%>>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nacionalidad</label>
		  	<select name="slcPaisPersona" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listaPaises")!=null){
					listapaises = (ArrayList<Pais>)request.getAttribute("listaPaises");
					if(!listapaises.isEmpty()){
						for(Pais pa : listapaises){
						%>
						<option value="<%=pa.getIdNacionalidad()%>" 
						<%
			  			if(medic!=null && medic.getNacionalidad().getIdNacionalidad() == pa.getIdNacionalidad()){%>selected<%;}
						%>
						><%=pa.getNombre() %></option>
						<%
						}
					}
				}
				%>
			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Sexo</label> 
	  			<select name="slcSexoPersona" id="slcSexoPersona" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listasexos")!=null){
					listasexos = (ArrayList<Sexo>)request.getAttribute("listasexos");
					if(!listasexos.isEmpty()){
						for(Sexo sex : listasexos){
						%>
						<option value="<%=sex.getIdSexo()%>" 
						<%
			  			if(medic!=null && medic.getSexo().getIdSexo() == sex.getIdSexo()){%>selected<%;}
						%>
						><%=sex.getNombre()%></option>
						<%
						}
					}
				}
				%>
			</select>
  		</div>
  	</div>
  	<div class="row justify-content-center border-bottom mb-3">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Provincia</label> 
  			<select name="slcProvPersona" id="slcProvPersona" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listaProvincias")!=null){
					listaProvincias = (ArrayList<Provincia>)request.getAttribute("listaProvincias");
					if(!listaProvincias.isEmpty()){
						for(Provincia prov : listaProvincias){
						%>
						<option value="<%=prov.getIdProv() %>" 
						<%
			  			if(medic!=null && medic.getDirecc().getLoc().getProvincia().getIdProv() == prov.getIdProv()){%>selected<%;}
						%>
						><%=prov.getNombre() %></option>
							
						<%
						}
					}
				}
				%>
			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Localidad</label> 
  			<select name="slcLocPersona" id="slcLocPersona" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listaLocalidades")!=null){
					listaLocalidades = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
					if(!listaLocalidades.isEmpty()){
						for(Localidad loc : listaLocalidades){
							%>
							<option value="<%=loc.getIdLocalidad()%>" 
							<%
				  			if(medic!=null && medic.getDirecc().getLoc().getIdLocalidad() == loc.getIdLocalidad()){%>selected<%;}
							%>
							><%=loc.getNombre() %></option>	
							<%
						}
					}
				}
				%>
			</select>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Dirección</label>
	  		<input type="text" name="txfDireccion" class="w-100" required placeholder="Dirección"
	  		<% 
  			if(medic!=null){%>value="<%=medic.getDirecc().getCalleYNum()%>"<%;}
	  		%>>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-6 mh-2 justify-content-center p-2">
  			<label class="subtitle w-100">Datos laborales</label>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="subtitle w-100">Cuenta de usuario</label>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Hoarios de atencion</label>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Domingo</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TIDomingo" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==1 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  				  	<select name="TEDomingo" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==1 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Lunes</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TILunes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==2 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  					<select name="TELunes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==2 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Martes</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TIMartes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==3 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  					<select name="TEMartes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==3 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Miércoles</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TIMiercoles" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==4 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  					<select name="TEMiercoles" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==4 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Jueves</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TIJueves" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==5 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  					<select name="TEJueves" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==5 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Viernes</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TIViernes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==6 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  					<select name="TEViernes" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==6 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  			<div class="row justify-content-center">
  				<div class="col col-md-4">
  					<label class="p-1">Sábado</label>
  				</div>
  				<div class="col col-md-4">
  					<select name="TISabado" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==7 && horas.getHoraDesde()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  				<div class="col col-md-4">
  				  	<select name="TESabado" class="w-100" required><%for(int x=0; x<24; x++){ %><option value="<%=x %>"
  					<%if(medic!=null){for (Horario horas : medic.getHorarios()) {if(horas.getDia()==7 && horas.getHoraHasta()==x){%>selected <%}}}%>
  					><%=x%>:00</option><%} %></select>
  				</div>
  			</div>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2"> 
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Especialidad</label> 
	  			<select name=slcEsp class="w-100" required>
					<option value="0" disabled>Seleccione opción</option>
					<%
					if(request.getAttribute("listaesp")!=null){
						listaesp = (ArrayList<Especialidad>)request.getAttribute("listaesp");
						if(!listaesp.isEmpty()){
							for(Especialidad esp : listaesp){
								%>
								<option value="<%=esp.getIdEspecialidad()%>" 
								<%
					  			if(medic!=null && medic.getUsuario().getTipo().getIdTipo() == esp.getIdEspecialidad()){%>selected<%;}
								%>
								><%=esp.getNombre() %></option>	
								<%
							}
						}
					}
					%>
			</select>	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Correo Electrónico</label>
	  			<input type="email" name="txfCorreo" class="w-100" required placeholder="correo@dominio.tipo"
		  		<% 
	  			if(medic!=null){%>value="<%=medic.getEmail()%>"<%;}
		  		%>>
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Telefono</label>
	  			<input type="text" name="txfTelefono" class="w-100" required placeholder="123 1234 1234"
		  		<% 
	  			if(medic!=null){%>value="<%=medic.getTelefono()%>"<%;}
		  		%>>
	  		</div>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2"> 
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Nombre de usuario</label>
	  			<input type="text" name="txfUsername" class="w-100" required placeholder="Usuario"
		  		<% 
	  			if(medic!=null){%>value="<%=medic.getUsuario().getUser() %>" readonly<%;}
		  		%>>	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Contraseña</label>
	  			<input type="password" name="txfPassword1" class="w-100" required placeholder="Clave"
		  		<% 
	  			if(medic!=null){%>value="<%=medic.getUsuario().getPassword() %>"<%;}
		  		%>>	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Confirme contraseña</label>
	  			<input type="password" name="txfPassword2" class="w-100" required placeholder="Verificación"
		  		<% 
	  			if(medic!=null){%>value="<%=medic.getUsuario().getPassword() %>"<%;}
		  		%>>		
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Tipo de cuenta</label>
	  			<select name="slcTipoUsuario" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listaTipos")!=null){
					listaTipos = (ArrayList<Tipo>)request.getAttribute("listaTipos");
					if(!listaTipos.isEmpty()){
						for(Tipo tip : listaTipos){
							%>
							<option value="<%=tip.getIdTipo()%>" 
							<%
				  			if(medic!=null && medic.getUsuario().getTipo().getIdTipo() == tip.getIdTipo()){%>selected<%;}
							%>
							><%=tip.getNombre() %></option>	
							<%
						}
					}
				}
				%>
			</select>	
	  		</div>
	  		<div class="row justify-content-center mx-2">
	  			<label class="p-1">Estado</label>
	  			<select name="slcEstadoCuenta" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<option value="1" 
				<% 
	  			if(medic!=null && medic.isEstado()){%>selected<%;}
				%>>Activo</option>
				<option value="2" 
				<% 
	  			if(medic!=null && !medic.isEstado()){%>selected<%;}
				%>>Inactivo</option>
			</select>	
	  		</div>
  		</div>
  	</div>
  	
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center mh-2 p-2">
	  		<input type="submit" name="btnAceptar" class="btn btn-success p-3 w-100" value="Aceptar">
	  	</div>	
	</div>	
	</form>
</div>	

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