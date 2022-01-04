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
		System.out.println("PROGRAMA CITAS DE UNA CLINICA");
		System.out.println("-----------------------------");
		System.out.println("1 - Insertar cita");
		System.out.println("2 - Buscar cita");
		System.out.println("3 - Borrar cita");
		System.out.println("4 - Mostrar todas las citas");
		System.out.println("5 - Mostrar citas de un dia");
		System.out.println("0 - Salir");
	}
	
	
	public static Opciones elegirOpcion() {
		int num = 0;
		do {
			System.out.println("Elija una opcion:");
			num = Entrada.entero();
		} while (num < 0 || num > 5 );
		
		if (num == 1)
			return Opciones.INSERTAR_CITA;
		else if (num == 2)
			return Opciones.BUSCAR_CITA;
		else if (num == 3)
			return Opciones.BORRAR_CITA;
		else if (num == 4)
			return Opciones.MOSTRAR_CITAS;
		else if (num == 5)
			return Opciones.MOSTRAR_CITAS_DIA;
		else
			return Opciones.SALIR;
		
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
		boolean fechaHoraValida = false;
		
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
	/*
	public static void main(String args[]) {
		// 29/02/2016 23:59
		// 29/02/2017 00:10
		// 19/03/1991 20:00
		Cita cita = null;
		LocalDateTime fechaHora = null;
		Paciente paciente = null;
		try {
			//fechaHora = Consola.leerFechaHora(); ec
			// LocalDate localDate = Consola.leerFecha();
			cita = Consola.leerCita();
			System.out.println(cita.toString());
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
		}
			

	}
*/
}
