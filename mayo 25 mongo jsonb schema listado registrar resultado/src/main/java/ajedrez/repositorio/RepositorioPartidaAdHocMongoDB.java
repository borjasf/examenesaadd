package ajedrez.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import ajedrez.modelo.Partida;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioPartidaAdHocMongoDB extends RepositorioPartidaMongoDB implements RepositorioPartidaAdHoc{
	
	protected MongoCollection<Document> coleccionSinCodificar;
	
	public RepositorioPartidaAdHocMongoDB() throws IOException {
		super();
		coleccionSinCodificar = database.getCollection("partidas");
	}

	@Override
	public List<Partida> buscarPartidasRondaYEstado(int ronda, boolean pendiente) throws RepositorioException, EntidadNoEncontrada {
		try {
			List<Partida> resultado = new ArrayList<>();
			// Filtro base: por ronda
			Document filtro = new Document("ronda", ronda);
			if (pendiente) {
				// Partidas sin terminar: resultado == null
				filtro.append("resultado", null);
			}
			// Si pendiente es false, devuelve todas las partidas de la ronda (sin filtrar resultado)
			resultado = getCollection().find(filtro).into(new ArrayList<>());
			return resultado;
		} catch (MongoException ex) {
			throw new RepositorioException("Error buscando partidas por ronda y estado", ex);
		}
	}

	
	
	
	
	
	@Override
	public List<Partida> buscarPartidasRondaYEstado(boolean pendiente) throws RepositorioException, EntidadNoEncontrada {
		try {
			List<Partida> resultado = new ArrayList<>();
			if (pendiente) {
				// Partidas sin terminar: resultado == null
				Bson filtro = Filters.eq("resultado", null);
				resultado = getCollection().find(Filters.eq("resultado", null)).into(new ArrayList<>());
			} else {
				// Todas las partidas
				resultado = getCollection().find().into(new ArrayList<>());
			}
			return resultado;
		} catch (MongoException ex) {
			throw new RepositorioException("Error buscando partidas por estado", ex);
		}
	}

}