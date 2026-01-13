package tareas.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utils.LocalDateAdapter;
@Entity
@XmlType(propOrder = { "estado", "fechaInicio", "fechaFin" })
public class EstadoTarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Estado estado;
	
	@Column(name = "fecha_inicio", nullable = false)
	private LocalDate fechaInicio;
	@Column (name = "fecha_fin")
	private LocalDate fechaFin;
	public EstadoTarea(Estado estado, LocalDate inicio) {
		this.estado = estado;
		this.fechaInicio = inicio;

	}
	public EstadoTarea() {
		
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class) // anotación JAXB
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate inicio) {
		this.fechaInicio = inicio;
	}
	@XmlJavaTypeAdapter(value=LocalDateAdapter.class) // anotación JAXB
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fin) {
		this.fechaFin = fin;
	}
	
}
