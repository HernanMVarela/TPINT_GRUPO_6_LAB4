package negocio;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoNegocio {
	
	public Medico buscar_usuario(int idUser);
	public boolean bajaMedico(Medico baja);
	public boolean Modificar(Medico medic);
	public boolean agregarMedico(Medico agregar);
	public Medico buscar_dni(int DNI);
	public boolean existe_medico(int DNI);
	public Medico buscar_id(int idMedico);
	public ArrayList<Medico> listar();
	public int contarMedicos();
	public int mejorMedico();
	public int turnosMejorMedico();
}
