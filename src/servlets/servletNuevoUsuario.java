package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import Excepciones.DniException;
import entidad.Administrador;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Tipo;
import entidad.Usuario;
import negocio.AdministradorNegocio;
import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.PersonaNegocio;
import negocio.ProvinciaNegocio;
import negocio.SexoNegocio;
import negocio.TipoNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.AdministradorNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.SexoNegocioImpl;
import negocioImpl.TipoNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/servletNuevoUsuario")
public class servletNuevoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public servletNuevoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "/NuevoUsuario.jsp";
		boolean aux = true;
		request.setAttribute("admin", null);
		
		// EVENTO BOTON MODIFICAR USUARIO
		if(request.getParameter("btnModificarUsuario")!=null) {
			if(request.getParameter("radSelect")!=null){
				if(!carga_datos(request, response)) {
					request.setAttribute("Mensaje", "ERROR");
				}
			}else {
				request.setAttribute("Mensaje", "SELECT");
				redirect = "servletPanelAdministrador"; 
				aux = false;
			}
		}
		
		// EVENTO BOTON AGREGAR NUEVO USUARIO
		if(request.getParameter("btnAgregarUsuario")!=null) {
			if (request.getParameter("adminId")!=null){
				try {
					if(modificar_usuario(request, response)) {
						request.setAttribute("Mensaje", "MODIOK");
					}else {
						request.setAttribute("Mensaje", "ERROR");
					}
				} catch (DniException e) {
					request.getSession().setAttribute("dniException", "DNI Inválido");
					e.printStackTrace();
				}
				redirect = "servletPanelAdministrador";
				aux = false;
			}else {
				try {
					if(agregar_nuevo_usuario(request, response)) {
						request.setAttribute("Mensaje", "AGROK");
						redirect = "servletPanelAdministrador";
						aux = false;
					}
				} catch (DniException e) {
					request.getSession().setAttribute("dniException", "DNI Inválido");
					e.printStackTrace();
				}
			}
			
		}
		
		if(request.getParameter("btnEliminarUsuario")!=null) {
			if(request.getParameter("radSelect")!=null){
				if(eliminar_usuario(request, response)) {
					request.setAttribute("Mensaje", "ELIMOK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}
				redirect = "servletPanelAdministrador";
				aux = false;
			}else {
				request.setAttribute("Mensaje", "SELECT");
			}
		}
		
		// CARGA LISTAS COMPLETAS
		if(aux) {
			request.setAttribute("listaProvincias", create_provincia_list());
			request.setAttribute("listaLocalidades", create_localidad_list());
			request.setAttribute("listasexos", create_sexo_list());
			request.setAttribute("listaTipos", create_tipo_list());
			request.setAttribute("listaPaises", create_pais_list());
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirect);   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private List<Provincia> create_provincia_list(){
		ProvinciaNegocio provneg = new ProvinciaNegocioImpl();
		return provneg.ListarTodo();
	}
	
	private List<Localidad> create_localidad_list(){
		LocalidadNegocio locneg = new LocalidadNegocioImpl();
		return locneg.ListarTodo();
	}
	
	private List<Sexo> create_sexo_list(){
		SexoNegocio sexneg = new SexoNegocioImpl();
		return sexneg.ListarTodo();
	}
	
	private List<Tipo> create_tipo_list(){
		TipoNegocio tipneg = new TipoNegocioImpl();
		return tipneg.ListarTodo();
	}
	
	private List<Pais> create_pais_list(){
		PaisNegocio paisneg = new PaisNegocioImpl();
		return paisneg.ListarTodo();
	}

	private Persona carga_datos_persona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, DniException {
		Persona Perso = new Persona();
		Perso.setDirecc(new Direccion());
		
		LocalidadNegocio locdao = new LocalidadNegocioImpl();
		PaisNegocio paisdao = new PaisNegocioImpl();
		SexoNegocio sexdao = new SexoNegocioImpl();
		
		boolean flag = true;
		
		// NOMBRE PERSONA
				if(!request.getParameter("txfNombrePersona").isEmpty()) {
					Perso.setNombre(request.getParameter("txfNombrePersona").toString());
				}else {
					flag = false;
				}
				// APELLIDO PERSONA
				if(!request.getParameter("txfApellidoPersona").isEmpty()) {
					Perso.setApellido(request.getParameter("txfApellidoPersona").toString());
				}else {
					flag = false;
				}
				
				// DOCUMENTO PERSONA
				if(request.getParameter("adminId")==null) {
					if(!request.getParameter("txfDocumentoPersona").isEmpty()) 
					{
						if(VerificarDniInvalido(request.getParameter("txfDocumentoPersona"))) {
							request.getSession().setAttribute("dniException", "DNI Inválido");
							flag = false;							
						} else {
							Perso.setDni(Integer.parseInt(request.getParameter("txfDocumentoPersona")));
						}
					} else {
						flag = false;
					}
				}
				// PAIS PERSONA
				if(Integer.parseInt(request.getParameter("slcPaisPersona"))!=0) {
					Perso.setNacionalidad(paisdao.ObtenerObjeto((Integer.parseInt(request.getParameter("slcPaisPersona")))));
				}else {
					flag = false;
				}
				
				// DIRECCION PERSONA
					// LOCALIDAD
				if(Integer.parseInt(request.getParameter("slcLocPersona"))!=0) {
					Perso.getDirecc().setLoc(locdao.ObtenerObjeto(Integer.parseInt(request.getParameter("slcLocPersona"))));
				}else {
					flag = false;
				}
					// DIRECCION
				if(!request.getParameter("txfDireccionPersona").isEmpty()) {
					Perso.getDirecc().setCalleYNum(request.getParameter("txfDireccionPersona").toString());
				}else {
					flag = false;
				}
				
				// SEXO
				if(Integer.parseInt(request.getParameter("slcSexoPersona"))!=0) {
					Perso.setSexo(sexdao.ObtenerObjeto(Integer.parseInt(request.getParameter("slcSexoPersona"))));
				}else {
					flag = false;
				}
				
				// EMAIL PERSONA
				if(!request.getParameter("txfEmailPersona").isEmpty()) {
					Perso.setEmail(request.getParameter("txfEmailPersona").toString());
				}else {
					flag = false;
				}
				// TELEFONO
				if(!request.getParameter("txfTelefonoPersona").isEmpty()) {
					Perso.setTelefono(request.getParameter("txfTelefonoPersona").toString());
				}else {
					flag = false;
				}
				
				// FECHA DE NACIMIENTO
				if(!request.getParameter("txfFechaNacPersona").isEmpty()) {
					Perso.setFecha_nacimiento(Date.valueOf(request.getParameter("txfFechaNacPersona").toString()));
				}else {
					flag = false;
				}
		if(!flag) return null;
		return Perso;
	}

	public static boolean VerificarDniInvalido(String dni) throws DniException
	{
		boolean result = false;
		if(!dni.matches("[0-9]+"))
		{
			result = true;
		}
		
		if(result) {			
			throw new DniException();
		}

		return result;
		
	}
	
	
	//CARGA DATOS DE USUARIO
	
	private Usuario carga_datos_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario User = new Usuario();
		TipoNegocio tipneg = new TipoNegocioImpl(); // REEMPLAZAR POR NEGOCIO
				
		// VALIDACION USUARIO VACIO?
		
		if(!request.getParameter("txfUsername").isEmpty()) {
			User.setUser(request.getParameter("txfUsername").toString());
		}else {
			request.setAttribute("Mensaje", "ERROR");
			return null;
		}
		
		// VALIDACION PASSWORD
		try {
				if(!request.getParameter("txfPassword1").isEmpty() && !request.getParameter("txfPassword2").isEmpty()) {
				if(request.getParameter("txfPassword1").toString().equals(request.getParameter("txfPassword2").toString())) {
					User.setPassword(request.getParameter("txfPassword1").toString());
				}else {
					request.setAttribute("Mensaje", "PWDERROR");
					return null;
				}
			}else {
				request.setAttribute("Mensaje", "ERROR");
				return null;
			}
		}
		catch (Exception e) {
			System.out.println("Error");
		}
		
		// TIPO DE USUARIO
		if(Integer.parseInt(request.getParameter("slcTipoUsuario"))!=0) {
			User.setTipo(tipneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcTipoUsuario"))));
		}else {
			request.setAttribute("Mensaje", "ERROR");
			return null;
		}
		
		return User;
	}
	
	// AGREGA NUEVO USUARIO
	private boolean agregar_nuevo_usuario (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, DniException {
		
		Administrador Admin = new Administrador();
		Usuario User = null;
		Persona Perso= null;
		
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		AdministradorNegocio adminneg = new AdministradorNegocioImpl();
		
		boolean flag = true;
		
		// CARGO DATOS DE PERSONA
		Perso = carga_datos_persona(request, response);
		if(Perso == null) {
			request.setAttribute("Mensaje", "ERROR");
			return false;
		}
		
		// CARGO DATOS DEL USUARIO
		User = carga_datos_usuario(request, response);
		if(User == null) return false;
		
		// VALIDA QUE ADMIN NO EXISTA
		if(adminneg.existeAdmin(Perso.getDni())) { // SI ADMIN EXISTE - REGRESA SIN AGREGAR NADA
			request.setAttribute("existeAdmin", true);
			return false;
		}

		// VALIDA QUE PERSONA Y USUARIO NO EXISTAN
		if(perneg.existePersona(Perso.getDni()) || userneg.existeUsuario(User.getUser())!=-1) {
			if(perneg.existePersona(Perso.getDni())) {
				request.setAttribute("existePersona", true);
			}
			if (userneg.existeUsuario(User.getUser())!=-1) {
				request.setAttribute("existeUsuario", true);
			}
			return false;
		} else if (!perneg.agregarPersona(Perso)) {
			
			return false;
		} else if (!userneg.agregarUsuario(User)) {
			//perneg.eliminarPersona(Perso.getDni()); // SI NO SE PUEDE AGREGAR EL USUARIO, SE ELIMINA LA PERSONA - A REVISAR COMPORTAMIENTO ADECUADO
			return false;
		}
		
		// ESTADO DE ADMIN
		if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))==1) {
				Admin.setEstado(true);
			}else {
				Admin.setEstado(false);
			}
		}else {
			flag = false;
		}
		
		User.setIdUsuario(userneg.existeUsuario(User.getUser()));
		if(flag) {
			Admin.setUsuario(User);
			Admin.setDni(Perso.getDni());
			return adminneg.agregarAdmin(Admin);
		}
		return false;
	}

	
	private boolean modificar_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, DniException {
		Administrador Admin = null;
		Usuario User = null;
		Persona Perso= null;
		
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		AdministradorNegocio adminneg = new AdministradorNegocioImpl();

		Admin = adminneg.ObtenerObjeto(Integer.parseInt(request.getParameter("adminId").toString()));
		if(Admin == null) {return false;}
		
		Perso = carga_datos_persona(request, response);
		if(Perso == null) {return false;}
		Perso.setDni(Admin.getDni());
		
		User = carga_datos_usuario(request, response);
		if(User == null) {return false;}
		User.setIdUsuario(Admin.getUsuario().getIdUsuario());
		
		// ESTADO DE ADMIN
		if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))==1) {
				Admin.setEstado(true);
			}else {
				Admin.setEstado(false);
			}
		}
		
		if(!perneg.Modificar(Perso)) {return false;}
		if(!userneg.Modificar(User)) {return false;}
		if(!adminneg.Modificar(Admin)) {return false;}
		else {
		return true;}
		
	}
	
	// CAMBIA ESTADO A 0 DEL ADMINISTRADOR
	private boolean eliminar_usuario (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministradorNegocio adminneg = new AdministradorNegocioImpl();
		Administrador admin = new Administrador();
		
		admin = adminneg.buscar_usuario(Integer.parseInt(request.getParameter("radSelect")));
		if(admin != null) {
			return adminneg.bajaAdmin(admin);
		}
		
		return false;
	}

	// CARGA DATOS COMPLETOS DE ADMIN
	private boolean carga_datos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministradorNegocio adminneg = new AdministradorNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		Administrador admin = new Administrador();
		
		user = userneg.ObtenerObjeto(Integer.parseInt(request.getParameter("radSelect")));
		if (user!=null) {
			admin = adminneg.buscar_usuario(user.getIdUsuario());
			if (admin!=null) {
				PersonaNegocio personeg = new PersonaNegocioImpl(); 
				Persona perso = personeg.buscarPersona(admin.getDni());
				admin.setNombre(perso.getNombre());
				admin.setApellido(perso.getApellido());
				admin.setDirecc(perso.getDirecc());
				admin.setEmail(perso.getEmail());
				admin.setFecha_nacimiento(perso.getFecha_nacimiento());
				admin.setNacionalidad(perso.getNacionalidad());
				admin.setSexo(perso.getSexo());
				admin.setTelefono(perso.getTelefono());
				admin.setUsuario(user);
				request.setAttribute("admin", admin);
				return true;
			}
		
		}
		return false;
	}

}
