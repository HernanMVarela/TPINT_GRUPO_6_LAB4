package dao;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoDao {
	
	public ArrayList<Medico> ListarTodo();
	
	
	public void Modificar();
	
	public void Eliminar();
	
	public void Buscar();
	
	public void UltimoID();

	public boolean Agregar(Medico medico);
	
}
