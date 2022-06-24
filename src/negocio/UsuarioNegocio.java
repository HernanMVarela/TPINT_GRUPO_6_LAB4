package negocio;

import entidad.Usuario;

public interface UsuarioNegocio {
	
	public boolean agregarUsuario(Usuario agregar);
	
	public int existeUsuario(String User);
}
