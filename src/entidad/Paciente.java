package entidad;

public class Paciente {

	private int idPaciente;
	private DatoPersonal datoPersonal;
	private boolean estado;
	
	public Paciente() {}

	public Paciente(int idPaciente, DatoPersonal datoPersonal, boolean estado) {
		this.idPaciente = idPaciente;
		this.datoPersonal = datoPersonal;
		this.estado = estado;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public DatoPersonal getDatoPersonal() {
		return datoPersonal;
	}

	public void setDatoPersonal(DatoPersonal datoPersonal) {
		this.datoPersonal = datoPersonal;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", datoPersonal=" + datoPersonal + ", estado=" + estado + "]";
	}
	
}
