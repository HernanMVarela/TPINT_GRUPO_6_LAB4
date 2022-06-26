package dao;

import java.util.ArrayList;

import entidad.Horario;

public interface HorariosDao {
	public boolean Agregar(int idMedico, Horario hora);
	public ArrayList<Horario> Listar(int idMedico);
}
