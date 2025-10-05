package ar.edu.unlam.dominio;

public interface Alquilable {
	Double obtenerPrecioAlquilerPorDia();
	Double obtenerPrecioAlquilerPorDiaAtrasado();
	//por ahora estos de arriba
	
	Boolean alquilarProducto();
	void devolverProducto(); // si se devuelve y esta vencido que le devuelve al usuario?
	Double calcularPrecio();
	Double calcularPrecioAlquilerVencido();
}
