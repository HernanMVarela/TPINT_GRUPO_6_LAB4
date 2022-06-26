package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.HorariosDao;
import entidad.Horario;

public class HorariosDaoImpl implements HorariosDao{

	private String listar = "SELECT * FROM bdtp_integrador.Horarios where IDMedico = ?";
	private String agregar = "INSERT INTO bdtp_integrador.Horarios (IDMedico, Dia, HoraDesde, HoraHasta) VALUES (?,?,?,?)";
	
	@Override
	public boolean Agregar(int idMedico, Horario hora) {
		
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
			statement.setInt(1, idMedico);
			statement.setInt(2, hora.getDia());
			statement.setInt(3, hora.getHoraDesde());
			statement.setInt(4, hora.getHoraHasta());
			
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
	public ArrayList<Horario> Listar(int idMedico) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		ArrayList<Horario> result = new ArrayList<Horario>();
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			
			statement = conexion.getSQLConexion().prepareStatement(listar);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			
			for (int x=1; x<=7; x++) {
				Horario aux = new Horario();
				aux.setDia(x);
				aux.setHoraDesde(0);
				aux.setHoraHasta(0);
				result.add(aux);
			}
			while(resultSet.next()){
				for (Horario temporal : result) {
					if(temporal.getDia() == resultSet.getInt("Dia")) {
						temporal.setHoraDesde(resultSet.getInt("HoraDesde"));
						temporal.setHoraHasta(resultSet.getInt("HoraHasta"));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ }		

		return result;
	}

}
