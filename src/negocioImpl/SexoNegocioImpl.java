package negocioImpl;

import java.util.ArrayList;

import dao.SexoDao;
import daoImpl.SexoDaoImpl;
import entidad.Sexo;
import negocio.SexoNegocio;

public class SexoNegocioImpl implements SexoNegocio{

	SexoDao daoLocalidad = new SexoDaoImpl();

	@Override
	public boolean Agregar(Sexo sexo) {
		return daoLocalidad.Agregar(sexo);
	}

	@Override
	public ArrayList<Sexo> ListarTodo() {
		return daoLocalidad.ListarTodo();
	}

	@Override
	public Sexo ObtenerObjeto(int idSexo) {
		return daoLocalidad.ObtenerObjeto(idSexo);
	}

	@Override
	public boolean Eliminar(int idSexo) {
		return daoLocalidad.Eliminar(idSexo);
	}

	@Override
	public boolean Modificar(Sexo sexo) {
		return daoLocalidad.Modificar(sexo);
	}
	
	
	
}
