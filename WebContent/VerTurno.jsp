<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	<jsp:include page="cssStyles/StyleSheet.css"></jsp:include>
</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle de turnos</title>
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
<div class="container-fluid">
	<div class="row mx-1 mb-2 justify-content-center p-2">
	  	<label class="subtitle w-100">Detalle del turno</label>
	</div>
	<div class="row mx-2 mb-2 justify-content-center p-2">
		<div class="col col-md-3 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del paciente</label>
			<h5>Nombre completo: Jill Williams</h5><br>
			<h5>DNI: 35251764</h5><br>
			<h5>Nacionalidad: Bolivia</h5><br>
			<h5>Sexo: Masculino</h5><br>
			<h5>Fecha de nacimiento: 1981-09-14</h5><br>
			<h5>Telefono: 510-555-0121</h5><br>
			<h5>Correo: jill.williams@mail.com</h5><br>
			<h5>Dirección: 80 Sunview Terrace</h5><br>
			<h5>Localidad: Cariló</h5><br>
			<h5>Provincia: Buenos Aires</h5><br>
		</div>
		<div class="col mx-1 col-md-5 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del turno</label>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Medico: Ovidiu Cracium</h4>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Especialidad: Cardiología</h4><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Paciente: José Lopez</h4><br>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h4>Sexo: Masculino</h4><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h5>Fecha: 10/07/2022 - Hora: 14:00hs</h5><br>
				</div>
				<div class="col col-md-6 mh-2 d-flex justify-content-between p-2">
					<h5>Estado del turno: Ocupado</h5><br>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-12 mh-2 d-flex justify-content-between p-2">
					<textarea rows="5" style="width: 100%; max-width: 100%;" disabled>Observación realizada del paciente</textarea>
				</div>
			</div>
			<div class="row mb-2 justify-content-center p-2">
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
					<a href="NuevoTurno.jsp" class="btn btn-primary p-2 w-100">Nuevo turno</a>
				</div>
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
					<input type="submit" name="btnModificarTurno" class="btn btn-primary p-2 w-100" value="Modificar turno">
				</div>
				<div class="col col-md-3 mh-2 d-flex justify-content-between p-2">
					<input type="submit" name="btnEliminarTurno" class="btn btn-danger p-2 w-100" value="Eliminar turno">
				</div>
			</div>
			
		</div>
		<div class="col col-md-3 justify-content-center p-2 cuadro_uno btn-outline-info">
			<label class="subtitle w-100">Datos del médico</label>
			<h5>Nombre completo: Ovidiu Cracium</h5><br>
			<h5>Especialidad: Cardiología Clínica</h5><br>
			<h5>DNI: 40418871</h5><br>
			<h5>Legajo: 0023</h5><br>
			<h5>Fecha de nacimiento: 1990-04-27</h5><br>
			<h5>Telefono: 719-555-0181</h5><br>
			<h5>Correo: ovidiu.cracium@medic.com</h5><br>
			<h5>Dirección: 1318 Lasalle Street</h5><br>
			<h5>Localidad: Balcarce</h5><br>
			<h5>Provincia: Buenos Aires</h5><br>
		</div>
	</div>		
	<div class="row mx-1 mb-2 justify-content-center p-2">
	  	<a href="" name="btnVolver" class="btn btn-outline-primary p-2 w-25">Volver</a>
	</div>
</div>	
</body>
</html>