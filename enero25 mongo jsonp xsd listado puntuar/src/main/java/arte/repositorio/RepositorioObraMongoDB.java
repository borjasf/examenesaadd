package arte.repositorio;


import java.io.IOException;

import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import arte.modelo.Obra;
import repositorio.RepositorioMongoDB;
import utils.MongoDBConnectionManager;
import utils.PropertiesReader;

public class RepositorioObraMongoDB extends RepositorioMongoDB<Obra> {

	private MongoCollection<Obra> collection;
	protected MongoDatabase database;

	public RepositorioObraMongoDB() throws IOException {

		MongoDBConnectionManager manager = MongoDBConnectionManager.getMongoDBConnectionManager();
		MongoClient mongoClient = manager.getMongoClient();
		PropertiesReader properties = new PropertiesReader("mongo.properties");
		String databaseString = properties.getProperty("mongodatabase");
		
		database = mongoClient.getDatabase(databaseString);
		
		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		
		CodecRegistry defacultCodecRegistry = CodecRegistries
				.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		// CAMBIAR ESTO
		collection = database.getCollection("obras",Obra.class) //Hay que cambiar "obras" también respectivamente en el inicio de RepositorioObraAdHocMongoDB
				.withCodecRegistry(defacultCodecRegistry);
		// Estructura genérica mental:
		//collection = database.getCollection("NOMBRE_COLECCION_EN_ENUNCIADO", ClaseJava.class)...
		
	}

	@Override
	public MongoCollection<Obra> getCollection() {
		return collection;
	}

	@Override
	public Document getFilterById(String id) {
		return new Document("_id", new ObjectId(id));
		
		//return new Document("_id", id); // si manejáramos los identificadores como tipo String
		
	}

	@Override
	public String getIdValue(BsonValue valor) {		
		return valor.asObjectId().getValue().toString();
		
		//return valor.asString().getValue().toString(); // si manejáramos los identificadores como tipo String
	}

}