package negocio;

import java.util.ArrayList;
import entidad.Especialidad;

public interface EspecialidadNegocio {
	
	public boolean Agregar(Especialidad especialidad);
	public ArrayList<Especialidad> ListarTodo();
	public Especialidad ObtenerObjeto(int idEspecialidad);
	public boolean Eliminar(int idEspecialidad);
	public boolean Modificar(Especialidad especialidad);
	public int ContarEspecialidades();
	
}
