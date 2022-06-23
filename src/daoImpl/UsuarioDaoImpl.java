package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.UsuarioDao;
import dao.ProvinciaDao;
import dao.TipoDao;
import entidad.Usuario;
import entidad.Provincia;
import entidad.Tipo;

public class UsuarioDaoImpl implements UsuarioDao {

	private String listarTodo = "SELECT * FROM bdtp_integrador.Usuarios";
	private String obtenerObjeto = "SELECT * FROM bdtp_integrador.Usuarios WHERE IDUsuario = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.Usuarios WHERE IDUsuario = ?";
	private String modificar = "UPDATE bdtp_integrador.Usuarios SET User = ?, Password = ?, IDTipo = ? WHERE IDUsuario = ?";
	private String agregar = "INSERT INTO bdtp_integrador.Usuarios (User, Password, IDTipo) VALUES (?,?,?)";
	private String buscaruser = "SELECT * FROM bdtp_integrador.Usuarios WHERE User LIKE ?";
	private Tipo tipo;
	private TipoDao tipoDao;
	
	public boolean Agregar(Usuario usuario) {
		
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
			
			statement.setString(1, usuario.getUser());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getTipo().getIdTipo());
			
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
	public ArrayList<Usuario> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Usuario> result = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				tipoDao = new TipoDaoImpl();
				tipo = new Tipo();
				tipo = tipoDao.ObtenerObjeto(resultSet.getInt("IDTipo"));
				
				Usuario temporal = new Usuario(
						resultSet.getInt("IDUsuario"), 
						resultSet.getString("User"),
						resultSet.getString("Password"),
						tipo
						);
				result.add(temporal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public Usuario ObtenerObjeto(int idUsuario) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Usuario result = new Usuario();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idUsuario);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				tipoDao = new TipoDaoImpl();
				tipo = new Tipo();
				tipo = tipoDao.ObtenerObjeto(resultSet.getInt("IDTipo"));
				
				result = new Usuario(
						resultSet.getInt("IDUsuario"), 
						resultSet.getString("User"),
						resultSet.getString("Password"),
						tipo
						);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public boolean Eliminar(int idUsuario) {
		
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
			statement.setInt(1, idUsuario);
			
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
	public boolean Modificar(Usuario usuario) {
		
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
			
			statement.setString(1, usuario.getUser());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getTipo().getIdTipo());
			statement.setInt(4, usuario.getIdUsuario());
			
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
	public int existeuser(String username) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		int result=-1;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(buscaruser);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				result = resultSet.getInt("IDUsuario");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
	}

}
