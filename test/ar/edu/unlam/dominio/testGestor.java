package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014,S "James Ward Byrkit", 120, 2000.0, 500.0); 
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
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP);
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
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP);
		assertFalse(seAlquilo);
		
		Boolean seDevolvio = gestor.devolverDisco(pelicula, cliente);
		assertTrue(seDevolvio);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP);
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
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente);
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
		
	}
}
