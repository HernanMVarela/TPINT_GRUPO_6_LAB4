package negocioImpl;

import java.util.ArrayList;
import dao.EspecialidadDao;
import daoImpl.EspecialidadDaoImpl;
import entidad.Especialidad;
import negocio.EspecialidadNegocio;

public class EspecialidadNegocioImpl implements EspecialidadNegocio{

	EspecialidadDao dao = new EspecialidadDaoImpl();

	@Override
	public boolean Agregar(Especialidad especialidad) {
		return dao.Agregar(especialidad);
	}

	@Override
	public ArrayList<Especialidad> ListarTodo() {
		return dao.ListarTodo();
	}

	@Override
	public Especialidad ObtenerObjeto(int idEspecialidad) {
		return dao.ObtenerObjeto(idEspecialidad);
	}

	@Override
	public boolean Eliminar(int idEspecialidad) {
		return dao.Eliminar(idEspecialidad);
	}

	@Override
	public boolean Modificar(Especialidad especialidad) {
		return dao.Modificar(especialidad);
	}
	
}
