package entidad;

public class Horario {
	
	private int dia;
	private int horaDesde;
	private int horaHasta;
	
	public Horario() {}
	
	public Horario(Medico medico, int dia, int horaDesde, int horaHasta) {
		if (Verificar1a7(dia) && Verificar0a23(horaDesde) && Verificar0a23(horaHasta)) {
			this.dia = dia;
			this.horaDesde = horaDesde;
			this.horaHasta = horaHasta;
		}
	}
	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		if (Verificar1a7(dia)) this.dia = dia;
	}

	public int getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(int horaDesde) {
		if (Verificar0a23(horaDesde)) this.horaDesde = horaDesde;
	}

	public int getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(int horaHasta) {
		if (Verificar0a23(horaHasta)) this.horaHasta = horaHasta;
	}

	private boolean Verificar1a7(int dia) {
		boolean result = false;		
		if(dia>0 && dia<8) result = true;		
		return result;
	}
	
	private boolean Verificar0a23(int hora) {
		boolean result = false;		
		if(hora>=0 && hora<24) result = true;		
		return result;
	}

}
