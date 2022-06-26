package entidad;

import java.util.ArrayList;

public class Medico extends Persona {

	private int idMedico;
	private Especialidad especialidad;
	private Usuario usuario;
	private ArrayList<Horario> horarios;
	private boolean estado;
	
	public Medico() {}

	public Medico(int idMedico, Persona persona, Especialidad especialidad, Usuario usuario, boolean estado) {
		super(persona.getDni(),persona.getNombre(),persona.getApellido(),persona.getNacionalidad(),persona.getDirecc(),persona.getSexo(),persona.getEmail(),persona.getTelefono(),persona.getFecha_nacimiento());
		this.idMedico = idMedico;
		this.especialidad = especialidad;
		this.usuario = usuario;
		this.estado = estado;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public ArrayList<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(ArrayList<Horario> horarios) {
		this.horarios = horarios;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
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
		return "Medico [idMedico=" + idMedico + ", especialidad=" + especialidad + ", usuario="
				+ usuario + ", estado=" + estado + "]";
	}
	
}
