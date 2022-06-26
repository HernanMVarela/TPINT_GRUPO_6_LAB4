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

import dao.EspecialidadDao;
import dao.HorarioDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import dao.TipoDao;
import daoImpl.EspecialidadDaoImpl;
import daoImpl.HorarioDaoImpl;
import daoImpl.LocalidadDaoImpl;
import daoImpl.PaisDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.SexoDaoImpl;
import daoImpl.TipoDaoImpl;
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
import negocio.MedicoNegocio;
import negocio.PersonaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/servletNuevoMedico")
public class servletNuevoMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletNuevoMedico() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAceptar")!=null) {
			ArrayList<Horario> lista = new ArrayList<Horario>();
			
			lista = carga_horarios_medico(request, response);
			System.out.println("LLEGA HASTA ACEPTAR");
			for (Horario horario : lista) {
				System.out.println(horario.getDia() + " - " + horario.getHoraDesde() + " | " + horario.getHoraHasta());
			}
			
			//agregar_medico(request, response);
		}
		
		request.setAttribute("listaesp", listarEspecialidades());
		request.setAttribute("listaProvincias", create_provincia_list());
		request.setAttribute("listaLocalidades", create_localidad_list());
		request.setAttribute("listasexos", create_sexo_list());
		request.setAttribute("listaTipos", create_tipo_list());
		request.setAttribute("listaPaises", create_pais_list());
		
		RequestDispatcher rd = request.getRequestDispatcher("/NuevoMedico.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<Especialidad> listarEspecialidades() {
		List<Especialidad> listaDeEsp = new ArrayList<Especialidad>();
		EspecialidadDao espDao = new EspecialidadDaoImpl();
		listaDeEsp = espDao.ListarTodo();
		return listaDeEsp;
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
	
	private Persona carga_datos_persona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona Perso = new Persona();
		Perso.setDirecc(new Direccion());
		
		LocalidadDao locdao = new LocalidadDaoImpl(); // REEMPLAZAR POR NEGOCIO
		PaisDao paisdao = new PaisDaoImpl(); // REEMPLAZAR POR NEGOCIO
		SexoDao sexdao = new SexoDaoImpl(); // REEMPLAZAR POR NEGOCIO
		
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
				if(!request.getParameter("txfDireccion").isEmpty()) {
					Perso.getDirecc().setCalleYNum(request.getParameter("txfDireccion").toString());
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
		TipoDao tipodao = new TipoDaoImpl(); // REEMPLAZAR POR NEGOCIO
		
		boolean flag=true;
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
		
		if(!flag) return null;
		return User;
	}
	
	private ArrayList<Horario> carga_horarios_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Horario aux = new Horario();
		ArrayList<Horario> lista = new ArrayList<Horario>();
		HorarioDao horaneg = new HorarioDaoImpl(); 	// REEMPLAZAR POR NEGOCIO
		
		boolean flag=true;
		
		aux.setDia(1);
		if(!request.getParameter("TIDomingo").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIDomingo")));
		}else {flag = false;}
		if(!request.getParameter("TEDomingo").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEDomingo")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(2);
		if(!request.getParameter("TILunes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TILunes")));
		}else {flag = false;}
		if(!request.getParameter("TELunes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TELunes")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(3);
		if(!request.getParameter("TIMartes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIMartes")));
		}else {flag = false;}
		if(!request.getParameter("TEMartes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEMartes")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(4);
		if(!request.getParameter("TIMiercoles").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIMiercoles")));
		}else {flag = false;}
		if(!request.getParameter("TEMiercoles").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEMiercoles")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(5);
		if(!request.getParameter("TIJueves").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIJueves")));
		}else {flag = false;}
		if(!request.getParameter("TEJueves").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEJueves")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(6);
		if(!request.getParameter("TIViernes").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TIViernes")));
		}else {flag = false;}
		if(!request.getParameter("TEViernes").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TEViernes")));
		}else {flag = false;}
		lista.add(aux);
		aux = new Horario();
		
		aux.setDia(7);
		if(!request.getParameter("TISabado").isEmpty()) {
			aux.setHoraDesde(Integer.parseInt(request.getParameter("TISabado")));
		}else {flag = false;}
		if(!request.getParameter("TESabado").isEmpty()) {
			aux.setHoraHasta(Integer.parseInt(request.getParameter("TESabado")));
		}else {flag = false;}
		lista.add(aux);
		
		if(flag) {return lista;}
		else {return null;}
	}
	
	private boolean agregar_medico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Medico Medic = new Medico();
		Medic.setEspecialidad(new Especialidad());
		Usuario User = null;
		Persona Perso= null;
		
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		MedicoNegocio medneg = new MedicoNegocioImpl();
		
		boolean flag = true;
		
		// CARGO DATOS DE PERSONA
		Perso = carga_datos_persona(request, response);
		if(Perso == null) flag = false;
		
		// CARGO DATOS DEL USUARIO
		User = carga_datos_usuario(request, response);
		if(User == null) flag = false;
		
		// VALIDA QUE MEDICO NO EXISTA
		if(medneg.existe_medico(Perso.getDni())) { // SI MEDICO EXISTE - REGRESA SIN AGREGAR NADA
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
		if(Integer.parseInt(request.getParameter("slcEsp"))!=0) {
			if(Integer.parseInt(request.getParameter("slcEsp"))==1) {
				Medic.getEspecialidad().setIdEspecialidad(Integer.parseInt(request.getParameter("slcEsp")));
			}
		}else {
			flag = false;
		}
		
		// HORARIOS DEL MEDICO
		ArrayList<Horario> horas = carga_horarios_medico(request, response);
		HorarioDao horasneg = new HorarioDaoImpl();
		
		// OBTIENE ID DE MEDICO AGREGADO
		Medico Aux = new Medico();
		Aux = medneg.buscar_dni(Medic.getDni());
		
		// CARGA HORAS DE ATENCION DEL MEDICO (NECESITA IDMEDICO)
		for (Horario horario : horas) {
			horasneg.Agregar(Aux.getIdMedico(), horario);
		}
		
		return false;
	}

}
