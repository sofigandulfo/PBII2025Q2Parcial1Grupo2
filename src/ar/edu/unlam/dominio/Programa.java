package ar.edu.unlam.dominio;

public class Programa extends Disco {

	private String version;
	
	public Programa(String nombre, String version, Double precioVenta, Double precioAlquiler) {
		super(nombre,precioVenta);
		this.version = version;
	}
	public String getTipo(){
		return "Programa";
	}
}
