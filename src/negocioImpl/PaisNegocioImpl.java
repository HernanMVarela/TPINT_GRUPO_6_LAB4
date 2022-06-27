package negocioImpl;

import java.util.ArrayList;

import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNegocio;


public class PaisNegocioImpl implements PaisNegocio{

	@Override
	public boolean Agregar(Pais pais) {
		PaisDao daoPais = new PaisDaoImpl();
		return daoPais.Agregar(pais);
	}

	@Override
	public ArrayList<Pais> ListarTodo() {
		PaisDao daoPais = new PaisDaoImpl();
		return daoPais.ListarTodo();
	}

	@Override
	public Pais ObtenerObjeto(int idNacionalidad) {
		PaisDao daoPais = new PaisDaoImpl();
		return daoPais.ObtenerObjeto(idNacionalidad);
	}

	@Override
	public boolean Eliminar(int idNacionalidad) {
		PaisDao daoPais = new PaisDaoImpl();
		return daoPais.Eliminar(idNacionalidad);
	}

	@Override
	public boolean Modificar(Pais pais) {
		PaisDao daoPais = new PaisDaoImpl();
		return daoPais.Modificar(pais);
	}

	

	
}
