package negocioImpl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Excepciones.DniException;
import dao.PacienteDao;
import daoImpl.PacienteDaoImpl;
import entidad.Paciente;
import negocio.PacienteNegocio;

public class PacienteNegocioImpl implements PacienteNegocio{

	@Override
	public Paciente buscar_id(int idPaciente) {
		PacienteDao pacdao = new PacienteDaoImpl();
		return pacdao.ObtenerObjeto(idPaciente);
	}
	
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

	@Override
	public ArrayList<Paciente> listar() {
		PacienteDao padao = new PacienteDaoImpl();
		return padao.ListarTodo();
	}

	@Override
	public void VerificarDniInvalido(String dni) throws DniException {
		if(!dni.matches("[0-9]+"))
		{
			throw new DniException();
		}
		
	}

	@Override
	public int contarPacientes() {
		PacienteDao padao = new PacienteDaoImpl();
		return padao.ContarPacientes();
	}
}
