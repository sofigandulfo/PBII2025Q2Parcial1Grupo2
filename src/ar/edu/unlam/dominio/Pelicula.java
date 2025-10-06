package ar.edu.unlam.dominio;


public class Pelicula extends Disco implements Alquilable{

	private GeneroPelicula genero;
	private Integer anio;
	private String director;
	private Integer duracion;
	private Double precioAlquiler;
	
	public Pelicula(String nombre, GeneroPelicula genero, Integer anio, String director, Integer duracion,
			Double precioVenta, Double precioAlquiler) {
		super(nombre,precioVenta);
		this.genero = genero;
		this.anio = anio;
		this.director = director;
		this.duracion = duracion;
		this.precioAlquiler = precioAlquiler;

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
	public GeneroPelicula getGenero() {
		return genero;
	}
	public void setGenero(GeneroPelicula genero) {
		this.genero = genero;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	
	
}
