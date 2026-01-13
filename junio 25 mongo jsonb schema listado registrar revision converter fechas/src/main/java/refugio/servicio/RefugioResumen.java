package refugio.servicio;

import java.time.LocalDate;

public class RefugioResumen {

	private String id;
	private String nombre;
	private String especie;
	private LocalDate fechaEntrada;
	private boolean adopcion;

	public RefugioResumen() {
		super();
	}

	public RefugioResumen(String id, String nombre, String especie, LocalDate fechaEntrada, boolean adopcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaEntrada = fechaEntrada;
		this.adopcion = adopcion;
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

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public boolean isAdopcion() {
		return adopcion;
	}

	public void setAdopcion(boolean adopcion) {
		this.adopcion = adopcion;
	}

	@Override
	public String toString() {
		return "RefugioResumen [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", fechaEntrada="
				+ fechaEntrada + ", adopcion=" + adopcion + "]";
	}

	

}