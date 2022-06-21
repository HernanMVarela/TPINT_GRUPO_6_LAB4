package dao;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioDao {
	
	public boolean Agregar(Usuario usuario);
	public ArrayList<Usuario> ListarTodo();
	public Usuario ObtenerObjeto(int idUsuario);
	public boolean Eliminar(int idUsuario);
	public boolean Modificar(Usuario usuario);

}
