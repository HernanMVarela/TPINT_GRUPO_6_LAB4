package entidad;

import java.time.LocalDate;

public class Medico extends Persona {

	private int idMedico;
	private int DNI;
	private int idEspecialidad;
	private int idUsuario;
	private int Estado;
	
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	
	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", DNI=" + DNI + ", idEspecialidad=" + idEspecialidad + ", idUsuario="
				+ idUsuario + ", Estado=" + Estado + "]";
	}
	
	public Medico(String nombre, String apellido, String nacionalidad, String email, String telefono, LocalDate fNac,
			Sexo sex, Direccion direcc, int idMedico, int dNI, int idEspecialidad, int idUsuario, int estado) {
		super(nombre, apellido, nacionalidad, email, telefono, fNac, sex, direcc);
		this.idMedico = idMedico;
		DNI = dNI;
		this.idEspecialidad = idEspecialidad;
		this.idUsuario = idUsuario;
		Estado = estado;
	}
	
	public Medico() {}
	
	
}
