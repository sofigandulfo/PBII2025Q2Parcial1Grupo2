package ar.edu.unlam.dominio;

public class ClienteNormal extends Cliente {

	public ClienteNormal(Integer dni, String nombre, String apellido) {
		super(dni, nombre, apellido);
	}

	@Override
	public Integer obtenerPlazoDiasAlquiler() {
		return 7;
	}

}
