package entidades;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import utils.Datos;
import validaciones.Validaciones;

public class Manager {
	private static Object[] MANAGERS;
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public Manager() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	// Examen 5 Ejercicio 4
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Manager, los valida y si son correctos devuelve un objeto Manager completo
	 * 
	 * @return un objeto Manager completo válido o null si hubo algún error
	 */
	public static Manager nuevoManager() {
		Manager ret = null;
		long id = -1;
		String telefono = "";
		String direccion = "";
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo mánager:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca el telefono de empresa del nuevo mánager");
			telefono = in.nextLine();
			valido = Validaciones.validarTelefono(telefono);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teéfono no es válido.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca la dirección del nuevo mánager:");
			direccion = in.next();
			valido = Validaciones.validarDireccion(direccion);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la dirección no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}

	/**
	 * /*** Ejercicio3 (Daniel) Función que devuelve una cadena de caracteres con
	 * los datos del mánager en el
	 * <DatosPersona.id>|<DatosPersona.nombre>|<DatosPersona.documentacion>|<DatosPersona.fec
	 * haNac>|<DatosPersona.telefono>|<Manager.id>|<Manager.telefono>|<Manager.direccion>
	 * 
	 * public String data() { String ret = "";
	 * 
	 * ret = this.persona.getId() + "|" + this.persona.getNombre() + "|" +
	 * this.persona.getNifnie() + "|" + this.persona.getFechaNac() + "|" +
	 * this.persona.getTelefono() + "|" + this.getId() + "|" + this.getTelefono() +
	 * "|" + this.getDireccion(); return ret; }
	 * 
	 * public static void exportDtManager() { System.out.println("Datos guardados
	 * del Manager.txt"); File fOut = new File("datosmanagers.txt"); FileWriter fw =
	 * null; BufferedWriter bw = null; String data; try { fw = new FileWriter(fOut);
	 * bw = new BufferedWriter(fw);
	 * 
	 * for (int i = 0; i < Datos.MANAGERS.length; i++) { Manager m = new Manager();
	 * m = Datos.MANAGERS[i]; bw.write(m.data() + "\n"); bw.close(); } } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

////Examen 6 Ejercicio 3
	/**
	 * Función que devuelve una cadena de caracteres con la siguiente estructura
	 * <DatosPersona.id>|<DatosPersona.nombre>|<DatosPersona.documentacion>|<DatosPersona.fec
	 * haNac>|<DatosPersona.telefono>|<Manager.id>|<Manager.telefono>|<Manager.direccion>
	 * Cada campo se separa mediante el caracter '|'
	 * 
	 * @return
	 */
	public String data() {
		return "" + persona.getId() + "|" + persona.getNombre() + "|" + persona.getNifnie().mostrar() + "|"
				+ persona.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "|" + persona.getTelefono()
				+ "|" + this.id + "|" + this.telefono + "|" + this.direccion;
	}

	/***
	 * Función para exportar los datos de cada uno de los mánagers de una colección
	 * que se le pasa como parámetro, a través del método data() anterior, separando
	 * la información de cada mánager en una línea distinta.
	 * 
	 * @param managers la coleccion de managers a exportar
	 */
	private static void exportar(Manager[] managers) {
		String path = "managers.txt";
		File fichero = new File(path);
		FileWriter escritor = null;
		PrintWriter buffer = null;
		try {
			try {
				escritor = new FileWriter(fichero, false);
				buffer = new PrintWriter(escritor);
				for (Manager m : managers) {
					buffer.println(m.data());
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (escritor != null) {
					escritor.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

	/**
	 * Ejercicio 3 Daniel
	 */
	public static void MapDocumentacion() {
		
		System.out.println("********* TreeMap *********");
		Map <String, Documentacion> managers = new TreeMap<String, Documentacion>();
	//	managers.put("Rosa", new Documentacion(1, 2020, MANAGERS[0], equipo1));
	//	managers.put("Laura", new Documentacion(2, 2020, MANAGERS[1], equipo2));
	//	managers.put("Ramon", new Documentacion(3, 2020, MANAGERS[2], equipo3));
	
	}
	
	/***
	 * Función que devuelve una cadena de caracteres con los datos del mánager en el
	 * siguiente formato: <idManager> <nombre> ” (” <documentacion> ”) del año ”
	 * <fechaNac.año> ” Tfno1: <Manager.telefono>” ,Tfno2: ” <DatosPersona.telefono>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + " Tfno1: " + telefono + " , Tfno2:" + persona.getTelefono() + "]";
	}

}
