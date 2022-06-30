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

	PacienteDao pacdao = new PacienteDaoImpl();
	
	@Override
	public Paciente buscar_dni(int dni) {
		return pacdao.buscar_dni(dni);
	}
	
	@Override
	public boolean agregarPaciente(Paciente agregar) {
		PersonaNegocioImpl perneg = new PersonaNegocioImpl();
		
		// VALIDACION PERSONA EXISTENTE
		if(perneg.existePersona(agregar.getDni())) {
			return false;
		}
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
		if(!perneg.agregarPersona(nueva)) {
			return false;
		}
		return pacdao.Agregar(agregar);
	}

	@Override
	public boolean modificarPaciente(Paciente modificar) {

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
		return pacdao.Modificar(modificar);
	}

	@Override
	public Paciente buscar_paciente(int idPaciente) {
		return pacdao.Buscar(idPaciente);
	}

	@Override
	public boolean bajaPaciente(Paciente baja) {
		if(buscar_dni(baja.getDni()) == null) {
			return false;
		}
		return pacdao.Eliminar(baja.getIdPaciente());
	}		

	@Override
	public ArrayList<Paciente> listar() {
		return pacdao.ListarTodo();
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
		return pacdao.ContarPacientes();
	}

	@Override
	public boolean BajaLogica(int idPaciente) {
		if(buscar_paciente(idPaciente) == null) {
			return false;
		}
		return pacdao.BajaLogica(idPaciente);
	}
}
