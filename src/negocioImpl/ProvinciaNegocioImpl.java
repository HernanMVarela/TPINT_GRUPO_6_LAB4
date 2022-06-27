package negocioImpl;

import java.util.ArrayList;
import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	ProvinciaDao daoProvincia = new ProvinciaDaoImpl();
	
	@Override
	public boolean Agregar(Provincia provincia) {
		return daoProvincia.Agregar(provincia);
	}

	@Override
	public ArrayList<Provincia> ListarTodo() {
		return daoProvincia.ListarTodo();
	}

	@Override
	public Provincia ObtenerObjeto(int idProvincia) {
		return daoProvincia.ObtenerObjeto(idProvincia);
	}

	@Override
	public boolean Eliminar(int idProvincia) {
		return daoProvincia.Eliminar(idProvincia);
	}

	@Override
	public boolean Modificar(Provincia provincia) {
		return daoProvincia.Modificar(provincia);
	}

}
