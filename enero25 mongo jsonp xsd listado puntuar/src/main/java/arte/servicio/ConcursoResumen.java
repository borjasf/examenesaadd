package arte.servicio;

import java.util.LinkedList;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;



public class ConcursoResumen {

	private String id;
    private String codigoInscripcion;
    private String titulo;
    private String tecnica;
    private String artista;
    private String descripcion;

   public ConcursoResumen() {
	   super();
   }
    public ConcursoResumen(String id, String codigoInscripcion, String titulo, String tecnica, String artista, String descripcion) {
    	this.id = id;
		this.codigoInscripcion = codigoInscripcion;
		this.titulo = titulo;
		this.tecnica = tecnica;
		this.artista = artista;
		this.descripcion = descripcion;
	}
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigoInscripcion() {
		return codigoInscripcion;
	}	public void setCodigoInscripcion(String codigoInscripcion) {
		this.codigoInscripcion = codigoInscripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ConcursoResumen [codigoInscripcion=" + codigoInscripcion + ", titulo=" + titulo + ", tecnica=" + tecnica
				+ ", artista=" + artista + ", descripcion=" + descripcion + "]";
	}
	
}