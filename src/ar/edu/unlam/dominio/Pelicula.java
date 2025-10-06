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
	@Override
	public Boolean alquilarProducto() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void devolverProducto() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Double calcularPrecio() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Double calcularPrecioAlquilerVencido() {
		// TODO Auto-generated method stub
		return null;
	}
}
