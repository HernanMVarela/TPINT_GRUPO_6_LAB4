package Excepciones;

public class DniException extends Exception {

	private static final long serialVersionUID = 1L;

	public DniException() { }
	
	@Override
	public String getMessage() {
		return "DNI Inválido";
	}
}