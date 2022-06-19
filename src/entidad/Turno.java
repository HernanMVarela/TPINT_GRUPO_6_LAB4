package entidad;

import java.sql.Date;

public class Turno {
	
	private Paciente paciente;
	private Medico medico;
	private Date dia;
	private int hora;
	private Especialidad especialidad;
	private Estado estadoTurno;
	private String ObservacionConsulta;
	private boolean estado;
	
	public Turno() {}
	
	public Turno(Paciente paciente, Medico medico, Date dia, int hora, Especialidad especialidad, Estado estadoTurno,
			String observacionConsulta, boolean estado) {
		this.paciente = paciente;
		this.medico = medico;
		this.dia = dia;
		this.hora = hora;
		this.especialidad = especialidad;
		this.estadoTurno = estadoTurno;
		ObservacionConsulta = observacionConsulta;
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public int getHora() {
		return hora;
	}

	public void setHoraDesde(int hora) {
		if (Verificar0a23(hora)) this.hora = hora;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Estado getEstadoTurno() {
		return estadoTurno;
	}

	public void setEstadoTurno(Estado estadoTurno) {
		this.estadoTurno = estadoTurno;
	}

	public String getObservacionConsulta() {
		return ObservacionConsulta;
	}

	public void setObservacionConsulta(String observacionConsulta) {
		ObservacionConsulta = observacionConsulta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Turno [Paciente=" + paciente + ", medico=" + medico + ", dia=" + dia + ", hora=" + hora
				+ ", especialidad=" + especialidad + ", estadoTurno=" + estadoTurno + ", ObservacionConsulta="
				+ ObservacionConsulta + ", estado=" + estado + "]";
	}
	
	private boolean Verificar0a23(int hora) {
		boolean result = false;		
		if(hora>=0 && hora<24) result = true;		
		return result;
	}

}
