package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.SexoDao;
import entidad.Sexo;

public class SexoDaoImpl implements SexoDao {
	
	private String listarTodo = "SELECT * FROM bdtp_integrador.sexos";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.sexos WHERE IDSexo = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.sexos WHERE IDSexo = ?";
	private String modificar = "UPDATE bdtp_integrador.sexos SET Nombre = ? WHERE IDSexo = ?";
	private String agregar = "INSERT INTO bdtp_integrador.sexos (Nombre) VALUES (?)";

	@Override
	public boolean Agregar(Sexo sexo) {
		
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
			statement.setString(1, sexo.getNombre());
			
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
	public ArrayList<Sexo> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Sexo> result = new ArrayList<Sexo>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				Sexo temporal = new Sexo(
						resultSet.getInt("IDSexo"),
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
	public Sexo ObtenerObjeto(int idSexo) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Sexo result = new Sexo();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idSexo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				result = new Sexo(
						resultSet.getInt("IDSexo"),
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
	public boolean Eliminar(int idSexo) {
		
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
			statement.setInt(1, idSexo);
			
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
	public boolean Modificar(Sexo sexo) {
		
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
			
			statement.setString(1, sexo.getNombre());
			statement.setInt(2, sexo.getIdSexo());
			
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
