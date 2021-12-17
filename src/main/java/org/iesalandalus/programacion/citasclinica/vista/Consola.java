package org.iesalandalus.programacion.citasclinica.vista;

import java.time.DateTimeException;
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
	
	public static Cita leerCita() throws UnsupportedOperationException {
		Paciente paciente = null;
		LocalDateTime fechaHora = null;
		
		paciente =  leerPaciente(); 
		fechaHora = leerFechaHora();

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
	public static LocalDateTime leerFechaHora() {
		
		LocalDateTime fechaHora = null;
		LocalDate fecha;
		boolean fechaHoraValida = false, fechaValida = false, horaValida = false;		
	
		
		String str;
		String [] pts, arrayFecha, arrayHora;
		int anio=0, mes=0, dia=0, hora=0, minuto=0;
		
		while (!fechaHoraValida) {
			
			System.out.println("Introduzca la fecha y hora en formato dd/mm/yyyy HH:mm ej: 10/11/2100 00:59");
			str = Entrada.cadena();
			pts = str.split("\\s");
			
			if (pts.length == 2) {				
				arrayFecha = pts[0].split("/"); // fecha				
				arrayHora = pts[1].split(":"); // hora
				
				if (arrayFecha.length == 3) { 
					anio = Integer.parseInt(arrayFecha[2]);
					mes = Integer.parseInt(arrayFecha[1]);
					dia  = Integer.parseInt(arrayFecha[0]);		
				} 
				if (arrayHora.length == 2) {
					hora = Integer.parseInt(arrayHora[0]);
					minuto = Integer.parseInt(arrayHora[1]);
				}
				try {
					fechaHora = LocalDateTime.of(anio,mes,dia,hora,minuto,0);
					fechaHoraValida = true;
					
				} catch (DateTimeException e ) {
					System.out.println(e.getMessage());
				}
			}
		}
		return fechaHora;
	}
	public static LocalDate leerFecha() {
		LocalDate localDate = null;
		boolean fechaValida = false;
		String fecha;
		String [] pts;
		while (!fechaValida) {
			
			System.out.println("Introduzca una fecha en formato dd/mm/yyyy");
			fecha = Entrada.cadena();
		
			try {
				pts = fecha.split("/");
				localDate = LocalDate.of(Integer.parseInt(pts[0]),Integer.parseInt(pts[1]),Integer.parseInt(pts[2]));
				fechaValida = true;
			} catch (DateTimeException e) {
				System.out.println(e.getMessage());
				
			}
		}
		
		return localDate;
	}
	
	public static void main(String args[]) {
		// 29/02/2016 23:59
		// 29/02/2017 00:10
		// 19/03/1991 20:00
		Cita cita = null;
		LocalDateTime fechaHora = null;
		Paciente paciente = null;
		try {
			//fechaHora = Consola.leerFechaHora();
			LocalDate localDate = Consola.leerFecha();
			System.out.println(localDate.toString());
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
		}
			

	}

}
