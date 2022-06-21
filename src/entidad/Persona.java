package entidad;

import java.sql.Date;

public class Persona {

	private int dni;
	private String nombre;
	private String apellido;
	private Pais nacionalidad;
	private Sexo sexo;
	private String email;
	private String telefono;
	private Date fecha_nacimiento;
	private Direccion Direcc;
	
	public Persona() {}

	public Persona(int dni, String nombre, String apellido, Pais nacionalidad, Direccion direccion, Sexo sexo,
			String email, String telefono, Date fecha_nacimiento) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.Direcc = direccion;
		this.sexo = sexo;
		this.email = email;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public Direccion getDirecc() {
		return Direcc;
	}

	public void setDirecc(Direccion direcc) {
		Direcc = direcc;
	}

	
	
}
