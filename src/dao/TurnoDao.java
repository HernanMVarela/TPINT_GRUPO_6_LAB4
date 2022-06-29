package dao;

import java.util.ArrayList;

import entidad.Medico;
import entidad.Turno;

public interface TurnoDao {

	public boolean Agregar(Turno turno);
	public ArrayList<Turno> ListarTodo();
	public Turno ObtenerObjeto(int idEstado);
	public boolean Modificar(Turno turno);

	public boolean Eliminar(int idTurno);

	public Turno Buscar(int idTurno);

	public int UltimoID();
	
	public int ContarTurnosTotales();
	
	public int ContarTurnosPorEstado(int idEstado);
	
}
