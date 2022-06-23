package negocioImpl;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {

	@Override
	public boolean agregarPersona(Persona agregar) {
		PersonaDao perdao = new PersonaDaoImpl();
	if(!perdao.existedni(agregar.getDni())) {
			return perdao.Agregar(agregar);	
		}else {
			return true; // REVISAR COMO MANEJAR CASO DE PERSONA EXISTENTE, por ahora retorna ok
		}	
	}

}
