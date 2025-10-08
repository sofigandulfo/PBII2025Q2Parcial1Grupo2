package ar.edu.unlam.dominio;

public abstract class Disco {
	
	private String nombre;
	private Double precioDeVenta;
	private Boolean estaDisponible;
	
	public Disco(String nombre,Double precioDeVenta) {
		this.nombre = nombre;
		this.precioDeVenta = precioDeVenta;
		this.estaDisponible=true;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Double getPrecioDeVenta() {
		return precioDeVenta;
	}



	public void setPrecioDeVenta(Double precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}



	public Boolean obtenerEstaDisponible() {
		return this.estaDisponible;
	}
	public void marcarComoNoDisponible() {
		this.estaDisponible = false;
	}
	protected abstract String getTipo();

	public Double getPrecioVenta() {
		return this.precioDeVenta;
	}

	public void marcarComoDisponible() {
		this.estaDisponible=true;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre+" "+precioDeVenta;
	}
}
