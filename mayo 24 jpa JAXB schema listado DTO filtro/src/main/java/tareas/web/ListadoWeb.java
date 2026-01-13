package tareas.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import tareas.modelo.Estado;
import tareas.servicio.IServicioTareas;
import tareas.servicio.TareaResumen;


@Named
@ViewScoped
public class ListadoWeb implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TareaResumen> tareas;

	private IServicioTareas servicioTareas;

	private String filtroEstadoTarea;

	@Inject
	protected FacesContext facesContext;

	public ListadoWeb() {
		servicioTareas = FactoriaServicios.getServicio(IServicioTareas.class);
		tareas = new ArrayList<>();
		filtroEstadoTarea = ""; 
	}

	@PostConstruct
	public void init() {
		System.out.println("=== Inicializando ListadoWeb ===");
		cargarListado();
		System.out.println("=== tareas cargados: " + tareas.size() + " ===");
	}

	public void cargarListado() {
		try {
			if (!filtroEstadoTarea.equals("")) {
				tareas = servicioTareas.recuperarTareas(java.util.Optional.of(Estado.valueOf(filtroEstadoTarea)));
			} else {
				tareas = servicioTareas.recuperarTareas(java.util.Optional.empty());
			}
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se pudo cargar el listado: " + e.getMessage()));
			tareas = new ArrayList<>();
		}
	}

	public void filtrar() {
		cargarListado();
	}

	public void limpiarFiltro() {
		filtroEstadoTarea = "";
		cargarListado();
	}

	public List<TareaResumen> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaResumen> tareas) {
		this.tareas = tareas;
	}

	public String getFiltroEstadoTarea() {
		return filtroEstadoTarea;
	}

	public void setFiltroEstadoTarea(String filtroEstadoTarea) {
		this.filtroEstadoTarea = filtroEstadoTarea;
	}

	

}