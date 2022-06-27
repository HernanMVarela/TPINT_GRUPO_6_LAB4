package negocio;

import entidad.Medico;

public interface MedicoNegocio {
	
	public Medico buscar_usuario(int idUser);
	public boolean bajaMedico(Medico baja);
	public boolean modificar_usuario(Medico modif_user);
	public boolean agregarMedico(Medico agregar);
	public Medico buscar_dni(int DNI);
	public boolean existe_medico(int DNI);
	public Medico buscar_id(int idMedico);
}
