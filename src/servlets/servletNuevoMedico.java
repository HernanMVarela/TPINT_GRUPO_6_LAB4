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

import Excepciones.UsernameException;
import entidad.Direccion;
import entidad.Especialidad;
import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Tipo;
import entidad.Usuario;
import negocio.EspecialidadNegocio;
import negocio.LocalidadNegocio;
import negocio.MedicoNegocio;
import negocio.PaisNegocio;
import negocio.ProvinciaNegocio;
import negocio.SexoNegocio;
import negocio.TipoNegocio;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.SexoNegocioImpl;
import negocioImpl.TipoNegocioImpl;

@WebServlet("/servletNuevoMedico")
public class servletNuevoMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletNuevoMedico() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "/NuevoMedico.jsp";
		boolean aux = true;
		request.setAttribute("medic", null);
		request.setAttribute("Mensaje", null);		
		
		// EVENTO BOTON MODIFICAR USUARIO
		if(request.getParameter("btnModificarMedico")!=null) {
			if(request.getParameter("radSelect")!=null){
				if(carga_datos(request, response)) {
					// DATOS DEL ELEMENTO SELECCIONADO OBTENIDOS
				}else {
					request.setAttribute("medic", null);	
					request.setAttribute("Mensaje", "ERROR");
				}
			}else {
				request.setAttribute("Mensaje", "SELECT");
				aux = false;
				redirect = "servletMedicos"; 
			}
		}
		
		
		if(request.getParameter("btnEliminarMedico")!=null) {
			if(request.getParameter("radSelect")!=null){
				if(eliminar_medico(request, response)) {
					request.setAttribute("Mensaje", "ELIMOK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}
				aux = false;
				redirect = "servletMedicos";
			}else {
				request.setAttribute("Mensaje", "SELECT");
			}
		}
		
		// EVENTO BOTON AGREGAR NUEVO USUARIO
		if(request.getParameter("btnAceptar")!=null) {
			if (request.getParameter("medicId")!=null){
				if(modificar_medico(request, response)) {
					request.setAttribute("Mensaje", "MODIOK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}		
			}else {
				if(agregar_medico(request, response)){
					request.setAttribute("Mensaje", "AGROK");
				}else {
					request.setAttribute("Mensaje", "ERROR");
				}
			}
			redirect = "servletMedicos";
			aux = false;
		}
		
		if(aux) {
			request.setAttribute("listaesp", listarEspecialidades());
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

	private List<Especialidad> listarEspecialidades() {
		List<Especialidad> listaDeEsp = new ArrayList<Especialidad>();
		EspecialidadNegocio espneg = new EspecialidadNegocioImpl();
		listaDeEsp = espneg.ListarTodo();
		return listaDeEsp;
	}
	
	private List<Provincia> create_provincia_list(){
		List<Provincia> prov = new ArrayList<Provincia>();
		ProvinciaNegocio provneg = new ProvinciaNegocioImpl();
		prov = provneg.ListarTodo();
		return prov;
	}
	
	private List<Localidad> create_localidad_list(){
		List<Localidad> loc = new ArrayList<Localidad>();
		LocalidadNegocio locneg = new LocalidadNegocioImpl();
		loc = locneg.ListarTodo();
		return loc;
	}
	
	private List<Sexo> create_sexo_list(){
		List<Sexo> sex = new ArrayList<Sexo>();
		SexoNegocio sexneg = new SexoNegocioImpl();		
		sex = sexneg.ListarTodo();
		return sex;
	}
	
	private List<Tipo> create_tipo_list(){
		List<Tipo> tipo = new ArrayList<Tipo>();
		TipoNegocio tipneg = new TipoNegocioImpl();		
		tipo = tipneg.ListarTodo();
		return tipo;
	}
	
	private List<Pais> create_pais_list(){
		List<Pais> pais = new ArrayList<Pais>();
		PaisNegocio paineg = new PaisNegocioImpl();		
		pais = paineg.ListarTodo();
		return pais;
	}
	
	private Persona carga_datos_persona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona Perso = new Persona();
		Perso.setDirecc(new Direccion());
		
		LocalidadNegocio locneg = new LocalidadNegocioImpl();
		PaisNegocio paineg = new PaisNegocioImpl();	
		SexoNegocio sexneg = new SexoNegocioImpl();
		
		boolean flag=true;
		
		// NOMBRE PERSONA
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
				if(request.getParameter("medicId")==null) {
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
				if(Integer.parseInt(request.getParameter("slcLocalidad"))!=0) {
					Perso.getDirecc().setLoc(locneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcLocalidad"))));
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
				if(!request.getParameter("txfFechaNacPersona").isEmpty()) {
					Perso.setFecha_nacimiento(Date.valueOf(request.getParameter("txfFechaNacPersona").toString()));
				}else {
					flag = false;
				}
		if(!flag) return null;
		return Perso;
	}
	
	private Usuario carga_datos_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario User = new Usuario();
		TipoNegocio tipneg = new TipoNegocioImpl();
		
		boolean flag=true;
		if(!request.getParameter("txfUsername").isEmpty()) {
			try {
				Usuario.VerificarDniInvalido(request.getParameter("txfUsername").toString());
			} catch (UsernameException e) {
				System.out.println(e.getMessage());
				return null;
			}
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
			User.setTipo(tipneg.ObtenerObjeto(Integer.parseInt(request.getParameter("slcTipoUsuario"))));
		}else {
			flag = false;
		}
		
		if(!flag) return null;
		return User;
	}
	
	private ArrayList<Horario> carga_horarios_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Horario aux = new Horario();
		ArrayList<Horario> lista = new ArrayList<Horario>();		
		boolean flag=true;
		
		aux.setDia(1);
		if(!request.getParameter("TIDomingo").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIDomingo")));
		}else {flag = false;}
		if(!request.getParameter("TEDomingo").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEDomingo")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(2);
		if(!request.getParameter("TILunes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TILunes")));
		}else {flag = false;}
		if(!request.getParameter("TELunes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TELunes")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(3);
		if(!request.getParameter("TIMartes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIMartes")));
		}else {flag = false;}
		if(!request.getParameter("TEMartes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEMartes")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(4);
		if(!request.getParameter("TIMiercoles").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIMiercoles")));
		}else {flag = false;}
		if(!request.getParameter("TEMiercoles").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEMiercoles")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(5);
		if(!request.getParameter("TIJueves").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIJueves")));
		}else {flag = false;}
		if(!request.getParameter("TEJueves").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEJueves")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(6);
		if(!request.getParameter("TIViernes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIViernes")));
		}else {flag = false;}
		if(!request.getParameter("TEViernes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEViernes")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		aux = new Horario();
		
		aux.setDia(7);
		if(!request.getParameter("TISabado").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TISabado")));
		}else {flag = false;}
		if(!request.getParameter("TESabado").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TESabado")));
		}else {flag = false;}
		if(aux.getHoraDesde()!=0 && aux.getHoraHasta()!=0) {lista.add(aux);}
		
		
		// EL HORARIO DE INICIO DEBE SER MENOR QUE EL FINAL
		for (Horario horario : lista) {
			if(horario.getHoraDesde()>horario.getHoraHasta()) {
				flag=false;
			}
		}
		
		if(flag) {return lista;}
		else {return null;}
	}
	
	private boolean agregar_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Medico Medic = new Medico();
		Usuario User = null;
		Persona Perso= null;

		MedicoNegocio medneg = new MedicoNegocioImpl();
		
		boolean flag = true;
		
		// CARGO DATOS DE PERSONA
		Perso = carga_datos_persona(request, response);
		if(Perso == null) {
			return false;
		}
		// CARGO DATOS DEL USUARIO
		User = carga_datos_usuario(request, response);
		if(User == null) {
			return false;
		}

		// HORARIOS DEL MEDICO
		ArrayList<Horario> horas = carga_horarios_medico(request, response);

		// VALIDACION DE HORARIO CARGADO CORRECTAMENTE
		if(horas==null) {
			return false;
		}
		
		Medic = nuevo_medico_datos(Perso, User, horas);

		// ESTADO DE CUENTA
		if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))==1) {
				Medic.setEstado(true);
			}else {
				Medic.setEstado(false);
			}
		}else {
			flag = false;
		}

		// ESPECIALIDAD DEL MEDICO
		Medic.setEspecialidad(new Especialidad());
		if(Integer.parseInt(request.getParameter("slcEsp"))!=0) {
			Medic.getEspecialidad().setIdEspecialidad(Integer.parseInt(request.getParameter("slcEsp")));
		}else {
			flag = false;
		}
		if(!flag) {return false;}
		
		return medneg.agregarMedico(Medic);
	}
		
	private Medico nuevo_medico_datos(Persona perso, Usuario user, ArrayList<Horario> horas) {
		Medico nuevo = new Medico();
		
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
		nuevo.setUsuario(user);
		nuevo.setHorarios(horas);
		
		return nuevo;
	}

	// CAMBIA ESTADO A 0 DEL MEDICO
	private boolean eliminar_medico (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicoNegocio medneg = new MedicoNegocioImpl();
		Medico medic = new Medico();
		
		medic = medneg.buscar_id(Integer.parseInt(request.getParameter("radSelect")));
		if(medic != null) {
			return medneg.bajaMedico(medic);
		}
		
		return false;
	}
	
	private boolean carga_datos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Medico medic = new Medico();
		
		MedicoNegocio medneg = new MedicoNegocioImpl();
		medic = medneg.buscar_id(Integer.parseInt(request.getParameter("radSelect")));
		if (medic!=null) {
			request.setAttribute("medic", medic);
			return true;
		}
		return false;
	}
	
	private boolean modificar_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Medico Medic = null;
		Usuario User = null;
		Persona Perso= null;
		ArrayList<Horario> horas = carga_horarios_medico(request, response);
		
		MedicoNegocio medicneg = new MedicoNegocioImpl();

		Medic = medicneg.buscar_id(Integer.parseInt(request.getParameter("medicId").toString()));
		
		if(Medic == null) {return false;}
		int id = Medic.getIdMedico();
		
		Perso = carga_datos_persona(request, response);
		if(Perso == null) {return false;}
		Perso.setDni(Medic.getDni());
		User = carga_datos_usuario(request, response);
		if(User == null) {return false;}
		
		User.setIdUsuario(Medic.getUsuario().getIdUsuario());	
		
		Medic = nuevo_medico_datos(Perso, User, horas);		
		Medic.setIdMedico(id);
		// ESTADO DE CUENTA
		if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEstadoCuenta"))==1) {
				Medic.setEstado(true);
			}else {
				Medic.setEstado(false);
			}
		}
		
		// ESPECIALIDAD DEL MEDICO
		Medic.setEspecialidad(new Especialidad());
		if(Integer.parseInt(request.getParameter("slcEsp"))!=0) {
			Medic.getEspecialidad().setIdEspecialidad(Integer.parseInt(request.getParameter("slcEsp")));
		}
		
		return medicneg.Modificar(Medic);
	}
	
}
