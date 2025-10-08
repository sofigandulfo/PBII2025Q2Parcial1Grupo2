package ar.edu.unlam.interfaz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlam.dominio.Cliente;
import ar.edu.unlam.dominio.ClienteNormal;
import ar.edu.unlam.dominio.ClientePremium;
import ar.edu.unlam.dominio.Consola;
import ar.edu.unlam.dominio.Disco;
import ar.edu.unlam.dominio.GeneroMusica;
import ar.edu.unlam.dominio.GeneroPelicula;
import ar.edu.unlam.dominio.Gestor;
import ar.edu.unlam.dominio.Juego;
import ar.edu.unlam.dominio.Musica;
import ar.edu.unlam.dominio.Pelicula;
import ar.edu.unlam.dominio.Programa;
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
				mostrarMensajePorPantalla("1. Premium");
				mostrarMensajePorPantalla("2. Normal");
				mostrarMensajePorPantalla("Selecciones un tipo de cliente: ");
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
				alquilarDisco(teclado,gestor);
				break;
			case 4:
				devolverDisco(teclado,gestor);
				break;
			//           ------- alquiler----
			//------------------- ventas=---------------
			case 5:
				venderDisco(teclado,gestor);
				break;
			//------------------- ventas=---------------
			case 6:
				salir = true;
				mostrarMensajePorPantalla("Saliendo del programa....");
				break;
			default:
				opcionInvalida();
				break;
			}
		}
		
		
	}
	private static void opcionInvalida() {
		mostrarMensajePorPantalla("Opcion invalida, intenta de nuevo");
	}
	
	private static void imprimirListaDiscos(ArrayList<Disco> listaDiscos) {
	    if (listaDiscos == null || listaDiscos.isEmpty()) {
	        System.out.println("No hay discos en el sistema");
	        return;
	    }

	    for (Disco disco : listaDiscos) {
	        System.out.println(disco);
	    }
	}
	
	private static void alquilarDisco(Scanner teclado,Gestor gestor) {
		mostrarMensajePorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		if(gestor.estaElClienteRegistrado(gestor.buscarClientePorDni(dni))) {
			mostrarMensajePorPantalla("Cliente registrado");
			mostrarMensajePorPantalla("--------------------------");
			for (Disco disco : gestor.obtenerInventarioAlquilableDisponible()) {
				System.out.println(disco);
			}
			ArrayList<Disco> listaInventarioDisponible=gestor.obtenerInventarioAlquilableDisponible();
			if(listaInventarioDisponible.size()!=0) {
				for (int i = 0; i < listaInventarioDisponible.size(); i++) {
					System.out.println(i+" "+listaInventarioDisponible.get(i));
				}

				System.out.println("seleccione opcion");
				Integer opcionDisco = teclado.nextInt();
				LocalDateTime fechaEmision = LocalDateTime.now();
				gestor.alquilarDisco(listaInventarioDisponible.get(opcionDisco), gestor.buscarClientePorDni(dni), fechaEmision);
			}else {
				System.out.println("no hay discos en la lista");
			}
		}
		else {
			mostrarMensajePorPantalla("El cliente no esta registrado no se puede alquilar");
		}
		
	}
	private static void devolverDisco(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		Cliente clienteBuscado=gestor.buscarClientePorDni(dni);
		if(gestor.estaElClienteRegistrado(clienteBuscado)) {
			mostrarMensajePorPantalla("Cliente registrado");
			mostrarMensajePorPantalla("--------------------------");
//			ArrayList<Alquiler> listaAlquilados = gestor.obtenerAlquileresActivos();
//			for (int i = 0; i < listaAlquilados.size(); i++) {
//				System.out.println(i+" "+listaAlquilados.get(i));
//			}
//			System.out.println("seleccione opcion");
////			Integer opcionAlquilado = teclado.nextInt();
			LocalDateTime fechaActual = LocalDateTime.now();
			Disco discoAlquilado=gestor.obtenerDiscoAlquiladoPorCliente(clienteBuscado);
			gestor.devolverDisco(discoAlquilado, clienteBuscado, fechaActual);
		}
		else {
			mostrarMensajePorPantalla("El cliente no esta registrado no se puede alquilar");
		}
		
	}
	private static void venderDisco(Scanner teclado, Gestor gestor) {
		imprimirListaDiscos(gestor.obtenerInventario());
		mostrarMensajePorPantalla("Ingrese Nro Dni cliente: ");
		Integer dni = teclado.nextInt();
		if(gestor.estaElClienteRegistrado(gestor.buscarClientePorDni(dni))) {
			mostrarMensajePorPantalla("Cliente registrado");
			mostrarMensajePorPantalla("--------------------------");
			ArrayList<Disco> listaInventarioDisponible=gestor.obtenerInventario();
			if(listaInventarioDisponible.size()!=0) {
				for (int i = 0; i < listaInventarioDisponible.size(); i++) {
					System.out.println(i+" "+listaInventarioDisponible.get(i));
				}
				System.out.println("seleccione opcion");
				Integer opcionDisco = teclado.nextInt();
				LocalDateTime fechaActual = LocalDateTime.now();
				gestor.venderDisco(listaInventarioDisponible.get(opcionDisco), gestor.buscarClientePorDni(dni), fechaActual);	
			}else {
				System.out.println("no hay discos en la lista");
			}
		}
		else {
			mostrarMensajePorPantalla("El cliente no esta registrado no se puede alquilar");
		}
		
	}
	private static void agregarClienteNormal(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Nro Dni del cliente: ");
		Integer clienteDniNormal = teclado.nextInt();
		mostrarMensajePorPantalla("Nombre del cliente: ");
		teclado.nextLine(); 
		String nombreClienteNormal = teclado.nextLine();
		mostrarMensajePorPantalla("Apellido del cliente: ");
		String apellidoClienteNormal = teclado.nextLine(); 
		
		if(clienteDniNormal>0 && !nombreClienteNormal.isEmpty() && !apellidoClienteNormal.isEmpty()) {
			Cliente cliente = new ClienteNormal(clienteDniNormal,nombreClienteNormal,apellidoClienteNormal);
			
			gestor.agregarCliente(cliente);
			
			mostrarMensajePorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarClientePremium(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Nro Dni del cliente: ");
		Integer clienteDni = teclado.nextInt();
		teclado.nextLine(); 
		mostrarMensajePorPantalla("Nombre del cliente: ");
		String nombreCliente = teclado.nextLine();
		mostrarMensajePorPantalla("Apellido del cliente: ");
		String apellidoCliente = teclado.nextLine();
		Double descuento = 10.0;
		
		if(clienteDni>0 && !nombreCliente.isEmpty() && !apellidoCliente.isEmpty()) {
			Cliente clienteP = new ClientePremium(clienteDni,nombreCliente,apellidoCliente,descuento);
			
			gestor.agregarCliente(clienteP);
			
			mostrarMensajePorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoPrograma(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Nombre del programa: ");
		teclado.nextLine();
		String nombrePrograma = teclado.nextLine();
		mostrarMensajePorPantalla("Versión: ");
		String version = teclado.nextLine();
		mostrarMensajePorPantalla("Precio de venta: ");
		Double precioProg = teclado.nextDouble();
		mostrarMensajePorPantalla("Precio de alquiler: ");
		Double precioAlqProg = teclado.nextDouble();
		
		if (nombrePrograma != null && !nombrePrograma.isEmpty() &&
		        version != null && !version.isEmpty() &&
		        precioProg > 0 && precioAlqProg >= 0) {

		        Disco programa = new Programa(nombrePrograma, version, precioProg, precioAlqProg);
		        gestor.agregarDisco(programa);
		        mostrarMensajePorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoJuego(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Nombre del juego: ");
		teclado.nextLine(); 
		String nombreJuego = teclado.nextLine();
		Consola consola = Consola.PLAY_STATION; 
		mostrarMensajePorPantalla("Precio de venta: ");
		Double precioJuego = teclado.nextDouble();

		
		if (nombreJuego != null && !nombreJuego.isEmpty() && precioJuego > 0) {
		    Disco juego = new Juego(nombreJuego, consola, precioJuego);
		    
		    gestor.agregarDisco(juego);
		    
		    mostrarMensajePorPantalla("Se agrego exitosamente.");
		    }
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoPelicula(Scanner teclado, Gestor gestor) {
	    mostrarMensajePorPantalla("Nombre de la pelicula: ");
	    teclado.nextLine(); 
	    String nombrePeli = teclado.nextLine();
	    GeneroPelicula generoPeli = GeneroPelicula.SUSPENSO; 
	    mostrarMensajePorPantalla("Año: ");
	    Integer anio = teclado.nextInt();
	    teclado.nextLine(); 
	    mostrarMensajePorPantalla("Director: ");
	    String director = teclado.nextLine();
	    mostrarMensajePorPantalla("Duracion (min): ");
	    Integer duracion = teclado.nextInt();
	    mostrarMensajePorPantalla("Precio de venta: ");
	    Double precioVenta = teclado.nextDouble();
	    mostrarMensajePorPantalla("Precio de alquiler: ");
	    Double precioAlquiler = teclado.nextDouble();
		
		if (nombrePeli != null && !nombrePeli.isEmpty() &&
		        director != null && !director.isEmpty() &&
		        anio > 0 && duracion > 0 &&
		        precioVenta > 0 && precioAlquiler >= 0) {
				
		        Disco peli = new Pelicula(nombrePeli, generoPeli, anio, director, duracion, precioVenta, precioAlquiler);
		        
		        gestor.agregarDisco(peli);
		        
		        mostrarMensajePorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static void agregarDiscoMusica(Scanner teclado, Gestor gestor) {
		mostrarMensajePorPantalla("Nombre del disco: ");
		teclado.nextLine(); 
		String nombre = teclado.nextLine();
		mostrarMensajePorPantalla("Nombre del autor: ");
		String autor = teclado.nextLine();
		GeneroMusica genero = GeneroMusica.POP;
		
		mostrarMensajePorPantalla("Numero de canciones: ");
		Integer cantidadDeCanciones = teclado.nextInt();
		mostrarMensajePorPantalla("Precio de venta del disco: ");
		Double precioDeVenta = teclado.nextDouble();

		if (nombre != null && !nombre.isEmpty() &&
			    autor != null && !autor.isEmpty() &&
			    cantidadDeCanciones > 0 && precioDeVenta > 0)  {
			
			Disco album = new Musica(nombre,autor,genero,cantidadDeCanciones,precioDeVenta);
			
			gestor.agregarDisco(album);
			
			mostrarMensajePorPantalla("Se agrego exitosamente.");
		}
		else {
			mostrarMensajePorPantalla("Dato incorrecto intente de nuevo.");
		}
	}
	private static Integer menuDiscos(Scanner teclado) {
		mostrarMensajePorPantalla("1. Musica");
		mostrarMensajePorPantalla("2. Pelicula");
		mostrarMensajePorPantalla("3. Juego");
		mostrarMensajePorPantalla("4. Programa");
		mostrarMensajePorPantalla("Elige un tipo de Disco: ");
		Integer opcionDisco = teclado.nextInt();
		return opcionDisco;
	}
	private static Integer menuPrincipal(Scanner teclado) {
		mostrarMensajePorPantalla("\n=== Menú Tienda de Discos ===");
		mostrarMensajePorPantalla("1. Agregar Disco");
		mostrarMensajePorPantalla("2. Agregar Cliente");
		mostrarMensajePorPantalla("3. Alquilar Disco");
		mostrarMensajePorPantalla("4. Devolver Disco");
		mostrarMensajePorPantalla("5. Vender Disco");
		mostrarMensajePorPantalla("6. Salir");
		mostrarMensajePorPantalla("Elige una opción: ");
		Integer opcion = teclado.nextInt();
		return opcion;
	}
	private static void mostrarMensajePorPantalla(String string) {
		System.out.println(string);
	}
}
