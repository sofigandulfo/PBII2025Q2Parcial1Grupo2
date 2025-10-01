package ar.edu.unlam.dominio;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

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
}
