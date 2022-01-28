package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {

	private static String ER_DNI = "(([0-9]{8})([A-Z|a-z]))";
	private static String ER_TELEFONO = "([0-9]{9})";
	
	private String nombre;
	private String dni;
	private String telefono;

	
	
	public Paciente( String nombre, String dni, String telefono) {

		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente paciente) {
		if (paciente == null) 
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");	
		setNombre(paciente.getNombre());
		setDni(paciente.getDni());
		setTelefono(paciente.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().equals("")  ) 
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		
		this.nombre = formateaNombre(nombre);
		
	}
	
	public String getIniciales() {
		String [] pts = getNombre().split("\\s");
		String resultado = "";
		for (int i=0; i<pts.length; i++) {
			resultado += pts[i].charAt(0);
		}
		return resultado;
	}
	public String getDni() {
		return dni;
	}
	
	
	private void setDni(String dni) {
		
		if (dni == null || dni.trim().equals("") ) 
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		
		if (comprobarLetraDni(dni)) 
			this.dni = dni;
		else 
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		
		if (telefono == null || telefono.trim().equals("") )
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		
		Pattern pattern = Pattern.compile(ER_TELEFONO);
		Matcher matcher = pattern.matcher(telefono); 
	
		if (matcher.matches()) {
			this.telefono = matcher.group();
		} else
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
	}
	
	/*
	 * Crea el método formateaNombre. Este método debe normalizar un nombre eliminando caracteres en blanco de sobra 
	 * y poniendo en mayúsculas la primera letra de cada palabra y en minúsculas las demás.
	 */
	private String formateaNombre(String nombre) {

		char primeraLetra;
		String aux, result="";
		nombre = nombre.trim();
		String [] pts = nombre.split("\\s+");// W+  words en ingles no llevan tildes s+ 1 o + espacios
		
		for (int i=0; i<pts.length; i++) {
			primeraLetra = pts[i].charAt(0);
			aux = primeraLetra+"";
			aux = aux.toUpperCase();
			result += aux+pts[i].substring(1,pts[i].length()).toLowerCase()+" ";	
		}
		result = result.substring(0,result.length()-1);
		
		return result;
	}
	
/*
 	algoritmo letra del dni
 */
	private boolean comprobarLetraDni(String dni) {
	
		int num=0;
		char letra = '\0';
		char [] letras = {  'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E' };
		
		Pattern pattern = Pattern.compile(ER_DNI);
		Matcher matcher = pattern.matcher(dni); 
	
		if (matcher.matches()) {
		
			num = Integer.parseInt( matcher.group(2) );
			letra =  Character.toUpperCase( matcher.group(3).charAt(0) );

		} else
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		
		num = (num % 23);
		
		if (letras[num] == letra) 
			return true;		
		 else 
			return false;	
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
		// && Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	// sabiendo que dos pacientes se considerarán iguales si su DNI es el mismo.
	}
	
	@Override
	public String toString() {
		return "nombre="+this.getNombre()+
				" (" + this.getIniciales() + "), "+
				"DNI="+this.getDni()+", "+
				"teléfono="+this.getTelefono();
	}
	
	public static void main(String args[]) {
		Paciente p = new Paciente("José Ramón Jiménez Reyes", "23305525t", "950112233");
		System.out.println(p.toString());
	}
	
}
