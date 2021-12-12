package org.iesalandalus.programacion.citasclinica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {

	private static String ER_DNI = "(([0-9]{8})([a-z]))";//"[0-9]{8}";//"([0-9]{8})(.)";//;
	private static String ER_TELEFONO = "\\(d{9})";
	
	private String nombre;
	private String dni;
	private String telefono;

	
	
	public Paciente(String dni, String nombre, String telefono) {

		setDni(dni);
		
		setNombre(nombre);
		
		setTelefono(telefono);

	}
	public Paciente(Paciente paciente) {
		if (paciente == null) 
			throw new NullPointerException("paciente es null");	
		// desdoblamiento del tiempo
		setDni(paciente.getDni());
		setNombre(paciente.getNombre());
		setTelefono(paciente.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/*
	 * Crea el método formateaNombre. Este método debe normalizar un nombre eliminando caracteres en blanco de sobra 
	 * y poniendo en mayúsculas la primera letra de cada palabra y en minúsculas las demás.
	 */
	private String formateaNombre(String nombre) {
		// regexp java
	//	Pattern erNombre = Pattern.compile("([A-z]*) ([A-z]*) ([A-z]*)");
	//	Matcher matcher = erNombre.matcher(nombre); matches();
		char primeraLetra;
		String aux, result="";
		
		nombre = nombre.trim();
		String [] pts = nombre.split("\\W+");
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
	Crea el método comprobarLetraDni. 	Este método hará uso de los grupos de
	las expresiones regulares (para ello has debido definir la expresión regular con grupos)
	para quedarse con el número por un lado y con la letra por otro. Para saber si la letra es válida 
	puedes seguir las instrucciones del siguiente enlace
	*/
	private boolean comprobarLetraDni(String dni) {
		/*
		 	algoritmo letra dni: parte entera / 23 y resto de dividir 0 a 22 equivale a una letra
		 */
		int digitos;
		char letra;
		char [] letras = { 'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		Pattern pattern = Pattern.compile(ER_DNI);
		Matcher matcher = pattern.matcher(dni); 
		
		// while (matcher.find()) System.out.println(matcher.group() +" group");
		
		if (matcher.matches()) {
			/*	System.out.println(matcher.group(3)+" 3 c");
			System.out.println(matcher.group(2)+" 2 int");
			*/	
			digitos = Integer.parseInt( matcher.group(2) );
			letra =  matcher.group(3).charAt(0) ;
			System.out.println(digitos+":"+letra);
			
		}
		
		return true;
	}
	public static void main(String args[]) {
		String aux="david motOs olmEdo";
		Paciente p = new Paciente("21231532a","a","a");
		p.comprobarLetraDni("21231532a");
		System.out.println(p.formateaNombre(aux));
	}
}
