package ar.edu.unlam.dominio;

public class ClientePremium extends Cliente {

	private Integer descuento;

	public ClientePremium(Integer dni, String nombre, String apellido, Integer descuento) {
		super(dni, nombre, apellido);
		this.descuento = descuento; 
	}

	public Integer getDescuento() {
		return  descuento / 100;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}
	
	

}
