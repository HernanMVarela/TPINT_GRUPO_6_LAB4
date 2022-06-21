package entidad;

import java.sql.Date;

public class Administrador extends Persona {
	
	private int idAdmin;
	private Usuario usuario;
	private boolean estado;
	
	public Administrador() {}

	public Administrador(int idAdmin, Persona persona, Usuario usuario, boolean estado) {
		super(persona.getDni(),persona.getNombre(),persona.getApellido(),persona.getNacionalidad(),persona.getDirecc(),persona.getSexo(),persona.getEmail(),persona.getTelefono(),persona.getFecha_nacimiento());
		this.idAdmin = idAdmin;
		this.usuario = usuario;
		this.estado = estado;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
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
		return "Administrador [idAdmin=" + idAdmin + ", usuario=" + usuario + ", estado="
				+ estado + "]";
	}
		
}
