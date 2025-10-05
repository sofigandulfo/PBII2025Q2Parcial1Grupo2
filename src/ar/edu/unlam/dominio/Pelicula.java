package ar.edu.unlam.dominio;

public class Pelicula extends Disco implements Alquilable{
	private Double precioAlquiler;
	public Pelicula(String nombre, GeneroPelicula genero, Integer anio, String director, Integer duracion,
			Double precioVenta, Double precioAlquiler) {
		super(nombre,precioVenta);
		
		this.precioAlquiler=precioAlquiler;
	}
	public String getTipo(){
		return "Pelicula";
	}
	
	public Double obtenerPrecioAlquilerPorDia() {
		return this.precioAlquiler;
	}
	
	public Double obtenerPrecioAlquilerPorDiaAtrasado() {
		return this.precioAlquiler*1.5;
	}
}
