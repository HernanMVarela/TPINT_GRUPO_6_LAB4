package negocioImpl;

import java.util.ArrayList;

import dao.MedicoDao;
import daoImpl.MedicoDaoImpl;
import entidad.Horario;
import entidad.Medico;
import entidad.Persona;
import entidad.Usuario;
import negocio.HorarioNegocio;
import negocio.MedicoNegocio;
import negocio.PersonaNegocio;
import negocio.UsuarioNegocio;

public class MedicoNegocioImpl implements MedicoNegocio{

	@Override
	public boolean agregarMedico(Medico agregar) {
		MedicoDao medao = new MedicoDaoImpl();
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		HorarioNegocio horasneg = new HorarioNegocioImpl();
		
		if(perneg.existePersona(agregar.getDni())) {
			return false;
		}
		if(userneg.existeUsuario(agregar.getUsuario().getUser())>0 ){
			return false;
		}
		
		if(existe_medico(agregar.getDni())){
			return false;
		}
		
		if(!perneg.agregarPersona(new Persona(
				agregar.getDni(),
				agregar.getNombre(),
				agregar.getApellido(),
				agregar.getNacionalidad(),
				agregar.getDirecc(),
				agregar.getSexo(),
				agregar.getEmail(),
				agregar.getTelefono(),
				agregar.getFecha_nacimiento()))) {
			return false;
		}
		
		// AGREGA NUEVO USUARIO
		if(!userneg.agregarUsuario(new Usuario(agregar.getUsuario().getUser(), agregar.getUsuario().getPassword(), agregar.getUsuario().getTipo()))) {
			perneg.Eliminar(agregar.getDni()); // ROLLBACK DE PERSONA
			return false;
		}
		
		// SETEA ID DE NUEVO USUARIO
		agregar.getUsuario().setIdUsuario(userneg.existeUsuario(agregar.getUsuario().getUser()));

		// AGREGA MEDICO
		if(!medao.Agregar(agregar)) {
			return false;
		}
		// OBTIENE ID DE MEDICO AGREGADO
		int idMedico = buscar_dni(agregar.getDni()).getIdMedico();
		
		// CARGA HORAS DEL MEDICO
		for (Horario hora : agregar.getHorarios()) {
			horasneg.Agregar(idMedico,hora);
		}
		return true;
		
	}
	
	@Override
	public Medico buscar_usuario(int idUser) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.buscar_usuario(idUser);
	}

	@Override
	public boolean bajaMedico(Medico baja) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.bajaMedico(baja.getIdMedico());
	}

	@Override
	public boolean Modificar(Medico medic) {
		MedicoDao meddao = new MedicoDaoImpl();
		PersonaNegocio perneg = new PersonaNegocioImpl();
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		HorarioNegocio horasneg = new HorarioNegocioImpl();
		
		if(!perneg.Modificar(new Persona(
				medic.getDni(),
				medic.getNombre(),
				medic.getApellido(),
				medic.getNacionalidad(),
				medic.getDirecc(),
				medic.getSexo(),
				medic.getEmail(),
				medic.getTelefono(),
				medic.getFecha_nacimiento()))) {
			return false;
		}
		
		// AGREGA NUEVO USUARIO
		int userid = userneg.existeUsuario(medic.getUsuario().getUser());
		if(userneg.Modificar(new Usuario(userid, medic.getUsuario().getUser(), medic.getUsuario().getPassword(), medic.getUsuario().getTipo()))) {
			return false;
		}
		medic.getUsuario().setIdUsuario(userid);
		
		if(!meddao.Modificar(medic)) {
			return false;
		}	
		// MODIFICAR HORAS DEL MEDICO
		horasneg.Modificar(medic.getIdMedico(),medic.getHorarios());

		
		return meddao.Modificar(medic);
	}

	@Override
	public Medico buscar_dni(int DNI) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.BuscarDNI(DNI);
	}

	@Override
	public boolean existe_medico(int DNI) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.existeMedico(DNI);
	}

	@Override
	public Medico buscar_id(int idMedico) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.ObtenerObjeto(idMedico);
	}

	@Override
	public ArrayList<Medico> listar() {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.ListarTodo();
	}

	@Override
	public int contarMedicos() {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.ContarMedicos();
	}

	@Override
	public int mejorMedico() {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.MejorMedico();
	}

	@Override
	public int turnosMejorMedico() {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.TurnosMejorMedico();
	}


}
