package negocio;

import entidad.Medico;

public interface MedicoNegocio {
	
	public Medico buscar_usuario(int idUser);
	public boolean bajaMedico(Medico baja);
	public boolean modificar_usuario(Medico modif_user);
}
