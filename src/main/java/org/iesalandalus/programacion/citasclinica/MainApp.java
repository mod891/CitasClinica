package org.iesalandalus.programacion.citasclinica;

import org.iesalandalus.programacion.citasclinica.vista.Opciones;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
public class MainApp {

	private static final int NUM_MAX_CITAS = 1;
	private static Citas citasClinica = null;
	public MainApp() {
		citasClinica = new Citas(2);
	
	}
	
	public void ejecutarOpcion(Opciones opcion ) {
		if (opcion == Opciones.BORRAR_CITA)
			this.borrarCita();
		else if (opcion == Opciones.BUSCAR_CITA)
			this.buscarCita();
		else if (opcion == Opciones.INSERTAR_CITA)
			this.insertarCita();
		else if (opcion == Opciones.MOSTRAR_CITAS)
			this.mostrarCitas();
		else if (opcion == Opciones.MOSTRAR_CITAS_DIA)
			this.mostraCitasDia();
		else if (opcion == Opciones.SALIR) return;
	}
	
	public void insertarCita() {
		Cita cita = null;
		try {
			cita = Consola.leerCita();
			citasClinica.insertar(cita);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void buscarCita() {
		
		LocalDateTime fechaHora = Consola.leerFechaHora();
		
		// setPaciente throw !null
		Cita buscada = new Cita(new Paciente("23305525q","a","123123123"), fechaHora);
		buscada = citasClinica.buscar(buscada); 
		if (buscada == null) {
			System.out.println("La cita buscada no existe");
		} else {
			System.out.println(buscada.toString());
		}
	}
	
	public void borrarCita() {
		LocalDateTime fechaHora = Consola.leerFechaHora();
		Cita cita = new Cita(new Paciente("23305525q","a","123123123"), fechaHora);
		citasClinica.borrar(cita);
	}
	
	public void mostraCitasDia() {
		LocalDate fecha = Consola.leerFecha();
		Cita[] citas = citasClinica.getCitas(fecha);
		if (citas != null) {
			System.out.println("Citas del dia "+fecha.toString());
			for (int i=0; i < citas.length; i++) {
				System.out.println(citas[i].toString());
			}
		}
	}
	
	public void mostrarCitas() {
		System.out.println(citasClinica.toString());
	}
	
	public static void main(String[] args) {
		Opciones opcion = null;
		MainApp app = new MainApp();

	do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			try {
				app.ejecutarOpcion(opcion);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			
		} while (opcion != Opciones.SALIR);

	}
	
}
