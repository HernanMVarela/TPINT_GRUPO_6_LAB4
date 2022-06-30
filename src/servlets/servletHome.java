package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import negocio.EspecialidadNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocio.TurnoNegocio;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.TurnoNegocioImpl;


@WebServlet("/servletHome")
public class servletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletHome() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirect = "/Home.jsp";
		
		cargaEstadisitcasPacientes(request, response);
		cargaEstadisitcasMedicos(request, response);
		cargaEstadisitcasTurnos(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(redirect);   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean cargaEstadisitcasPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		TurnoNegocio turneg = new TurnoNegocioImpl();
		request.setAttribute("consultas", turneg.ContarTurnosPorEstado(4));
		request.setAttribute("totalpacientes", pacneg.contarPacientes());
		int turnosact = turneg.ContarTurnosPorEstado(2) + turneg.ContarTurnosPorEstado(4);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String turnosporpac =  df.format((float)turnosact / pacneg.contarPacientes());
		
		request.setAttribute("turnosporpac", turnosporpac);
		request.setAttribute("totalturnos", turneg.ContarTurnosTotales());

		return true;
	}
	
	private boolean cargaEstadisitcasMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MedicoNegocio medneg = new MedicoNegocioImpl();
		EspecialidadNegocio espneg = new EspecialidadNegocioImpl();
		
		Medico mejor = new Medico();
		
		mejor = medneg.buscar_id(medneg.mejorMedico());
		
		request.setAttribute("medicostotales", medneg.contarMedicos());
		request.setAttribute("especialidades", espneg.ContarEspecialidades());
		request.setAttribute("mejormedico", mejor.getNombre() + " " + mejor.getApellido());
		request.setAttribute("turnosmejormedico", medneg.turnosMejorMedico());
		
		return false;
	}
	
	private boolean cargaEstadisitcasTurnos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TurnoNegocio turneg = new TurnoNegocioImpl();
		request.setAttribute("turnosocupados", turneg.ContarTurnosPorEstado(2));
		request.setAttribute("turnosatendidos", turneg.ContarTurnosPorEstado(4));
		request.setAttribute("turnoscancelados", turneg.ContarTurnosPorEstado(1)+turneg.ContarTurnosPorEstado(3));
		
		return false;
	}

}
