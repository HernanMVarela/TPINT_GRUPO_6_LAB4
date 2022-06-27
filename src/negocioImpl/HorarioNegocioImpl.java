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
		if(dao.Eliminar(idMedico)) {System.out.println("HORARIOS ELIMINADOS");}
	
		for (Horario horario : horas) {
			System.out.println("HORARIOS A CARGAR: " + horario.getDia() +" | "+ horario.getHoraDesde()+" - "+ horario.getHoraHasta());
			if(!dao.Agregar(idMedico, horario)) {return false;}
		}
		
		return true;
	}

}
