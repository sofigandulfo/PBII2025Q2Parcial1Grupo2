package ar.edu.unlam.dominio;

public class Musica extends Disco {

	private String autor;
	private GeneroMusica genero;
	private Integer cantidadDeCanciones;
	
	public Musica(String nombre, String autor, GeneroMusica genero, Integer cantidadDeCanciones,
			Double precioDeVenta) {
		super(nombre,precioDeVenta);
		this.autor = autor;
		this.genero = genero;
		this.cantidadDeCanciones = cantidadDeCanciones;
		
	}
	public String getTipo(){
		return "Musica";
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public GeneroMusica getGenero() {
		return genero;
	}
	public void setGenero(GeneroMusica genero) {
		this.genero = genero;
	}
	public Integer getCantidadDeCanciones() {
		return cantidadDeCanciones;
	}
	public void setCantidadDeCanciones(Integer cantidadDeCanciones) {
		this.cantidadDeCanciones = cantidadDeCanciones;
	}


	
}
