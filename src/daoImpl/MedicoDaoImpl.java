package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadDao;
import dao.HorarioDao;
import dao.LocalidadDao;
import dao.MedicoDao;
import dao.PaisDao;
import dao.PersonaDao;
import dao.SexoDao;
import dao.UsuarioDao;
import entidad.Administrador;
import entidad.Direccion;
import entidad.Especialidad;
import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Pais;
import entidad.Persona;
import entidad.Sexo;
import entidad.Usuario;
import negocio.PersonaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

public class MedicoDaoImpl implements MedicoDao{
	
	//Atributos
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos";
	private String alinsertar = "INSERT INTO bdtp_integrador.medicos (DNI,IDEspecialidad,IDUsuario,Estado) VALUES (?,?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.medicos SET DNI = ?, IDEspecialidad = ?, IDUsuario = ?, Estado = ? WHERE IDMedico = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String proxid = "SELECT MAX(m.IDMedico) FROM bdtp_integrador.medicos m order by m.IDMedico";
	private String buscaruserid = "SELECT * FROM bdtp_integrador.medicos where idUsuario = ?";
	private String buscarDNI = "SELECT * FROM bdtp_integrador.medicos M inner join personas P on M.DNI = P.DNI inner join usuarios u on u.idUsuario = m.IDUsuario where M.DNI = ?";
	private String bajaMedic = "UPDATE bdtp_integrador.medicos SET Estado = 0 where IDMedico = ?";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String contarpac = "SELECT COUNT(DNI) FROM bdtp_integrador.medicos WHERE ESTADO = 1";
	private String mejormedico = "select count(idMedico) as c, idMedico from bdtp_integrador.turnos where IDEstado=4 group by idMedico ORDER BY c DESC LIMIT 1;";
	
	@Override
	public ArrayList<Medico> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	
		ArrayList<Medico> result = new ArrayList<Medico>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{
			
			statement = conexion.getSQLConexion().prepareStatement(leerTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Medico Medic = new Medico();
				
				Persona Perso = new Persona();
				PersonaDao perdao = new PersonaDaoImpl();
				Perso = perdao.ObtenerObjeto(resultSet.getInt("DNI"));
				
				Especialidad Espe = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				Espe = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
				Usuario User = new Usuario();
				UsuarioDao userdao = new UsuarioDaoImpl();
				User = userdao.ObtenerObjeto(resultSet.getInt("IDUsuario"));
				
				// DATOS DE PERSONA
				Medic.setDni(resultSet.getInt("DNI"));			
				Medic.setNombre(Perso.getNombre());
				Medic.setApellido(Perso.getApellido());
				Medic.setDirecc(Perso.getDirecc());
				Medic.setEmail(Perso.getEmail());
				Medic.setEstado(resultSet.getBoolean("Estado"));
				Medic.setFecha_nacimiento(Perso.getFecha_nacimiento());
				Medic.setNacionalidad(Perso.getNacionalidad());
				Medic.setSexo(Perso.getSexo());
				Medic.setTelefono(Perso.getTelefono());
				
				// DATOS DE MEDICO
				Medic.setIdMedico(resultSet.getInt("IDMedico"));
				Medic.setEspecialidad(Espe);
				Medic.setUsuario(User);
				
				HorarioDao horasdao = new HorarioDaoImpl();
				Medic.setHorarios(horasdao.Listar(Medic.getIdMedico()));				
				
				result.add(Medic);
				
			}
			//connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
			
	}

	@Override
	public boolean Agregar(Medico medico) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		PreparedStatement Stat;
		Connection conex = Conexion.getConexion().getSQLConexion();
		
		boolean InsertState = false;
		
