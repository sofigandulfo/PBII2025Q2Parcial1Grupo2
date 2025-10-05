package ar.edu.unlam.dominio;

public class Juego extends Disco {

	public Juego(String nombre, Consola consola, Double precioVenta) {
		super(nombre,precioVenta);
		
	}
	public String getTipo(){
		return "Juego";
	}
}
