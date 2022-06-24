package negocio;

import entidad.Usuario;

public interface UsuarioNegocio {
	
	public boolean agregarUsuario(Usuario agregar);
	
	public int existeUsuario(String User);
	public Usuario ObtenerObjeto(int idUsuario);
}
