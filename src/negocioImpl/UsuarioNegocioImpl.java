package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Override
	public boolean agregarUsuario(Usuario agregar) {
		UsuarioDao userdao = new UsuarioDaoImpl();
		return userdao.Agregar(agregar);
	}

	@Override
	public int existeUsuario(String User) {
		UsuarioDao userdao = new UsuarioDaoImpl();
		return userdao.existeuser(User);
	}

	@Override
	public Usuario ObtenerObjeto(int idUsuario) {
		UsuarioDao userdao = new UsuarioDaoImpl();
		return userdao.ObtenerObjeto(idUsuario);
	}

	@Override
	public boolean Modificar(Usuario usuario) {
		UsuarioDao userdao = new UsuarioDaoImpl();
		return userdao.Modificar(usuario);
	}
	
	

}
