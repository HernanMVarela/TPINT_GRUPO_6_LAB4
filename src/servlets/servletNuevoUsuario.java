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

import dao.LocalidadDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import dao.TipoDao;
import daoImpl.LocalidadDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.SexoDaoImpl;
import daoImpl.TipoDaoImpl;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Tipo;

@WebServlet("/servletNuevoUsuario")
public class servletNuevoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public servletNuevoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CARGA LISTAS COMPLETAS
		request.setAttribute("listaProvincias", create_provincia_list());
		request.setAttribute("listaLocalidades", create_localidad_list());
		request.setAttribute("listasexos", create_sexo_list());
		request.setAttribute("listaTipos", create_tipo_list());
		
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
	
	private List<Localidad> create_localidad_list(){
		List<Localidad> loc = new ArrayList<Localidad>();
		LocalidadDao locdao = new LocalidadDaoImpl();
		
		loc = locdao.ListarTodo();
		return loc;
	}
	
	private List<Sexo> create_sexo_list(){
		List<Sexo> sex = new ArrayList<Sexo>();
		SexoDao sexdao = new SexoDaoImpl();
		
		sex = sexdao.ListarTodo();
		return sex;
	}
	
	private List<Tipo> create_tipo_list(){
		List<Tipo> tipo = new ArrayList<Tipo>();
		TipoDao tipodao = new TipoDaoImpl();
		
		tipo = tipodao.ListarTodo();
		return tipo;
	}
}
