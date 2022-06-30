package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Estado;
import entidad.Horario;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import negocio.EspecialidadNegocio;
import negocio.EstadoNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocio.TurnoNegocio;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.EstadoNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.TurnoNegocioImpl;


@WebServlet("/servletNuevoTurno")
public class servletNuevoTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletNuevoTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "/NuevoTurno.jsp";
		boolean flag = true;
		request.setAttribute("Mensaje", null);
		request.setAttribute("preturno", null);
		request.setAttribute("fullturno", null);
		if(request.getParameter("btnFiltrarDatos")!=null) {
			filtar_horarios(request, response);
		}
		
		if(request.getParameter("btnConfirmarTurno")!=null) {
			if(validar_campos_finales(request, response)) {
				// TODOS LOS CAMPOS SON CORRECTOS - SE PROCEDE CON EL ALTA DE TURNO
				if(generar_turno(request, response)) {
					// SE AGREGA O MODIFICA TURNO
					redirect = "servletTurnos";
					flag = false;
				}
			}
		}
		
		if(request.getParameter("btnVerTurno")!=null) { 
			if(request.getParameter("radSelect")!=null && Integer.parseInt(request.getParameter("radSelect"))!=0) {
				
				TurnoNegocio turneg = new TurnoNegocioImpl();
				Turno mostrar = new Turno();
				mostrar = turneg.Buscar(Integer.parseInt(request.getParameter("radSelect")));
				request.setAttribute("turno", mostrar);
				redirect = "VerTurno.jsp";
			}else {
				request.setAttribute("Mensaje", "SELECT");
				redirect = "servletTurnos";
			}
			flag=false;
		}		
		
		if(request.getParameter("btnEliminarTurno")!=null) { 
			if(request.getParameter("radSelect")!=null) {
				if(liberar_turno(request, response)) {
					request.setAttribute("Mensaje", "LIBRE");
					redirect = "servletTurnos";
					flag = false;
				}
			}else {
				request.setAttribute("Mensaje", "SELECT");
				redirect = "servletTurnos";
				flag = false;
			}
		}
				
		if(request.getParameter("btnModificarTurno")!=null) { 
			if(request.getParameter("radSelect")!=null) { 
				if(cargar_turno_a_modificar(request, response)) {
					request.setAttribute("Mensaje", "ERROR");
					redirect = "servletTurnos";
					flag = false;
				}
			}else {
				request.setAttribute("Mensaje", "SELECT");
				redirect = "servletTurnos";
				flag = false;
			}
		}
		if(flag) {
			request.setAttribute("listaPacientes", listarPacientes());
			request.setAttribute("listaMedicos", listarMedicos());
			request.setAttribute("listaEsp", listarEspecialidades());
			request.setAttribute("listaEstadoTurno", listarEstadoTurno());
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirect);   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<Paciente> listarPacientes() {
		List<Paciente> listapacientes = new ArrayList<Paciente>();
		PacienteNegocio pacneg = new PacienteNegocioImpl();

		listapacientes = pacneg.listar();
		return listapacientes;
	}
	
	private List<Estado> listarEstadoTurno() {
		List<Estado> listaEstados = new ArrayList<Estado>();
		EstadoNegocio estneg = new EstadoNegocioImpl();

		listaEstados = estneg.ListarTodo();
		return listaEstados;
	}
	
	private List<Medico> listarMedicos() {
		List<Medico> listamedicos = new ArrayList<Medico>();
		MedicoNegocio medneg = new MedicoNegocioImpl();

		listamedicos = medneg.listar();
		return listamedicos;
	}
	
	private List<Especialidad> listarEspecialidades() {
		List<Especialidad> listaEsp = new ArrayList<Especialidad>();
		EspecialidadNegocio espneg = new EspecialidadNegocioImpl();

		listaEsp = espneg.ListarTodo();
		return listaEsp;
	}
	
	private Medico cargar_datos_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		Medico medic = new Medico();
		MedicoNegocio medneg = new MedicoNegocioImpl();
		medic = medneg.buscar_id(Integer.parseInt(request.getParameter("slcMedico")));
		return medic;
	}
	
	private Paciente cargar_datos_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		Paciente paci = new Paciente();
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		paci = pacneg.buscar_paciente(Integer.parseInt(request.getParameter("slcPaciente")));
		return paci;
	}
	
	private Especialidad cargar_datos_especialidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		Especialidad espe = new Especialidad();
		EspecialidadNegocio espneg = new EspecialidadNegocioImpl();
		espe = espneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcEsp")));
		return espe;
	}
	
	private boolean filtar_horarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Medico medico = new Medico();
		Paciente paci = new Paciente();
		
		if(!validacion_campos_iniciales(request, response)) {return false;}
		// SETEA MEDICO ELEGIDO
		medico=cargar_datos_medico(request, response);
		
		// SETEA PACIENTE ELEGIDO
		paci=cargar_datos_paciente(request, response);
		
		// HORARIOS DISPONIBLES DEL MEDICO POR DIA DE LA SEMANA
		ArrayList<Integer> horas = new ArrayList<Integer>();		
		
		// CARGA HORAS DEL MEDICO
		horas = cargar_horas_medico(medico, horas,request.getParameter("inpFechaTurno").toString());
		
		// SE QUITAN HORARIOS OCUPADOS POR FECHAS EN OTROS TURNOS
		horas = quitar_horas_ocupadas(medico,horas,request.getParameter("inpFechaTurno").toString(),request,response);
		
		Turno turno = new Turno();
		turno.setMedico(medico);
		turno.setPaciente(paci);
		turno.setEspecialidad(cargar_datos_especialidad(request, response));
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		java.util.Date date = null;
		try {
			date = df.parse(request.getParameter("inpFechaTurno").toString());
			java.sql.Date fecha = new java.sql.Date(date.getTime());
			turno.setDia(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("preturno", turno);
		request.setAttribute("horasDisponibles", horas);
		
		return false;
	}
	
	private ArrayList<Integer> cargar_horas_medico(Medico medico, ArrayList<Integer> horarios, String fecha){
		ArrayList<Integer> horas = new ArrayList<Integer>();
		TurnoNegocio turneg = new TurnoNegocioImpl();
		
		for (Horario hora : medico.getHorarios()) {
			if(hora.getDia()==turneg.ObtenerDiaSemana(fecha.toString())) {
				for(int x=hora.getHoraDesde(); x<=hora.getHoraHasta(); x++) {
					horas.add(x);
				}
			}
		}	
		return horas;
	}
	
	
	private ArrayList<Integer> quitar_horas_ocupadas(Medico medico, ArrayList<Integer> horas, String fecha, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CARGA LISTA DE TURNOS
		TurnoNegocio turneg = new TurnoNegocioImpl();
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		turnos = turneg.Listar();
		
		int turno_actual = 0;
		if(request.getParameter("radSelect")!=null && Integer.parseInt(request.getParameter("radSelect"))!=0){
			turno_actual=Integer.parseInt(request.getParameter("radSelect"));
			
		}
		for (Turno turno : turnos) {
			if(turno.getDia().toString().equals(fecha)){
				if(turno.getMedico().getIdMedico()==medico.getIdMedico() && turno.getIdTurno()!=turno_actual){
					Iterator<Integer> it = horas.iterator();
					while(it.hasNext()) {
						if(it.next()+1==turno.getHora()) {
							it.remove();
						}
					}
				}
			}
		}
		return horas;
	}
	
	private boolean validacion_campos_iniciales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=true;
		// VALIDA MEDICO SELECCIONADO
		if(request.getParameter("slcMedico")==null) {
			request.setAttribute("elegirmedico", false);
			flag= false;
		}else {
			request.setAttribute("elegirmedico", true);
		}
		
		// VALIDA FECHA SELECCIONADA
		if(request.getParameter("inpFechaTurno")==null || request.getParameter("inpFechaTurno").isEmpty()) {
			request.setAttribute("elegirfecha", false);
			flag= false; 
		}else {
			request.setAttribute("elegirfecha", true);
		}
		
		// VALIDA ESPECIALIDAD SELECCIONADA
		if(request.getParameter("slcEsp")==null) {
			request.setAttribute("elegirespecialidad", false);
			flag= false;
		}else {
			request.setAttribute("elegirespecialidad", true);
		}
		
		// VALIDA PACIENTE SELECCIONADO
		if(request.getParameter("slcPaciente")==null) {
			request.setAttribute("elegirpaciente", false);
			flag= false;
		}else {
			request.setAttribute("elegirpaciente", true);
		}
		return flag;
	}
	
	private boolean validar_campos_finales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=true;
		if(!validacion_campos_iniciales(request, response)) {flag=false;}
		// VALIDA PACIENTE SELECCIONADO
		if(request.getParameter("slcHoras")==null) {
			request.setAttribute("elegirhoras", false);
			flag= false;
		}else {
			request.setAttribute("elegirhoras", true);
		}

		if(request.getParameter("txfObservacion")!=null){
			if(request.getParameter("txfObservacion").isEmpty()){
				request.setAttribute("elegirobservacion", false);
				flag= false;
			}else {
				request.setAttribute("elegirobservacion", true);
			}
		}else {
			flag= false;
			request.setAttribute("elegirobservacion", false);
		}
		if(request.getParameter("slcEstadoTurno")==null) {
			request.setAttribute("elegirestado", false);
			flag= false;
		}else {
			request.setAttribute("elegirestado", true);
		}
		return flag;
	}

	private boolean generar_turno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		TurnoNegocio turneg = new TurnoNegocioImpl();
		Turno nuevo = new Turno();
		EstadoNegocio est = new EstadoNegocioImpl();
		
		nuevo.setMedico(cargar_datos_medico(request, response));
		nuevo.setPaciente(cargar_datos_paciente(request, response));
		nuevo.setEspecialidad(cargar_datos_especialidad(request, response));
		
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		java.util.Date date = null;
		try {
			date = df.parse(request.getParameter("inpFechaTurno").toString());
			java.sql.Date fecha = new java.sql.Date(date.getTime());
			nuevo.setDia(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nuevo.setHora(Integer.parseInt(request.getParameter("slcHoras")));
		nuevo.setObservacionConsulta(request.getParameter("txfObservacion").toString());
		nuevo.setEstadoTurno(est.ObtenerObjeto(Integer.parseInt(request.getParameter("slcEstadoTurno"))));
		nuevo.setEstado(true);
		
		if(request.getParameter("turnoId")!=null && Integer.parseInt(request.getParameter("turnoId"))!=0) {
			nuevo.setIdTurno(Integer.parseInt(request.getParameter("turnoId")));
			if(turneg.Modificar(nuevo)) {
				request.setAttribute("Mensaje", "MODIOK");
				return true;
			}else {
				request.setAttribute("Mensaje", "ERROR");
				return false;
			}
		}else {
			if(turneg.agregarTurno(nuevo)) {
				request.setAttribute("Mensaje", "AGROK");
				return true;
			}else {
				request.setAttribute("Mensaje", "ERROR");
				return false;
			}
		}	
	}

	private boolean liberar_turno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TurnoNegocio turneg = new TurnoNegocioImpl();
		Turno turno = new Turno();
		EstadoNegocio estneg = new EstadoNegocioImpl();
		
		turno = turneg.Buscar(Integer.parseInt(request.getParameter("radSelect")));
		if(turno != null) {
			turno.setEstadoTurno(estneg.ObtenerObjeto(1));
			turno.setEstado(false);
			if(turneg.Modificar(turno)) {return true;}
		}
		return false;
	}
	
	private boolean cargar_turno_a_modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TurnoNegocio turneg = new TurnoNegocioImpl();
		Turno turno = new Turno();
		
		turno = turneg.Buscar(Integer.parseInt(request.getParameter("radSelect")));
		if(turno != null) {
			ArrayList<Integer> horas = new ArrayList<Integer>();	
			horas = cargar_horas_medico(turno.getMedico(), horas, turno.getDia().toString());
			horas = quitar_horas_ocupadas(turno.getMedico(),horas, turno.getDia().toString(),request,response);
			request.setAttribute("horasDisponibles", horas);
			request.setAttribute("fullturno", turno);
		}
		return false;
	}

}
