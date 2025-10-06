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
		super.getDisco().marcarComoDisponible();
		super.setPrecioFinal(calcularPrecioFinal());			//Se settea el precio final (a partir de devolver el disco tarde o no)
	}

	public Double calcularRecargo(LocalDateTime fechaDevolucion) {
		Double recargo = 0.0;
		Integer diasAtrasados = (int)ChronoUnit.DAYS.between(fechaVencimiento, fechaDevolucion);
		if(getDisco() instanceof Alquilable) {
			Alquilable alquilable = (Alquilable)getDisco();
			recargo = alquilable.obtenerPrecioAlquilerPorDiaAtrasado() * diasAtrasados;
			return recargo;
		}
		return recargo;
		
	}

	public Double calcularPrecioFinal( ) {
		Double precioFinal = 0.0;
		Integer diasAlquilados=(int)ChronoUnit.DAYS.between(super.getFechaEmision(), fechaDevolucion);
		if(getDisco() instanceof Alquilable) {
			Alquilable alquilable = (Alquilable)getDisco();			
			if(diasAlquilados>this.plazoAlquiler) {		
				precioFinal=this.plazoAlquiler*alquilable.obtenerPrecioAlquilerPorDia()+this.recargo;
			}else {
				precioFinal=diasAlquilados*alquilable.obtenerPrecioAlquilerPorDia();
			}
			
			return precioFinal;
		}
		return precioFinal;
	}

	
	
}
