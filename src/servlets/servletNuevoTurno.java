package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Estado;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import negocio.EspecialidadNegocio;
import negocio.EstadoNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.EstadoNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;


@WebServlet("/servletNuevoTurno")
public class servletNuevoTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletNuevoTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnFiltrarDatos")!=null) {
			
			
			Date date = new Date();
			
			SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd");
			Turno nuevo = new Turno();
			nuevo.setMedico(cargar_datos_medico(request, response));
			try {
				date = format.parse(request.getParameter("inpFechaTurno"));
			} catch (ParseException e) {
				
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			System.out.println(date);
			System.out.println(cal.toString());
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			cal = null;
			date = null;
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
	
}
