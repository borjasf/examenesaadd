package ajedrez.modelo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;


public class TiempoLimite {
    
   
    
    private double por_jugador;
    private double incremento;
    
    public TiempoLimite() {
   
    }
    
    public TiempoLimite(double por_jugador,double incremento) {
    	super();
    	this.incremento=incremento;
    	this.por_jugador=por_jugador;
    }

	

	public double getPor_jugador() {
		return por_jugador;
	}

	public void setPor_jugador(double por_jugador) {
		this.por_jugador = por_jugador;
	}

	public double getIncremento() {
		return incremento;
	}

	public void setIncremento(double incremento) {
		this.incremento = incremento;
	}
    
    
}