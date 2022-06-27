package negocioImpl;

import java.util.ArrayList;

import dao.EstadoDao;
import daoImpl.EstadoDaoImpl;
import entidad.Estado;
import negocio.EstadoNegocio;

public class EstadoNegocioImpl implements EstadoNegocio{

	EstadoDao dao = new EstadoDaoImpl();

	@Override
	public boolean Agregar(Estado estado) {
		return dao.Agregar(estado);
	}

	@Override
	public ArrayList<Estado> ListarTodo() {
		return dao.ListarTodo();
	}

	@Override
	public Estado ObtenerObjeto(int idEstado) {
		return dao.ObtenerObjeto(idEstado);
	}

	@Override
	public boolean Eliminar(int idEstado) {
		return dao.Eliminar(idEstado);
	}

	@Override
	public boolean Modificar(Estado estado) {
		return dao.Modificar(estado);
	}
}
