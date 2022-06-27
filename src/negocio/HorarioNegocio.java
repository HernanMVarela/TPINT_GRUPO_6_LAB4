package negocio;

import java.util.ArrayList;
import entidad.Horario;


public interface HorarioNegocio {
	
	public boolean Agregar(int idMedico, Horario hora);
	public ArrayList<Horario> Listar(int idMedico);
	public boolean Modificar(Horario horarioViejo, Horario horarioNuevo);
	
}
