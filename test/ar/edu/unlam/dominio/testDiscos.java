package ar.edu.unlam.dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class testDiscos {
	
	@Test
	public void dadoQueUnDiscoNuevoDeMusicaDebeEstarDisponibleParaSuUso() {
		String nombre = "Miranda es imposible";
		String autor = "Miranda";
		GeneroMusica genero = GeneroMusica.POP;
		Integer cantidadDeCanciones = 9;
		Double precioDeVenta = 1500.0;
		
		Disco album = new Musica(nombre,autor,genero,cantidadDeCanciones,precioDeVenta);
		
		assertTrue(album.obtenerEstaDisponible());
		
	}
	@Test
	public void dadoQueUnDiscoNuevoDePeliculaDebeEstarDisponibleParaSuUso() {
		String nombre = "Coherence";
		GeneroPelicula genero = GeneroPelicula.SUSPENSO;
		Integer anio = 2014;
		String director ="James Ward Byrkit";
		Integer duracion = 120;
		Double precioVenta = 2000.0;
		Double precioAlquiler = 500.0;
		
		Disco pelicula = new Pelicula(nombre, genero, anio,director,duracion,precioVenta,precioAlquiler);
		

		assertTrue(pelicula.obtenerEstaDisponible());
	}
	@Test
	public void dadoQueUnDiscoNuevoDeJuegoDebeEstarDisponibleParaSuUso() {
		String nombre = "Crash";
		Consola consola = Consola.PLAY_STATION;
		Double precioVenta = 500.0;
		
		Disco juego = new Juego(nombre, consola,precioVenta);
		
		assertTrue(juego.obtenerEstaDisponible());
	}
	
	@Test
	public void dadoQueUnDiscoNuevoDeProgramaDebeEstarDisponibleParaSuUso() {
		String nombre = "Photoshop";
		String version = "2.1.5";
		Double precioVenta = 2000.0;
		Double precioAlquiler = 300.0;
		
		Disco programa = new Programa(nombre,version,precioVenta,precioAlquiler);
		
		assertTrue(programa.obtenerEstaDisponible());
	}
	@Test
	public void dadoQueNecesitoElPrecioDeVentaDeUnDisco() {
		
		Disco juego = new Juego("Crash", Consola.XBOX, 500.0); 
		
		
		Double valorEsperado = 500.0;
		Double valorObtenido = juego.getPrecioVenta();
		
		assertEquals(valorEsperado,valorObtenido);
		
	}
	@Test
	public void dadoQueUnDiscoDebeDejarDeEstarDisponibleSiSeAlquilaOSeVende() {
	Disco juego = new Juego("Crash", Consola.XBOX, 500.0); 
	
	juego.marcarComoNoDisponible();
	
	boolean valorEsperado = false;
	boolean valorObtenido = juego.obtenerEstaDisponible();
	
	assertEquals(valorEsperado,valorObtenido);
	
	}
	@Test
	public void dadoQueUnDiscoDebeEstarDisponibleSiSeDevuelve() {
	Disco juego = new Juego("Crash", Consola.XBOX, 500.0); 
	

	juego.marcarComoNoDisponible();
	juego.marcarComoDisponible();
	
	boolean valorEsperado = true;
	boolean valorObtenido = juego.obtenerEstaDisponible();
	
	assertEquals(valorEsperado,valorObtenido);
	}
	
	@Test
	public void dadoQueSeNecesitaObtenerElTipoDeDisco() {
		
		Disco peli = new Pelicula("Back to the future", GeneroPelicula.CIENCIA_FICCION, 1985, "Robert Zemeckis", 116, 3000.0, 500.0); 
		Disco juego = new Juego("Crash", Consola.XBOX, 500.0); 
		Disco musica = new Musica("Back in Black", "AC/DC", GeneroMusica.ROCK, 10, 5000.0);
		Disco programa = new Programa("Visual Studio Code", "1.5", 2000.0, 300.0 ); 
		
		
		String valorEsperado = "Pelicula";
		String valorObtenido = peli.getTipo();
		assertEquals(valorEsperado,valorObtenido);
		
		valorEsperado = "Juego";
		valorObtenido = juego.getTipo();
		assertEquals(valorEsperado,valorObtenido);
		
		valorEsperado = "Musica";
		valorObtenido = musica.getTipo();
		assertEquals(valorEsperado,valorObtenido);
		
		
		valorEsperado = "Programa";
		valorObtenido = programa.getTipo();
		assertEquals(valorEsperado,valorObtenido);
	}
	
	
	
}
