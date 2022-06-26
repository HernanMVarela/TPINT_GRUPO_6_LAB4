package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadDao;
import dao.HorariosDao;
import dao.LocalidadDao;
import dao.MedicoDao;
import dao.PaisDao;
import dao.SexoDao;
import dao.UsuarioDao;
import entidad.Administrador;
import entidad.Direccion;
import entidad.Especialidad;
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
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos M inner join personas P on M.DNI = P.DNI inner join usuarios u on u.idUsuario = m.IDUsuario";
	private String alinsertar = "INSERT INTO bdtp_integrador.medicos (DNI,IDEspecialidad,IDUsuario,Estado) VALUES (?,?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.medicos SET DNI = ?, IDEspecialidad = ?, IDUsuario = ?, Estado = ? WHERE IDMedico = ?";
	//private String modif_user = "UPDATE bdtp_integrador.medicos SET IDUsuario = ?, Estado = ? WHERE DNI = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String proxid = "SELECT MAX(m.IDMedico) FROM bdtp_integrador.medicos m order by m.IDMedico";
	private String buscaruserid = "SELECT * FROM bdtp_integrador.medicos where idUsuario = ?";
	private String buscarDNI = "SELECT * FROM bdtp_integrador.medicos where DNI = ?";
	private String bajaMedic = "UPDATE bdtp_integrador.medicos SET Estado = 0 where IDMedico = ?";
	
	
	
	@Override
	public ArrayList<Medico> ListarTodo() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
				temp.setEmail(resultSet.getString("Telefono"));
				temp.setFecha_nacimiento(resultSet.getDate("Fecha_nacimiento"));
				
				Medico temporal = new Medico(resultSet.getInt("idMedico"),temp,esp,user,resultSet.getBoolean("Estado"));
				HorariosDao horasdao = new HorariosDaoImpl();
				temporal.setHorarios(horasdao.Listar(temporal.getIdMedico()));				
				
				result.add(temporal);
				
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
			Class.forName("com.mysql.jdbc.Driver");
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
			Stat.setString(2,medico.getNombre());
			Stat.setString(3,medico.getApellido());
			//Aca se guarda el ID
			Stat.setInt(4,medico.getNacionalidad().getIdNacionalidad());
			Stat.setString(5,medico.getDirecc().getCalleYNum());
			Stat.setInt(6,medico.getSexo().getIdSexo());
			Stat.setInt(7,medico.getDirecc().getLoc().getIdLocalidad());
			Stat.setString(8,medico.getEmail());
			Stat.setString(9,medico.getTelefono());
			Stat.setDate(10,medico.getFecha_nacimiento());
			Stat.setInt(11,medico.getEspecialidad().getIdEspecialidad());
			Stat.setInt(12,medico.getUsuario().getIdUsuario());
			Stat.setBoolean(13,medico.isEstado());
			
			
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
			statement.setString(2,medico.getNombre());
			statement.setString(3,medico.getApellido());
			//Aca se guarda el ID
			statement.setInt(4,medico.getNacionalidad().getIdNacionalidad());
			statement.setString(5,medico.getDirecc().getCalleYNum());
			statement.setInt(6,medico.getSexo().getIdSexo());
			statement.setInt(7,medico.getDirecc().getLoc().getIdLocalidad());
			statement.setString(8,medico.getEmail());
			statement.setString(9,medico.getTelefono());
			statement.setDate(10,medico.getFecha_nacimiento());
			statement.setInt(11,medico.getEspecialidad().getIdEspecialidad());
			statement.setInt(12,medico.getUsuario().getIdUsuario());
			statement.setBoolean(13,medico.isEstado());
			
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
				HorariosDao horasdao = new HorariosDaoImpl();
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
			Class.forName("com.mysql.jdbc.Driver");
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
            	System.out.println("Seleccionado buscar user: " + idUser);
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
			System.out.println("Seleccionado baja medico: " + idMedic);
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
	
/*
	@Override
	public boolean Modif_user(Medico medico) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		boolean result = false;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		Persona perso = new Persona();
		Usuario user = new Usuario();
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();	
		
		try {
			
			perso.setNombre(medico.getNombre());
			perso.setApellido(medico.getApellido());
			perso.setDni(medico.getDni());
			perso.setEmail(medico.getEmail());
			perso.setFecha_nacimiento(medico.getFecha_nacimiento());
			perso.setNacionalidad(medico.getNacionalidad());
			perso.setSexo(medico.getSexo());
			perso.setTelefono(medico.getTelefono());
			
			user.setIdUsuario(medico.getUsuario().getIdUsuario());
			user.setUser(medico.getUsuario().getUser());
			user.setPassword(medico.getUsuario().getPassword());
			user.setTipo(medico.getUsuario().getTipo());
			
			if(!perneg.Modificar(perso)) {return false;}
			if(!userneg.Modificar(user)) {return false;}
			
			statement = conexion.getSQLConexion().prepareStatement(modif_user);
			
			statement.setInt(1,medico.getUsuario().getIdUsuario());
			statement.setInt(2,medico.isEstado() ? 1 : 0);
			statement.setInt(3,medico.getDni());
			
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
	}*/

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
				HorariosDao horasdao = new HorariosDaoImpl();
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
	
	
}
