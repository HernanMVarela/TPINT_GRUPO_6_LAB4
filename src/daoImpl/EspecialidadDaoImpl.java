package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.EspecialidadDao;
import entidad.Especialidad;

public class EspecialidadDaoImpl implements EspecialidadDao {
	
	private String listarTodo = "SELECT * FROM bdtp_integrador.Especialidades";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.Especialidades WHERE IDEspecialidad = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.Especialidades WHERE IDEspecialidad = ?";
	private String modificar = "UPDATE bdtp_integrador.Especialidades SET Nombre = ? WHERE IDEspecialidad = ?";
	private String agregar = "INSERT INTO bdtp_integrador.Especialidades (Nombre) VALUES (?)";

	@Override
	public boolean Agregar(Especialidad especialidad) {
		
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
			statement = conexion.getSQLConexion().prepareStatement(agregar);
			statement.setString(1, especialidad.getNombre());
			
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
	public ArrayList<Especialidad> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Especialidad> result = new ArrayList<Especialidad>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				Especialidad temporal = new Especialidad(
						resultSet.getInt("IDEspecialidad"),
						resultSet.getString("Nombre")
						);
				result.add(temporal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
	}

	@Override
	public Especialidad ObtenerObjeto(int idEspecialidad) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Especialidad result = new Especialidad();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idEspecialidad);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				result = new Especialidad(
						resultSet.getInt("IDEspecialidad"),
						resultSet.getString("Nombre")
						);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public boolean Eliminar(int idEspecialidad) {
		
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
			statement.setInt(1, idEspecialidad);
			
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
	public boolean Modificar(Especialidad especialidad) {
		
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
			
			statement.setString(1, especialidad.getNombre());
			statement.setInt(2, especialidad.getIdEspecialidad());
			
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

}
