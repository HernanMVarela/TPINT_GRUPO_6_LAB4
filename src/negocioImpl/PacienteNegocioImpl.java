package negocioImpl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Excepciones.DniException;
import dao.PacienteDao;
import daoImpl.PacienteDaoImpl;
import entidad.Paciente;
import entidad.Persona;
import negocio.PacienteNegocio;

public class PacienteNegocioImpl implements PacienteNegocio{

	@Override
	public Paciente buscar_dni(int dni) {
		PacienteDao pacdao = new PacienteDaoImpl();
		return pacdao.buscar_dni(dni);
	}
	
	@Override
	public boolean agregarPaciente(Paciente agregar) {
		PacienteDao padao = new PacienteDaoImpl();
		
		try {
			VerificarDniInvalido(Integer.toString(agregar.getDni()));
		} catch (DniException e) {
			return false;
		}
		Persona nueva = new Persona();
		nueva.setDni(agregar.getDni());
		nueva.setNombre(agregar.getNombre());
		nueva.setApellido(agregar.getApellido());
		nueva.setDirecc(agregar.getDirecc());
		nueva.setEmail(agregar.getEmail());
		nueva.setFecha_nacimiento(agregar.getFecha_nacimiento());
		nueva.setNacionalidad(agregar.getNacionalidad());
		nueva.setSexo(agregar.getSexo());
		nueva.setTelefono(agregar.getTelefono());
		PersonaNegocioImpl perneg = new PersonaNegocioImpl();
		if(!perneg.agregarPersona(nueva)) {
			return false;
		}
		return padao.Agregar(agregar);
	}

	@Override
	public boolean modificarPaciente(Paciente modificar) {
		PacienteDao padao = new PacienteDaoImpl();
		PersonaNegocioImpl perneg = new PersonaNegocioImpl();
		Persona modif = new Persona();
		
		modif.setDni(modificar.getDni());
		modif.setNombre(modificar.getNombre());
		modif.setApellido(modificar.getApellido());
		modif.setDirecc(modificar.getDirecc());
		modif.setEmail(modificar.getEmail());
		modif.setFecha_nacimiento(modificar.getFecha_nacimiento());
		modif.setNacionalidad(modificar.getNacionalidad());
		modif.setSexo(modificar.getSexo());
		modif.setTelefono(modificar.getTelefono());
		if(!perneg.Modificar(modif)) {
			return false;
		}
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
		return padao.Eliminar(baja.getIdPaciente());
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
