package ar.edu.unlam.dominio;

import org.junit.Test;
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
		
		assertTrue(album.estaDisponible());
		
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
		

		assertTrue(pelicula.estaDisponible());
	}
	// juego: nombre, consola, precio venta 
		//prog: nombre del prog, version precio venta, precio alquiler
	@Test
	public void dadoQueUnDiscoNuevoDeJuegoDebeEstarDisponibleParaSuUso() {
		String nombre = "Crash";
		Consola consola = Consola.PLAY_STATION;
		Double precioVenta = 500.0;
		
		Disco juego = new Juego(nombre, consola,precioVenta);
		
		assertTrue(juego.estaDisponible());
	}
	
	@Test
	public void dadoQueUnDiscoNuevoDeProgramaDebeEstarDisponibleParaSuUso() {
		String nombre = "Photoshop";
		String version = "2.1.5";
		Double precioVenta = 2000.0;
		Double precioAlquiler = 300.0;
		
		Disco programa = new Programa(nombre,version,precioVenta,precioAlquiler);
		
		assertTrue(programa.estaDisponible());
	}
	
}
