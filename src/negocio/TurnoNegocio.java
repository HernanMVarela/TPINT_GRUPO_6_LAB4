package negocio;

import entidad.Turno;

public interface TurnoNegocio {

	public boolean agregarTurno(Turno agregar);

	public boolean Modificar(Turno modificar);

	public boolean Eliminar(int baja);

	public Turno Buscar(int idTurno);

}
