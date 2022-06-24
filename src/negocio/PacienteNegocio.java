package negocio;

import entidad.Paciente;

public interface PacienteNegocio {

	public boolean agregarPaciente(Paciente agregar);

	public boolean modificarPaciente(Paciente modificar);

	public Paciente buscar_paciente(int idPaciente);

	public boolean bajaPaciente(Paciente baja);

}
