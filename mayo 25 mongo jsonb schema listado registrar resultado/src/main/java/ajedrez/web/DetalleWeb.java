/*package ajedrez.web;

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

import ajedrez.modelo.Partida;
import ajedrez.modelo.Puntuacion;
import ajedrez.servicio.IServicioPartida;
import servicio.FactoriaServicios;


@Named
@ViewScoped
public class DetalleWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    // Parámetro de navegación
    private String idObra;
    
    // Lista de mensajes a mostrar
    private List<Puntuacion> puntuaciones;
    
   
    // Servicio
    private IServicioPartida servicioObra;
    
    @Inject
    protected FacesContext facesContext;

    public DetalleWeb() {
    	servicioObra = FactoriaServicios.getServicio(IServicioPartida.class);
    	puntuaciones = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        // La inicialización se hace en load() después de recibir el parámetro
    }

    
    public void load() {
        if (idObra == null || idObra.isEmpty()) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha especificado la obra"));
            return;
        }
        cargarPuntuaciones();
    }

    
     * Carga los mensajes del tablón aplicando los filtros si están definidos.
     
    public void cargarPuntuaciones() {
        try {
          
            
            
            puntuaciones = servicioObra.getPuntuacionesObra(idObra);
        } catch (Exception e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "No se pudieron cargar los mensajes: " + e.getMessage()));
            puntuaciones = new ArrayList<>();
        }
    }

	public String getIdObra() {
		return idObra;
	}

	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}

	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

    
  

 
    
    

    
}*/
