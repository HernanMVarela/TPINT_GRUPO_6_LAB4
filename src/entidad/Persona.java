package entidad;

import java.time.LocalDate;

public class Persona {

//Atributos

	//Simples
	private String Nombre;
	private String Apellido;
	private String Nacionalidad;
	private String Email;
	private String Telefono;
	private LocalDate FNac;

	//Objetos
	private Sexo Sex;
	private Direccion Direcc;
	
	
	//Métodos
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public LocalDate getFNac() {
		return FNac;
	}
	public void setFNac(LocalDate fNac) {
		FNac = fNac;
	}
	public Sexo getSex() {
		return Sex;
	}
	public void setSex(Sexo sex) {
		Sex = sex;
	}
	public Direccion getDirecc() {
		return Direcc;
	}
	public void setDirecc(Direccion direcc) {
		Direcc = direcc;
	}
	
	@Override
	public String toString() {
		return "Persona [Nombre=" + Nombre + ", Apellido=" + Apellido + ", Nacionalidad=" + Nacionalidad + ", Email="
				+ Email + ", Telefono=" + Telefono + ", FNac=" + FNac + ", Sex=" + Sex + ", Direcc=" + Direcc + "]";
	}

	//Constructores
	public Persona(String nombre, String apellido, String nacionalidad, String email, String telefono, LocalDate fNac,
			Sexo sex, Direccion direcc) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		Nacionalidad = nacionalidad;
		Email = email;
		Telefono = telefono;
		FNac = fNac;
		Sex = sex;
		Direcc = direcc;
	}
	
	public Persona() {}
}
