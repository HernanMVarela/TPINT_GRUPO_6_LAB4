package entidad;

public class Sexo {
	
	private int idSex;
	private String Descripcion;
	
	public int getIdSex() {
		return idSex;
	}
	public void setIdSex(int idSex) {
		this.idSex = idSex;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Sexo [idSex=" + idSex + ", Descripcion=" + Descripcion + "]";
	}
	
	public Sexo(int idSex, String descripcion) {
		this.idSex = idSex;
		Descripcion = descripcion;
	}	
	
	public Sexo() {}

}
