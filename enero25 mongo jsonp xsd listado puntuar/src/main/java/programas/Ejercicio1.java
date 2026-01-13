package programas;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

public class Ejercicio1 {

	public static void main(String[] args) throws Exception {
		InputStreamReader fuente = new FileReader("json/obra.json");
		JsonReader jsonReader = Json.createReader(fuente);
		
		JsonObject obra = jsonReader.readObject();
		
		JsonArray puntuaciones = obra.getJsonArray("puntuaciones");
		
		double sumaTecnica = 0;
		double sumaArtistica = 0;
		int count = 0;
		
		if (puntuaciones != null) {
			for (JsonValue v : puntuaciones) {
				JsonObject p = (JsonObject) v;
				sumaTecnica += p.getJsonNumber("nota_tecnica").doubleValue();
				sumaArtistica += p.getJsonNumber("nota_artistica").doubleValue();
				count++;
			}
		}
		
		if (count > 0) {
			double mediaTecnica = sumaTecnica / count;
			double mediaArtistica = sumaArtistica / count;
			double notaGlobal = mediaTecnica + mediaArtistica;
			
			System.out.println("Media nota técnica: " + mediaTecnica);
			System.out.println("Media nota artística: " + mediaArtistica);
			System.out.println("Nota global: " + notaGlobal);
		} else {
			System.out.println("No hay puntuaciones.");
		}
	}
}
