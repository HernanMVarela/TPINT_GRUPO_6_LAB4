package dao;

import java.util.ArrayList;

import entidad.Estado;

public interface EstadoDao {
	
	public boolean Agregar(Estado estado);
	public ArrayList<Estado> ListarTodo();
	public Estado ObtenerObjeto(int idEstado);
	public boolean Eliminar(int idEstado);
	public boolean Modificar(Estado estado);

}
