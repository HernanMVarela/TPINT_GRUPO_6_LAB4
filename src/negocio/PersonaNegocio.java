package negocio;

import entidad.Persona;

public interface PersonaNegocio {

	public boolean agregarPersona(Persona agregar);
	public boolean existePersona(int dni);
}
