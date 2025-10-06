package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public class Venta extends Operacion {
//	public Venta(Cliente cliente, Disco disco, LocalDateTime fechaEmision, Double precio) {
//		super(cliente, disco, fechaEmision, precio);
//
//	}

	public Venta(Disco disco, Cliente cliente, LocalDateTime fechaEmision) {
		super(cliente, disco, fechaEmision);
	}
	
	public Double obtenerPrecioFinal() {
		Double precioFinal=super.getDisco().getPrecioVenta();
		if(super.getCliente() instanceof ClientePremium) {
			ClientePremium clientePremium = (ClientePremium) getCliente();
			precioFinal = getDisco().getPrecioVenta() - getDisco().getPrecioVenta() * clientePremium.getDescuento();
		}
		return precioFinal;
	}
}
