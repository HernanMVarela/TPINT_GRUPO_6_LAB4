package dao;

import java.util.ArrayList;

import entidad.Administrador;

public interface AdministradorDao {
	
	public boolean Agregar(Administrador administrador);
	public boolean Modificar(Administrador administrador);
	public boolean Eliminar(int idAdministrador);
	public Administrador ObtenerObjeto(int idAdmin);
	public ArrayList<Administrador> ListarTodo();

}
