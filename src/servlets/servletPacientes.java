package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Paciente;
import negocio.PacienteNegocio;
import negocioImpl.PacienteNegocioImpl;



@WebServlet("/servletPacientes")
public class servletPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletPacientes() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaPacientes", listarPacientes());
		
		RequestDispatcher rd = request.getRequestDispatcher("/Pacientes.jsp");   
        rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private List<Paciente> listarPacientes() {
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		return pacneg.listar();
	}

}
