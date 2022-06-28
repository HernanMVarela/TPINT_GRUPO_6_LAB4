package negocio;

import java.util.ArrayList;

import entidad.Turno;

public interface TurnoNegocio {

	public boolean agregarTurno(Turno agregar);
	public boolean Modificar(Turno modificar);
	public boolean Eliminar(int baja);
	public Turno Buscar(int idTurno);	
	public ArrayList<Turno> Listar();
	public int ObtenerDiaSemana (String dia);
	public boolean ValidarDia(String fecha);

}
