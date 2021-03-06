package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dao.TurnoDao;
import dao.UsuarioDao;
import dao.PacienteDao;
import dao.MedicoDao;
import dao.EspecialidadDao;
import dao.PersonaDao;
import dao.EstadoDao;
import dao.SexoDao;

import entidad.Turno;
import entidad.Usuario;
import entidad.Paciente;
import entidad.Medico;
import entidad.Especialidad;
import entidad.Persona;
import entidad.Estado;
import entidad.Sexo;

public class TurnoDaoImpl implements TurnoDao{

	private String leerTodo = "SELECT * FROM bdtp_integrador.turnos";
	private String alinsertar = "INSERT INTO bdtp_integrador.turnos (IDPaciente,IDMedico,Dia,Hora,IDEspecialidad, IDEstado, ObservacionConsulta,Estado) VALUES (?,?,?,?,?,?,?,?)";
    private String modificar = "UPDATE bdtp_integrador.turnos SET IDPaciente = ?,IDMedico = ?,Dia = ?,Hora = ?,IDEspecialidad = ?, IDEstado = ?, ObservacionConsulta = ?,Estado = ? WHERE IDTurno = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.turnos WHERE IDTurno = ?";
    private String eliminar = "DELETE FROM bdtp_integrador.turnos WHERE IDTurno = ?";
    private String proxid = "SELECT MAX(m.IDTurno) FROM bdtp_integrador.turnos m order by m.IDTurno";
	private String contarturnostotal = "SELECT COUNT(idTurno) FROM bdtp_integrador.turnos";
	private String contarturnosporestado = "SELECT COUNT(idTurno) FROM bdtp_integrador.turnos WHERE idEstado = ?";

	@Override
	public ArrayList<Turno> ListarTodo() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	
		ArrayList<Turno> result = new ArrayList<Turno>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{
			
			statement = conexion.getSQLConexion().prepareStatement(leerTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
           
                Paciente pac = new Paciente();
				PacienteDao pdao = new PacienteDaoImpl();
				pac = pdao.Buscar(resultSet.getInt("IDPaciente"));

                Medico med = new Medico();
				MedicoDao mdao = new MedicoDaoImpl();
				med = mdao.Buscar(resultSet.getInt("IDMedico"));

				Especialidad esp = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				esp = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));

                Estado est = new Estado();
				EstadoDao edao = new EstadoDaoImpl();
				est = edao.ObtenerObjeto(resultSet.getInt("IDEstado"));

				Turno temp = new Turno();
				
				temp.setIdTurno(resultSet.getInt("IDTurno"));
				temp.setHora(resultSet.getInt("Hora"));
				temp.setPaciente(pac);
				temp.setMedico(med);
				temp.setDia(resultSet.getDate("Dia"));
				temp.setEspecialidad(esp);
				temp.setEstadoTurno(est);
				temp.setObservacionConsulta(resultSet.getString("ObservacionConsulta"));
				temp.setEstado(resultSet.getBoolean("Estado"));
				
				result.add(temp);
				
			}
			//connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
	}

	@Override
	public boolean Agregar(Turno turno) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		PreparedStatement Stat;
		Connection conex = Conexion.getConexion().getSQLConexion();
		
		boolean InsertState = false;
		
		try {
			Stat = conex.prepareStatement(alinsertar);
            Stat.setInt(1,turno.getPaciente().getIdPaciente());
            Stat.setInt(2,turno.getMedico().getIdMedico());
            Stat.setDate(3,turno.getDia());
            Stat.setInt(4,turno.getHora());
            Stat.setInt(5,turno.getEspecialidad().getIdEspecialidad());
            Stat.setInt(6,turno.getEstadoTurno().getIdEstado());
            Stat.setString(7,turno.getObservacionConsulta());
            Stat.setBoolean(8,turno.isEstado());
			
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
	public boolean Modificar(Turno turno) {
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
			
            statement.setInt(1,turno.getPaciente().getIdPaciente());
            statement.setInt(2,turno.getMedico().getIdMedico());
            statement.setDate(3,turno.getDia());
            statement.setInt(4,turno.getHora());
            statement.setInt(5,turno.getEspecialidad().getIdEspecialidad());
            statement.setInt(6,turno.getEstadoTurno().getIdEstado());
            statement.setString(7,turno.getObservacionConsulta());
            statement.setBoolean(8,turno.isEstado());
            statement.setInt(9,turno.getIdTurno());
			            
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
	public boolean Eliminar(int idTurno) {
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
			
			statement.setInt(1, idTurno);
			
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
	public Turno Buscar(int idTurno) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		Turno result = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(buscar);
			statement.setInt(1, idTurno);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){

                Paciente par = new Paciente();
				PacienteDao pdao = new PacienteDaoImpl();
				par = pdao.Buscar(resultSet.getInt("IDPaciente"));

                Medico med = new Medico();
				MedicoDao mdao = new MedicoDaoImpl();
				med = mdao.Buscar(resultSet.getInt("IDMedico"));

				Especialidad esp = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				esp = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
                Estado est = new Estado();
				EstadoDao edao = new EstadoDaoImpl();
				est = edao.ObtenerObjeto(resultSet.getInt("IDEstado"));
				
				result = new Turno();
				
				result.setIdTurno(resultSet.getInt("IDTurno"));
				result.setHora(resultSet.getInt("Hora"));
				result.setPaciente(par);
				result.setMedico(med);
				result.setDia(resultSet.getDate("Dia"));
				result.setEspecialidad(esp);
				result.setEstadoTurno(est);
				result.setObservacionConsulta(resultSet.getString("ObservacionConsulta"));
				result.setEstado(resultSet.getBoolean("Estado"));
				
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
			Class.forName("com.mysql.cj.jdbc.Driver");
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
	public Turno ObtenerObjeto(int idTurno) {
		return Buscar(idTurno);
	}

	@Override
	public int ContarTurnosTotales() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int total=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(contarturnostotal);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	@Override
	public int ContarTurnosPorEstado(int idEstado) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int total=0;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conexion.getSQLConexion().prepareStatement(contarturnosporestado);
			st.setInt(1, idEstado);
			rs = st.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	

		
}
