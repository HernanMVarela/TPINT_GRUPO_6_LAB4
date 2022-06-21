package entidad;

public class Paciente extends Persona{

	private int idPaciente;
	private boolean estado;
	
	public Paciente() {}

	public Paciente(int idPaciente, Persona persona, boolean estado) {
		super(persona.getDni(),persona.getNombre(),persona.getApellido(),persona.getNacionalidad(),persona.getDirecc(),persona.getSexo(),persona.getEmail(),persona.getTelefono(),persona.getFecha_nacimiento());
		this.idPaciente = idPaciente;
		this.estado = estado;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + " estado=" + estado + "]";
	}
	
}
