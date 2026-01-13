package tablones.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import repositorio.Identificable;

@XmlRootElement // anotación JAXB
@Entity // anotacion JPA
public class Tablon implements Identificable {
	
	@Id //anotacion JPA
	private String id;
	private String nombre;
	
	@OneToMany(mappedBy = "tablon", cascade = CascadeType.ALL) //anotación JPA
	private LinkedList<Anuncio> anuncios = new LinkedList<>();
	
	public Tablon() { // POJO
		this.id = UUID.randomUUID().toString();
	}
	
	public Tablon(String nombre) {	
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.anuncios = new LinkedList<>();	
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LinkedList<Anuncio> getAnuncios() {
		return anuncios;
	}
	
	public void setAnuncios(LinkedList<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	// Método helper para agregar anuncios manteniendo la relación bidireccional
	public void addAnuncio(Anuncio anuncio) {
		anuncios.add(anuncio);
		anuncio.setTablon(this);
	}
	
	public void removeAnuncio(Anuncio anuncio) {
		anuncios.remove(anuncio);
		anuncio.setTablon(null);
	}

	@Override
	public String toString() {
		return "Tablon [id=" + id + ", nombre=" + nombre + ", anuncios=" + anuncios + "]";
	}
}