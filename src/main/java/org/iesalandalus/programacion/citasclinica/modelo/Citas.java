package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

public class Citas {

	private int capacidad;
	private int tamano;
	private Cita[] citas;
	

	public Citas(int n) {
		if (n <= 0) 
			throw new IllegalArgumentException("Error:el número de citas debe ser positivo");
		
		capacidad = n;
		citas = new Cita[capacidad];
		tamano = 0;
	}
	
	public Cita[] getCitas() {
		return citas;
	}
	
	public Cita[] getCitas(LocalDate localDate) {
		int total = 0;
		// no se sabe cuantas citas va a encontrar asi que se reserva memoria
		Cita[] aux = new Cita[capacidad]; 
		Cita[] result = null;
		for (int i=0; i<this.getTamano(); i++) {
			if (this.citas[i].getFechaHora().toLocalDate().equals(localDate)) {
				aux[total] = this.citas[i];
				total++;
			}
		}
		// y despues se pasa un array de tamaño justo o nulo si no hay ninguna
		if (total > 0) {
			result = new Cita[total];
			for (int i=0; i<total; i++) {
				result[i] = aux[i];
			}
		}	
		
		return result;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar(Cita cita) throws OperationNotSupportedException {
		
		if (this.capacidadSuperada(getTamano()))
			throw new OperationNotSupportedException("Error: capacidad superada");
		
		if (cita == null) 
			throw new NullPointerException("Error: cita es nulo");

		if (buscar(cita) == null) { // no existe se inserta
			this.citas[this.tamano] = cita;
			this.tamano++;			
		} else
			throw new OperationNotSupportedException("pasar tests");
	}
	
	private int buscarIndice(Cita cita) {
		int tam = getTamano();
		int indice = tam+1;
		boolean encontrado = false;
		LocalDateTime fechaHora = cita.getFechaHora();
		
		for (int i=0; i < tam && !encontrado; i++) {
			if (citas[i].getFechaHora() == fechaHora) {
			    indice = i;
				encontrado = true;
			}
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int val) {
		if (val > getTamano())
			return true;	
		else
			return true;	
	}
	
	private boolean capacidadSuperada(int val) {
		if (val > this.getCapacidad())
			return true;	
		else
			return false;	
	}
	
	public Cita buscar(Cita cita) {
		if (cita == null) 
			throw new NullPointerException("Error: Cita es null");
		
		for (int i=0; i<getTamano(); i++ ) {
			if (citas[i].getFechaHora().equals(cita.getFechaHora()))
				return new Cita(citas[i]);				
		}
		return null;
	}
	
	public void borrar(Cita cita) {
		int indice = -1;
		Cita buscada = buscar(cita);
		if (buscada != null) {
			indice = this.buscarIndice(buscada);
			this.desplazarUnaPosicionHaciaIzquierda(indice);
		} else 
			throw new NullPointerException("Error: La cita buscada no existe");
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int pos) {
	
		if (pos == getTamano()) 
			throw new ArrayIndexOutOfBoundsException("Error: ultimo elemento del array, nada que desplazar");
		if (pos >= getCapacidad()) 
			throw new ArrayIndexOutOfBoundsException("Error: capacidad superada");
		if (pos < 0) 
			throw new IllegalArgumentException("Error: posicion es menor que 0");
//		if (tamanoSuperado(pos)) 
		//		throw new ArrayIndexOutOfBoundsException("Error: acceso a una posicion mayor que la posicion del ultimo elemento");
		
		for (int i = pos; i < getTamano()-1; i++) {
			citas[i] = citas[i+1];
		}
		this.tamano--;
	
	}
	
	@Override
	public String toString() {
		String msg = "";
		for (int i=0; i<getTamano(); i++) {
			msg += citas[i].toString()+"\n";
		}
		return msg+"\ntam:"+this.tamano;
	}
	/*
	public static void main(String args[]) {
		
		Paciente paciente = new Paciente("23305525q","DaviD motOs olmedO","623456789");
		LocalDateTime fechaHora = LocalDateTime.now(), fechaHora2, fechaHora3;
		fechaHora2 = LocalDateTime.of(2020, 11, 1, 23, 59);
	 	Cita cita1  = new Cita(paciente,fechaHora);
		Cita cita2 = new Cita(paciente,fechaHora2);
		System.out.println("cita1:"+cita1.toString());
		System.out.println("cita2:"+cita2.toString());
		Citas citas = new Citas(10);
		System.out.println("capacidad:"+citas.getCapacidad());
		try {
			citas.insertar(cita1);
			System.out.println("citas::"+citas.toString());
			citas.insertar(cita2);
			System.out.println("citas::total:"+citas.getTamano()+citas.toString());

			Cita buscada = citas.buscar(cita2);
			Cita buscada2 = citas.buscar(cita1);
			citas.borrar(cita1);
			System.out.println("borrada 1 total:"+citas.getTamano());
			//citas.borrar(cita2);
			//System.out.println("borrada 2 total:"+citas.getTamano());
			buscada = citas.buscar(cita2);
			//	System.out.println(cita1.toString());
			System.out.println(citas.toString());
			if (buscada != null) // throw en buscar ? 
				System.out.println("buscada:" +buscada.toString());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	} 		*/
}
