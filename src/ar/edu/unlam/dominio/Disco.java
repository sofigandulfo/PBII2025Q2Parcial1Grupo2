package ar.edu.unlam.dominio;

public abstract class Disco {
	
	private String nombre;
	private Double precioDeVenta;
	private boolean estaDisponible;
	
	public Disco(String nombre,Double precioDeVenta) {
		this.nombre = nombre;
		this.precioDeVenta = precioDeVenta;
		this.estaDisponible=true;
	}
	public boolean estaDisponible() {
		return this.estaDisponible;
	}

}
