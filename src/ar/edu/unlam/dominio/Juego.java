package ar.edu.unlam.dominio;

public class Juego extends Disco {

	private Consola consola;
	public Juego(String nombre, Consola consola, Double precioVenta) {
		super(nombre,precioVenta);
		this.consola = consola;
		
	}
	public String getTipo(){
		return "Juego";
	}
	public Consola getConsola() {
		return consola;
	}
	public void setConsola(Consola consola) {
		this.consola = consola;
	}
	@Override
	public String toString() {
		return "Juego [consola=" + consola + ", Nombre=" + getNombre() + ", PrecioVenta=" + getPrecioVenta()
				+ "]";
	}
	
	
}
