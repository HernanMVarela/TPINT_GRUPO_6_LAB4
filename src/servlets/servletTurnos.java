package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Turno;
import negocio.TurnoNegocio;
import negocioImpl.TurnoNegocioImpl;

@WebServlet("/servletTurnos")
public class servletTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletTurnos() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaTurnos", listarTurnos());
		
		RequestDispatcher rd = request.getRequestDispatcher("/Turnos.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<Turno> listarTurnos() {
		List<Turno> listaturnos = new ArrayList<Turno>();
		TurnoNegocio turneg = new TurnoNegocioImpl();
		
		listaturnos = turneg.Listar();
		
		return listaturnos;
	}
}