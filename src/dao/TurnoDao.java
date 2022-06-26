package dao;

import java.util.ArrayList;

import entidad.Turno;

public interface TurnoDao {

	public ArrayList<Turno> ListarTodo();

	public boolean Agregar(Turno turno);

	

}
