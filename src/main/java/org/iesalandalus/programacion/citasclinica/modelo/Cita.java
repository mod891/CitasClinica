package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public class Cita {

	private static final String FORMATO_FECHA_HORA = "dd MM yyyy";
	private LocalDateTime fechaHora;
	private Paciente paciente = null;
	
	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		
		setPaciente(paciente);
		
		setFechaHora(fechaHora);
	}
	
	public Cita(Cita cita) {
		if (cita == null) 
			throw new NullPointerException("Error: Cita null");
		setFechaHora(cita.getFechaHora());
		setPaciente(cita.getPaciente());
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		
		if (fechaHora == null) throw new NullPointerException("Error: fechaHora nulo");

	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA);
	//	this.fechaHora = LocalDateTime.parse("11 08 1991",formatter);
		
		this.fechaHora = fechaHora;
		
	}

	public Paciente getPaciente() {
		return new Paciente(paciente);
	}

	public void setPaciente(Paciente paciente) {
		if (paciente == null) 
			throw new NullPointerException("Error: paciente nulo");
			
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
		return "Cita [fechaHora=" + fechaHora + ", paciente=" + paciente.toString() + "]";
	}
	
	public static void main(String args[]) {

		try {
			Paciente paciente = new Paciente("23305525q","DaviD motOs olmedO","623456789");
			LocalDateTime fechaHora = LocalDateTime.now();
			
		 	Cita cita  = new Cita(paciente,fechaHora);
		 	System.out.println(paciente.toString());
		 	Cita cita2   = new Cita(cita);
		 	System.out.println(cita2.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

}
