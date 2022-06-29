package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PacienteNegocio;
import negocio.TurnoNegocio;
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
		
		
		
		return false;
	}
	
	private boolean cargaEstadisitcasMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return false;
	}
	
	private boolean cargaEstadisitcasTurnos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return false;
	}

}
