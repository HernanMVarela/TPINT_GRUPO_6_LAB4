package entidad;

import java.time.LocalDate;

public class Medico extends Persona {

	private int idMedico;
	private int DNI;
	private int Estado;
	private Usuario User;
	private Especialidad Esp;
	
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
	
	public Usuario getUser() {
		return User;
	}
	public void setUser(Usuario user) {
		User = user;
	}
	public Especialidad getEsp() {
		return Esp;
	}
	public void setEsp(Especialidad esp) {
		Esp = esp;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	
	
	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", DNI=" + DNI + ", Estado=" + Estado + ", User=" + User + ", Esp="
				+ Esp + "]";
	}
	
	public Medico(String nombre, String apellido, String nacionalidad, String email, String telefono, LocalDate fNac,
			Sexo sex, Direccion direcc, int idMedico, int dNI, int estado, Usuario user, Especialidad esp) {
		super(nombre, apellido, nacionalidad, email, telefono, fNac, sex, direcc);
		this.idMedico = idMedico;
		DNI = dNI;
		Estado = estado;
		User = user;
		Esp = esp;
	}
	
	public Medico() {}
	
	
}
