package refugio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import refugio.servicio.IServicioRefugio;
import refugio.servicio.RefugioResumen;
import servicio.FactoriaServicios;

@Named
@ViewScoped
public class ListadoWeb implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RefugioResumen> animales;

	private IServicioRefugio servicioRefugio;

	private String filtroAnosRefugio;

	@Inject
	protected FacesContext facesContext;

	public ListadoWeb() {
		servicioRefugio = FactoriaServicios.getServicio(IServicioRefugio.class);
		animales = new ArrayList<>();
		filtroAnosRefugio = ""; 
	}

	@PostConstruct
	public void init() {
		System.out.println("=== Inicializando ListadoWeb ===");
		cargarListado();
		System.out.println("=== Animales cargados: " + animales.size() + " ===");
	}

	public void cargarListado() {
		try {
			if (!filtroAnosRefugio.equals("")) {
				animales = servicioRefugio.recuperarAnimales(java.util.Optional.of(Integer.valueOf(filtroAnosRefugio)));
			} else {
				animales = servicioRefugio.recuperarAnimales(java.util.Optional.empty());
			}
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se pudo cargar el listado: " + e.getMessage()));
			animales = new ArrayList<>();
		}
	}

	public void filtrar() {
		cargarListado();
	}

	public void limpiarFiltro() {
		filtroAnosRefugio = "";
		cargarListado();
	}

	public List<RefugioResumen> getAnimales() {
		return animales;
	}

	public void setAnimales(List<RefugioResumen> animales) {
		this.animales = animales;
	}

	public String getFiltroAnosRefugio() {
		return filtroAnosRefugio;
	}

	public void setFiltroAnosRefugio(String filtroAnosRefugio) {
		this.filtroAnosRefugio = filtroAnosRefugio;
	}

}