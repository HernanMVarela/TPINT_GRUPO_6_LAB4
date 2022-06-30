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
import javax.xml.bind.helpers.ParseConversionEventImpl;

import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import daoImpl.LocalidadDaoImpl;
import daoImpl.PaisDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.SexoDaoImpl;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Paciente;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Sexo;
import negocio.LocalidadNegocio;
import negocio.PacienteNegocio;
import negocio.PaisNegocio;
import negocio.PersonaNegocio;
import negocio.SexoNegocio;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.SexoNegocioImpl;

@WebServlet("/servletNuevoPaciente")
public class servletNuevoPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletNuevoPaciente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean aux = true;
		String redirect = "/NuevoPaciente.jsp";
		request.setAttribute("paciente", null);
		
		
		// EVENTO BOTON MODIFICAR USUARIO
		if(request.getParameter("btnModificarPaciente")!=null) {
			if(request.getParameter("radSelect")!=null){
				if(carga_datos(request, response)) {
					// DATOS DEL ELEMENTO SELECCIONADO OBTENIDOS
				}else {
					request.setAttribute("paciente", null);	
					// NO SE PUEDEN CARGAR LOS DATOS
				}
			}else {
				request.setAttribute("Mensaje", "SELECT");
				// NO HAY ELEMENTO SELECCIONADO
				aux = false;
				redirect = "servletPacientes"; 
			}
		}
		
		if(request.getParameter("btnAceptar")!=null) {
			if(request.getParameter("pacienteID")!=null) {
				if(modificar_paciente(request, response)) {
					request.setAttribute("Mensaje", "MODIOK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}
			}else {
				if(agregar_paciente(request, response)) {
					request.setAttribute("Mensaje", "AGROK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}
			}
			aux = false;
			redirect = "servletPacientes"; 
		}
		
		if(request.getParameter("btnEliminarUsuario")!=null) {
			if(request.getParameter("radSelect")!=null){
			    if(eliminar_paciente(request, response)) {
			    	request.setAttribute("Mensaje", "ELIMOK");
			    }else {
			    	request.setAttribute("Mensaje", "ERROR");
			    }
			    aux = false;
			    redirect = "servletPacientes";
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

	private boolean modificar_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona modificar = new Persona();
		Paciente paciente = new Paciente();
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		
		modificar = carga_datos_persona(request, response);
		
		paciente = pacneg.buscar_dni(modificar.getDni());
		
		if(paciente.getIdPaciente() != Integer.parseInt(request.getParameter("pacienteID"))) {
			return false;
		}
		
		paciente = nuevo_paciente(modificar);
		paciente.setEstado(true);

		return pacneg.modificarPaciente(paciente);
		
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

	private boolean carga_datos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Paciente modificar = new Paciente();
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		
		modificar = pacneg.buscar_paciente(Integer.parseInt(request.getParameter("radSelect")));
		
		if(modificar==null) {
			return false;
		}
		request.setAttribute("paciente", modificar);
		return true;
	}
	
	private boolean agregar_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona perso = new Persona();
		perso = carga_datos_persona(request, response);
		
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		Paciente nuevo = new Paciente();
		nuevo = nuevo_paciente(perso);
		
		return pacneg.agregarPaciente(nuevo);
	}
	
	private Paciente nuevo_paciente(Persona perso) {
		Paciente nuevo = new Paciente();
		
		nuevo.setDni(perso.getDni());
		nuevo.setNombre(perso.getNombre());
		nuevo.setApellido(perso.getApellido());
		nuevo.setEmail(perso.getEmail());
		nuevo.setFecha_nacimiento(perso.getFecha_nacimiento());
		nuevo.setDirecc(perso.getDirecc());
		nuevo.setNacionalidad(perso.getNacionalidad());
		nuevo.setSexo(perso.getSexo());
		nuevo.setTelefono(perso.getTelefono());
		nuevo.setEstado(true);
		
		return nuevo;
	}
	
	private Persona carga_datos_persona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona Perso = new Persona();
		Perso.setDirecc(new Direccion());
		
		LocalidadNegocio locneg = new LocalidadNegocioImpl();
		PaisNegocio paineg = new PaisNegocioImpl();	
		SexoNegocio sexneg = new SexoNegocioImpl();
		
		boolean flag=true;
		try {
			if(!request.getParameter("txfNombre").isEmpty()) {
				Perso.setNombre(request.getParameter("txfNombre").toString());
			}else {
				flag = false;
			}
			// APELLIDO PERSONA
			if(!request.getParameter("txfApellido").isEmpty()) {
				Perso.setApellido(request.getParameter("txfApellido").toString());
			}else {
				flag = false;
			}
			
			// DOCUMENTO PERSONA
			if(request.getParameter("adminId")==null) {
				if(!request.getParameter("txfDocumento").isEmpty()) {
				{
					Perso.setDni(Integer.parseInt(request.getParameter("txfDocumento")));
				}
				}else {
					flag = false;
				}
			}
			// PAIS PERSONA
			if(Integer.parseInt(request.getParameter("slcPaisPersona"))!=0) {
				Perso.setNacionalidad(paineg.ObtenerObjeto((Integer.parseInt(request.getParameter("slcPaisPersona")))));
			}else {
				flag = false;
			}
			
			// DIRECCION PERSONA
				// LOCALIDAD
			if(Integer.parseInt(request.getParameter("slcLocPersona"))!=0) {
				Perso.getDirecc().setLoc(locneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcLocPersona"))));
			}else {
				flag = false;
			}
				// DIRECCION
			if(!request.getParameter("txfDireccion").isEmpty()) {
				Perso.getDirecc().setCalleYNum(request.getParameter("txfDireccion").toString());
			}else {
				flag = false;
			}
			
			// SEXO
			if(Integer.parseInt(request.getParameter("slcSexoPersona"))!=0) {
				Perso.setSexo(sexneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcSexoPersona"))));
			}else {
				flag = false;
			}
			
			// EMAIL PERSONA
			if(!request.getParameter("txfCorreo").isEmpty()) {
				Perso.setEmail(request.getParameter("txfCorreo").toString());
			}else {
				flag = false;
			}
			// TELEFONO
			if(!request.getParameter("txfTelefono").isEmpty()) {
				Perso.setTelefono(request.getParameter("txfTelefono").toString());
			}else {
				flag = false;
			}
			
			// FECHA DE NACIMIENTO
			if(!request.getParameter("inpNacimiento").isEmpty()) {
				Perso.setFecha_nacimiento(Date.valueOf(request.getParameter("inpNacimiento").toString()));
			}else {
				flag = false;
			}
		} catch (Exception e) {
			return null;
		}
		
		if(!flag) return null;
		return Perso;
	}
	
	private boolean eliminar_paciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PacienteNegocio pacneg = new PacienteNegocioImpl();
		int idPaciente = Integer.valueOf(request.getParameter("radSelect"));
		
		return pacneg.BajaLogica(idPaciente);

	}

}
