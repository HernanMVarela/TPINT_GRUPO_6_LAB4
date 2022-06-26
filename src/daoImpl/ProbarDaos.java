package daoImpl;

import dao.HorarioDao;
import dao.MedicoDao;
import entidad.Horario;
import entidad.Medico;

public class ProbarDaos {

	public static void main(String[] args) {

		Medico medico = new Medico();
		MedicoDao daoMedico = new MedicoDaoImpl();
		medico = daoMedico.ObtenerObjeto(1);
		
		Horario horarioViejo = new Horario(medico, 6, 9, 18);
		Horario horarioNuevo = new Horario(medico, 6, 9, 17);
		
		HorarioDao daoHorario = new HorarioDaoImpl();
		
		if(daoHorario.Modificar(horarioViejo, horarioNuevo)) {
			System.out.println("Horario Modificado correctamente");
		} else {
			System.out.println("Horario NO Modificado");
		}

	}

}
