package negocioImpl;

import java.util.ArrayList;
import dao.HorarioDao;
import daoImpl.HorarioDaoImpl;
import entidad.Horario;
import negocio.HorarioNegocio;

public class HorarioNegocioImpl implements HorarioNegocio{

	HorarioDao dao = new HorarioDaoImpl();

	@Override
	public boolean Agregar(int idMedico, Horario hora) {
		return dao.Agregar(idMedico, hora);
	}

	@Override
	public ArrayList<Horario> Listar(int idMedico) {
		return dao.Listar(idMedico);
	}

	@Override
	public boolean Modificar(int idMedico, ArrayList<Horario> horas) {
		// Se valida lista de horarios para modificar horarios en cero
				
		if(!dao.Eliminar(idMedico)) {
			
			if(!Listar(idMedico).isEmpty()) { dao.Eliminar(idMedico); } 
//			else {return false;
			}
			
		for (Horario horario : horas) {
	
		if(!dao.Agregar(idMedico, horario)) {return false;}
		
		
		}		
		return true;
		}
	

}