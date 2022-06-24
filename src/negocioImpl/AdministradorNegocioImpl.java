package negocioImpl;

import dao.AdministradorDao;
import daoImpl.AdministradorDaoImpl;
import entidad.Administrador;
import negocio.AdministradorNegocio;

public class AdministradorNegocioImpl implements AdministradorNegocio {

	@Override
	public boolean agregarAdmin(Administrador agregar) {
		AdministradorDao admindao = new AdministradorDaoImpl();
		return admindao.Agregar(agregar);
	}

	@Override
	public boolean existeAdmin(int dni) {
		AdministradorDao admindao = new AdministradorDaoImpl();
		return admindao.existeAdmin(dni);
	}
	
	

}
