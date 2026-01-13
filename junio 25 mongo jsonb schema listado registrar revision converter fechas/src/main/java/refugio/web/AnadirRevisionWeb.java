package refugio.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public class AnadirRevisionWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private IServicioRefugio servicioRefugio;

    private List<RefugioResumen> animalesDisponibles;
    private String idAnimalSeleccionado;
    
    private LocalDate fechaRevision;
    private String pesoAnimal;
    private String observaciones;

    @Inject
    protected FacesContext facesContext;

    public AnadirRevisionWeb() {
    	servicioRefugio = FactoriaServicios.getServicio(IServicioRefugio.class);
    }

    @PostConstruct
    public void init() {
    	cargarAnimales();
    	System.out.println("Animales cargados en añadir revision: "+animalesDisponibles.size());
    }

    private void cargarAnimales() {
        try {
        	animalesDisponibles = servicioRefugio.recuperarAnimales(Optional.empty());
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se pudieron cargar los animales: " + e.getMessage()));
        }
    }

    public String anadirRevision() {
        try {

            if (idAnimalSeleccionado == null) {
                 throw new Exception("Animal no encontradao en la lista");
            }
            
            servicioRefugio.anadirRevision(idAnimalSeleccionado, fechaRevision, Double.valueOf(pesoAnimal), observaciones);
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Revisión registrada correctamente."));
            
            // Limpiar formulario
            idAnimalSeleccionado = "";
            fechaRevision = null;
            observaciones = "";
            pesoAnimal = "";

            
            return "listado?faces-redirect=true"; // Navegar al listado
            
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Error al añadir revision al animal: " + e.getMessage()));
            return null;
        }
    }

    // Getters y Setters

  
	public List<RefugioResumen> getAnimalesDisponibles() {
		return animalesDisponibles;
	}

	public void setAnimalesDisponibles(List<RefugioResumen> animalesDisponibles) {
		this.animalesDisponibles = animalesDisponibles;
	}

	public String getIdAnimalSeleccionado() {
		return idAnimalSeleccionado;
	}

	public void setIdAnimalSeleccionado(String idAnimalSeleccionado) {
		this.idAnimalSeleccionado = idAnimalSeleccionado;
	}

	public LocalDate getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(LocalDate fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getPesoAnimal() {
		return pesoAnimal;
	}

	public void setPesoAnimal(String pesoAnimal) {
		this.pesoAnimal = pesoAnimal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}