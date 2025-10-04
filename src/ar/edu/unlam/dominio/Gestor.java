package ar.edu.unlam.dominio;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Gestor {
	
	private HashSet<Cliente> clientes;
	
	public Gestor() {
		this.clientes = new HashSet<>();
	}
	
	
	

	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}
	
	
	public Double obtenerPrecioVenta(Disco disco, Cliente cliente) {
		Double precio = disco.getPrecioVenta();
		
		if(cliente instanceof ClientePremium) {
			ClientePremium clientePremium = (ClientePremium) cliente;
			precio = disco.getPrecioVenta() - disco.getPrecioVenta() * clientePremium.getDescuento();
		}
		
		return precio;
	}
	
	public Boolean estaElClienteRegistrado(Cliente cliente) {
		for(Cliente client: clientes) {
			if(client.equals(cliente)) {
				return true;
			}
		}
		return false;
	}




	public Boolean alquilarDisco(Disco disco, Cliente cliente, LocalDateTime fechaEmision) {
		if (disco.getEstaDisponible() && estaElClienteRegistrado(cliente) && estaElClienteBloqueado(cliente)==false) {
			Alquiler nuevo = new Alquiler (cliente, disco, fechaEmision);
			return true;
		}
		return false;
	}
	

}
