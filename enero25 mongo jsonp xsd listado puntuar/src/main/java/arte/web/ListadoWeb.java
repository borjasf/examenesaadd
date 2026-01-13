package arte.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arte.servicio.IServicioConcurso;
import arte.servicio.ConcursoResumen;
import servicio.FactoriaServicios;

@Named
@ViewScoped
public class ListadoWeb implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ConcursoResumen> obras;

	private IServicioConcurso servicioConcurso;

	private String filtroMinTecnica;

	@Inject
	protected FacesContext facesContext;

	public ListadoWeb() {
		servicioConcurso = FactoriaServicios.getServicio(IServicioConcurso.class);
		obras = new ArrayList<>();
		filtroMinTecnica = ""; 
	}

	@PostConstruct
	public void init() {
		System.out.println("=== Inicializando ListadoWeb ===");
		cargarListado();
		System.out.println("=== Partidas cargadas: " + obras.size() + " ===");
	}

	public void cargarListado() {
		try {
			if (!filtroMinTecnica.equals("")) {
				obras = servicioConcurso.recuperarObras(java.util.Optional.of(Double.valueOf(filtroMinTecnica)));
			} else {
				obras = servicioConcurso.recuperarObras(java.util.Optional.empty());
			}
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se pudo cargar el listado: " + e.getMessage()));
			obras = new ArrayList<>();
		}
	}

	public void filtrar() {
		cargarListado();
	}

	public void limpiarFiltro() {
		filtroMinTecnica = "";
		cargarListado();
	}

	public List<ConcursoResumen> getObras() {
		return obras;
	}

	public void setObras(List<ConcursoResumen> obras) {
		this.obras = obras;
	}

	public String getFiltroMinTecnica() {
		return filtroMinTecnica;
	}

	public void setFiltroMinTecnica(String filtroMinTecnica) {
		this.filtroMinTecnica = filtroMinTecnica;
	}

}