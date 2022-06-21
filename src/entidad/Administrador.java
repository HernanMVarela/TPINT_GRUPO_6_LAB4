package entidad;

public class Administrador extends Persona {
	
	private int idAdmin;
	private Persona persona;
	private Usuario usuario;
	private boolean estado;
	
	public Administrador() {}

	public Administrador(int idAdmin, Persona persona, Usuario usuario, boolean estado) {
		super();
		this.idAdmin = idAdmin;
		this.persona = persona;
		this.usuario = usuario;
		this.estado = estado;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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
		return "Administrador [idAdmin=" + idAdmin + ", persona=" + persona + ", usuario=" + usuario + ", estado="
				+ estado + "]";
	}
		
}
