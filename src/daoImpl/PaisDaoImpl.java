package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.PaisDao;
import entidad.Pais;

public class PaisDaoImpl implements PaisDao {

	private String listarTodo = "SELECT * FROM bdtp_integrador.paises";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.paises WHERE IDPais = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.paises WHERE IDPais = ?";
	private String modificar = "UPDATE bdtp_integrador.paises SET ISO = ?, Nombre = ? WHERE IDPais = ?";
	private String agregar = "INSERT INTO bdtp_integrador.paises (ISO, Nombre) VALUES (?,?)";
	
	@Override
	public boolean Agregar(Pais pais) {
		
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
			statement.setString(1, pais.getIso());
			statement.setString(2, pais.getNombre());
			
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
	public ArrayList<Pais> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Pais> result = new ArrayList<Pais>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				Pais temporal = new Pais(
						resultSet.getInt("IDPais"), 
						resultSet.getString("ISO"), 
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
	public Pais ObtenerObjeto(int idNacionalidad) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Pais result = new Pais();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idNacionalidad);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				result = new Pais(
						resultSet.getInt("IDPais"), 
						resultSet.getString("ISO"), 
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
	public boolean Eliminar(int idNacionalidad) {
		
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
			statement.setInt(1, idNacionalidad);
			
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
	public boolean Modificar(Pais pais) {
		
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
			
			statement.setString(1, pais.getIso());
			statement.setString(2, pais.getNombre());
			statement.setInt(3, pais.getIdNacionalidad());
			
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
