package ar.edu.unlam.dominio;

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
	
	// peli: nombre, genero, año, director, duracion en min precio de venta, precio de alquiler
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
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP);
		assertFalse(seAlquilo);
		
		Boolean seDevolvio = gestor.devolverDisco(pelicula, cliente);
		assertTrue(seDevolvio);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP);
		assertTrue(seAlquilo);
		
	}
}
