package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Administrador;
import negocio.AdministradorNegocio;
import negocioImpl.AdministradorNegocioImpl;

@WebServlet("/servletPanelAdministrador")
public class servletPanelAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletPanelAdministrador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaadmin", create_admin_list());
		
		RequestDispatcher rd = request.getRequestDispatcher("/PanelAdministrador.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private List<Administrador> create_admin_list(){
		AdministradorNegocio adminneg = new AdministradorNegocioImpl();
		return adminneg.Listar();
	}

}
