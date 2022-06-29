<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Paciente"%>
<%@page import="servlets.servletNuevoPaciente"%>

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

<title>Nuevo Paciente</title>
</head>
<body>

<%!ArrayList<Provincia> listaProvincias = null;%>
<%!ArrayList<Localidad> listaLocalidades = null;%>
<%!ArrayList<Sexo> listasexos = null;%>
<%!ArrayList<Pais> listapaises = null;%>
<%!Paciente paci = null;%>

<div class="container-fluid">
	<jsp:include page="Menu.html"></jsp:include>
	<div class="row mh-2 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo paciente</label>
	</div>	

  	<div class="row justify-content-center">
  		<label class="subtitle w-75">Datos personales</label>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nombre</label>
	  		<input type="text" name="txfNombre" class="w-100" required>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Apellido</label>
	  		<input type="text" name="txfApellido" class="w-100" required>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Documento</label>
	  		<input type="number" name="txfDocumento" class="w-100" required>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=inpNacimiento class="w-100" required>
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
			  			if(paci!=null && paci.getNacionalidad().getIdNacionalidad() == pa.getIdNacionalidad()){%>selected<%;}
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
			  			if(paci!=null && paci.getSexo().getIdSexo() == sex.getIdSexo()){%>selected<%;}
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
  	<div class="row justify-content-center">
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
			  			if(paci!=null && paci.getDirecc().getLoc().getProvincia().getIdProv() == prov.getIdProv()){%>selected<%;}
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
				  			if(paci!=null && paci.getDirecc().getLoc().getIdLocalidad() == loc.getIdLocalidad()){%>selected<%;}
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
	  		<input type="text" name="txfDireccion" class="w-100" required>
  		</div>
  	</div>
	<div class="row justify-content-center border-bottom mb-3">
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Correo Electrónico</label>
	  		<input type="email" name="txfCorreo" class="w-100" required>
  		</div>
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Telefono</label>
	  		<input type="text" name="txfTelefono" class="w-100" required>
  		</div>
	</div>
  	
	<div class="row mh-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center mh-2 p-2">
	  		<input type="submit" name="btnAceptar" class="btn btn-success p-3 w-100" value="Aceptar">
	  	</div>	
	</div>	
</div>	
</body>
</html>