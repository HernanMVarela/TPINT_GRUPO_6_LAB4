package dao;

import java.util.ArrayList;

import entidad.Turno;

public interface TurnoDao {

	public boolean Agregar(Turno turno);
	public ArrayList<Turno> ListarTodo();
	public Turno ObtenerObjeto(int idEstado);
	public boolean Modificar(Turno turno);
	
}
