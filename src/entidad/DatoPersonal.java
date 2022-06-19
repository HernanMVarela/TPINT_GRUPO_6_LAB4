package entidad;

import java.sql.Date;

public class DatoPersonal {
	
	private int dni;
	private String nombre;
	private String apellido;
	private Pais nacionalidad;
	private String direccion;
	private Sexo sexo;
	private Localidad localidad;
	private String email;
	private String telefono;
	private Date fecha_nacimiento;
	
	public DatoPersonal() {}
	
	public DatoPersonal(int dni, String nombre, String apellido, Pais nacionalidad, String direccion, Sexo sexo,
			Localidad localidad, String email, String telefono, Date fecha_nacimiento) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.sexo = sexo;
		this.localidad = localidad;
		this.email = email;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
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

	@Override
	public String toString() {
		return "DatoPersonal [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad="
				+ nacionalidad + ", direccion=" + direccion + ", sexo=" + sexo + ", localidad=" + localidad + ", email="
				+ email + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + "]";
	}

}
