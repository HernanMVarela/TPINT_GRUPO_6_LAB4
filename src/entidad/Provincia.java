package entidad;

public class Provincia {
	
	private String Nombre;
	private int idProv;
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getIdProv() {
		return idProv;
	}
	public void setIdProv(int idProv) {
		this.idProv = idProv;
	}
	
	@Override
	public String toString() {
		return "Provincia [Nombre=" + Nombre + ", idProv=" + idProv + "]";
	}
	
	public Provincia(String nombre, int idProv) {
		super();
		Nombre = nombre;
		this.idProv = idProv;
	}
	
	public Provincia() {}
	
}
