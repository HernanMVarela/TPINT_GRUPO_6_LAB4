package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadDao;
import dao.LocalidadDao;
import dao.MedicoDao;
import dao.PaisDao;
import dao.SexoDao;
import dao.UsuarioDao;
import entidad.Direccion;
import entidad.Especialidad;
import entidad.Localidad;
import entidad.Medico;
import entidad.Pais;
import entidad.Persona;
import entidad.Sexo;
import entidad.Usuario;

public class MedicoDaoImpl implements MedicoDao{
	
	//Atributos
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos M inner join personas P on M.DNI = P.DNI inner join usuarios u on u.idUsuario = m.IDUsuario";
	private String alinsertar = "INSERT INTO bdtp_integrador.medicos (DNI,IDEspecialidad,IDUsuario,Estado) VALUES (?,?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.medicos SET DNI = ?, IDEspecialidad = ?, IDUsuario = ?, Estado = ? WHERE IDMedico = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String proxid = "SELECT MAX(m.IDMedico) FROM bdtp_integrador.medicos m order by m.IDMedico";
	
	
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
				
				result.add(new Medico(resultSet.getInt("idMedico"),temp,esp,user,resultSet.getBoolean("Estado")));
				
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
		
		Medico result = new Medico();
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
	
	
}
