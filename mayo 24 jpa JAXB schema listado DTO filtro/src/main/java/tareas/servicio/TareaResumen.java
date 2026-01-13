package tareas.servicio;

import java.time.LocalDate;



import tareas.modelo.Estado;


public class TareaResumen {

	private String id;
	private String nombre;
	private LocalDate fechaPrevista;
	private Estado estadoActual;
	
	public TareaResumen(String id, String nombre, LocalDate fechaPrevista, Estado estadoActual) {
		this.id = id;
		this.nombre = nombre;
		this.fechaPrevista = fechaPrevista;
		this.estadoActual = estadoActual;
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

	public LocalDate getFechaPrevista() {
		return fechaPrevista;
	}
	public void setFechaPrevista(LocalDate fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}
	public Estado getEstadoActual() {
		return estadoActual;
	}
	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}
	@Override
	public String toString() {
		return "TareaResumen [id=" + id + ", nombre=" + nombre + ", fechaPrevista=" + fechaPrevista + ", estadoActual="
				+ estadoActual + "]";
	}
	
	
}
