package tablones.web;

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
import tablones.servicio.IServicioTablones;
import tablones.servicio.TablonResumen;

@Named
@ViewScoped
public class ListadoTablonesWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TablonResumen> tablones;
    
    private IServicioTablones servicioTablones;
    
    @Inject
    protected FacesContext facesContext;

    public ListadoTablonesWeb() {
        servicioTablones = FactoriaServicios.getServicio(IServicioTablones.class);
        tablones = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        cargarListado();
    }

    public void cargarListado() {
        try {
            tablones = servicioTablones.getListadoResumen();
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cargar el listado: " + e.getMessage()));
        }
    }

    // Getters y Setters
    public List<TablonResumen> getTablones() {
        return tablones;
    }

    public void setTablones(List<TablonResumen> tablones) {
        this.tablones = tablones;
    }
}
