package negocio;

import entidad.Administrador;

public interface AdministradorNegocio {

	public boolean agregarAdmin(Administrador agregar);
	
	public boolean existeAdmin(int dni);
}
