package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdministradorDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import dao.TipoDao;
import daoImpl.AdministradorDaoImpl;
import daoImpl.LocalidadDaoImpl;
import daoImpl.PaisDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.SexoDaoImpl;
import daoImpl.TipoDaoImpl;
import entidad.Administrador;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Tipo;
import entidad.Usuario;

@WebServlet("/servletNuevoUsuario")
public class servletNuevoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public servletNuevoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// EVENTO BOTON AGREGAR NUEVO USUARIO
		if(request.getParameter("btnAgregarUsuario")!=null) {					
			agregar_nuevo_usuario(request, response);
		}
		
		// CARGA LISTAS COMPLETAS
		request.setAttribute("listaProvincias", create_provincia_list());
		request.setAttribute("listaLocalidades", create_localidad_list());
		request.setAttribute("listasexos", create_sexo_list());
		request.setAttribute("listaTipos", create_tipo_list());
		request.setAttribute("listaPaises", create_pais_list());
		
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
	
	private List<Pais> create_pais_list(){
		List<Pais> pais = new ArrayList<Pais>();
		PaisDao paisdao = new PaisDaoImpl();
		
		pais = paisdao.ListarTodo();
		return pais;
	}

	private void agregar_nuevo_usuario (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Direccion Direc = new Direccion();
		LocalidadDao locdao = new LocalidadDaoImpl(); // REEMPLAZAR POR NEGOCIO
		PaisDao paisdao = new PaisDaoImpl(); // REEMPLAZAR POR NEGOCIO
		Sexo Genero = new Sexo();
		SexoDao sexdao = new SexoDaoImpl(); // REEMPLAZAR POR NEGOCIO
		TipoDao tipodao = new TipoDaoImpl(); // REEMPLAZAR POR NEGOCIO
		Usuario User = new Usuario();
		Administrador Admin = new Administrador();
		boolean flag = true;
		
		// NOMBRE PERSONA
		if(!request.getParameter("txfNombrePersona").isEmpty()) {
			Admin.setNombre(request.getParameter("txfNombrePersona").toString());
		}else {
			flag = false;
		}
		// APELLIDO PERSONA
		if(!request.getParameter("txfApellidoPersona").isEmpty()) {
			Admin.setApellido(request.getParameter("txfApellidoPersona").toString());
		}else {
			flag = false;
		}
		
		// DOCUMENTO PERSONA
		if(!request.getParameter("txfDocumentoPersona").isEmpty()) {
			Admin.setDni(Integer.parseInt(request.getParameter("txfDocumentoPersona")));
		}else {
			flag = false;
		}
		// PAIS PERSONA
		if(Integer.parseInt(request.getParameter("slcPaisPersona"))!=0) {
			Admin.setNacionalidad(paisdao.ObtenerObjeto((Integer.parseInt(request.getParameter("slcPaisPersona")))));
		}else {
			flag = false;
		}
		
		// DIRECCION PERSONA
			// LOCALIDAD
		if(Integer.parseInt(request.getParameter("slcLocPersona"))!=0) {
			Direc.setLoc(locdao.ObtenerObjeto((Integer.parseInt(request.getParameter("slcLocPersona")))));
		}else {
			flag = false;
		}
			// DIRECCION
		if(!request.getParameter("txfDireccionPersona").isEmpty()) {
			Direc.setCalleYNum(request.getParameter("txfDireccionPersona").toString());
		}else {
			flag = false;
		}
		
		// SEXO
		if(Integer.parseInt(request.getParameter("slcSexoPersona"))!=0) {
			Genero = sexdao.ObtenerObjeto(Integer.parseInt(request.getParameter("slcSexoPersona")));
		}else {
			flag = false;
		}
		
		// EMAIL PERSONA
		if(!request.getParameter("txfEmailPersona").isEmpty()) {
			Admin.setEmail(request.getParameter("txfEmailPersona").toString());
		}else {
			flag = false;
		}
		// TELEFONO
		if(!request.getParameter("txfTelefonoPersona").isEmpty()) {
			Admin.setTelefono(request.getParameter("txfTelefonoPersona").toString());
		}else {
			flag = false;
		}
		
		// FECHA DE NACIMIENTO
		if(!request.getParameter("txfFechaNacPersona").isEmpty()) {
			Admin.setFecha_nacimiento(Date.valueOf(request.getParameter("txfFechaNacPersona").toString()));
		}else {
			flag = false;
		}
		
		// USERNAME
		if(!request.getParameter("txfUsername").isEmpty()) {
			User.setUser(request.getParameter("txfUsername").toString());
		}else {
			flag = false;
		}
		
		// PASSWORD
		if(!request.getParameter("txfPassword1").isEmpty() && !request.getParameter("txfPassword2").isEmpty()) {
			if(request.getParameter("txfPassword1").toString().equals(request.getParameter("txfPassword2").toString())) {
				User.setPassword(request.getParameter("txfPassword1").toString());
			}else {
				flag = false;
			}
		}else {
			flag = false;
		}
		
		// TIPO DE USUARIO
		if(Integer.parseInt(request.getParameter("slcTipoUsuario"))!=0) {
			User.setTipo(tipodao.ObtenerObjeto(Integer.parseInt(request.getParameter("slcTipoUsuario"))));
		}else {
			flag = false;
		}
		
		// ESTADO DE CUENTA
		if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=1) {
				Admin.setEstado(true);
			}else {
				Admin.setEstado(false);
			}
		}else {
			flag = false;
		}
		
		if(flag) {
			Admin.setDirecc(Direc);
			Admin.setSexo(Genero);
			Admin.setUsuario(User);
		
			AdministradorDao admindao = new AdministradorDaoImpl();
			if(admindao.Agregar(Admin)) {
				System.out.println("AGREGADO OK");	
			}else {
				System.out.println("AGREGADO NO OK");
			}
			
		}else {
			System.out.println("ALGO NO ANDUVO");		
		}

	}
}
