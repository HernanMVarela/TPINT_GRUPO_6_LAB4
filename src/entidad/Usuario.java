package entidad;

public class Usuario {
	
	private int idUsuario;
	private String user;
	private String password;
	private Tipo tipo;
	
	public Usuario () {}

	public Usuario(int idUsuario, String user, String password, Tipo tipo) {
		this.idUsuario = idUsuario;
		this.user = user;
		this.password = password;
		this.tipo = tipo;
	}
	public Usuario(String user, String password, Tipo tipo) {
		this.user = user;
		this.password = password;
		this.tipo = tipo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", user=" + user + ", password=" + password + ", tipo=" + tipo + "]";
	}
	
}
