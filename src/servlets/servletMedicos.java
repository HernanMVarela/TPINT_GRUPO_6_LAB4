package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import negocio.MedicoNegocio;
import negocioImpl.MedicoNegocioImpl;


@WebServlet("/servletMedicos")
public class servletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servletMedicos() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaMedicos", listarMedicos());
		
		RequestDispatcher rd = request.getRequestDispatcher("/Medicos.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private List<Medico> listarMedicos() {
		MedicoNegocio medneg = new MedicoNegocioImpl();
		return medneg.listar();
	}
	
}
