package entidad;

public class Pais {
	private int idNacionalidad;
	private String iso;
	private String nombre;
	
	public Pais() {}

	public Pais(int idNacionalidad, String iso, String nombre) {
		this.idNacionalidad = idNacionalidad;
		this.iso = iso;
		this.nombre = nombre;
	}
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Pais [idNacionalidad=" + idNacionalidad + ", iso=" + iso + ", nombre=" + nombre + "]";
	}
			
}
