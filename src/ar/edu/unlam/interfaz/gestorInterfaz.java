package ar.edu.unlam.interfaz;

import java.util.Scanner;
import ar.edu.unlam.dominio.*;
public class gestorInterfaz {

	public static void main(String[] args) {
	
		Scanner teclado = new Scanner(System.in);
		
		Boolean salir = false;
		
		while(!salir) {
			mostrarPorPantalla("\n=== Menú Tienda de Discos ===");
			mostrarPorPantalla("1. Agregar Disco");
			mostrarPorPantalla("2. Agregar Cliente");
			mostrarPorPantalla("3. Alquilar Disco");
			mostrarPorPantalla("4. Vender Disco");
			mostrarPorPantalla("5. Salir");
			mostrarPorPantalla("Elige una opción: ");
			Integer opcion = teclado.nextInt();
			
			switch(opcion) {
			case 1:
				mostrarPorPantalla("1. Musica");
				mostrarPorPantalla("2. Pelicula");
				mostrarPorPantalla("3. Juego");
				mostrarPorPantalla("4. Programa");
				mostrarPorPantalla("Elige un tipo de Disco: ");
				Integer opcionDisco = teclado.nextInt();
				switch(opcionDisco) {
				case 1:
					mostrarPorPantalla("Nombre del disco: ");
					String nombre = teclado.next();
					mostrarPorPantalla("Nombre del autor: ");
					String autor = teclado.next();
					GeneroMusica genero = GeneroMusica.POP;
					
					mostrarPorPantalla("Numero de canciones: ");
					Integer cantidadDeCanciones = teclado.nextInt();
					mostrarPorPantalla("Precio de venta del disco: ");
					Double precioDeVenta = teclado.nextDouble();

					if (nombre != null && !nombre.isEmpty() &&
						    autor != null && !autor.isEmpty() &&
						    cantidadDeCanciones > 0 && precioDeVenta > 0)  {
						
						Disco album = new Musica(nombre,autor,genero,cantidadDeCanciones,precioDeVenta);
						mostrarPorPantalla("Se agrego exitosamente.");
					}
					else {
						mostrarPorPantalla("Dato incorrecto intente de nuevo.");
					}
					
					
					break;
				case 2:
					 	mostrarPorPantalla("Nombre de la película: ");
	                    String nombrePeli = teclado.next();
	                    GeneroPelicula generoPeli = GeneroPelicula.SUSPENSO; 
	                    mostrarPorPantalla("Año: ");
	                    Integer anio = teclado.nextInt();
	                    teclado.nextLine();
	                    mostrarPorPantalla("Director: ");
	                    String director = teclado.next();
	                    mostrarPorPantalla("Duración (min): ");
	                    Integer duracion = teclado.nextInt();
	                    mostrarPorPantalla("Precio de venta: ");
	                    Double precioVenta = teclado.nextDouble();
	                    mostrarPorPantalla("Precio de alquiler: ");
	                    Double precioAlquiler = teclado.nextDouble();
	                    
	                    
	                    
	                    if (nombrePeli != null && !nombrePeli.isEmpty() &&
	                            director != null && !director.isEmpty() &&
	                            anio > 0 && duracion > 0 &&
	                            precioVenta > 0 && precioAlquiler >= 0) {

	                            Disco peli = new Pelicula(nombrePeli, generoPeli, anio, director, duracion, precioVenta, precioAlquiler);
	                    }
	                    else {
							mostrarPorPantalla("Dato incorrecto intente de nuevo.");
						}
	                    
					break;
				case 3:
					
					mostrarPorPantalla("Nombre del juego: ");
	                String nombreJuego = teclado.nextLine();
	                Consola consola = Consola.PLAY_STATION; 
	                mostrarPorPantalla("Precio de venta: ");
	                Double precioJuego = teclado.nextDouble();

	                
	                if (nombreJuego != null && !nombreJuego.isEmpty() && precioJuego > 0) {
	                    Disco juego = new Juego(nombreJuego, consola, precioJuego);
	                    
	                    mostrarPorPantalla("Se agrego exitosamente.");
	                    }
	                else {
						mostrarPorPantalla("Dato incorrecto intente de nuevo.");
					}
					break;
				case 4:
					mostrarPorPantalla("Nombre del programa: ");
                    String nombrePrograma = teclado.next();
                    mostrarPorPantalla("Versión: ");
                    String version = teclado.next();
                    mostrarPorPantalla("Precio de venta: ");
                    Double precioProg = teclado.nextDouble();
                    mostrarPorPantalla("Precio de alquiler: ");
                    Double precioAlqProg = teclado.nextDouble();
                    
                    if (nombrePrograma != null && !nombrePrograma.isEmpty() &&
                            version != null && !version.isEmpty() &&
                            precioProg > 0 && precioAlqProg >= 0) {

                            Disco programa = new Programa(nombrePrograma, version, precioProg, precioAlqProg);
                    }
                    else {
						mostrarPorPantalla("Dato incorrecto intente de nuevo.");
					}
                    
					break;
				default:
					mostrarPorPantalla("Opcion invalida, intenta de nuevo");
					break;
				}
				
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				salir = true;
				mostrarPorPantalla("Saliendo del programa....");
				break;
			default:
				mostrarPorPantalla("Opcion invalida, intenta de nuevo");
				break;
			}
		}
		
		
	}
	private static void mostrarPorPantalla(String string) {
		System.out.println(string);
	}
}
