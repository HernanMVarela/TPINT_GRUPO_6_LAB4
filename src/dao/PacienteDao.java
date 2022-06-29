package dao;

import java.util.ArrayList;

import entidad.Paciente;


public interface PacienteDao {
	
	public ArrayList<Paciente> ListarTodo();
	
	public boolean Agregar(Paciente paciente);

	public boolean Modificar(Paciente paciente);

	public boolean Eliminar(int idPaciente);

	public int UltimoID();

	public Paciente Buscar(int idPaciente);

	public int ContarPacientes();
	
}
