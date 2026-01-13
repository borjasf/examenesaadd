package arte.modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;

public class Obra implements Identificable {
	@BsonId // anotacion MongoDB
	@BsonRepresentation(BsonType.OBJECT_ID) // anotacion MongoDB
	String id;
	String codigoInscripcion;
	String titulo;
	String descripcion;
	String tecnica;
	Dimensiones dimensiones;
	String artista;
	List<Puntuacion> puntuaciones;
public Obra() {
		super();
	}
	public Obra(String codigoInscripcion, String titulo, String descripcion, String tecnica, Dimensiones dimensiones,
			String artista) {
		this.codigoInscripcion = codigoInscripcion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tecnica = tecnica;
		this.dimensiones = dimensiones;
		this.artista = artista;
		this.puntuaciones = new ArrayList<>();
	}

	public String getCodigoInscripcion() {
		return codigoInscripcion;
	}

	public void setCodigoInscripcion(String codigo_inscripcion) {
		this.codigoInscripcion = codigo_inscripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public Dimensiones getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(Dimensiones dimensiones) {
		this.dimensiones = dimensiones;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
