package servlets;

import java.io.IOException;
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
			
			TurnoNegocio turneg = new TurnoNegocioImpl();

			Turno nuevo = new Turno();
			nuevo.setMedico(cargar_datos_medico(request, response));
			
			ArrayList<Turno> turnos = new ArrayList<Turno>();
			turnos = turneg.Listar();
			
			ArrayList<Integer> horas = new ArrayList<Integer>();
			
			// HORARIOS DISPONIBLES DEL MEDICO POR DIA DE LA SEMANA
			for (Horario hora : nuevo.getMedico().getHorarios()) {
				System.out.println("Dia: "+hora.getDia()+" Desde "+hora.getHoraDesde()+" Hasta "+ hora.getHoraHasta());
				System.out.println("Dia elegido:" +turneg.ObtenerDiaSemana(request.getParameter("inpFechaTurno").toString()));
				if(hora.getDia()==turneg.ObtenerDiaSemana(request.getParameter("inpFechaTurno").toString())) {
					for(int x=hora.getHoraDesde(); x<hora.getHoraHasta(); x++) {
						System.out.println(x+":00hs");
						horas.add(x);
					}
				}
			}	
			
			for (Integer integer : horas) {
				System.out.println(integer+":00hs");
			}

			// SE QUITAN HORARIOS OCUPADOS POR FECHAS EN OTROS TURNOS
			for (Turno turno : turnos) {
				if(turno.getDia().toString().equals(request.getParameter("inpFechaTurno").toString())){
					if(turno.getMedico().getIdMedico()==nuevo.getMedico().getIdMedico()){
						System.out.println(turno.getMedico().getIdMedico() +" "+nuevo.getMedico().getIdMedico());
						Iterator<Integer> it = horas.iterator();
						while(it.hasNext()) {
							if(it.next()==turno.getHora()) {
								System.out.println("SE QUITA: "+it.next()+":00hs");
								it.remove();
							}
						}
					}
				}
			}
			request.setAttribute("horasDisponibles", horas);
		
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
