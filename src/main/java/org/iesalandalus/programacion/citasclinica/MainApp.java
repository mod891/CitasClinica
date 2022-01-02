package org.iesalandalus.programacion.citasclinica;

import org.iesalandalus.programacion.citasclinica.vista.Opciones;

import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
public class MainApp {

	private static final int NUM_MAX_CITAS = 1;
	private static Citas citasClincia = null;
	public MainApp() {
		citasClincia = new Citas(2);
	
	}
	
	public void ejecutarOpcion(Opciones opcion ) {
		
	}
	
	public void insertarCita() {
		Cita cita = null;
		try {
			cita = Consola.leerCita();
			citasClincia.insertar(cita);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void buscarCita() {
		LocalDateTime fechaHora = Consola.leerFechaHora();
		
		//public Cita(Paciente paciente, LocalDateTime fechaHora)
		// setPaciente throw !null
		Cita buscada = new Cita(new Paciente("23305525q","a","123123123"), fechaHora);
		buscada = citasClincia.buscar(buscada); 
		if (buscada == null) {
			System.out.println("La cita buscada no existe");
		} else {
			System.out.println(buscada.toString());
		}
	}
	
	public void borrarCita() {
		
	}
	
	public void mostraCitasDia() {
		
	}
	
	public void mostrarCitas() {
		System.out.println(citasClincia.toString());
	}
	
	public static void main(String[] args) {
		
		MainApp app = new MainApp();
		app.insertarCita();
		app.mostrarCitas();
		app.buscarCita();

	}
	
}
