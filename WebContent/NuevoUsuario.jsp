<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Tipo"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Administrador"%>
<%@page import="servlets.servletNuevoUsuario"%>

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

<title>Nuevo usuario</title>
</head>
<body>
<%!ArrayList<Provincia> listaProvincias = null;%>
<%!ArrayList<Localidad> listaLocalidades = null;%>
<%!ArrayList<Sexo> listasexos = null;%>
<%!ArrayList<Tipo> listaTipos = null;%>
<%!ArrayList<Pais> listapaises = null;%>
<%!Medico medic = null;%>
<%!Administrador admin = null;%>
<%
	if(request.getAttribute("medico")!=null){
		
		medic = (Medico)request.getAttribute("medico");
	}

	if(request.getAttribute("admin")!=null){
		admin = (Administrador)request.getAttribute("admin");
	}
%>

<jsp:include page="Menu.html"></jsp:include>

<div class="row mh-2 mb-2 justify-content-center p-2">
  		<label class="subtitle w-100">Nuevo Usuario</label>
</div>
<div class="container-fluid">
	<form action="servletNuevoUsuario" method="get" ><!-- REEMPLAZAR POR INPUT CON RUTA A SERVLET -->
	<div class="row justify-content-center">
	  	<div class="col col-md-6 mh-2 justify-content-center p-2">
	  		<label class="subtitle w-100">Datos personales</label>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="subtitle w-100">Cuenta de usuario</label>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Nombre</label>
		  	<input type="text" name="txfNombrePersona" class="w-100" required
	  		<% 
	  			if(medic!=null){%>value="<%=medic.getNombre()%>"<%;}
	  			if(admin!=null){%>value="<%=admin.getNombre()%>"<%;}
		  	%>>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Apellido</label>
		  	<input type="text" name="txfApellidoPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getApellido()%>"<%;}
  			if(admin!=null){%>value="<%=admin.getApellido()%>"<%;}
	  		%>
		  	>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Nombre de usuario</label>
		  	<input type="text" name="txfUsername" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getUsuario().getUser()%>"<%;}
  			if(admin!=null){%>value="<%=admin.getUsuario().getUser()%>"<%;}
	  		%>
		  	>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Documento</label>
		  	<input type="number" name="txfDocumentoPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getDni()%>" <%;}
  			if(admin!=null){%>value="<%=admin.getDni()%>" <%;}
	  		%>
		  	>
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
			  			if(admin!=null && admin.getNacionalidad().getIdNacionalidad() == pa.getIdNacionalidad()){%>selected<%;}
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
	  		<label class="p-1">Contraseña</label>
		  	<input type="password" name="txfPassword1" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getUsuario().getPassword()%>"<%;}
  			if(admin!=null){%>value="<%=admin.getUsuario().getPassword()%>"<%;}
	  		%>
		  	>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
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
			  			if(admin!=null && admin.getSexo().getIdSexo() == sex.getIdSexo()){%>selected<%;}
						%>
						><%=sex.getNombre()%></option>
						<%
						}
					}
				}
				%>
			</select>
	    </div>
	     <div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Fecha de nacimiento</label>
		  	<input type="date" name="txfFechaNacPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getFecha_nacimiento() %>"<%;}
  			if(admin!=null){%>value="<%=admin.getFecha_nacimiento() %>"<%;}
	  		%>
		  	>
	    </div>
	     <div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Verifique su contraseña</label>
		  	<input type="password" name="txfPassword2" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getUsuario().getPassword()%>"<%;}
  			if(admin!=null){%>value="<%=admin.getUsuario().getPassword()%>"<%;}
	  		%>
		  	>
	    </div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
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
			  			if(admin!=null && admin.getDirecc().getLoc().getProvincia().getIdProv() == prov.getIdProv()){%>selected<%;}
						%>
						><%=prov.getNombre() %></option>
							
						<%
						}
					}
				}
				%>
			</select>
	  	</div>
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
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
				  			if(admin!=null && admin.getDirecc().getLoc().getIdLocalidad() == loc.getIdLocalidad()){%>selected<%;}
							%>
							><%=loc.getNombre() %></option>	
							<%
						}
					}
				}
				%>
			</select>
	  	</div>
	  	<div class="col col-md-2 mh-2 justify-content-center p-2">
	 		<label class="p-1">Dirección</label>
		  	<input type="text" name="txfDireccionPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getDirecc().getCalleYNum()%>"<%;}
  			if(admin!=null){%>value="<%=admin.getDirecc().getCalleYNum()%>"<%;}
	  		%>
		  	>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Tipo de usuario</label> 
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
				  			if(admin!=null && admin.getUsuario().getTipo().getIdTipo() == tip.getIdTipo()){%>selected<%;}
							%>
							><%=tip.getNombre() %></option>	
							<%
						}
					}
				}
				%>
			</select>
	  	</div>
	  </div>
	  <div class="row justify-content-center">
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Email</label>
		  	<input type="email" name="txfEmailPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getEmail() %>"<%;}
  			if(admin!=null){%>value="<%=admin.getEmail()%>"<%;}
	  		%>
		  	>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  		<label class="p-1">Telefono</label>
		  	<input type="text" name="txfTelefonoPersona" class="w-100" required
	  		<% 
	  		if(medic!=null){%>value="<%=medic.getTelefono() %>"<%;}
  			if(admin!=null){%>value="<%=admin.getTelefono()%>"<%;}
	  		%>
		  	>
	  	</div>
	  	<div class="col col-md-3 mh-2 justify-content-center p-2">
	  	  	<label class="p-1">Estado de cuenta</label> 
			<select name="slcEstadoCuenta" class="w-100" required>
				<option value="0" disabled>Seleccione opción</option>
				<option value="1" 
				<% 
				if(medic!=null && medic.isEstado()){%>selected<%;}
	  			if(admin!=null && admin.isEstado()){%>selected<%;}
				%>>Activo</option>
				<option value="2" 
				<% 
				if(medic!=null && !medic.isEstado()){%>selected<%;}
	  			if(admin!=null && !admin.isEstado()){%>selected<%;}
				%>>Inactivo</option>
			</select>
	  	</div>
	  </div>
		<div class="row justify-content-center m-3">
			<div class="col col-md-3">
				
				<input type="submit" class="btn btn-info w-100" name="btnAgregarUsuario" value="Agregar">
				
			</div>
	 	</div>
	</form>
  </div><!-- FIN DE CONTAINER -->
  
  
 
</body>


</html>

