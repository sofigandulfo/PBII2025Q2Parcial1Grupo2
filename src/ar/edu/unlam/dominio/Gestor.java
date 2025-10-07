package ar.edu.unlam.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Gestor {
	
	private HashSet<Cliente> clientes;
	private HashSet<Operacion> operaciones;
	private ArrayList<Disco> discos;
	
	public Gestor() {
		this.clientes = new HashSet<>();
		this.operaciones = new HashSet<>();
		this.discos = new ArrayList<>();
	}
	
	
	

	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}
	
	
	public Boolean estaElClienteRegistrado(Cliente cliente) {
		for(Cliente client: clientes) {
			if(client.equals(cliente)) {
				return true;
			}
		}
		return false;
	}

	public Double obtenerPrecioAlquiler(Operacion operacion) {
		if(operacion instanceof Alquiler) {
			Alquiler alquiler = (Alquiler)operacion;
			return alquiler.calcularPrecioFinal();
		}
		return null;
	}



	public Boolean alquilarDisco(Disco disco, Cliente cliente, LocalDateTime fechaEmision) {
		if (disco.obtenerEstaDisponible() && estaElClienteRegistrado(cliente) && cliente.estaBloqueado() == false && disco instanceof Alquilable) {
			Operacion nuevo = new Alquiler (cliente, disco, fechaEmision);
			Boolean seAgrego = this.operaciones.add(nuevo);
			if(seAgrego) {
				disco.marcarComoNoDisponible();;
				return true;
			}
		}
		return false;
	}


	public Alquiler encontrarAlquilerActivoDelDisco(Disco disco, Cliente cliente) {
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

	public Alquiler encontrarAlquilerDelDisco(Disco disco, Cliente cliente) {
		for(Operacion operacion : operaciones) {
			if(operacion instanceof Alquiler) {
				Alquiler alquiler = (Alquiler) operacion;
				if(alquiler.getDisco().equals(disco) && alquiler.getCliente().equals(cliente)) {
					return alquiler;
				}
			}
		}
		return null;
	}
	
	public Boolean devolverDisco(Disco disco, Cliente cliente, LocalDateTime fechaDevolucion) {
		Alquiler alquiler = encontrarAlquilerActivoDelDisco(disco, cliente);
		if(alquiler != null && alquiler.getFechaDevolucion() == null && !fechaDevolucion.isBefore(alquiler.getFechaEmision())) { 
			alquiler.devolverDisco(fechaDevolucion);
			return true;
		}
		return false;
	}




	public Boolean agregarDisco(Disco disco) {
		return this.discos.add(disco);
	}

	
	public Venta obtenerVentaDelDisco(Disco disco, Cliente cliente) {
		for(Operacion operacion : operaciones) {
			if(operacion instanceof Venta) {
				Venta venta = (Venta) operacion;
				if(venta.getDisco().equals(disco) && venta.getCliente().equals(cliente)) {
					return venta;
				}
			}
		}
		return null;
	}


	public Boolean venderDisco(Disco disco, Cliente cliente,LocalDateTime fechaEmision) {
		if (disco.obtenerEstaDisponible() && estaElClienteRegistrado(cliente) && cliente.estaBloqueado() == false) {
			Operacion venta = new Venta(disco, cliente, fechaEmision);
			this.operaciones.add(venta);
			this.discos.remove(disco);
			return true;
		}
		
		return false;
	}




	public HashSet<Cliente> obtenerListaClientes() {
		// TODO Auto-generated method stub
		return this.clientes;
	}




	public <Tipo extends Cliente> HashSet<Cliente> obtenerListaClientesTipo(Class<Tipo> tipo) {
		HashSet<Cliente> coincidencias = new HashSet<>();
		for(Cliente cliente:clientes) {
			if(tipo.isInstance(cliente)) {
				coincidencias.add(cliente);
			}
		}
		return coincidencias;
	}




	public ArrayList<Disco> obtenerInventario() {
		// TODO Auto-generated method stub
		return this.discos;
	}




	public  <Tipo extends Disco> ArrayList<Disco> obtenerInventarioPorTipo(Class<Tipo> tipo) {
		ArrayList<Disco> coincidencias = new ArrayList<>();
		for(Disco disco:discos) {
			if(tipo.isInstance(disco)) {
				coincidencias.add(disco);
			}
		}
		return coincidencias;
	}




	public HashSet<Operacion> obtenerOperaciones() {
		// TODO Auto-generated method stub
		return operaciones;
	}




	public  <Tipo extends Operacion> HashSet<Operacion> obtenerOperacionesPorTipo(Class<Tipo> tipo) {
		HashSet<Operacion> coincidencias = new HashSet<>();
		for(Operacion operacion:operaciones) {
			if(tipo.isInstance(operacion)) {
				coincidencias.add(operacion);
			}
		}
		return coincidencias;
	}
	public Cliente buscarClientePorDni(Integer dni) {
		for (Cliente cliente : clientes) {
			if(cliente.getDni().equals(dni)) {
				return cliente;
			}
		}
		return null;
	}
	
	
	
	

}
