package ajedrez.modelo;

import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonId;

public class Movimiento {

	    
	    private String blancas;
	    private String negras;

	    
	    public Movimiento() {
	    	
	    }

		public Movimiento(String blancas, String negras) {
			super();
			this.blancas = blancas;
			this.negras = negras;
		}
		
		public Movimiento(String blancas) {
			super();
			this.blancas = blancas;
		}

	

		public String getBlancas() {
			return blancas;
		}

		public void setBlancas(String blancas) {
			this.blancas = blancas;
		}

		public String getNegras() {
			return negras;
		}

		public void setNegras(String negras) {
			this.negras = negras;
		}
	    
	
}
