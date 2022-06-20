package dao;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoDao {
	
	public ArrayList<Medico> ListarTodo();
	
	public boolean Agregar(Medico medico);

	public boolean Modificar(Medico medico);

	public boolean Eliminar(int idMedico);

	public int UltimoID();

	public Medico Buscar(int idMedico);
	
}
