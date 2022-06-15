package entidad;

public class Usuario {
	private int idUsuario;
	private int idTipo;
	private Boolean Estado;
	private String UserName;
	private String Password;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", idTipo=" + idTipo + ", Estado=" + Estado + ", UserName="
				+ UserName + ", Password=" + Password + "]";
	}
	
	public Usuario(int idUsuario, int idTipo, Boolean estado, String userName, String password) {
		super();
		this.idUsuario = idUsuario;
		this.idTipo = idTipo;
		Estado = estado;
		UserName = userName;
		Password = password;
	}
	
	public Usuario () {}
	
}
