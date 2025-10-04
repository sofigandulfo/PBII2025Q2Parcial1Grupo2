package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public class Alquiler extends Operacion {
	private LocalDateTime fechaVencimiento;
	private LocalDateTime fechaDevolucion;
	private Double recargo;

	public Alquiler(Cliente cliente, Disco disco, LocalDateTime fechaEmision) {
		super(cliente, disco, fechaEmision);

	}


}
