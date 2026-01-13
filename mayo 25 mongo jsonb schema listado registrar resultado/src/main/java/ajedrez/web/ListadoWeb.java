package ajedrez.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ajedrez.modelo.TipoFinalizacion; // IMPORTANTE: Necesitas el Enum
import ajedrez.servicio.IServicioPartida;
import ajedrez.servicio.PartidaResumen;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Named
@ViewScoped
public class ListadoWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    // --- Datos del Listado ---
    private List<PartidaResumen> partidas;
    private String filtroRonda;
    private Boolean filtroEstado;

    // --- Datos para el Diálogo "Añadir Resultado" ---
    private String idPartidaSeleccionada;
    private String nuevoGanador;
    private TipoFinalizacion nuevoTipoFinalizacion;

    private IServicioPartida servicioPartida;

    @Inject
    protected FacesContext facesContext;

    public ListadoWeb() {
        // En un entorno JEE real se usa @Inject, pero Factoria es aceptable académica
        servicioPartida = FactoriaServicios.getServicio(IServicioPartida.class);
        partidas = new ArrayList<>();
        filtroEstado = false;
    }

    @PostConstruct
    public void init() {
        cargarListado();
    }

    public void cargarListado() {
        try {
            Integer ronda = null;
            if (filtroRonda != null && !filtroRonda.trim().isEmpty()) {
                ronda = Integer.parseInt(filtroRonda);
            }
            
            // Usamos el método unificado que arreglamos en el Servicio
            // recuperarPartidas(Integer ronda, boolean pendiente)
            partidas = servicioPartida.recuperarPartidas(ronda, filtroEstado != null ? filtroEstado : false);

        } catch (NumberFormatException e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La ronda debe ser un número válido"));
            partidas = new ArrayList<>();
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error cargando datos: " + e.getMessage()));
        }
    }

    public void filtrar() {
        cargarListado();
    }

    public void limpiarFiltro() {
        filtroRonda = null;
        filtroEstado = false;
        cargarListado();
    }
    
    // --- Lógica para AÑADIR RESULTADO (Requisito faltante) ---

    public void guardarResultado() {
        try {
            servicioPartida.establecerResultadoPartida(idPartidaSeleccionada, nuevoGanador, nuevoTipoFinalizacion);
            
            facesContext.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Resultado registrado correctamente"));
            
            // Limpiamos los campos del diálogo
            idPartidaSeleccionada = null;
            nuevoGanador = null;
            nuevoTipoFinalizacion = null;
            
            // Recargamos la lista para que se vea el cambio (ya no estará pendiente)
            cargarListado();
            
        } catch (RepositorioException | EntidadNoEncontrada | IllegalArgumentException e) {
            facesContext.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    
    // Helper para rellenar el desplegable (SelectOneMenu) del enum en la vista
    public TipoFinalizacion[] getTiposFinalizacion() {
        return TipoFinalizacion.values();
    }

    // --- Getters y Setters ---

    public List<PartidaResumen> getPartidas() { return partidas; }
    public void setPartidas(List<PartidaResumen> partidas) { this.partidas = partidas; }

    public String getFiltroRonda() { return filtroRonda; }
    public void setFiltroRonda(String filtroRonda) { this.filtroRonda = filtroRonda; }

    public Boolean getFiltroEstado() { return filtroEstado; }
    public void setFiltroEstado(Boolean filtroEstado) { this.filtroEstado = filtroEstado; }

    public String getIdPartidaSeleccionada() { return idPartidaSeleccionada; }
    public void setIdPartidaSeleccionada(String idPartidaSeleccionada) { this.idPartidaSeleccionada = idPartidaSeleccionada; }

    public String getNuevoGanador() { return nuevoGanador; }
    public void setNuevoGanador(String nuevoGanador) { this.nuevoGanador = nuevoGanador; }

    public TipoFinalizacion getNuevoTipoFinalizacion() { return nuevoTipoFinalizacion; }
    public void setNuevoTipoFinalizacion(TipoFinalizacion nuevoTipoFinalizacion) { this.nuevoTipoFinalizacion = nuevoTipoFinalizacion; }
}