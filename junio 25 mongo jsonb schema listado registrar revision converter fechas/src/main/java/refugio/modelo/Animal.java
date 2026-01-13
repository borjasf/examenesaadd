package refugio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;

public class Animal implements Identificable {
	@BsonId // anotacion MongoDB
	@BsonRepresentation(BsonType.OBJECT_ID) // anotacion MongoDB
	private String id;
	private String nombre;
	private String especie;
	private int edad;
	private LocalDate fechaEntrada;
	private List<Revision> revisiones;
	private Adopcion adopcion;

	public Animal(String nombre, String especie, int edad, LocalDate fechaEntrada) {
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.fechaEntrada = fechaEntrada;
		this.revisiones = new ArrayList<>();
		this.adopcion = null;
	}

	public Animal() {
		super();
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

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public List<Revision> getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}

	public Adopcion getAdopcion() {
		return adopcion;
	}

	public void setAdopcion(Adopcion adopcion) {
		this.adopcion = adopcion;
	}

}
