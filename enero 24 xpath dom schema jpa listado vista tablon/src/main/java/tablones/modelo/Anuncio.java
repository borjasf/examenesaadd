package tablones.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // anotación JPA
public class Anuncio {
	
	@Id // anotación JPA
	@GeneratedValue(strategy = GenerationType.IDENTITY) // anotación JPA
	private Integer id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String resumen;
	
	@Column(nullable = false)
	private LocalDateTime fechaPublicacion;
	
	private String enlace; // opcional
	
	@ManyToOne
	@JoinColumn(name = "id_tablon", nullable = false)
	private Tablon tablon;
	
	public Anuncio() { // POJO
		
	}

	public Anuncio(String titulo, String resumen, String enlace) {
		this.titulo = titulo;
		this.resumen = resumen;
		this.fechaPublicacion = LocalDateTime.now();
		this.enlace = enlace;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Tablon getTablon() {
		return tablon;
	}

	public void setTablon(Tablon tablon) {
		this.tablon = tablon;
	}
	
	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", titulo=" + titulo + ", resumen=" + resumen + 
				", fechaPublicacion=" + fechaPublicacion + ", enlace=" + enlace + "]";
	}
}