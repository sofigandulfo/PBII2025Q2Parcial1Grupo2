package ar.edu.unlam.dominio;

import java.time.LocalDateTime;

public abstract class Operacion {
private Cliente cliente;
private LocalDateTime fechaEmision;
private Disco disco;
private Integer codigo;
private static Integer proximoCodigo = 1;

public Operacion (Cliente cliente, Disco disco, LocalDateTime fechaEmision) {
	this.cliente =cliente;
	this.disco = disco;
	this.fechaEmision = fechaEmision;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public LocalDateTime getFechaEmision() {
	return fechaEmision;
}

public void setFechaEmision(LocalDateTime fechaEmision) {
	this.fechaEmision = fechaEmision;
}

public Disco getDisco() {
	return disco;
}

public void setDisco(Disco disco) {
	this.disco = disco;
}

public Integer getCodigo() {
	return codigo;
}

public void setCodigo(Integer codigo) {
	this.codigo = codigo;
}
	
}
