package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class testGestor {
	
	private Gestor gestor;
	
	@Before
	public void inicializacion() {
		gestor = new Gestor();
	}
	
	// base para saber el constructor de los discos
	// peli: nombre, genero, año, director, duracion en min, precio de venta, precio de alquiler
	// juego: nombre, consola, precio venta 
	// musica: nombre del album, artista genero, cantidad de canciones, precioventa 
	//prog: nombre del prog, version precio venta, precio alquiler
	
	@Test
	public void dadoQueExisteUnGestorSePuedenAgregarDistintosTipoDeDiscoCorrectamente() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		Disco musica = new Musica("Miranda es imposible", "Miranda", GeneroMusica.POP, 9, 1500.0);
		Disco programa = new Programa("Photoshop", "2.1.5", 2000.0, 300.0 ); 
		
		Boolean seAgrego = gestor.agregarDisco(peli);
		assertTrue(seAgrego);
		
		seAgrego = gestor.agregarDisco(juego);
		assertTrue(seAgrego);
		
		seAgrego = gestor.agregarDisco(musica);
		assertTrue(seAgrego);

		seAgrego = gestor.agregarDisco(programa);
		assertTrue(seAgrego);
	
	
	}
	
	@Test 
	public void dadoQueExisteUnGestorSePuedePrestarUnDiscoAlquilableSiEstaDisponible() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente clienteP = new ClientePremium(456, "Jorge", "Jorgelin", 10);
	
		gestor.agregarCliente(cliente);
		gestor.agregarCliente(clienteP);
		
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision);
		assertFalse(seAlquilo);
		
	}
	
	@Test
	public void dadoQueExisteUnGestorNoSePuedePrestarUnDiscoAlquilableHastaQueNoSeDevuelva() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente clienteP = new ClientePremium(456, "Jorge", "Jorgelin", 10);
		
		gestor.agregarCliente(cliente);
		gestor.agregarCliente(clienteP);
		

		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		LocalDateTime fechaDevolucion = LocalDateTime.of(2025, 02, 23, 14, 30);
		LocalDateTime fechaEmision2 = LocalDateTime.of(2025, 02, 24, 14, 30);
		
		

		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision);
		assertFalse(seAlquilo);
		
		Boolean seDevolvio = gestor.devolverDisco(pelicula, cliente, fechaDevolucion);
		assertTrue(seDevolvio);
		

		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision2);

		assertTrue(seAlquilo);
		
	}
	
	@Test 
	public void dadoQueExisteUnGestorNoSePuedenRegistrarDosClientesConElMismoDNI() {
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente cliente2 = new ClientePremium(123, "Jorge", "Jorgelin", 10);
		
		Boolean seRegistro = gestor.agregarCliente(cliente);
		assertTrue(seRegistro);
		
		seRegistro = gestor.agregarCliente(cliente2);
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnGestorNoSePuedeAlquilarNiVenderDiscosAClientesQueNoEstenRegistrados() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertFalse(seAlquilo);
		
		Boolean seVendio = gestor.venderDisco(pelicula, cliente);
		assertFalse(seVendio);
	}
	
	@Test
	public void dadoQueExisteUnGestorAUnClientePremiumSeLeHaceDescuentoEnLaCompraDeUnCdCorrectamente() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente clientePremium = new ClientePremium(123, "Sofia", "Gandulfo", 10);
		gestor.agregarCliente(clientePremium);
		
		gestor.venderDisco(pelicula, clientePremium);
		
		Double precioObtenido = gestor.obtenerPrecioVenta(pelicula, clientePremium);
		
		Double precioEsperado = 1800.0;
		
		assertEquals(precioEsperado, precioObtenido);
	}
	
	@Test
	public void dadoQueExisteUnGestorAUnClienteNormalNoSeLeHaceDescuentoEnLaCompraDeUnCd() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new Cliente(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		
		gestor.venderDisco(pelicula, cliente);
		
		Double precioObtenido = gestor.obtenerPrecioVendido(pelicula, cliente);
		
		Double precioEsperado = 2000.0;
		
		assertEquals(precioEsperado, precioObtenido);
		
	}
	
	@Test
	public void dadoQueExisteUnGestorSiUnClienteTieneMasDe3DevolucionesTardeQuedaBloqueadoEnLaTienda() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new Cliente(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		
		LocalDateTime fechaEmision1 = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision1);
		
		LocalDateTime fechaDevolucion1 = LocalDateTime.of(2025, 02, 29, 12, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion1);
		
		Integer cantidadDeStikesObtenidos = cliente.getStrike();
		Integer cantidadDeStrikesEsperados = 1;
		
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
		
		LocalDateTime fechaEmision2 = LocalDateTime.of(2025, 03, 01, 10, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision2);
		
		LocalDateTime fechaDevolucion2 = LocalDateTime.of(2025, 03, 9, 23, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion2);
		
		cantidadDeStikesObtenidos = cliente.getStrike();
		cantidadDeStrikesEsperados = 2;
		
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
		
		LocalDateTime fechaEmision3 = LocalDateTime.of(2025, 04, 01, 10, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision3);
		
		LocalDateTime fechaDevolucion3 = LocalDateTime.of(2025, 04, 9, 23, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion3);
		
		cantidadDeStikesObtenidos = cliente.getStrike();
		cantidadDeStrikesEsperados = 3;
		
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
		

		LocalDateTime fechaEmision4 = LocalDateTime.of(2025, 05, 01, 10, 30);
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision4);
		
		assertFalse(seAlquilo);
		
		Boolean estaBloqueado = cliente.estaBloqueado();
		
		assertTrue(estaBloqueado);
		
	}
}
