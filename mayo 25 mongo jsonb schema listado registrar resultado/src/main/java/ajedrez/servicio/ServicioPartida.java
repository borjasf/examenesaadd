package ajedrez.servicio;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import ajedrez.modelo.Movimiento;
import ajedrez.modelo.Partida;
import ajedrez.modelo.Resultado;
import ajedrez.modelo.TiempoLimite;
import ajedrez.modelo.TipoFinalizacion;
import ajedrez.repositorio.RepositorioPartidaAdHoc;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ServicioPartida implements IServicioPartida {
	
	// NO PONER IMPLEMENTACION CONCRETA DE REPOSITORIO
	private RepositorioPartidaAdHoc repositorio = FactoriaRepositorios.getRepositorio(Partida.class);

	@Override
	public String crearPartida(int ronda, String torneo, String jugador_blancas, String jugador_negras,
			double tiempo_limite, double incremento) throws RepositorioException, EntidadNoEncontrada {
		
		if(ronda < 0)
			throw new IllegalArgumentException("ronda no puede ser negativa");
		if(torneo==null || torneo.isEmpty())
			throw new IllegalArgumentException("torneo: no debe ser nulo ni vacio");
		if(jugador_blancas==null || jugador_blancas.isEmpty())
			throw new IllegalArgumentException("jugador_blancas: no debe ser nulo ni vacio");
		if(jugador_negras==null || jugador_negras.isEmpty())
			throw new IllegalArgumentException("jugador_negras: no debe ser nulo ni vacio");
		if(incremento < 0)
			throw new IllegalArgumentException("incremento no puede ser negativa");
		
		TiempoLimite t = new TiempoLimite(tiempo_limite, incremento);
		Partida partida = new Partida(ronda, torneo, jugador_blancas, jugador_negras, t);
		return repositorio.add(partida); //DARLE UN ID A LA PARTIDA SE HACE AL AÑADIRLA AL REPOSITORIO
	}
	
	public void registrarMovimiento(String idPartida,String blancas,String negras) throws RepositorioException,EntidadNoEncontrada{
		if(idPartida==null || idPartida.isEmpty())
			throw new IllegalArgumentException("idPartida: no debe ser nulo ni vacio");
		if(blancas==null || blancas.isEmpty())
			throw new IllegalArgumentException("blancas: no debe ser nulo ni vacio");
		
		Movimiento movimiento = new Movimiento(blancas);
		if(negras!=null) {
			movimiento.setNegras(negras);
		}
			
		Partida partida = repositorio.getById(idPartida);
		partida.getMovimientos().add(movimiento);
		repositorio.update(partida);
	}
	
	public void registrarMovimiento(String idPartida,String blancas) throws RepositorioException,EntidadNoEncontrada{
		if(idPartida==null || idPartida.isEmpty())
			throw new IllegalArgumentException("idPartida: no debe ser nulo ni vacio");
		if(blancas==null || blancas.isEmpty())
			throw new IllegalArgumentException("blancas: no debe ser nulo ni vacio");
	
		Movimiento movimiento = new Movimiento(blancas);
		Partida partida = repositorio.getById(idPartida);
		partida.getMovimientos().add(movimiento);
		repositorio.update(partida);
	}
	
	public void establecerResultadoPartida(String idPartida,String ganador,TipoFinalizacion tipoFinalizacion)  throws RepositorioException,EntidadNoEncontrada{
		if(idPartida==null || idPartida.isEmpty())
			throw new IllegalArgumentException("idPartida: no debe ser nulo ni vacio");
		
		if(tipoFinalizacion==null )
			throw new IllegalArgumentException("tipoFinalizacion: no debe ser nulo ni vacio");
		Resultado resultado = new Resultado(tipoFinalizacion);
		
		if(ganador!=null) {
			resultado.setGanador(ganador);
		}
		Partida partida = repositorio.getById(idPartida);
		partida.setResultado(resultado);
		repositorio.update(partida);
		
		
	}
	
	public List<PartidaResumen> recuperarPartidas(int ronda,boolean pendiente)throws RepositorioException,EntidadNoEncontrada{
		return getListadoResumen(repositorio.buscarPartidasRondaYEstado(ronda,pendiente));
	}
	
	public List<PartidaResumen> recuperarPartidas()throws RepositorioException,EntidadNoEncontrada{
		// devuelve todas las partidas
		return getListadoResumen(repositorio.getAll());
	}
	
	public List<PartidaResumen> recuperarPartidas(boolean pendiente)throws RepositorioException,EntidadNoEncontrada{
		return getListadoResumen(repositorio.buscarPartidasRondaYEstado(pendiente));
	}
	
	
	private PartidaResumen toResumen(Partida partida) {
		PartidaResumen resumen = new PartidaResumen();
		resumen.setId(partida.getId());
		resumen.setTorneo(partida.getTorneo());
		resumen.setRonda(partida.getRonda());
		resumen.setJugador_blancas(partida.getJugador_blancas());
		resumen.setJugador_negras(partida.getJugador_negras());
		resumen.setResultado(partida.getResultado());
		return resumen;
	}
	
	public List<PartidaResumen> getListadoResumen(List<Partida> partidas) {
		List<PartidaResumen> resumenList = new LinkedList<>();
		for (Partida partida : partidas) {
			resumenList.add(toResumen(partida));
		}
		return resumenList;
	}
	
	public void añadirJson(String idPartida) throws JsonbException, FileNotFoundException {
		// Construcción del contexto
				Jsonb contexto = JsonbProvider.provider().create().build();
				// Creación del objeto Persona
				TiempoLimite t = new TiempoLimite(3.2, 2);
				Partida partida = new Partida(1, "t1", "blanco", "negro", t);
				Movimiento m = new Movimiento();
				m.setBlancas("e5");
				m.setNegras("d4");
				partida.getMovimientos().add(m);
				partida.setId(idPartida);
				// Serialización a JSON
				String cadenaJSON = contexto.toJson(partida);

				System.out.println("partida: " + cadenaJSON);
				// Serialización a JSON en un fichero
				JsonbConfig config = new JsonbConfig()
		                .withNullValues(true)
		                .withFormatting(true)
		                .withPropertyNamingStrategy(
							PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
		                .withPropertyOrderStrategy(
							PropertyOrderStrategy.LEXICOGRAPHICAL);   
		    	// Nuevo contexto con configuración
				Jsonb contexto2 = JsonbProvider.provider().create().withConfig(config).build();
				//Serialización a JSON con nueva configuración
				String cadenaJSON2 = contexto2.toJson(partida);

				System.out.println("Partida v2: " + cadenaJSON2);
				//	 Escritura en fichero
				contexto2.toJson(partida, new PrintStream("partida/partida.json"));
			
				
				System.out.println("fin.");
	}


}