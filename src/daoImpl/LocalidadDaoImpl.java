package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.LocalidadDao;
import dao.ProvinciaDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {

	private String listarTodo = "SELECT * FROM bdtp_integrador.localidades";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.localidades WHERE IDLocalidad = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.localidades WHERE IDLocalidad = ?";
	private String modificar = "UPDATE bdtp_integrador.localidades SET IDProvincia = ?, Nombre = ? WHERE IDLocalidad = ?";
	private String agregar = "INSERT INTO bdtp_integrador.localidades (IDProvincia, Nombre) VALUES (?,?)";
	private Provincia provincia;
	private ProvinciaDao provinciaDao;
	
	public boolean Agregar(Localidad localidad) {
		
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
			
			statement.setInt(1, localidad.getProvincia().getIdProv());
			statement.setString(2, localidad.getNombre());
			
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
	public ArrayList<Localidad> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Localidad> result = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				provinciaDao = new ProvinciaDaoImpl();
				provincia = new Provincia();
				provincia = provinciaDao.ObtenerObjeto(resultSet.getInt("IDProvincia"));
				
				Localidad temporal = new Localidad(
						resultSet.getInt("IDLocalidad"), 
						provincia,
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
	public Localidad ObtenerObjeto(int idLocalidad) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Localidad result = new Localidad();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				provinciaDao = new ProvinciaDaoImpl();
				provincia = new Provincia();
				provincia = provinciaDao.ObtenerObjeto(resultSet.getInt("IDProvincia"));
				
				result = new Localidad(
						resultSet.getInt("IDLocalidad"), 
						provincia,
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
	public boolean Eliminar(int idLocalidad) {
		
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
			statement.setInt(1, idLocalidad);
			
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
	public boolean Modificar(Localidad localidad) {
		
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
			
			statement.setInt(1, localidad.getProvincia().getIdProv());
			statement.setString(2, localidad.getNombre());
			statement.setInt(3, localidad.getIdLocalidad());
			
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
