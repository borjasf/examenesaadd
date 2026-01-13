package tablones.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servicio.FactoriaServicios;
import tablones.modelo.Anuncio;
import tablones.servicio.IServicioTablones;


@Named
@ViewScoped
public class DetalleTablonWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    // Parámetro de navegación
    private String idTablon;
    
    // Lista de mensajes a mostrar
    private List<Anuncio> mensajes;
    
    // Filtros
    private String filtroPalabraClave;
    private LocalDate filtroFecha;
    
    // Servicio
    private IServicioTablones servicioTablones;
    
    @Inject
    protected FacesContext facesContext;

    public DetalleTablonWeb() {
        servicioTablones = FactoriaServicios.getServicio(IServicioTablones.class);
        mensajes = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        // La inicialización se hace en load() después de recibir el parámetro
    }

    /**
     * Método invocado por f:viewAction para cargar los datos iniciales.
     * Se ejecuta después de que se asigne el parámetro idTablon.
     */
    public void load() {
        if (idTablon == null || idTablon.isEmpty()) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha especificado el tablón"));
            return;
        }
        cargarMensajes();
    }

    /**
     * Carga los mensajes del tablón aplicando los filtros si están definidos.
     */
    public void cargarMensajes() {
        try {
            // Convertir LocalDate a LocalDateTime si hay filtro de fecha
            LocalDateTime fechaFiltro = null;
            if (filtroFecha != null) {
                fechaFiltro = filtroFecha.atStartOfDay();
            }
            
            mensajes = servicioTablones.getMensajes(idTablon, filtroPalabraClave, fechaFiltro);
        } catch (Exception e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "No se pudieron cargar los mensajes: " + e.getMessage()));
            mensajes = new ArrayList<>();
        }
    }

    /**
     * Aplica los filtros y recarga la lista de mensajes.
     */
    public void filtrar() {
        cargarMensajes();
    }

    /**
     * Limpia los filtros y recarga todos los mensajes.
     */
    public void limpiarFiltros() {
        filtroPalabraClave = null;
        filtroFecha = null;
        cargarMensajes();
    }

    // Getters y Setters
    
    public String getIdTablon() {
        return idTablon;
    }

    public void setIdTablon(String idTablon) {
        this.idTablon = idTablon;
    }

    public List<Anuncio> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Anuncio> mensajes) {
        this.mensajes = mensajes;
    }

    public String getFiltroPalabraClave() {
        return filtroPalabraClave;
    }

    public void setFiltroPalabraClave(String filtroPalabraClave) {
        this.filtroPalabraClave = filtroPalabraClave;
    }

    public LocalDate getFiltroFecha() {
        return filtroFecha;
    }

    public void setFiltroFecha(LocalDate filtroFecha) {
        this.filtroFecha = filtroFecha;
    }
}
