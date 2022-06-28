package daoImpl;

import dao.HorarioDao;
import dao.MedicoDao;
import entidad.Horario;
import entidad.Medico;
import negocio.TurnoNegocio;
import negocioImpl.TurnoNegocioImpl;

public class ProbarDaos {

	public static void main(String[] args) {

		TurnoNegocio negocio = new TurnoNegocioImpl();
		int dia;
		
		String diaString = "01/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "02/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "03/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "04/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "05/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "06/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);
		
		diaString = "07/01/2022";
		dia = negocio.ObtenerDiaSemana(diaString);
		System.out.println("Dia "+ diaString +" es el día de la semana: "+ dia);

	}

}
