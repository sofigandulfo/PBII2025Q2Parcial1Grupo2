package ar.edu.unlam.dominio;

public class Pelicula extends Disco {

	public Pelicula(String nombre, GeneroPelicula genero, Integer anio, String director, Integer duracion,
			Double precioVenta, Double precioAlquiler) {
		super(nombre,precioVenta);
		
	}
	public String getTipo(){
		return "Pelicula";
	}
}
