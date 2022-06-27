package negocio;

import java.util.ArrayList;

import entidad.Paciente;


public interface PacienteNegocio {

	public boolean agregarPaciente(Paciente agregar);

	public boolean modificarPaciente(Paciente modificar);

	public Paciente buscar_paciente(int idPaciente);

	public boolean bajaPaciente(Paciente baja);

	public ArrayList<Paciente> listar();
	
}
