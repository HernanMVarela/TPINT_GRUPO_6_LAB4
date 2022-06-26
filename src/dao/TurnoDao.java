package dao;

import java.util.ArrayList;

import entidad.Medico;
import entidad.Turno;

public interface TurnoDao {

	public ArrayList<Turno> ListarTodo();

	public boolean Agregar(Turno turno);

	public boolean Modificar(Turno turno);

	public boolean Eliminar(int idTurno);

	public Turno Buscar(int idTurno);

	public int UltimoID();

	

}
