package servlets;

import java.io.IOException;
import java.sql.Date;
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

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

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
		
		if(request.getParameter("btnFiltrarDatos")!=null) {
			filtar_horarios(request, response);
		}
		
		if(request.getParameter("btnConfirmarTurno")!=null) {
			if(validar_campos_finales(request, response)) {
				// TODOS LOS CAMPOS SON CORRECTOS - SE PROCEDE CON EL ALTA DE TURNO
				if(generar_turno(request, response)) {
					System.out.println("NUEVO TURNO CARGADO OK");
				}
			}else {
				System.out.println("NO PASA POR GENERAR TURNO");
			}
		}
		
		request.setAttribute("listaPacientes", listarPacientes());
		request.setAttribute("listaMedicos", listarMedicos());
		request.setAttribute("listaEsp", listarEspecialidades());
		request.setAttribute("listaEstadoTurno", listarEstadoTurno());
		
		RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");   
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
		
		TurnoNegocio turneg = new TurnoNegocioImpl();
		Medico medico = new Medico();
		Paciente paci = new Paciente();
		
		if(!validacion_campos_iniciales(request, response)) {return false;}
		
		// SETEA MEDICO ELEGIDO
		medico=cargar_datos_medico(request, response);
		
		// SETEA PACIENTE ELEGIDO
		paci=cargar_datos_paciente(request, response);
		
		// CARGA LISTA DE TURNOS
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		turnos = turneg.Listar();
		
		// HORARIOS DISPONIBLES DEL MEDICO POR DIA DE LA SEMANA
		ArrayList<Integer> horas = new ArrayList<Integer>();		
		
		for (Horario hora : medico.getHorarios()) {
			if(hora.getDia()==turneg.ObtenerDiaSemana(request.getParameter("inpFechaTurno").toString())) {
				for(int x=hora.getHoraDesde(); x<=hora.getHoraHasta(); x++) {
					horas.add(x);
				}
			}
		}	
		
		// SE QUITAN HORARIOS OCUPADOS POR FECHAS EN OTROS TURNOS
		for (Turno turno : turnos) {
			if(turno.getDia().toString().equals(request.getParameter("inpFechaTurno").toString())){
				if(turno.getMedico().getIdMedico()==medico.getIdMedico()){
					Iterator<Integer> it = horas.iterator();
					while(it.hasNext()) {
						if(it.next()+1==turno.getHora()) {
							System.out.println("SE QUITA: "+it.next()+":00hs - HORA OCUPADA POR OTRO TURNO");
							it.remove();
						}
					}
				}
			}
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("preturno", turno);
		request.setAttribute("horasDisponibles", horas);
		
		return false;
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
		System.out.println("VALIDACIONES: " + flag);
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

		System.out.println(nuevo.getDia().toString()+" "+nuevo.getHora()+":00 "+nuevo.getEspecialidad().getNombre());
		System.out.println("Medico: "+ nuevo.getMedico().getNombre() + " " + nuevo.getMedico().getApellido());
		System.out.println("Paciente: "+ nuevo.getPaciente().getNombre() + " " + nuevo.getPaciente().getApellido());
		System.out.println(nuevo.getEstadoTurno().getNombre() + " - " + nuevo.getObservacionConsulta());
		
		return turneg.agregarTurno(nuevo);
	}
}
