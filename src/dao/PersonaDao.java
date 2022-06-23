package dao;

import java.util.ArrayList;

import entidad.Persona;

public interface PersonaDao {
	
	public boolean Agregar(Persona persona);
	public ArrayList<Persona> ListarTodo();
	public Persona ObtenerObjeto(int idPersona);
	public boolean Eliminar(int idPersona);
	public boolean Modificar(Persona persona);
	public boolean existedni(int dni);

}
