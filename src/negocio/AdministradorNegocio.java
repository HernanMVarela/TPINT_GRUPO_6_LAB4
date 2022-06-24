package negocio;

import entidad.Administrador;

public interface AdministradorNegocio {

	public boolean agregarAdmin(Administrador agregar);
	public boolean existeAdmin(int dni);
	public Administrador buscar_usuario(int idUser);
	public boolean bajaAdmin(Administrador baja);
}
