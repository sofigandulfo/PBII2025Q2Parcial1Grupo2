package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class testGestor {
	
	private Gestor gestor;
	
	@Before
	public void inicializacion() {
		gestor = new Gestor();
	}
	
	// base para saber el constructor de los discos
	// peli: nombre, genero, año, director, duracion en min, precio de venta, precio de alquiler
	// juego: nombre, consola, precio venta 
	// musica: nombre del album, artista genero, cantidad de canciones, precioventa 
	//prog: nombre del prog, version precio venta, precio alquiler
	
	@Test
	public void dadoQueExisteUnGestorSePuedenAgregarDistintosTipoDeDiscoCorrectamente() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		Disco musica = new Musica("Miranda es imposible", "Miranda", GeneroMusica.POP, 9, 1500.0);
		Disco programa = new Programa("Photoshop", "2.1.5", 2000.0, 300.0 ); 
		
		Boolean seAgrego = gestor.agregarDisco(peli);
		assertTrue(seAgrego);
		
		seAgrego = gestor.agregarDisco(juego);
		assertTrue(seAgrego);
		
		seAgrego = gestor.agregarDisco(musica);
		assertTrue(seAgrego);

		seAgrego = gestor.agregarDisco(programa);
		assertTrue(seAgrego);
	
	
	}
	
	@Test 
	public void dadoQueExisteUnGestorSePuedePrestarUnDiscoAlquilableSiEstaDisponible() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente clienteP = new ClientePremium(456, "Jorge", "Jorgelin", 10.0);
	
		gestor.agregarCliente(cliente);
		gestor.agregarCliente(clienteP);
		
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision);
		assertFalse(seAlquilo);
		
	}
	
	@Test
	public void dadoQueExisteUnGestorNoSePuedePrestarUnDiscoAlquilableHastaQueNoSeDevuelva() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente clienteP = new ClientePremium(456, "Jorge", "Jorgelin", 10.0);
		
		gestor.agregarCliente(cliente);
		gestor.agregarCliente(clienteP);
		

		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		LocalDateTime fechaDevolucion = LocalDateTime.of(2025, 02, 23, 14, 30);
		LocalDateTime fechaEmision2 = LocalDateTime.of(2025, 02, 24, 14, 30);
		
		

		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertTrue(seAlquilo);
		
		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision);
		assertFalse(seAlquilo);
		
		Boolean seDevolvio = gestor.devolverDisco(pelicula, cliente, fechaDevolucion);
		assertTrue(seDevolvio);
		

		seAlquilo = gestor.alquilarDisco(pelicula, clienteP, fechaEmision2);

		assertTrue(seAlquilo);
		
	}
	
	@Test 
	public void dadoQueExisteUnGestorNoSePuedenRegistrarDosClientesConElMismoDNI() {
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		Cliente cliente2 = new ClientePremium(123, "Jorge", "Jorgelin", 10.0);
		
		Boolean seRegistro = gestor.agregarCliente(cliente);
		assertTrue(seRegistro);
		
		seRegistro = gestor.agregarCliente(cliente2);
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnGestorNoSePuedeAlquilarNiVenderDiscosAClientesQueNoEstenRegistrados() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		assertFalse(seAlquilo);
		
		Boolean seVendio = gestor.venderDisco(pelicula, cliente, fechaEmision);
		assertFalse(seVendio);
	}
	
	@Test
	public void dadoQueExisteUnGestorAUnClientePremiumSeLeHaceDescuentoEnLaCompraDeUnCdCorrectamente() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente clientePremium = new ClientePremium(123, "Sofia", "Gandulfo", 10.0);
		gestor.agregarCliente(clientePremium);
		
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		gestor.venderDisco(pelicula, clientePremium, fechaEmision);
		
		Venta venta = gestor.obtenerVentaDelDisco(pelicula, clientePremium);
		
		Double precioObtenido = venta.obtenerPrecioFinal();
		
		Double precioEsperado = 1800.0;
		
		assertEquals(precioEsperado, precioObtenido);
	}
	
	@Test
	public void dadoQueExisteUnGestorAUnClienteNormalNoSeLeHaceDescuentoEnLaCompraDeUnCd() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		
		LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
		
		gestor.venderDisco(pelicula, cliente, fechaEmision );
		
		Venta venta = gestor.obtenerVentaDelDisco(pelicula, cliente);
		
		Double precioObtenido = venta.obtenerPrecioFinal();
		
		Double precioEsperado = 2000.0;
		
		assertEquals(precioEsperado, precioObtenido);
		
	}
	
	@Test
	public void dadoQueExisteUnGestorSiUnClienteHaceUnaDevolucionTardeSeLeSumaUnStrike() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		
		LocalDateTime fechaEmision1 = LocalDateTime.of(2025, 01, 20, 14, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision1);
		
		LocalDateTime fechaDevolucion1 = LocalDateTime.of(2025, 01, 29, 12, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion1);
		
		Integer cantidadDeStikesObtenidos = cliente.getStrike();
		Integer cantidadDeStrikesEsperados = 1;
		
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
		
		LocalDateTime fechaEmision2 = LocalDateTime.of(2025, 03, 01, 10, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision2);
		
		LocalDateTime fechaDevolucion2 = LocalDateTime.of(2025, 03, 10, 23, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion2);
		
		cantidadDeStikesObtenidos = cliente.getStrike();
		cantidadDeStrikesEsperados = 2;
	
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
	}
	
	@Test
	public void dadoQueExisteUnGestorSiUnClienteTieneMasDe3DevolucionesTardeQuedaBloqueadoEnLaTienda() {
		Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		
		gestor.agregarDisco(pelicula);
		
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		//primero tarde
		
		LocalDateTime fechaEmision1 = LocalDateTime.of(2025, 03, 20, 14, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision1);
		
		LocalDateTime fechaDevolucion1 = LocalDateTime.of(2025, 03, 29, 12, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion1);
		
		//el segundo
		LocalDateTime fechaEmision2 = LocalDateTime.of(2025, 04, 01, 10, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision2);
		
		LocalDateTime fechaDevolucion2 = LocalDateTime.of(2025, 04, 10, 23, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion2);
		
		// el 3
		LocalDateTime fechaEmision3 = LocalDateTime.of(2025, 05, 01, 10, 30);
		
		gestor.alquilarDisco(pelicula, cliente, fechaEmision3);
		
		LocalDateTime fechaDevolucion3 = LocalDateTime.of(2025, 05, 10, 23, 00);
		
		gestor.devolverDisco(pelicula, cliente, fechaDevolucion3);
		
		Integer cantidadDeStikesObtenidos = cliente.getStrike();
		Integer cantidadDeStrikesEsperados = 3;
		
		assertEquals(cantidadDeStrikesEsperados, cantidadDeStikesObtenidos);
		
		Boolean estaBloqueado = cliente.estaBloqueado();
		
		assertTrue(estaBloqueado);
		
	}
	
	 @Test
	 public void dadoQueExisteUnGestorSiUnClienteEstaBloqueadoNoPuedeAlquilar() {
		 Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		 
		 cliente.setStrike(3);
		 
		 gestor.agregarCliente(cliente);
		 
		 Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		 gestor.agregarDisco(pelicula);
		 
		 LocalDateTime fechaEmision= LocalDateTime.of(2025, 3, 10, 14, 30);
		 
		 Boolean seAlquilo = gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		 
		 assertFalse(seAlquilo);
	 
	 }

	 
	 @Test
	 public void dadoQueTengoUnAlquilerAlDevolverlaObtendoElPrecioFinalConRecargos() {
		 Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		 gestor.agregarCliente(cliente);
		 
		 Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		 gestor.agregarDisco(pelicula);
		 
		 LocalDateTime fechaEmision= LocalDateTime.of(2025, 3, 3, 14, 30);
		 gestor.alquilarDisco(pelicula, cliente, fechaEmision);
		 
		 LocalDateTime fechaDevolucion= LocalDateTime.of(2025, 3, 11, 14, 30);
		 gestor.devolverDisco(pelicula, cliente, fechaDevolucion);
		 
		 Alquiler alquiler = gestor.encontrarAlquilerDelDisco(pelicula, cliente);
		 
		 Double precioEsperado=4250D;
		 Double precioObtenido=alquiler.calcularPrecioFinal();
		 
		 assertEquals(precioEsperado,precioObtenido);
	 }
	 
	 @Test
	 
	 public void dadoQueTengoUnDiscoAlVenderloObtengoResultadoExitoso() {
		 Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		 gestor.agregarCliente(cliente);
		 
		 Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		 gestor.agregarDisco(pelicula);
		 
		 LocalDateTime fechaEmision= LocalDateTime.of(2025, 3, 10, 14, 30);
		 Boolean seVende = gestor.venderDisco(pelicula, cliente, fechaEmision);	 

		 assertTrue(seVende);
	 }


	 @Test
	 public void dadoQueExisteUngestorSiUnClienteDevuelveAntesDelPlazoDeVencimientoNoSumaStrike() {
			Disco pelicula = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
			
			gestor.agregarDisco(pelicula);
			
			Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		
		
			
			LocalDateTime fechaEmision = LocalDateTime.of(2025, 02, 20, 14, 30);
			
			gestor.alquilarDisco(pelicula, cliente, fechaEmision);
			
			LocalDateTime fechaDevolucion = LocalDateTime.of(2025, 02, 23, 15, 00);
			
			gestor.devolverDisco(pelicula, cliente, fechaDevolucion);
			
			Integer strikesObtenido = cliente.getStrike();
			Integer strikesEsperados = 0;
			
			assertEquals(strikesEsperados, strikesObtenido);

	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarSusClientesObtengoLaListaDeClientes() {
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		Cliente cliente2 = new ClientePremium(321, "Jorge", "Jorgelin", 10.0);
		gestor.agregarCliente(cliente2);
		Cliente cliente3 = new ClienteNormal(495, "Camilo", "Sisci");
		gestor.agregarCliente(cliente3);
		
		HashSet<Cliente> clientesEsperados = new HashSet<>();
		clientesEsperados.add(cliente);
		clientesEsperados.add(cliente2);
		clientesEsperados.add(cliente3);
		
		HashSet<Cliente> clientesObtenidos = gestor.obtenerListaClientes();
		
		assertEquals(clientesEsperados, clientesObtenidos);
		
		
		 
	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarClientesDeUnTipoObtengoLaListaCorrecta() {
		Cliente cliente = new ClienteNormal(123, "Sofia", "Gandulfo");
		gestor.agregarCliente(cliente);
		Cliente cliente2 = new ClientePremium(321, "Jorge", "Jorgelin", 10.0);
		gestor.agregarCliente(cliente2);
		Cliente cliente3 = new ClienteNormal(495, "Camilo", "Sisci");
		gestor.agregarCliente(cliente3);
		
		HashSet<Cliente> clientesEsperados = new HashSet<>();
		clientesEsperados.add(cliente);
		clientesEsperados.add(cliente3);
		HashSet<Cliente> clientesObtenidos = gestor.obtenerListaClientesNormal();
		
		assertEquals(clientesEsperados, clientesObtenidos);
		
		HashSet<Cliente> clientesEsperados2 = new HashSet<>();
		clientesEsperados2.add(cliente2);
		HashSet<Cliente> clientesObtenidos2 = gestor.obtenerListaClientesPremium();
		
		assertEquals(clientesEsperados2, clientesObtenidos2);
	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarSuInventarioObtengoLaListaDeDiscos() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		Disco musica = new Musica("Miranda es imposible", "Miranda", GeneroMusica.POP, 9, 1500.0);
		Disco programa = new Programa("Photoshop", "2.1.5", 2000.0, 300.0 ); 	
		gestor.agregarDisco(peli);	
		gestor.agregarDisco(juego);	
		gestor.agregarDisco(musica);
		gestor.agregarDisco(programa);
		
		ArrayList<Disco> discosEsperados = new ArrayList<>();
		discosEsperados.add(peli);
		discosEsperados.add(juego);
		discosEsperados.add(musica);
		discosEsperados.add(programa);
		
		ArrayList<Disco> discosObtenidos = gestor.obtenerInventario();
		
		assertEquals(discosEsperados, discosObtenidos);
	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarPorLosDiscosDeUnTipoObtengoLaListaCorrecta() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco peli2 = new Pelicula("Nueve Reinas", GeneroPelicula.SUSPENSO, 2000, "Fabian Bielinsky", 115, 1500.0, 300.0);
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		Disco juego2 = new Juego("Mario", Consola.NINTENDO, 500.0); 
		Disco juego3 = new Juego("Halo", Consola.XBOX, 500.0); 
		Disco musica = new Musica("Miranda es imposible", "Miranda", GeneroMusica.POP, 9, 1500.0);
		Disco programa = new Programa("Photoshop", "2.1.5", 2000.0, 300.0 ); 	
		gestor.agregarDisco(peli);
		gestor.agregarDisco(peli2);
		gestor.agregarDisco(juego);	
		gestor.agregarDisco(juego2);	
		gestor.agregarDisco(juego3);	
		gestor.agregarDisco(musica);
		gestor.agregarDisco(programa);
		
		ArrayList<Disco> pelisEsperadas = new ArrayList<>();
		pelisEsperadas.add(peli);
		pelisEsperadas.add(peli2);
		ArrayList<Disco> pelisObtenidas = gestor.obtenerInventarioPeliculas();
		
		assertEquals(pelisEsperadas, pelisObtenidas);
		
		ArrayList<Disco> juegosEsperados = new ArrayList<>();
		juegosEsperados.add(juego);
		juegosEsperados.add(juego2);
		juegosEsperados.add(juego3);
		ArrayList<Disco> juegosObtenidos = gestor.obtenerInventarioJuegos();
		
		assertEquals(juegosEsperados, juegosObtenidos);
		
		ArrayList<Disco> musicaEsperados = new ArrayList<>();
		musicaEsperados.add(musica);
		
		ArrayList<Disco> musicaObtenidos = gestor.obtenerInventarioMusica();
		
		assertEquals(musicaEsperados, musicaObtenidos);
		
		ArrayList<Disco> programasEsperados = new ArrayList<>();
		programasEsperados.add(programa);
		
		ArrayList<Disco> programasObtenidos = gestor.obtenerInventarioPrograma();
		
		assertEquals(programasEsperados, programasObtenidos);
		
	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarPorLasOperacionesRealizadasbtengoLaListaCompleta() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco peli2 = new Pelicula("Nueve Reinas", GeneroPelicula.SUSPENSO, 2000, "Fabian Bielinsky", 115, 1500.0, 300.0);
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		gestor.agregarDisco(peli);
		gestor.agregarDisco(peli2);
		gestor.agregarDisco(juego);
		Cliente cliente = new ClienteNormal(495, "Camilo", "Sisci");
		gestor.agregarCliente(cliente);
		LocalDateTime fechaEmision= LocalDateTime.of(2025, 10, 6, 16, 58);
		gestor.alquilarDisco(peli, cliente, fechaEmision);
		gestor.alquilarDisco(peli2, cliente, fechaEmision);
		gestor.venderDisco(juego, cliente, fechaEmision);
		
		HashSet<Operacion> operacionesObtenidas = gestor.obtenerOperaciones();
		Integer cantEsperada = 3;
		
		assertEquals(cantEsperada,(Integer)operacionesObtenidas.size());
		
		
	 }
	 
	 @Test
	 public void dadoQueExisteUnGestorAlConsultarPorLasOperacionesDeUnTipoRealizadasbtengoLaListaCorrespondiente() {
		Disco peli = new Pelicula("Coherence", GeneroPelicula.SUSPENSO, 2014, "James Ward Byrkit", 120, 2000.0, 500.0); 
		Disco peli2 = new Pelicula("Nueve Reinas", GeneroPelicula.SUSPENSO, 2000, "Fabian Bielinsky", 115, 1500.0, 300.0);
		Disco juego = new Juego("Crash", Consola.PLAY_STATION, 500.0); 
		Disco juego2 = new Juego("Mario", Consola.NINTENDO, 500.0); 
		Disco juego3 = new Juego("Halo", Consola.XBOX, 500.0); 
		gestor.agregarDisco(peli);
		gestor.agregarDisco(peli2);
		gestor.agregarDisco(juego);
		gestor.agregarDisco(juego2);
		gestor.agregarDisco(juego3);
		Cliente cliente = new ClienteNormal(495, "Camilo", "Sisci");
		gestor.agregarCliente(cliente);
		LocalDateTime fechaEmision= LocalDateTime.of(2025, 10, 6, 16, 58);
		gestor.alquilarDisco(peli, cliente, fechaEmision);
		gestor.alquilarDisco(peli2, cliente, fechaEmision);
		gestor.venderDisco(juego, cliente, fechaEmision);
		gestor.venderDisco(juego2, cliente, fechaEmision);
		gestor.venderDisco(juego3, cliente, fechaEmision);
		
		HashSet<Operacion> operacionesObtenidas = gestor.obtenerOperacionesAlquiler();
		Integer cantEsperada = 2;
		
		assertEquals(cantEsperada,(Integer)operacionesObtenidas.size());
		
		operacionesObtenidas = gestor.obtenerOperacionesVenta();
		cantEsperada = 3;
		
		assertEquals(cantEsperada,(Integer)operacionesObtenidas.size());
	 }
	 
	 
	 
}
