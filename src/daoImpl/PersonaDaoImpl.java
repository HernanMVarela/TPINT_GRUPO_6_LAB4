package daoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.LocalidadDao;
import dao.PaisDao;
import dao.PersonaDao;
import dao.SexoDao;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Sexo;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {

	private String listarTodo = "SELECT * FROM bdtp_integrador.Personas";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.Personas WHERE DNI = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.Personas WHERE DNI = ?";
	private String modificar = "UPDATE bdtp_integrador.Personas SET Nombre = ?, Apellido = ?, IDNacionalidad = ?, Direccion = ?, IDSexo = ?, IDLocalidad = ?, Email = ?, Telefono = ?, Fecha_nacimiento = ? WHERE DNI = ?";
	private String agregar = "INSERT INTO bdtp_integrador.Personas (DNI, Nombre, Apellido, IDNacionalidad, Direccion, IDSexo, IDLocalidad, Email, Telefono, Fecha_nacimiento) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	public boolean Agregar(Persona persona) {
		
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
						
			statement.setInt(1, persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
			statement.setInt(4, persona.getNacionalidad().getIdNacionalidad());
			statement.setString(5, persona.getDirecc().getCalleYNum());
			statement.setInt(6, persona.getSexo().getIdSexo());
			statement.setInt(7, persona.getDirecc().getLoc().getIdLocalidad());
			statement.setString(8, persona.getEmail());
			statement.setString(9, persona.getTelefono());
			statement.setDate(10, persona.getFecha_nacimiento());
						
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
	public ArrayList<Persona> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Persona> result = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				PaisDao daoPais = new PaisDaoImpl();
				Pais pais = new Pais();
				pais = daoPais.ObtenerObjeto(resultSet.getInt("IDNacionalidad"));
				
				SexoDao daoSexo = new SexoDaoImpl();
				Sexo sexo = new Sexo();
				sexo = daoSexo.ObtenerObjeto(resultSet.getInt("IDSexo"));
								
				Direccion direc = new Direccion();
				direc.setCalleYNum(resultSet.getString("Direccion"));
				
				LocalidadDao daoLocalidad = new LocalidadDaoImpl();
				Localidad localidad = new Localidad();
				localidad = daoLocalidad.ObtenerObjeto(resultSet.getInt("IDLocalidad"));
				direc.setLoc(localidad);
				
				Persona temporal = new Persona(
						resultSet.getInt("DNI"), 
						resultSet.getString("Nombre"),
						resultSet.getString("Apellido"),
						pais,
						direc,
						sexo,
						resultSet.getString("Email"),
						resultSet.getString("Telefono"),
						resultSet.getDate("Fecha_nacimiento")						
						);
				result.add(temporal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public Persona ObtenerObjeto(int dni) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Persona result = new Persona();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, dni);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				PaisDao daoPais = new PaisDaoImpl();
				Pais pais = new Pais();
				pais = daoPais.ObtenerObjeto(resultSet.getInt("IDNacionalidad"));
				
				SexoDao daoSexo = new SexoDaoImpl();
				Sexo sexo = new Sexo();
				sexo = daoSexo.ObtenerObjeto(resultSet.getInt("IDSexo"));
				
				Direccion direc = new Direccion();
				direc.setCalleYNum(resultSet.getString("Direccion"));
				
				LocalidadDao daoLocalidad = new LocalidadDaoImpl();
				Localidad localidad = new Localidad();
				localidad = daoLocalidad.ObtenerObjeto(resultSet.getInt("IDLocalidad"));
				direc.setLoc(localidad);
								
				result = new Persona(
						resultSet.getInt("DNI"), 
						resultSet.getString("Nombre"),
						resultSet.getString("Apellido"),
						pais,
						direc,
						sexo,
						resultSet.getString("Email"),
						resultSet.getString("Telefono"),
						resultSet.getDate("Fecha_nacimiento")						
						);
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public boolean Eliminar(int dni) {
		
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
			statement.setInt(1, dni);
			
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
	public boolean Modificar(Persona persona) {
		
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
			
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setInt(3, persona.getNacionalidad().getIdNacionalidad());
			statement.setString(4, persona.getDirecc().getCalleYNum());
			statement.setInt(5, persona.getSexo().getIdSexo());
			statement.setInt(6, persona.getDirecc().getLoc().getIdLocalidad());
			statement.setString(7, persona.getEmail());
			statement.setString(8, persona.getTelefono());
			statement.setDate(9, persona.getFecha_nacimiento());
			statement.setInt(10, persona.getDni());
			
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
