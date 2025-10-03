package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public abstract class Operacion {
private Cliente cliente;
private LocalDateTime fechaEmision;
private Disco disco;
private Integer codigo;
private static Integer proximoCodigo = 1;
private Double precio;

public Operacion (Cliente cliente, Disco disco, LocalDateTime fechaEmision, Double precio) {
	this.cliente =cliente;
	this.disco = disco;
	this.fechaEmision = fechaEmision;
	this.precio = precio;
}
	
}
