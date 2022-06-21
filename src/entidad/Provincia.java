package entidad;

public class Provincia {
	
	private int idProv;
	private String nombre;
	private Pais pais;
		
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Provincia() {}
	
	public Provincia(int idProv, String nombre) {
		this.idProv = idProv;
		this.nombre = nombre;
	}
	public int getIdProv() {
		return idProv;
	}
	public void setIdProv(int idProv) {
		this.idProv = idProv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Provincia [idProv=" + idProv + ", nombre=" + nombre + "]";
	}
	
}
