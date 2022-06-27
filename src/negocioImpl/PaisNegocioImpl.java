package negocioImpl;

import java.util.ArrayList;
import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNegocio;

public class PaisNegocioImpl implements PaisNegocio{

	PaisDao daoPais = new PaisDaoImpl();
	
	@Override
	public boolean Agregar(Pais pais) {
		return daoPais.Agregar(pais);
	}

	@Override
	public ArrayList<Pais> ListarTodo() {
		return daoPais.ListarTodo();
	}

	@Override
	public Pais ObtenerObjeto(int idNacionalidad) {
		return daoPais.ObtenerObjeto(idNacionalidad);
	}

	@Override
	public boolean Eliminar(int idNacionalidad) {
		return daoPais.Eliminar(idNacionalidad);
	}

	@Override
	public boolean Modificar(Pais pais) {
		return daoPais.Modificar(pais);
	}
	
}
