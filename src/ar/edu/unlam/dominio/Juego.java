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
}
