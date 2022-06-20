package dao;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {
	
	public boolean Agregar(Localidad localidad);
	public ArrayList<Localidad> ListarTodo();
	public Localidad ObtenerObjeto(int idlocalidad);
	public boolean Eliminar(int idlocalidad);
	public boolean Modificar(Localidad localidad);

}
