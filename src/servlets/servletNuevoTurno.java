package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import entidad.Paciente;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;


@WebServlet("/servletNuevoTurno")
public class servletNuevoTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletNuevoTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listaPacientes", listarPacientes());
		request.setAttribute("listaMedicos", listarMedicos());
		
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
	
	private List<Medico> listarMedicos() {
		List<Medico> listamedicos = new ArrayList<Medico>();
		MedicoNegocio medneg = new MedicoNegocioImpl();

		listamedicos = medneg.listar();
		return listamedicos;
	}
	
}
