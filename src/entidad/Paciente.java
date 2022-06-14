package entidad;

import java.time.LocalDate;

public class Paciente extends Persona {

	private int idPaciente;
	private int DNI;
	private int Estado;
	
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", DNI=" + DNI + ", Estado=" + Estado + "]";
	}
	
	public Paciente(String nombre, String apellido, String nacionalidad, String email, String telefono, LocalDate fNac,
			Sexo sex, Direccion direcc, int idPaciente, int dNI, int estado) {
		super(nombre, apellido, nacionalidad, email, telefono, fNac, sex, direcc);
		this.idPaciente = idPaciente;
		DNI = dNI;
		Estado = estado;
	}
		
	public Paciente() {}
	
}
