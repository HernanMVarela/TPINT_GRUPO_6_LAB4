package entidad;

public class Medico extends Persona {

	private int idMedico;
	private Persona persona;
	private Especialidad especialidad;
	private Usuario usuario;
	private boolean estado;
	
	public Medico() {}

	public Medico(int idMedico, Persona persona, Especialidad especialidad, Usuario usuario, boolean estado) {
		super();
		this.idMedico = idMedico;
		this.persona = persona;
		this.especialidad = especialidad;
		this.usuario = usuario;
		this.estado = estado;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", persona=" + persona + ", especialidad=" + especialidad + ", usuario="
				+ usuario + ", estado=" + estado + "]";
	}
	
}
