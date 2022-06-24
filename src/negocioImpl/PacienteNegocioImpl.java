package negocioImpl;

import dao.PacienteDao;
import daoImpl.PacienteDaoImpl;
import entidad.Paciente;
import negocio.PacienteNegocio;

public class PacienteNegocioImpl implements PacienteNegocio{

	@Override
	public boolean agregarPaciente(Paciente agregar) {
		PacienteDao padao = new PacienteDaoImpl();
		return padao.Agregar(agregar);
	}

	@Override
	public boolean modificarPaciente(Paciente modificar) {
		PacienteDao padao = new PacienteDaoImpl();
		return padao.Modificar(modificar);
	}

	@Override
	public Paciente buscar_paciente(int idPaciente) {
		PacienteDao padao = new PacienteDaoImpl();
		return padao.Buscar(idPaciente);
	}

	@Override
	public boolean bajaPaciente(Paciente baja) {
		PacienteDao padao = new PacienteDaoImpl();
		padao.Eliminar(baja.getIdPaciente());
		return false;
	}
}
