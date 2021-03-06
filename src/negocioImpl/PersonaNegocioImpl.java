package negocioImpl;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {

	@Override
	public boolean agregarPersona(Persona agregar) {
		PersonaDao perdao = new PersonaDaoImpl();
		return perdao.Agregar(agregar);
	}

	@Override
	public boolean existePersona(int dni) {
		PersonaDao perdao = new PersonaDaoImpl();
		return perdao.existedni(dni);
	}

	@Override
	public Persona buscarPersona(int dni) {
		PersonaDao perdao = new PersonaDaoImpl();
		return perdao.ObtenerObjeto(dni);
	}

	@Override
	public boolean Modificar(Persona perso) {
		PersonaDao perdao = new PersonaDaoImpl();
		return perdao.Modificar(perso);
	}

	@Override
	public boolean Eliminar(int dni) {
		PersonaDao perdao = new PersonaDaoImpl();
		if(perdao.existedni(dni)) {
			return perdao.Eliminar(dni);
		}
		return false;
	}

}
