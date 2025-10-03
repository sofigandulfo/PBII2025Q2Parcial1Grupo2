package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public class Alquiler extends Operacion {
	private LocalDateTime fechaVencimiento;
	private LocalDateTime fechaDevolucion;
	private Double recargo;
	private Boolean alquilado;

	public Alquiler(Cliente cliente, Disco disco, LocalDateTime fechaEmision, Double precio) {
		super(cliente, disco, fechaEmision, precio);
		this.alquilado = true;

	}
	public Boolean alquilar(Cliente cliente,Disco disco, LocalDateTime fechaEmision,Double precio) {
		
	}
	public Boolean devolucion() {
		
	}
}
