package arte.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import arte.modelo.Obra;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioObraAdHocMongoDB extends RepositorioObraMongoDB implements RepositorioObraAdHoc {
	
	protected MongoCollection<Document> coleccionSinCodificar;
	
	public RepositorioObraAdHocMongoDB() throws IOException {
		super();
		coleccionSinCodificar = database.getCollection("obras");
	}

	@Override
	public List<Obra> buscarObrasMinTecnica(double minTecnica)
			throws RepositorioException, EntidadNoEncontrada {
		try {
			Bson filtro = Filters.gte("puntuaciones.nota_tecnica", minTecnica); //filtro para notas técnicas mayores o iguales al mínimo
			return getCollection().find(filtro).into(new ArrayList<>());
		} catch (Exception e) {
			throw new RepositorioException("Error al buscar obras por nota técnica mínima", e);
		}
	}

	@Override
	public List<Document> recuperarObrasNotasMedias() throws RepositorioException, EntidadNoEncontrada {
		try {
			return coleccionSinCodificar.aggregate(Arrays.asList(
				Aggregates.unwind("$puntuaciones"), //separa los elementos del array como en documentos independientes
				Aggregates.group("$_id", //agrupamos por id de la obra
					Accumulators.first("codigo_inscripcion", "$codigoInscripcion"), //tomamos el primer valor de codigo_inscripcion y titulo
					Accumulators.first("titulo", "$titulo"),
					Accumulators.avg("media_tecnica", "$puntuaciones.nota_tecnica"), //calculamos la media de las notas técnicas y artísticas
					Accumulators.avg("media_artistica", "$puntuaciones.nota_artistica")
				),
				Aggregates.project(Projections.fields( //proyectamos los campos que queremos en el resultado
					Projections.include("codigo_inscripcion", "titulo", "media_tecnica", "media_artistica"),
					Projections.excludeId() //quitamos la id
				))
			)).into(new ArrayList<>()); //convertimos el resultado en una lista
		} catch (Exception e) {
			throw new RepositorioException("Error al recuperar obras con notas medias", e);
		}
	}
}