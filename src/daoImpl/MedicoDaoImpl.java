package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.MedicoDao;
import entidad.Medico;

public class MedicoDaoImpl implements MedicoDao{
	
	//Atributos
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos M inner join datospersonales DP on M.DNI = DP.DNI";
	private String alinsertar = "INSERT INTO bdtp_integrador.medicos (DNI,IDEspecialidad,IDUsuario,Estado) VALUES (?,?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.medicos SET DNI = ?, IDEspecialidad = ?, IDUsuario = ?, Estado = ? WHERE IDMedico = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.medicos WHERE IDMedico = ?";
	private String proxid = "SELECT MAX(m.IDMedico) FROM bdtp_integrador.medicos m order by s.IDMedico";
	
	
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
				
				Medico temporal = new Medico();
				temporal.setApellido(resultSet.getString(leerTodo));
				
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
//			Stat.setInt(1,medico.getDatoPersonal().getDni());
//			Stat.setString(2,medico.getDatoPersonal().getNombre());
//			Stat.setString(3,medico.getDatoPersonal().getApellido());
//			//Aca se guarda el ID
//			Stat.setInt(4,medico.getDatoPersonal().getNacionalidad().getIdNacionalidad());
//			Stat.setString(5,medico.getDatoPersonal().getDireccion());
//			Stat.setInt(6,medico.getDatoPersonal().getSexo().getIdSexo());
//			Stat.setInt(7,medico.getDatoPersonal().getLocalidad().getIdLocalidad());
//			Stat.setString(8,medico.getDatoPersonal().getEmail());
//			Stat.setString(9,medico.getDatoPersonal().getTelefono());
//			Stat.setDate(10,medico.getDatoPersonal().getFecha_nacimiento());
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
			
//			statement.setInt(1,medico.getDatoPersonal().getDni());
//			statement.setString(2,medico.getDatoPersonal().getNombre());
//			statement.setString(3,medico.getDatoPersonal().getApellido());
//			//Aca se guarda el ID
//			statement.setInt(4,medico.getDatoPersonal().getNacionalidad().getIdNacionalidad());
//			statement.setString(5,medico.getDatoPersonal().getDireccion());
//			statement.setInt(6,medico.getDatoPersonal().getSexo().getIdSexo());
//			statement.setInt(7,medico.getDatoPersonal().getLocalidad().getIdLocalidad());
//			statement.setString(8,medico.getDatoPersonal().getEmail());
//			statement.setString(9,medico.getDatoPersonal().getTelefono());
//			statement.setDate(10,medico.getDatoPersonal().getFecha_nacimiento());
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
//						result = new Medico(			
//						resultSet.getInt("Medico.getDatoPersonal().getDni()"),
//						resultSet.getString("Medico.getDatoPersonal().getNombre()"),
//						resultSet.getInt("Medico.getDatoPersonal().getApellido()"),
//						resultSet.getInt("Medico.getDatoPersonal().getNacionalidad().getIdNacionalidad()"),
//						resultSet.getInt("Medico.getDatoPersonal().getDireccion()"),
//						resultSet.getInt("Medico.getDatoPersonal().getSexo().getIdSexo()"),
//						resultSet.getInt("Medico.getDatoPersonal().getLocalidad().getIdLocalidad()"),
//						resultSet.getInt("Medico.getDatoPersonal().getEmail()"),
//						resultSet.getInt("Medico.getDatoPersonal().getTelefono()"),
//						resultSet.getInt("Medico.getDatoPersonal().getFecha_nacimiento()"),
//						resultSet.getInt("Medico.getEspecialidad().getIdEspecialidad()"),
//						resultSet.getInt("Medico.getUsuario().getIdUsuario()"),
//						resultSet.getInt("Medico.getDatoPersonal().getSexo().getIdSexo()"),
//						resultSet.getInt("Medico.isEstado()")
//						);
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
