package ar.edu.unlam.dominio;

public class ClientePremium extends Cliente {

	private Double descuento;

	public ClientePremium(Integer dni, String nombre, String apellido, Double descuento) {
		super(dni, nombre, apellido);
		this.descuento = descuento; 
	}

	public Double getDescuento() {
		return  descuento / 100.0;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@Override
	public Integer obtenerPlazoDiasAlquiler() {
		return 10;
	}
	
	

}
