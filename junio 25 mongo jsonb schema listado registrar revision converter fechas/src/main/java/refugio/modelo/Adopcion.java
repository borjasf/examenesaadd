package refugio.modelo;

import java.time.LocalDate;

public class Adopcion {
	private LocalDate fechaAdopcion;
	private String nombreAdoptante;
	private String telefonoAdoptante;

	public Adopcion(LocalDate fechaAdopcion, String nombreAdoptante, String telefonoAdoptante) {
		this.fechaAdopcion = fechaAdopcion;
		this.nombreAdoptante = nombreAdoptante;
		this.telefonoAdoptante = telefonoAdoptante;
	}

	public Adopcion() {

	}

	public LocalDate getFechaAdopcion() {
		return fechaAdopcion;
	}

	public void setFechaAdopcion(LocalDate fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	public String getNombreAdoptante() {
		return nombreAdoptante;
	}

	public void setNombreAdoptante(String nombreAdoptante) {
		this.nombreAdoptante = nombreAdoptante;
	}

	public String getTelefonoAdoptante() {
		return telefonoAdoptante;
	}

	public void setTelefonoAdoptante(String telefonoAdoptante) {
		this.telefonoAdoptante = telefonoAdoptante;
	}

}
