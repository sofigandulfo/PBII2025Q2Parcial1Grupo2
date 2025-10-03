package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public class Venta extends Operacion {
	public Venta(Cliente cliente, Disco disco, LocalDateTime fechaEmision, Double precio) {
		super(cliente, disco, fechaEmision, precio);

	}
}
