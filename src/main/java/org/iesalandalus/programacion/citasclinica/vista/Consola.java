package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {
		
	}
	public static void mostrarMenu() {
		System.out.println("1 - Insertar cita");
		System.out.println("2 - Buscar cita");
		System.out.println("3 - Borrar cita");
		System.out.println("4 - Mostrar todas las citas");
		System.out.println("5 - Mostrar citas de una fecha");
		System.out.println("6 - salir");
	}
	
	
	public static Opciones elegirOpcion() {
		int num = 0;
		Opciones opcion = null;
		do {
			System.out.println("Elija una opcion:");
			num = Entrada.entero();
		} while (num >= 1 || num <= 6 );
		
		switch (num) {
		case 1:
			opcion = Opciones.INSERTAR_CITA;
		case 2:
			opcion = Opciones.BUSCAR_CITA;
		case 3:
			opcion = Opciones.BORRAR_CITA;
		case 4:
			opcion = Opciones.MOSTRAR_CITAS;
		case 5:
			opcion = Opciones.MOSTRAR_CITAS_DIA;
		case 6:
			opcion = null;
		
		}
		
		return opcion;	
	
	}
	
	public static Cita leerCita() {
		Paciente paciente = null;
		LocalDateTime fechaHora = null;
		return new Cita(paciente,fechaHora);
	}
	public static Paciente leerPaciente() throws UnsupportedOperationException {
		String nombre, dni, telefono;
		Paciente paciente = null;
		System.out.println("Nombre del paciente:");
		nombre = Entrada.cadena();
		System.out.println("DNI:");
		dni = Entrada.cadena();
		System.out.println("telefono:");
		telefono = Entrada.cadena();
		
		return new Paciente(dni,nombre,telefono);
		 
	}
	/*
	Crea el método leerFechaHora que nos pedirá que introduzcamos una cadena correspondiente a
	una fecha y hora en el formato adecuado y devolverá el objeto LocalDateTime correspondiente. 
	Esto se repetirá mientras la fecha y hora introducida no sea válida. */
	public LocalDateTime leerFechaHora() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm");
		LocalDateTime fechaHora = null;
		boolean fechaHoraValida = false;
		
		// regexp? matcher no porque limites de minutos max 60. regexp no
		// comprobacion cadena con strings
		
		String str;
		String [] pts;
		while (!fechaHoraValida) {
			
			System.out.println("Introduzca la fecha y hora en formato dd/mm/yyyy HH:mm ej: 10/11/2100 00:59");
			str = Entrada.cadena();
			fechaHora = LocalDateTime.parse(str,formatter);
			// 1ero regexp
		
			// 2ndo comprobacion rango valido + añobisiesto + 31 / 30 
			
			System.out.println(fechaHora.toString()); // ???
			//str.split("\\s");
			
			
			
		} 
		
		
		return fechaHora;
	}
	public LocalDate leerFecha() {
		LocalDate localDate = null;
		return localDate;
	}

}
