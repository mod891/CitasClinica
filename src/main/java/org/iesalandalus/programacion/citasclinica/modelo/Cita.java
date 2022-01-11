package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public class Cita {

	private static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente = null;
	
	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		
		setPaciente(paciente);
		
		setFechaHora(fechaHora);
	}
	
	public Cita(Cita cita) {
		
		if (cita == null) { 
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		setFechaHora(cita.getFechaHora());
		setPaciente(cita.getPaciente());
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {

		if (fechaHora == null)
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");

	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA);
	//	this.fechaHora = LocalDateTime.parse("11 08 1991",formatter);
		
		this.fechaHora = fechaHora;
		
	}

	public Paciente getPaciente() {
		return new Paciente(paciente);
	}

	public void setPaciente(Paciente paciente) {
		
		if (paciente == null) 
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
			
		this.paciente = new Paciente(paciente);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
		// dos citas se considerarán iguales si su fecha y hora son las mismas.
		// && Objects.equals(paciente, other.paciente);
	}

	@Override
	public String toString() {
		return "Cita [fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)) + ", paciente=" + paciente.toString() + "]";
	}
	/*
	 raro. en el test falla diciendo que no salta la excepcion. pero recreo el test aquí y si salta
	public static void main(String args[]) {
		Cita cita = null;
		Paciente paciente1 = new Paciente("José Ramón Jiménez Reyes", "11223344B", "950112233");
		try {
			cita = new Cita(paciente1, null);
		} catch (Exception e) {
			System.out.println(e.getMessage()+cita);
		}
	}
*/
}
