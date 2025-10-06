package ar.edu.unlam.interfaz;

import java.util.Scanner;
import ar.edu.unlam.dominio.*;
public class gestorInterfaz {
	
	static Gestor gestor = new Gestor();
	
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		Boolean salir = false;
		
		while(!salir) {
			Integer opcion = menuPrincipal(teclado);
			switch(opcion) {
		//  ------- Agregar Discos----
			case 1:
				Integer opcionDisco = menuDiscos(teclado);
				switch(opcionDisco) {
				case 1:
					agregarDiscoMusica(teclado,gestor);
					break;
				case 2:
					agregarDiscoPelicula(teclado,gestor);
					break;
				case 3:
					agregarDiscoJuego(teclado,gestor);
					break;
				case 4:
					agregarDiscoPrograma(teclado,gestor);
					break;
				default:
					opcionInvalida();
					break;
				}
				break;
		//  ------- Agregar Discos----
		//  ------- Agregar Clientes----
			case 2:
				mostrarPorPantalla("1. Premium");
				mostrarPorPantalla("2. Normal");
				mostrarPorPantalla("Selecciones un tipo de cliente: ");
				Integer opcionCliente = teclado.nextInt();
				
				switch(opcionCliente) {
				case 1:
					agregarClientePremium(teclado,gestor);
					break;
				case 2:
					agregarClienteNormal(teclado,gestor);
					break;
				default:
					opcionInvalida();
					break;
				}
				break;
				
			//  ------- Clientes----
			//  ------- alquiler----
			case 3:
				alquilarDisco(teclado);
				break;
			case 4:
				devolverDisco(teclado);
				break;
			//           ------- alquiler----
			//------------------- ventas=---------------
			case 5:
				venderDisco(teclado);
				break;
			//------------------- ventas=---------------
			case 6:
				salir = true;
				mostrarPorPantalla("Saliendo del programa....");
				break;
			default:
				opcionInvalida();
				break;
			}
		}
		
		
	}
	private static void opcionInvalida() {
		mostrarPorPantalla("Opcion invalida, intenta de nuevo");
	}
	private static void alquilarDisco(Scanner teclado) {
		mostrarPorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		if(gestor.estaElClienteRegistrado(gestor.buscarClientePorDni(dni))) {
			mostrarPorPantalla("Cliente registrado");
		}
		else {
			mostrarPorPantalla("El cliente no esta registrado no se puede alquilar");
		}
	}
	private static void devolverDisco(Scanner teclado) {
		mostrarPorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		if(gestor.estaElClienteRegistrado(gestor.buscarClientePorDni(dni))) {
			mostrarPorPantalla("Cliente registrado");
		}
		else {
			mostrarPorPantalla("El cliente no esta registrado no se puede alquilar");
			gestor.devolverDisco(null, null, null);
		}
	}
	private static void venderDisco(Scanner teclado) {
		mostrarPorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		if(gestor.estaElClienteRegistrado(gestor.buscarClientePorDni(dni))) {
			mostrarPorPantalla("Cliente registrado");
		}
		else {
			mostrarPorPantalla("El cliente no esta registrado no se puede alquilar");
			gestor.venderDisco(null, null, null);
		}
	}
	private static void agregarClienteNormal(Scanner teclado, Gestor gestor) {
		mostrarPorPantalla("Nro Dni del cliente: ");
		Integer clienteDniNormal = teclado.nextInt();
		mostrarPorPantalla("Nombre del cliente: ");
		String nombreClienteNormal = teclado.next();
		mostrarPorPantalla("Apellido del cliente: ");
		String apellidoClienteNormal = teclado.next();
		
		if(clienteDniNormal>0 && !nombreClienteNormal.isEmpty() && !apellidoClienteNormal.isEmpty()) {
			Cliente cliente = new ClienteNormal(clienteDniNormal,nombreClienteNormal,apellidoClienteNormal);
			
			gestor.agregarCliente(cliente);
			
			mostrarPorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarClientePremium(Scanner teclado, Gestor gestor) {
		mostrarPorPantalla("Nro Dni del cliente: ");
		Integer clienteDni = teclado.nextInt();
		mostrarPorPantalla("Nombre del cliente: ");
		String nombreCliente = teclado.next();
		mostrarPorPantalla("Apellido del cliente: ");
		String apellidoCliente = teclado.next();
		Double descuento = 10.0;
		
		if(clienteDni>0 && !nombreCliente.isEmpty() && !apellidoCliente.isEmpty()) {
			Cliente clienteP = new ClientePremium(clienteDni,nombreCliente,apellidoCliente,descuento);
			
			gestor.agregarCliente(clienteP);
			
			mostrarPorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoPrograma(Scanner teclado, Gestor gestor) {
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
		        gestor.agregarDisco(programa);
		        mostrarPorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoJuego(Scanner teclado, Gestor gestor) {
		mostrarPorPantalla("Nombre del juego: ");
		String nombreJuego = teclado.nextLine();
		Consola consola = Consola.PLAY_STATION; 
		mostrarPorPantalla("Precio de venta: ");
		Double precioJuego = teclado.nextDouble();

		
		if (nombreJuego != null && !nombreJuego.isEmpty() && precioJuego > 0) {
		    Disco juego = new Juego(nombreJuego, consola, precioJuego);
		    
		    gestor.agregarDisco(juego);
		    
		    mostrarPorPantalla("Se agrego exitosamente.");
		    }
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoPelicula(Scanner teclado, Gestor gestor) {
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
		        
		        gestor.agregarDisco(peli);
		        
		        mostrarPorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoMusica(Scanner teclado, Gestor gestor) {
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
			
			gestor.agregarDisco(album);
			
			mostrarPorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarPorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static Integer menuDiscos(Scanner teclado) {
		mostrarPorPantalla("1. Musica");
		mostrarPorPantalla("2. Pelicula");
		mostrarPorPantalla("3. Juego");
		mostrarPorPantalla("4. Programa");
		mostrarPorPantalla("Elige un tipo de Disco: ");
		Integer opcionDisco = teclado.nextInt();
		return opcionDisco;
	}
	private static Integer menuPrincipal(Scanner teclado) {
		mostrarPorPantalla("\n=== Menú Tienda de Discos ===");
		mostrarPorPantalla("1. Agregar Disco");
		mostrarPorPantalla("2. Agregar Cliente");
		mostrarPorPantalla("3. Alquilar Disco");
		mostrarPorPantalla("4. Devolver Disco");
		mostrarPorPantalla("5. Vender Disco");
		mostrarPorPantalla("6. Salir");
		mostrarPorPantalla("Elige una opción: ");
		Integer opcion = teclado.nextInt();
		return opcion;
	}
	private static void mostrarPorPantalla(String string) {
		System.out.println(string);
	}
}
