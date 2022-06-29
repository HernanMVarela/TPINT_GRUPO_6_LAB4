package negocioImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import entidad.Turno;
import negocio.TurnoNegocio;

public class TurnoNegocioImpl implements TurnoNegocio{

	@Override
	public boolean agregarTurno(Turno agregar) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Agregar(agregar);
	}
	
	@Override
	public boolean Modificar(Turno modificar) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Modificar(modificar);
	}
	
	
	@Override
	public boolean Eliminar(int baja) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Eliminar(baja);
	}

	@Override
	public Turno Buscar(int idTurno) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.Buscar(idTurno);
	}

	@Override
	public ArrayList<Turno> Listar() {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.ListarTodo();
	}

	public int ObtenerDiaSemana (String dia) {
		
		try{ 
			
			boolean diaValido= ValidarDia(dia); 
			if(diaValido==true) 
				{ 
					SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
					java.util.Date date = df.parse(dia);
					df.applyPattern( "EEE" );					
					String day= df.format( date );

					switch (day) {
					case "lun":
						return 1;
					case "mar":
						return 2;
					case "mié":
						return 3;
					case "jue":
						return 4;
					case "vie":
						return 5;
					case "sáb":
						return 6;
					case "dom":
						return 7;
					default:
						return 0;
					}
					
				} else return 0;
			} catch(Exception e) {
				return 0;
				} 
	} 
	
	public boolean ValidarDia(String fecha) {
		String dateArray[]= fecha.split("-");
		int year=Integer.parseInt(dateArray[0]);
		int month=Integer.parseInt(dateArray[1]);
		int day=Integer.parseInt(dateArray[2]);

		boolean leapYear=false; 
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
			leapYear=true;
			} 
		if(year>2099 || year<1900) return false; 
		if(month<13) {
			if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
				if(day>31) return false;
				} else if(month==4||month==6||month==9||month==11) { 
					if(day>30) return false; 
					} else if(leapYear==true && month==2) {
						if(day>29) return false; 
						} else if(leapYear==false && month==2) {
							if(day>28) return false; 
							} return true; 
							} else return false;

	}

	@Override
	public int ContarTurnosTotales() {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.ContarTurnosTotales();
	}

	@Override
	public int ContarTurnosPorEstado(int idEstado) {
		TurnoDao tdao = new TurnoDaoImpl();
		return tdao.ContarTurnosPorEstado(idEstado);
	}
	
}
