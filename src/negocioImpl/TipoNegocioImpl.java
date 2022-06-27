package negocioImpl;

import java.util.ArrayList;

import dao.TipoDao;
import daoImpl.TipoDaoImpl;
import entidad.Tipo;
import negocio.TipoNegocio;

public class TipoNegocioImpl implements TipoNegocio{

	TipoDao dao = new TipoDaoImpl();

	@Override
	public boolean Agregar(Tipo tipo) {
		return dao.Agregar(tipo);
	}

	@Override
	public ArrayList<Tipo> ListarTodo() {
		return dao.ListarTodo();
	}

	@Override
	public Tipo ObtenerObjeto(int idTipo) {
		return dao.ObtenerObjeto(idTipo);
	}

	@Override
	public boolean Eliminar(int idTipo) {
		return dao.Eliminar(idTipo);
	}

	@Override
	public boolean Modificar(Tipo tipo) {
		return dao.Modificar(tipo);
	}
}
