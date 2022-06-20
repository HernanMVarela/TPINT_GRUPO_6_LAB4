package dao;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaDao {
	
	public boolean Agregar(Provincia provincia);
	public ArrayList<Provincia> ListarTodo();
	public Provincia ObtenerObjeto(int idProvincia);
	public boolean Eliminar(int idProvincia);
	public boolean Modificar(Provincia provincia);

}
