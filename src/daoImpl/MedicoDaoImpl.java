package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.MedicoDao;
import entidad.Medico;

public class MedicoDaoImpl implements MedicoDao{
	
	//Atributos
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos M inner join datospersonales DP on M.DNI = DP.DNI";
	private String alinsertar = "INSERT INTO medicos (DNI,IDEspecialidad,IDUsuario,Estado) VALUES (?,?,?,?)";
	
	
	@Override
	public ArrayList<Medico> ListarTodo() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	
		ArrayList<Medico> result = new ArrayList<Medico>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;

		try{
			
			statement = conexion.getSQLConexion().prepareStatement(leerTodo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Medico temporal = new Medico();
				temporal.setApellido(resultSet.getString(leerTodo));
				
			}
			//connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		
		return result;
			
	}

	@Override
	public boolean Agregar(Medico medico) {
		
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
			Stat.setInt(1,medico.getDNI());
			Stat.setInt(2,medico.getEsp().getIdEspecialidad());
			Stat.setInt(3,medico.getUser().getIdTipo());
			Stat.setInt(1,medico.getEstado());
			
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
	public void Modificar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Eliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Buscar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UltimoID() {
		// TODO Auto-generated method stub
		
	}
	
	
}
