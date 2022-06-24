package negocio;

import entidad.Medico;

public interface MedicoNegocio {
	
	public Medico buscar_usuario(int idUser);
	public boolean bajaMedico(Medico baja);
}
