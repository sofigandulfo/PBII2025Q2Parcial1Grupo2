package ar.edu.unlam.dominio;

public interface Alquilable {
	Double precioAlquiler();
	Boolean alquilarProducto();
	void devolverProducto(); // si se devuelve y esta vencido que le devuelve al usuario?
	Double calcularPrecio();
	Double calcularPrecioAlquilerVencido();
}
