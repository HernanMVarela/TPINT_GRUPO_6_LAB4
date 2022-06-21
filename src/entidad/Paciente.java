package entidad;

public class Paciente extends Persona{

	private int idPaciente;
	private Persona persona;
	private boolean estado;
	
	public Paciente() {}

	public Paciente(int idPaciente, Persona persona, boolean estado) {
		super();
		this.idPaciente = idPaciente;
		this.persona = persona;
		this.estado = estado;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", persona=" + persona + ", estado=" + estado + "]";
	}
	
}
