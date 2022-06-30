package Excepciones;

public class UsernameException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UsernameException() { }
	
	@Override
	public String getMessage() {
		return "Nombre de usuario inválido";
	}
}
