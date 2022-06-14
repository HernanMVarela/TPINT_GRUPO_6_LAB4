package entidad;

public class Localidad {
	
	private String Nombre;
	private int idLoc;
	private Provincia Pcia;
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getIdLoc() {
		return idLoc;
	}
	public void setIdLoc(int idLoc) {
		this.idLoc = idLoc;
	}
	
	public Provincia getPcia() {
		return Pcia;
	}
	public void setPcia(Provincia pcia) {
		Pcia = pcia;
	}
	
	@Override
	public String toString() {
		return "Localidad [Nombre=" + Nombre + ", idLoc=" + idLoc + ", Pcia=" + Pcia + "]";
	}
	
	public Localidad(String nombre, int idLoc, Provincia pcia) {
		super();
		Nombre = nombre;
		this.idLoc = idLoc;
		Pcia = pcia;
	}
	
	public Localidad() {}
	
}
