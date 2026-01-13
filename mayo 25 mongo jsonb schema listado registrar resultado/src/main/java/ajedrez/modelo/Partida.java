package ajedrez.modelo;


import java.util.LinkedList;
import java.util.List;


import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;




public class Partida implements Identificable {
    
    @BsonId // anotacion MongoDB
    @BsonRepresentation(BsonType.OBJECT_ID) //anotacion MongoDB
    private String id;
    private int ronda;
    private String torneo;
    private String jugador_blancas;
    private String jugador_negras;
    private TiempoLimite tiempo_limite;
    private List<Movimiento> movimientos;
    private Resultado resultado;
   
    
    public Partida() {
		super();
	}

	public Partida(int ronda, String torneo, String jugador_blancas, String jugador_negras,
			TiempoLimite tiempo_limite) {
		super();
		this.ronda = ronda;
		this.torneo = torneo;
		this.jugador_blancas = jugador_blancas;
		this.jugador_negras = jugador_negras;
		this.tiempo_limite = tiempo_limite;
		this.movimientos=new LinkedList<>();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public String getTorneo() {
		return torneo;
	}

	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}

	public String getJugador_blancas() {
		return jugador_blancas;
	}

	public void setJugador_blancas(String jugador_blancas) {
		this.jugador_blancas = jugador_blancas;
	}

	public String getJugador_negras() {
		return jugador_negras;
	}

	public void setJugador_negras(String jugador_negras) {
		this.jugador_negras = jugador_negras;
	}

	public TiempoLimite getTiempo_limite() {
		return tiempo_limite;
	}

	public void setTiempo_limite(TiempoLimite tiempo_limite) {
		this.tiempo_limite = tiempo_limite;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}