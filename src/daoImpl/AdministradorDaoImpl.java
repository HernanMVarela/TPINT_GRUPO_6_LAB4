package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.AdministradorDao;
import dao.ProvinciaDao;
import dao.TipoDao;
import entidad.Administrador;
import entidad.Provincia;
import entidad.Tipo;
import entidad.Usuario;

public class AdministradorDaoImpl implements AdministradorDao {
	private String agregar = "INSERT INTO bdtp_integrador.Administradores (DNI, IDUsuario, Estado) VALUES (?,?,?)";
	private String modificar = "UPDATE bdtp_integrador.Administradores SET DNI = ?, IDUsuario = ?, Estado = ? WHERE idAdmin = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.Administradores WHERE idAdmin = ?";
	private String obtenerObjeto = "select a.idAdmin, a.dni, a.estado, a.idUsuario, u.user, u.password, u.idTipo, t.nombre tipoNombre from administradores a left join usuarios u on a.idUsuario = u.idUsuario left join tipos t on u.idTipo = t.idTipo where a.idAdmin = ?";
	private String listarTodo = "select a.idAdmin, a.dni, a.estado, a.idUsuario, u.user, u.password, u.idTipo, t.nombre tipoNombre from administradores a left join usuarios u on a.idUsuario = u.idUsuario left join tipos t on u.idTipo = t.idTipo"

	private Tipo tipo;
	private Usuario usuario;

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

			statement.setString(1, administrador.getDni());
			statement.setString(2, administrador.getUsuario.getIdUsuario());
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

			statement.setString(1, administrador.getDni());
			statement.setString(2, administrador.getUsuario().getIdUsuario());
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
				tipo = new Tipo();

                tipo.setIdTipo(resultSet.getInt("idTipo"));
                tipo.setNombre(resultSet.getString("tipoNombre"));

                usuario = new Usuario();
                
                usuario.setIdUsuario("idUsuario");
                usuario.setUser("user");
                usuario.setPassword("password");
                usuario.setTipo(tipo);

				result = new Administrador();

                result.setIdAdmin(resultSet.getId("idAdmin"));
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
				tipo = new Tipo();

                tipo.setIdTipo(temporalSet.getInt("idTipo"));
                tipo.setNombre(temporalSet.getString("tipoNombre"));

                usuario = new Usuario();
                
                usuario.setIdUsuario("idUsuario");
                usuario.setUser("user");
                usuario.setPassword("password");
                usuario.setTipo(tipo);

				temporal = new Administrador();

                temporal.setIdAdmin(temporalSet.getId("idAdmin"));
                temporal.setUsuario(usuario);
                temporal.setEstado(temporalSet.getInt("estado") == 1 ? true : false);

				result.add(temporal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }

		return result;

	}

}
