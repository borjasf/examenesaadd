package ajedrez.modelo;

import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonId;

public class Resultado {

	
	
	
    
	private String ganador;
	private TipoFinalizacion tipo;

    
    public Resultado() {
    	
    }


	public Resultado(String ganador, TipoFinalizacion tipo) {
		super();
		this.ganador = ganador;
		this.tipo = tipo;
	}
	public Resultado( TipoFinalizacion tipo) {
		super();
		this.tipo = tipo;
	}


	


	public String getGanador() {
		return ganador;
	}


	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public TipoFinalizacion getTipo() {
		return tipo;
	}


	public void setTipo(TipoFinalizacion tipo) {
		this.tipo = tipo;
	}


	
}