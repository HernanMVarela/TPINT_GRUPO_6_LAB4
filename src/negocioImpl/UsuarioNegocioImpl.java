package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Override
	public int agregarUsuario(Usuario agregar) {
		UsuarioDao userdao = new UsuarioDaoImpl();
		int iduser = userdao.existeuser(agregar.getUser());
		if(iduser != -1) {
			return iduser;
		}else {
			if (userdao.Agregar(agregar)) {
				iduser = userdao.existeuser(agregar.getUser());
			}
		}
		return iduser; // REVISAR COMO MANEJAR CASO DE PERSONA EXISTENTE, por ahora retorna ok
	}

}