		try {
			Stat = conex.prepareStatement(alinsertar);
			Stat.setInt(1,medico.getDni());
			Stat.setInt(2,medico.getEspecialidad().getIdEspecialidad());
			Stat.setInt(3,medico.getUsuario().getIdUsuario());
			Stat.setBoolean(4,medico.isEstado());

			if(Stat.executeUpdate()>0) {
				conex.commit();
				InsertState=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conex.rollback();
			}catch(Exception e2) {
				e.printStackTrace();
			}
		}
		return InsertState;
	}

	@Override
	public boolean Modificar(Medico medico) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		boolean result = false;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(modificar);
			
			statement.setInt(1,medico.getDni());
			statement.setInt(2,medico.getEspecialidad().getIdEspecialidad());
			statement.setInt(3,medico.getUsuario().getIdUsuario());
			statement.setInt(4,medico.isEstado() ? 1 : 0);
			statement.setInt(5,medico.getIdMedico());

			if(statement.executeUpdate() > 0) {
				conexion.getSQLConexion().commit();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.getSQLConexion().rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean Eliminar(int idMedico) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		boolean result = false;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(eliminar);
			
			statement.setInt(1, idMedico);
			
			if(statement.executeUpdate() > 0) {
				conexion.getSQLConexion().commit();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.getSQLConexion().rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Medico Buscar(int idMedico) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Medico result = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(buscar);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){

				Persona Perso = new Persona();
				PersonaDao perdao = new PersonaDaoImpl();
				Perso = perdao.ObtenerObjeto(resultSet.getInt("DNI"));
				
				Especialidad Espe = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				Espe = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
				Usuario User = new Usuario();
				UsuarioDao userdao = new UsuarioDaoImpl();
				User = userdao.ObtenerObjeto(resultSet.getInt("IDUsuario"));
				
				// DATOS DE PERSONA
				result = new Medico();
				result.setDni(resultSet.getInt("DNI"));			
				result.setNombre(Perso.getNombre());
				result.setApellido(Perso.getApellido());
				result.setDirecc(Perso.getDirecc());
				result.setEmail(Perso.getEmail());
				result.setEstado(resultSet.getBoolean("Estado"));
				result.setFecha_nacimiento(Perso.getFecha_nacimiento());
				result.setNacionalidad(Perso.getNacionalidad());
				result.setSexo(Perso.getSexo());
				result.setTelefono(Perso.getTelefono());
				
				// DATOS DE MEDICO
				result.setIdMedico(idMedico);
				result.setEspecialidad(Espe);
				result.setUsuario(User);
				
				HorarioDao horasdao = new HorarioDaoImpl();
				result.setHorarios(horasdao.Listar(result.getIdMedico()));				
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public int UltimoID() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int last=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(proxid);
			rs = st.executeQuery();
			while(rs.next()) {
				last = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last;
	}

	@Override
	public Medico buscar_usuario(int idUser) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		Medico result = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try {

			statement = conexion.getSQLConexion().prepareStatement(buscaruserid);
			statement.setInt(1, idUser);
			resultSet = statement.executeQuery();

			while(resultSet.next()){
                
				UsuarioDao daoUser = new UsuarioDaoImpl();
				Usuario usuario = new Usuario();
				usuario = daoUser.ObtenerObjeto(idUser);
                
            	result = new Medico();
                result.setIdMedico(resultSet.getInt("IDMedico"));
                result.setDni(resultSet.getInt("DNI"));
                result.setEspecialidad(new Especialidad());
                result.getEspecialidad().setIdEspecialidad(resultSet.getInt("IDEspecialidad"));
                result.setUsuario(usuario);
                result.setEstado(resultSet.getInt("estado") == 1 ? true : false);

				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }

		return result;
	}

	@Override
	public boolean bajaMedico(int idMedic) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		boolean result = false;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;

		try {
			statement = conexion.getSQLConexion().prepareStatement(bajaMedic);
			statement.setInt(1, idMedic);
			if(statement.executeUpdate() > 0) {
				conexion.getSQLConexion().commit();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.getSQLConexion().rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public Medico BuscarDNI(int DNI) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Medico result = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(buscarDNI);
			statement.setInt(1, DNI);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){

				Pais pais = new Pais();
				PaisDao paisdao = new PaisDaoImpl();
				
				pais = paisdao.ObtenerObjeto(resultSet.getInt("IDPais"));
				
				Localidad loc = new Localidad();
				LocalidadDao locdao = new LocalidadDaoImpl();
				loc = locdao.ObtenerObjeto(resultSet.getInt("IDLocalidad"));
				
				Direccion direc = new Direccion();
				direc.setCalleYNum(resultSet.getString("Direccion"));
				direc.setLoc(loc);				
				
				Sexo sex = new Sexo();
				SexoDao sexdao = new SexoDaoImpl();
				sex= sexdao.ObtenerObjeto(resultSet.getInt("IDSexo"));
				
				Especialidad esp = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				esp = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
				Usuario user = new Usuario();
				UsuarioDao userdao = new UsuarioDaoImpl();
				user = userdao.ObtenerObjeto(resultSet.getInt("IDUsuario"));
				
				Persona temp = new Persona();
				
				temp.setDni(resultSet.getInt("DNI"));
				temp.setNombre(resultSet.getString("Nombre"));
				temp.setApellido(resultSet.getString("Apellido"));
				temp.setNacionalidad(pais);
				temp.setDirecc(direc);
				temp.setSexo(sex);
				temp.setEmail(resultSet.getString("Email"));
				temp.setTelefono(resultSet.getString("Telefono"));
				temp.setFecha_nacimiento(resultSet.getDate("Fecha_nacimiento"));
				
				result = new Medico(resultSet.getInt("idMedico"),temp,esp,user,resultSet.getBoolean("Estado"));
				HorarioDao horasdao = new HorarioDaoImpl();
				result.setHorarios(horasdao.Listar(result.getIdMedico()));				
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
	}

	@Override
	public boolean Modif_user(Medico medico) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeMedico(int DNI) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		boolean result=false;
		
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{
			statement = conexion.getSQLConexion().prepareStatement(buscarDNI);
			statement.setInt(1, DNI);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
	}

	@Override
	public Medico ObtenerObjeto(int idMedico) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				
				PersonaDao daoPersona = new PersonaDaoImpl();
				Persona persona = new Persona();
				persona = daoPersona.ObtenerObjeto(resultSet.getInt("DNI"));
				
				EspecialidadDao daoEspecialidad = new EspecialidadDaoImpl();
				Especialidad especialidad = new Especialidad();
				especialidad = daoEspecialidad.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
				UsuarioDao daoUsuario = new UsuarioDaoImpl();
				Usuario usuario = new Usuario();
				usuario = daoUsuario.ObtenerObjeto(resultSet.getInt("IDUsuario"));
				
				HorarioDao horasdao = new HorarioDaoImpl();
				ArrayList<Horario> horas = new ArrayList<Horario>();
				horas = horasdao.Listar(idMedico);
								
				Medico result = new Medico(
						idMedico, 
						persona,
						especialidad,
						usuario,
						resultSet.getBoolean("Estado")		
						);
				result.setHorarios(horas);
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return null;
	}

	@Override
	public int ContarMedicos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int last=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(contarpac);
			rs = st.executeQuery();
			while(rs.next()) {
				last = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last;
	}

	@Override
	public int MejorMedico() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int last=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(mejormedico);
			rs = st.executeQuery();
			while(rs.next()) {
				last = rs.getInt(2);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last;
	}

	@Override
	public int TurnosMejorMedico() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int last=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(mejormedico);
			rs = st.executeQuery();
			while(rs.next()) {
				last = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last;
	}
	
	
}
