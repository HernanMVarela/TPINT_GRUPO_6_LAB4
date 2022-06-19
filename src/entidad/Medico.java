package entidad;

public class Medico {

	private int idMedico;
	private DatoPersonal datoPersonal;
	private Especialidad especialidad;
	private Usuario usuario;
	private boolean estado;
	
	public Medico() {}
	
	public Medico(int idMedico, DatoPersonal datoPersonal, Especialidad especialidad, Usuario usuario, boolean estado) {
		this.idMedico = idMedico;
		this.datoPersonal = datoPersonal;
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

	public DatoPersonal getDatoPersonal() {
		return datoPersonal;
	}

	public void setDatoPersonal(DatoPersonal datoPersonal) {
		this.datoPersonal = datoPersonal;
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
		return "Medico [idMedico=" + idMedico + ", datoPersonal=" + datoPersonal + ", especialidad=" + especialidad
				+ ", usuario=" + usuario + ", estado=" + estado + "]";
	}
	
}
