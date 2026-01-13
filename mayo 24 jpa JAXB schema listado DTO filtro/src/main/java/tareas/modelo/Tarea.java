package tareas.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import repositorio.Identificable;
@XmlRootElement
@Entity
@XmlType(propOrder = { "nombre", "descripcion", "estados" })
public class Tarea implements Identificable{
	
	@Id
	private String id;
	@Column(nullable=false)
	private String nombre;
	private String descripcion;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tarea_id")
	private List<EstadoTarea> estados;
	public Tarea() {
		
	}
	public Tarea(String nombre, String descripcion, LocalDate inicio,Estado e) {
		this.id=UUID.randomUUID().toString();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estados = new ArrayList<>();
		this.estados.add(new EstadoTarea(e,inicio));

	}
	
	@XmlAttribute
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<EstadoTarea> getEstados() {
		return estados;
	}
	public void setEstados(List<EstadoTarea> estados) {
		this.estados = estados;
	}
	
}
