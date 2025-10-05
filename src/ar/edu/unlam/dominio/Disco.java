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
	
	public boolean obtenerEstaDisponible() {
		return this.estaDisponible;
	}
	public void marcarComoNoDisponible() {
		this.estaDisponible = false;
	}
	protected abstract String getTipo();

	public double getPrecioVenta() {
		return this.precioDeVenta;
	}

	public void marcarComoDisponible() {
		this.estaDisponible=true;
		
	}
}
