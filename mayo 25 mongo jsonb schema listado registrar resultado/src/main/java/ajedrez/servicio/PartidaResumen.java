package ajedrez.servicio;

import java.util.LinkedList;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import ajedrez.modelo.Movimiento;
import ajedrez.modelo.Resultado;
import ajedrez.modelo.TiempoLimite;

public class PartidaResumen {

	
    private String id;
    private int ronda;
    private String torneo;
    private String jugador_blancas;
    private String jugador_negras;
    private Resultado resultado;
   
    
    public PartidaResumen() {
		super();
	}

	public PartidaResumen(int ronda, String torneo, String jugador_blancas, String jugador_negras,
			TiempoLimite tiempo_limite) {
		super();
		this.ronda = ronda;
		this.torneo = torneo;
		this.jugador_blancas = jugador_blancas;
		this.jugador_negras = jugador_negras;
		

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

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "PartidaResumen [id=" + id + ", ronda=" + ronda + ", torneo=" + torneo + ", jugador_blancas="
				+ jugador_blancas + ", jugador_negras=" + jugador_negras + ", resultado=" + resultado + "]";
	}
	
}