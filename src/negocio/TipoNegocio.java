package negocio;

import java.util.ArrayList;

import entidad.Tipo;

public interface TipoNegocio {
	
	public boolean Agregar(Tipo tipo);
	public ArrayList<Tipo> ListarTodo();
	public Tipo ObtenerObjeto(int idTipo);
	public boolean Eliminar(int idTipo);
	public boolean Modificar(Tipo tipo);
	
}
