package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.PacienteDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.PersonaDao;
import dao.SexoDao;
import dao.UsuarioDao;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Paciente;
import entidad.Pais;
import entidad.Persona;
import entidad.Sexo;
import entidad.Usuario;

public class PacienteDaoImpl implements PacienteDao{

	private String leerTodo = "SELECT * FROM bdtp_integrador.pacientes WHERE ESTADO = 1";
	private String alinsertar = "INSERT INTO bdtp_integrador.pacientes (DNI,Estado) VALUES (?,?)";
	private String modificar = "UPDATE bdtp_integrador.pacientes SET Estado = ? WHERE IDPaciente = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.pacientes WHERE IDPaciente = ?";
	private String eliminar = "DELETE FROM bdtp_integrador.pacientes WHERE IDPaciente = ?";
	private String bajaLogica = "UPDATE bdtp_integrador.pacientes SET Estado = 0 WHERE IDPaciente = ?";
	private String proxid = "SELECT MAX(m.IDPaciente) FROM bdtp_integrador.pacientes m order by m.IDPaciente"; 
	private String contarpac = "SELECT COUNT(DNI) FROM bdtp_integrador.pacientes WHERE ESTADO = 1";
	private String buscar_dni = "SELECT * FROM bdtp_integrador.pacientes WHERE DNI = ?";
	
	
	@Override
	public ArrayList<Paciente> ListarTodo() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	
		ArrayList<Paciente> result = new ArrayList<Paciente>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{
			
			statement = conexion.getSQLConexion().prepareStatement(leerTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				PersonaDao perdao = new PersonaDaoImpl();
				Persona Perso = new Persona();
				Perso = perdao.ObtenerObjeto(resultSet.getInt("DNI"));
				
				Paciente paciente = new Paciente(resultSet.getInt("IDPaciente"),Perso ,resultSet.getBoolean("Estado"));
				
				result.add(paciente);
				
			}
			//connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
			
	}

	@Override
	public boolean Agregar(Paciente paciente) {
		
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
			Stat.setInt(1,paciente.getDni());
			Stat.setBoolean(2,paciente.isEstado());
			
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
	public boolean Modificar(Paciente paciente) {
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
			
			statement.setBoolean(1,paciente.isEstado());
			statement.setInt(2,paciente.getIdPaciente());
			
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
	public boolean Eliminar(int idPaciente) {
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
	        
	        statement.setInt(1, idPaciente);
	        
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

	@Override
	public Paciente Buscar(int idPaciente) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
        Paciente result = new Paciente();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(buscar);
			statement.setInt(1, idPaciente);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){

				PersonaDao perdao = new PersonaDaoImpl();
				Persona Perso = new Persona();
				Perso = perdao.ObtenerObjeto(resultSet.getInt("DNI"));
			
				result.setDni(resultSet.getInt("DNI"));			
				result.setNombre(Perso.getNombre());
				result.setApellido(Perso.getApellido());
				result.setDirecc(Perso.getDirecc());
				result.setEmail(Perso.getEmail());
				result.setEstado(resultSet.getBoolean("Estado"));
				result.setFecha_nacimiento(Perso.getFecha_nacimiento());
				result.setIdPaciente(idPaciente);
				result.setNacionalidad(Perso.getNacionalidad());
				result.setSexo(Perso.getSexo());
				result.setTelefono(Perso.getTelefono());
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return result;
		
	}

	@Override
	public int ContarPacientes() {
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
			st = conexion.getSQLConexion().prepareStatement(contarpac);
			rs = st.executeQuery();
			while(rs.next()) {
				last = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last;
	}
	
	@Override
	public Paciente buscar_dni(int dni) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(buscar_dni);
			statement.setInt(1, dni);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){				
				PersonaDao daoPersona = new PersonaDaoImpl();
				Persona persona = new Persona();
				persona = daoPersona.ObtenerObjeto(dni);
								
				Paciente result = new Paciente(
						resultSet.getInt("idPaciente"), 
						persona,
						resultSet.getBoolean("Estado")		
						);
				
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		
		return null;
	}

	
	@Override
	public boolean BajaLogica(int idPaciente) {
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
	        statement = conexion.getSQLConexion().prepareStatement(bajaLogica);
	        
	        statement.setInt(1, idPaciente);
	        
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
