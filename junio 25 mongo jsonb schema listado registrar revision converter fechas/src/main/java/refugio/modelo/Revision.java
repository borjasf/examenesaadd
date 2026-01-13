package refugio.modelo;

import java.time.LocalDate;

public class Revision {
	private LocalDate fechaRevision;
	private String observaciones;
	private double peso;

	public Revision(LocalDate fechaRevision, String observaciones, double peso) {
		this.fechaRevision = fechaRevision;
		this.observaciones = observaciones;
		this.peso = peso;
	}

	public Revision() {

	}

	public LocalDate getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(LocalDate fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

}
