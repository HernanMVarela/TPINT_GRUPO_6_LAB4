package dao;

import java.util.ArrayList;

import entidad.Administrador;
import entidad.Medico;

public interface MedicoDao {
	
	public ArrayList<Medico> ListarTodo();
	
	public boolean Agregar(Medico medico);

	public boolean Modificar(Medico medico);
	
	public boolean Modif_user(Medico medico);

	public boolean Eliminar(int idMedico);

	public int UltimoID();

	public Medico Buscar(int idMedico);
	
	public Medico BuscarDNI(int DNI);
	
	public Medico buscar_usuario(int idUser);
	
	public boolean bajaMedico(int idAdmin);
	
	public boolean existeMedico(int DNI);

	
	
}
