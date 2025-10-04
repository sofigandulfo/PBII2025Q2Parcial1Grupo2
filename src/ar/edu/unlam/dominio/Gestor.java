package ar.edu.unlam.dominio;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Gestor {
	
	private HashSet<Cliente> clientes;
	private HashSet<Operacion> operaciones;
	
	public Gestor() {
		this.clientes = new HashSet<>();
		this.operaciones = new HashSet<>();
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
		if (disco.obtenerEstaDisponible() && estaElClienteRegistrado(cliente) && cliente.estaBloqueado() == false) {
			Operacion nuevo = new Alquiler (cliente, disco, fechaEmision);
			Boolean seAgrego = this.operaciones.add(nuevo);
			if(seAgrego) {
				disco.marcarComoNoDisponible();;
				return true;
			}
		}
		return false;
	}


	public Alquiler encontrarDiscoAlquilado(Disco disco, Cliente cliente) {
		for(Operacion operacion : operaciones) {
			if(operacion instanceof Alquiler) {
				Alquiler alquiler = (Alquiler) operacion;
				if(alquiler.getDisco().equals(disco) && alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
					return alquiler;
				}
			}
		}
		return null;
	}

	// chicos les aviso q esta es la base del metodo nomas 
	public Boolean devolverDisco(Disco disco, Cliente cliente, LocalDateTime fechaDevolucion3) {
		Alquiler discoAlquilado = encontrarDiscoAlquilado(disco, cliente);
		
		if(discoAlquilado != null) {
			
			
			return true;
		}
		
		return false;
	}
	
	
	

}
