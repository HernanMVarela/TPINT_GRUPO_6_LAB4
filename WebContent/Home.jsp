<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="bootstrap/StyleSheet.css"></jsp:include>
	<jsp:include page="bootstrap/css/bootstrap.min.css"></jsp:include>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<!-- MENU DE NAVEGACION Y LOGIN -->
  <div class="container">
    <div class="d-flex flex-wrap align-items-left justify-content-left justify-content-md-between py-3 mb-4 border-bottom">

      	<ul class="nav col-md-4 mb-2 justify-content-left mb-md-0">
        	<li><a href="Home.jsp" class="nav-link px-2 link-secondary">Home</a></li>
        	<li><a href="Pacientes.jsp" class="nav-link px-2 link-dark">Pacientes</a></li>
        	<li><a href="Medicos.jsp" class="nav-link px-2 link-dark">Médicos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">Turnos</a></li>
        	<li><a href="#" class="nav-link px-2 link-dark">About</a></li>
     	</ul>
     	<a href="Login.jsp" class="d-flex align-items-center col-md-2 mb-2 mb-md-0 text-dark text-decoration-none"></a>
 		<form action="servletLogin" method="post" class="col-md-6">
 			<div class="row align-items-right justify-content-end">
	      		<div class="col-md-auto text-end pr-2">
	       			<input type="submit" class="btn btn-outline-primary me-2" name="btnLogin" value="Login"> 	
	      		</div>
	      		<div class="col-md-auto text-end pr-2">
					<input type="text" name="txfUsername" placeholder="Usuario" class="form-control" required>
		  		</div>
		  		<div class="col-md-auto text-end pr-2">
					<input type="Password" name="txfPassword" placeholder="Contraseña" class="form-control" required>
	     		</div>
			</div>
		</form>
    </div>
  </div>
  
<!-- TITULO -->
<h1 class="title"> PAGINA PRINCIPAL</h1>


</body>
</html>