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

import dao.AdministradorDao;
import dao.MedicoDao;
import daoImpl.AdministradorDaoImpl;
import daoImpl.MedicoDaoImpl;
import entidad.Administrador;
import entidad.Medico;

@WebServlet("/servletPanelAdministrador")
public class servletPanelAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletPanelAdministrador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaadmin", create_admin_list());
		request.setAttribute("listamedic", create_medico_list());
		
		RequestDispatcher rd = request.getRequestDispatcher("/PanelAdministrador.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private List<Administrador> create_admin_list(){
		List<Administrador> ladmin = new ArrayList<Administrador>();
		AdministradorDao admindao = new AdministradorDaoImpl();
		
		ladmin = admindao.ListarTodo();
		return ladmin;
	}
	
	private List<Medico> create_medico_list(){
		List<Medico> lmedic = new ArrayList<Medico>();
		MedicoDao medicdao = new MedicoDaoImpl();
		
		lmedic = medicdao.ListarTodo();
		return lmedic;
	}
}
