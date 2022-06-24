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

	@Override
	public Administrador buscar_usuario(int idUser) {
		AdministradorDao admindao = new AdministradorDaoImpl();
		return admindao.buscar_usuario(idUser);
	}

	@Override
	public boolean bajaAdmin(Administrador baja) {
		AdministradorDao admindao = new AdministradorDaoImpl();
		admindao.bajaAdmin(baja.getIdAdmin());
		return false;
	}
	
	

}
