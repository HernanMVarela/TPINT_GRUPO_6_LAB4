package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.MedicoDao;
import entidad.Medico;

public class MedicoDaoImpl implements MedicoDao{
	
	private String leerTodo = "SELECT * FROM bdtp_integrador.medicos M inner join datospersonales DP on M.DNI = DP.DNI";

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
	public void Agregar() {
		// TODO Auto-generated method stub
		
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
