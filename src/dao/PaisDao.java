package dao;

import java.util.ArrayList;

import entidad.Pais;

public interface PaisDao {
	
	public boolean Agregar(Pais pais);
	public ArrayList<Pais> ListarTodo();
	public Pais ObtenerObjeto(int idNacionalidad);
	public boolean Eliminar(int idNacionalidad);
	public boolean Modificar(Pais pais);

}
