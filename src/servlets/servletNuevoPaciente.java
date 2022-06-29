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
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import daoImpl.LocalidadDaoImpl;
import daoImpl.PaisDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.SexoDaoImpl;
import entidad.Localidad;
import entidad.Paciente;
import entidad.Pais;
import entidad.Provincia;
import entidad.Sexo;
import negocio.PacienteNegocio;
import negocioImpl.PacienteNegocioImpl;

@WebServlet("/servletNuevoPaciente")
public class servletNuevoPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletNuevoPaciente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean aux = true;
		String redirect = "/NuevoPaciente.jsp";
		request.setAttribute("pacien", null);  ///////////
		
		if(request.getParameter("btnAceptar")!=null) {
			agregar_paciente(request, response);
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			if(request.getParameter("radSelect")!=null){
			    if(eliminar_paciente(request, response)) {
			        // 
			    }else {
			        // NO SE PUDO ELIMINAR
			    }
			    aux = false;
			    redirect = "servletMedicos";
			}else {
			    // NO HAY NADA SELECCIONADO - DEVOLVER ATRIBUTO O MENSAJE DE ERROR
			}
		}
		
		if(aux) {
			request.setAttribute("listaProvincias", create_provincia_list());
			request.setAttribute("listaLocalidades", create_localidad_list());
			request.setAttribute("listasexos", create_sexo_list());
			request.setAttribute("listaPaises", create_pais_list());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirect);   
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
	
	private List<Pais> create_pais_list(){
		List<Pais> pais = new ArrayList<Pais>();
		PaisDao paisdao = new PaisDaoImpl();
		
		pais = paisdao.ListarTodo();
		return pais;
	}

	private boolean agregar_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return false;
	}
	
	private boolean eliminar_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		Paciente pacien = new Paciente();
		
		pacien = pacneg.buscar_id(Integer.parseInt(request.getParameter("radSelect")));
		if(pacien != null) {
			return pacneg.bajaPaciente(pacien);
		}
		
		return false;
	}
}
