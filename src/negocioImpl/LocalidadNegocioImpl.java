package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{

	LocalidadDao daoLocalidad = new LocalidadDaoImpl();
	
	@Override
	public boolean Agregar(Localidad localidad) {
		return daoLocalidad.Agregar(localidad);
	}

	@Override
	public ArrayList<Localidad> ListarTodo() {
		return daoLocalidad.ListarTodo();
	}

	@Override
	public Localidad ObtenerObjeto(int idlocalidad) {
		return daoLocalidad.ObtenerObjeto(idlocalidad);
	}

	@Override
	public boolean Modificar(Localidad localidad) {
		return daoLocalidad.Modificar(localidad);
	}

	@Override
	public boolean Eliminar(int idlocalidad) {
		return daoLocalidad.Eliminar(idlocalidad);
	}
	
}
