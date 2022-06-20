package dao;

import java.util.ArrayList;

import entidad.Sexo;

public interface SexoDao {
	
	public boolean Agregar(Sexo sexo);
	public ArrayList<Sexo> ListarTodo();
	public Sexo ObtenerObjeto(int idSexo);
	public boolean Eliminar(int idSexo);
	public boolean Modificar(Sexo sexo);

}
