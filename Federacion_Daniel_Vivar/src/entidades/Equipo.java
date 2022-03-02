package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import validaciones.Validaciones;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}

	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}

	public int getAnioinscripcion() {
		return anioinscripcion;
	}

	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Atleta[] getAtletas() {
		return atletas;
	}

	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	// Examen 8 Ejercicio 1 Daniel
	/***
		 * Función que pregunta al usuario por cada uno de los campos para un nuevo
		 * Equipo, los valida y si son correctos devuelve un objeto Equipo completo
		 * 
		 * @return un objeto Equipo completo válido o null si hubo algún error
		 */
		public static Equipo nuevoEquipo() {
			
			Equipo ret = null;
			long idEquipo = -1;
			int anioinscripcion = 0;
			Manager manager = null;
			Atleta[] atletas= null;
			Scanner in;
			boolean valido = false;
	
			do {
				System.out.println("Introduzca el id del nuevo Equipo:");
				in = new Scanner(System.in);
				idEquipo = in.nextLong();
				if (idEquipo > 0)
					valido = true;
				else
					System.out.println("Valor incorrecto para el identificador.");
			} while (!valido);

			valido = false;
			do {
				in = new Scanner(System.in);
				System.out.println("Introduzca el año: ");
				anioinscripcion = in.nextInt();
				valido = Validaciones.validarAnio(anioinscripcion);
				if (!valido)
					System.out.println("ERROR: El valor introducido para el año no es válido.");
			} while (!valido);

		

			System.out.println("Introduzca ahora los datos del Manager:");
			in = new Scanner(System.in);
			manager = Manager.nuevoManager();
			
			System.out.println("Introduzca ahora los datos de los atletas:");
			in = new Scanner(System.in);
			
			
			System.out.println("Introduzca el numero de atletas que se van a incluir en el equipo:");
			in = new Scanner(System.in);
			
			
			
			ArrayList<Atleta> aux = new ArrayList<Atleta>();
			aux.add(Atleta.nuevoAtleta());
			
			
			atletas = aux.toArray(atletas);
//			int eleccion;
//			boolean eleccionValida = false;
//			
//			do {
//				do {
//					Atleta.nuevoAtleta();
//					eleccion = in.nextInt();
//					eleccionValida = (eleccion < 3 || eleccion > 5 ? false : true);
//					if (!eleccionValida)
//						System.out.println("Opcion invalida! Vuelva a introducir su eleccion.");
//
//				} while (!eleccionValida);
//				System.out.println("Usted ha elegido la opcion: " + eleccion);
//
//				if (eleccion == 3) {
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//				}
//
//				if (eleccion == 4) {
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//				}
//				if (eleccion == 5) {
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					aux.add(Atleta.nuevoAtleta());
//					
//				
//
//				}
			ret = new Equipo(idEquipo, anioinscripcion, manager, atletas);
			return ret;
			
		}
		
		private static void exportar(Prueba[] pruebas) {
			String path = "pruebas.txt";
			File fichero = new File(path);
			FileWriter escritor = null;
			PrintWriter buffer = null;
			try {
				try {
					escritor = new FileWriter(fichero, false);
					buffer = new PrintWriter(escritor);
					for (Prueba p : pruebas) {
					//	buffer.println(p.getId(), p.getNombre(), p.getFecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"), p.getLugar(), p.setIndividual(false));
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
		
		

	// Examen 1 Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret += "EQ" + idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion() + ") "
				+ atletas.length + " componentes en el equipo:\n";
		for (Atleta a : atletas) {
			ret += a.getId() + ". " + a.getPersona().getNombre() + "("
					+ a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") "
					+ " Datos físicos:\t" + a.getPeso() + "Kgs.\t" + a.getAltura() + "m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}

}
