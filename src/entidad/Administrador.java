package entidad;

public class Administrador {
	
	private int idAdmin;
	private DatoPersonal datoPersonal;
	private Usuario usuario;
	private boolean estado;
	
	public Administrador() {}
	
	public Administrador(int idAdmin, DatoPersonal datoPersonal, Usuario usuario, boolean estado) {
		this.idAdmin = idAdmin;
		this.datoPersonal = datoPersonal;
		this.usuario = usuario;
		this.estado = estado;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public DatoPersonal getDatoPersonal() {
		return datoPersonal;
	}

	public void setDatoPersonal(DatoPersonal datoPersonal) {
		this.datoPersonal = datoPersonal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Administrador [idAdmin=" + idAdmin + ", datoPersonal=" + datoPersonal + ", usuario=" + usuario
				+ ", estado=" + estado + "]";
	}
	
}
