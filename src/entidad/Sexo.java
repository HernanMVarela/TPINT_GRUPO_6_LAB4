package entidad;

public class Sexo {
	
	private int idSexo;
	private String nombre;
		
	public Sexo() {}

	public Sexo(int idSexo, String nombre) {
		this.idSexo = idSexo;
		this.nombre = nombre;
	}

	public int getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Sexo [idSexo=" + idSexo + ", nombre=" + nombre + "]";
	}
	
}
