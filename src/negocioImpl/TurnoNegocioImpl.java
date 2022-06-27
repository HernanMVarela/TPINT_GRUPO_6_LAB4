package negocioImpl;

import java.util.ArrayList;

import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import entidad.Turno;
import negocio.TurnoNegocio;

public class TurnoNegocioImpl implements TurnoNegocio{

	@Override
	public boolean agregarTurno(Turno agregar) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Agregar(agregar);
	}
	
	@Override
	public boolean Modificar(Turno modificar) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Modificar(modificar);
	}
	
	
	@Override
	public boolean Eliminar(int baja) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Eliminar(baja);
	}

	@Override
	public Turno Buscar(int idTurno) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Buscar(idTurno);
	}

	@Override
	public ArrayList<Turno> Listar() {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.ListarTodo();
	}

	

	
}
