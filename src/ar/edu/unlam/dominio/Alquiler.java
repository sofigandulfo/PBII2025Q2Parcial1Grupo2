package ar.edu.unlam.dominio;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Alquiler extends Operacion {
	private LocalDateTime fechaVencimiento;
	private LocalDateTime fechaDevolucion;
	private Double recargo;
	private Integer plazoAlquiler;

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public Alquiler(Cliente cliente, Disco disco, LocalDateTime fechaEmision) {
		super(cliente, disco, fechaEmision);
		this.plazoAlquiler = cliente.obtenerPlazoDiasAlquiler();
		this.fechaVencimiento = fechaEmision.plusDays(plazoAlquiler);
		this.recargo = 0.0;
	}

	public void devolverDisco(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
		Boolean seAtraso = fechaDevolucion.isAfter(fechaVencimiento);
		if (seAtraso) {
			this.recargo = calcularRecargo(fechaDevolucion); //se establece el recargo una vez que se devolvio
			super.getCliente().registrarNuevoStrike();		//registrar que se atraso (se le AGREGA un strike)								
		}
		super.setPrecioFinal(calcularPrecioFinal());			//Se settea el precio final (a partir de devolver el disco tarde o no)
	}

	public Double calcularRecargo(LocalDateTime fechaDevolucion) {
		Integer diasAtrasados = (int)ChronoUnit.DAYS.between(fechaVencimiento, fechaDevolucion);
		return ((Pelicula)getDisco()).obtenerPrecioAlquilerPorDiaAtrasado() * diasAtrasados;
		
	}

	public Double calcularPrecioFinal( ) {
		Double precioFinal;
		Integer diasAlquilados=(int)ChronoUnit.DAYS.between(super.getFechaEmision(), fechaDevolucion);
		
		if(diasAlquilados>this.plazoAlquiler) {		
			precioFinal=this.plazoAlquiler*((Pelicula)getDisco()).obtenerPrecioAlquilerPorDia()+this.recargo;
		}else {
			precioFinal=diasAlquilados*((Pelicula)getDisco()).obtenerPrecioAlquilerPorDia();
		}
		
		return precioFinal;
		
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	
	
}
