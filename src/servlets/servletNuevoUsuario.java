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

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidad.Provincia;

@WebServlet("/servletNuevoUsuario")
public class servletNuevoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public servletNuevoUsuario() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DEVOLVER LISTA E ID TIPO SEGURO
		request.setAttribute("listaProvincias", create_provincia_list());

		
		RequestDispatcher rd = request.getRequestDispatcher("/NuevoUsuario.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private List<Provincia> create_provincia_list(){
		List<Provincia> prov = new ArrayList<Provincia>();
		ProvinciaDao provdao = new ProvinciaDaoImpl();
		
		prov = provdao.ListarTodo();
		return prov;
	}
	

}
