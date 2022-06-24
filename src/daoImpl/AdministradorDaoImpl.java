package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.AdministradorDao;
import dao.PaisDao;
import dao.PersonaDao;
import dao.ProvinciaDao;
import dao.TipoDao;
import dao.UsuarioDao;
import entidad.Administrador;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Tipo;
import entidad.Usuario;
import negocio.PersonaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

public class AdministradorDaoImpl implements AdministradorDao {
	private String agregar = "INSERT INTO bdtp_integrador.Administradores (DNI, IDUsuario, Estado) VALUES (?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.Administradores SET DNI = ?, IDUsuario = ?, Estado = ? WHERE idAdmin = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.Administradores WHERE idAdmin = ?";
	private String obtenerObjeto = "select a.idAdmin, a.dni, a.estado, a.idUsuario from administradores a where a.idAdmin = ?";
	private String listarTodo = "select a.idAdmin, a.dni, a.estado, a.idUsuario, u.user, u.password, u.idTipo, t.nombre tipoNombre, p.nombre, p.apellido from administradores a left join usuarios u on a.idUsuario = u.idUsuario left join tipos t on u.idTipo = t.idTipo inner join personas p on p.dni = a.dni";
	private String buscardni = "SELECT * FROM bdtp_integrador.Administradores where DNI = ?";
	private String buscaruserid = "SELECT * FROM bdtp_integrador.Administradores where idUsuario = ?";
	private String bajaAdmin = "UPDATE bdtp_integrador.Administradores SET Estado = 0 where idAdmin = ?";
	
	private Usuario usuario;
	private Administrador admin;
	
	public boolean Agregar(Administrador administrador) {

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

			statement.setInt(1, administrador.getDni());
			statement.setInt(2, administrador.getUsuario().getIdUsuario());
			statement.setInt(3, administrador.isEstado() ? 1 : 0);

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
	public boolean Modificar(Administrador administrador) {

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

			statement.setInt(1, administrador.getDni());
			statement.setInt(2, administrador.getUsuario().getIdUsuario());
			statement.setInt(3, administrador.isEstado() ? 1 : 0);
			statement.setInt(4, administrador.getIdAdmin());

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
	public boolean Eliminar(int idAdministrador) {

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
			statement.setInt(1, idAdministrador);

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
	public Administrador ObtenerObjeto(int idAdministrador) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		Administrador result = new Administrador();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try {

			statement = conexion.getSQLConexion().prepareStatement(obtenerObjeto);
			statement.setInt(1, idAdministrador);
			resultSet = statement.executeQuery();

			while(resultSet.next()){
                
				UsuarioDao daoUser = new UsuarioDaoImpl();
				usuario = new Usuario();
				usuario = daoUser.ObtenerObjeto(resultSet.getInt("idUsuario"));
                
            	result = new Administrador();

                result.setIdAdmin(resultSet.getInt("idAdmin"));
                result.setNombre(resultSet.getString("Nombre"));
                result.setApellido(resultSet.getString("Apellido"));
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
	public ArrayList<Administrador> ListarTodo() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		ArrayList<Administrador> result = new ArrayList<Administrador>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{

			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();

			while(resultSet.next()){
				
                UsuarioDao daoUser = new UsuarioDaoImpl();
				usuario = new Usuario();
				usuario = daoUser.ObtenerObjeto(resultSet.getInt("idUsuario"));

				admin = new Administrador();

				admin.setIdAdmin(resultSet.getInt("idAdmin"));
				admin.setNombre(resultSet.getString("Nombre"));
				admin.setApellido(resultSet.getString("Apellido"));
				admin.setUsuario(usuario);
				admin.setEstado(resultSet.getInt("Estado") == 1 ? true : false);

				result.add(admin);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }

		return result;

	}

	@Override
	public boolean existeAdmin(int dni) {
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
			statement = conexion.getSQLConexion().prepareStatement(buscardni);
			statement.setInt(1, dni);
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
	public Administrador buscar_usuario(int idUser) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		Administrador result = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try {

			statement = conexion.getSQLConexion().prepareStatement(buscaruserid);
			statement.setInt(1, idUser);
			resultSet = statement.executeQuery();

			while(resultSet.next()){
                
				UsuarioDao daoUser = new UsuarioDaoImpl();
				usuario = new Usuario();
				usuario = daoUser.ObtenerObjeto(resultSet.getInt("idUsuario"));
                
            	result = new Administrador();

                result.setIdAdmin(resultSet.getInt("idAdmin"));
                result.setDni(resultSet.getInt("DNI"));
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
	public boolean bajaAdmin(int idAdmin) {
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
			statement = conexion.getSQLConexion().prepareStatement(bajaAdmin);
			statement.setInt(1, idAdmin);

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
