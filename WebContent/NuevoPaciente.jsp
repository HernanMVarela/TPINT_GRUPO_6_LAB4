<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Paciente"%>
<%@page import="servlets.servletNuevoPaciente"%>
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

<title>Nuevo Paciente</title>
</head>
<body onLoad="myOnLoad()">

<%!ArrayList<Provincia> listaProvincias = null;%>
<%!ArrayList<Localidad> listaLocalidad2 = null;%>
<%!ArrayList<Sexo> listasexos = null;%>
<%!ArrayList<Pais> listapaises = null;%>
<%!Paciente paci = null;%>
<%
	if(request.getAttribute("paciente")!=null){
		paci = (Paciente)request.getAttribute("paciente");
	}else{
		paci = null;
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

<%if(user!=null){ %>
<div class="container-fluid">
<form action="servletNuevoPaciente" method="get">

<%if(paci!=null){%><input type="hidden" name="pacienteID" value="<%= paci.getIdPaciente() %>"><%}%>

	<div class="row mh-2 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Nuevo paciente</label>
	</div>	

  	<div class="row justify-content-center">
  		<label class="subtitle w-75">Datos personales</label>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Nombre</label>
	  		<input type="text" name="txfNombre" class="w-100" required placeholder="Nombre" <% if(paci!=null){%>value="<%=paci.getNombre()%>"<%;}%>>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Apellido</label>
	  		<input type="text" name="txfApellido" class="w-100" required placeholder="Apellido" <% if(paci!=null){%>value="<%=paci.getApellido()%>"<%;}%>>
  		</div>
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Documento</label>
	  		<input type="number" name="txfDocumento" class="w-100" required placeholder="Documento" <% if(paci!=null){%>readonly value="<%=paci.getDni()%>"<%;}%>>
  		</div>
  	</div>
  	<div class="row justify-content-center">
  		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Fecha de nacimiento</label>
  				<input type="date" name=inpNacimiento id="datefield" class="w-100" required <% if(paci!=null){%>value="<%=paci.getFecha_nacimiento() %>"<%;}%>>
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
  			<select name="slcProvPersona" id="provincia1" onchange="cargar_localidades()" class="w-100" required>
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
  			<select required id="localidadReal" name="slcLocalidad" class="w-100"></select>
  			<select name="slcLocPersona" id="localidad2" hidden required>
				<option value="0" disabled>Seleccione opción</option>
				<%
				if(request.getAttribute("listaLocalidades")!=null){
					listaLocalidad2 = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
					if(!listaLocalidad2.isEmpty()){
						for(Localidad loc : listaLocalidad2){
							%>
							<option value="<%=loc.getProvincia().getIdProv() %>" data-uid="<%=loc.getIdLocalidad()%>"
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
	  		<input type="text" name="txfDireccion" class="w-100" required placeholder="Dirección" 
	  		<% 
	  		if(paci!=null){%>value="<%=paci.getDirecc().getCalleYNum() %>"<%;}
	  		%>>
  		</div>
  	</div>
	<div class="row justify-content-center border-bottom mb-3">
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Correo Electrónico</label>
	  		<input type="email" name="txfCorreo" class="w-100" required placeholder="nombre@dominio.tipo" <% if(paci!=null){%>value="<%=paci.getEmail() %>"<%;}%>>
  		</div>
		<div class="col col-md-3 mh-2 justify-content-center p-2">
  			<label class="p-1">Telefono</label>
	  		<input type="text" name="txfTelefono" class="w-100" required placeholder="123 1234 1234" <% if(paci!=null){%>value="<%=paci.getTelefono() %>"<%;}%>>
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
		<a href="servletHome" class="btn btn-outline-danger w-25 my-2">No hay usuario logueado - Volver a Home.</a>
	</div>
<%}%>	
	
	<script>
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
	   dd = '0' + dd;
	}
	if (mm < 10) {
	   mm = '0' + mm;
	} 
	    
	today = yyyy + '-' + mm + '-' + dd;
	document.getElementById("datefield").setAttribute("max", today);
	</script>
	 
	 
	 <script>
function myOnLoad() {
		var earrings = document.getElementById('localidad2');
		earrings.style.visibility = 'hidden';
		cargar_localidades();
	
	}
</script>
<script>
function cargar_localidades() {
	document.getElementById("localidadReal").options.length = 0;
	
	var x = document.getElementById("localidad2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}
	
	 addOptions("slcLocalidad", array, a,b);
	}
</script>
<script>
function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('provincia1').value;
	 for (value in array) {
		if(a[value] === inde){
	  var option = document.createElement("option");
	  option.text = array[value];
	  option.value = b[value];
	  select.add(option);
		}
	 }
	}
</script>
	
</body>
</html>