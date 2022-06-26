package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.TurnoDao;
import dao.PacienteDao;
import dao.MedicoDao;
import dao.EspecialidadDao;
import dao.PersonaDao;
import dao.EstadoDao;
import dao.SexoDao;

import entidad.Turno;
import entidad.Paciente;
import entidad.Medico;
import entidad.Especialidad;
import entidad.Persona;
import entidad.Estado;
import entidad.Sexo;

import negocio.TurnoNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;

import negocioImpl.TurnoNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;

public class TurnoDaoImpl implements TurnoDao{

	private String leerTodo = "SELECT * FROM bdtp_integrador.turnos T inner join Pacientes P on T.IDPaciente = P.IDPaciente inner join medicos M on T.IDMedico = M.IDMedico inner join especialidades E on T.IDEspecialidad = E.IDEspecialidad inner join estados ES on T.IDEstado = ES.IDEstado";
	private String alinsertar = "INSERT INTO bdtp_integrador.turnos (IDTurno,IDPaciente,IDMedico,Dia,Hora,IDEspecialidad, IDEstado, ObservacionConsulta,Estado) VALUES (?,?,?,?,?,?,?,?,?)";
    private String modificar = "UPDATE bdtp_integrador.turnos SET IDTurno = ?,IDPaciente = ?,IDMedico = ?,Dia = ?,Hora = ?,IDEspecialidad = ?, IDEstado = ?, ObservacionConsulta = ?,Estado = ? WHERE IDTurno = ?";
	private String buscar = "SELECT * FROM bdtp_integrador.turnos WHERE IDTurno = ?";
    private String eliminar = "DELETE FROM bdtp_integrador.turnos WHERE IDTurno = ?";
    private String proxid = "SELECT MAX(m.IDTurno) FROM bdtp_integrador.turnos m order by m.IDTurno";
	private String bajaMedic = "UPDATE bdtp_integrador.turnos SET Estado = 0 where IDTurno = ?";
	
	
	@Override
	public ArrayList<Turno> ListarTodo() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
				
           
                Paciente par = new Paciente();
				PacienteDao pdao = new PacienteDaoImpl();
				par = pdao.Buscar(resultSet.getInt("IDPaciente"));

                Medico med = new Medico();
				MedicoDao mdao = new MedicoDaoImpl();
				med = mdao.Buscar(resultSet.getInt("IDMedico"));

				Especialidad esp = new Especialidad();
				EspecialidadDao espdao = new EspecialidadDaoImpl();
				esp = espdao.ObtenerObjeto(resultSet.getInt("IDEspecialidad"));
				
				Persona per = new Persona();
				PersonaDao perdao = new PersonaDaoImpl();
				per = perdao.ObtenerObjeto(resultSet.getInt("IDPersona"));

                Estado est = new Estado();
				EstadoDao edao = new EstadoDaoImpl();
				est = edao.ObtenerObjeto(resultSet.getInt("IDEstado"));

                Sexo sex = new Sexo();
				SexoDao sdao = new SexoDaoImpl();
				sex = sdao.ObtenerObjeto(resultSet.getInt("IDSexo"));

				Turno temp = new Turno();
				
				temp.setHora(resultSet.getInt("Hora"));
				temp.setPaciente(par);
				temp.setMedico(med);
				temp.setDia(resultSet.getDate("Dia"));
				temp.setEspecialidad(esp);
				temp.setEstadoTurno(est);
				temp.setObservacionConsulta(resultSet.getString("ObservacionConsulta"));
				temp.setEstado(resultSet.getBoolean("Estado"));
				
				
				result.add(new Turno(par,med,resultSet.getDate("Dia"),resultSet.getInt("Hora"),esp,est,resultSet.getString("ObservacionConsulta"),resultSet.getBoolean("Estado")));
				
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
				
				Persona per = new Persona();
				PersonaDao perdao = new PersonaDaoImpl();
				per = perdao.ObtenerObjeto(resultSet.getInt("IDPersona"));

                Estado est = new Estado();
				EstadoDao edao = new EstadoDaoImpl();
				est = edao.ObtenerObjeto(resultSet.getInt("IDEstado"));

                Sexo sex = new Sexo();
				SexoDao sdao = new SexoDaoImpl();
				sex = sdao.ObtenerObjeto(resultSet.getInt("IDSexo"));

				Turno temp = new Turno();
				
				temp.setHora(resultSet.getInt("Hora"));
				temp.setPaciente(par);
				temp.setMedico(med);
				temp.setDia(resultSet.getDate("Dia"));
				temp.setEspecialidad(esp);
				temp.setEstadoTurno(est);
				temp.setObservacionConsulta(resultSet.getString("ObservacionConsulta"));
				temp.setEstado(resultSet.getBoolean("Estado"));
				
				result = new Turno(par,med,resultSet.getDate("Dia"),resultSet.getInt("Hora"),esp,est,resultSet.getString("ObservacionConsulta"),resultSet.getBoolean("Estado"));
					
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
	public Turno ObtenerObjeto(int idEstado) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean Modificar(Turno turno) {
		// TODO Auto-generated method stub
		return false;
	}
			
}
