package ejercicio2;


import java.io.PrintStream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import ajedrez.modelo.Partida;
import ajedrez.modelo.TiempoLimite;


public class Programa {

	public static void main(String[] args) throws Exception {

		// Construcción del contexto
		Jsonb contexto = JsonbProvider.provider().create().build();
		// Creación del objeto Persona
		TiempoLimite t = new TiempoLimite(3.2, 2);
		Partida partida = new Partida(1, "t1", "blanco", "negro", t);
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
