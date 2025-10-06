package ar.edu.unlam.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Operacion {
private Cliente cliente;
private LocalDateTime fechaEmision;
private Disco disco;
private Integer codigo;
private static Integer contadorCodigo = 0;
private Double precioFinal;

public Operacion (Cliente cliente, Disco disco, LocalDateTime fechaEmision) {
	this.cliente =cliente;
	this.disco = disco;
	this.fechaEmision = fechaEmision;
	contadorCodigo++;
	this.codigo = contadorCodigo;
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

public Double getPrecioFinal() {
	return precioFinal;
}

public void setPrecioFinal(Double precioFinal) {
	this.precioFinal = precioFinal;
}

@Override
public int hashCode() {
	return Objects.hash(cliente, disco, fechaEmision);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Operacion other = (Operacion) obj;
	return Objects.equals(cliente, other.cliente) && Objects.equals(disco, other.disco)
			&& Objects.equals(fechaEmision, other.fechaEmision);
}


	
}
