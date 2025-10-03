package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public class Alquiler extends Operacion {
	private LocalDateTime fechaVencimiento;
	private LocalDateTime fechaDevolucion;
	private Double recargo;

	public Alquiler(Cliente cliente, Disco disco, LocalDateTime fechaEmision) {
		super(cliente, disco, fechaEmision);

	}

	public Boolean alquilarProducto(Cliente cliente, Disco disco, LocalDateTime fechaEmision, Double precio) {
		if (disco.getEstaDisponible && disco instanceof Alquilable) {
			if (cliente instanceof ClienteNormal) {
				this.fechaVencimiento = getFechaEmision().plusDays(5);
			} else {
				this.fechaVencimiento = getFechaEmision().plusDays(7);
			}
			return true;
		}
		return false;
	}

	public Double devolucion(Cliente cliente, Disco disco, LocalDateTime fechaDevolucion) {
		
	}
}
