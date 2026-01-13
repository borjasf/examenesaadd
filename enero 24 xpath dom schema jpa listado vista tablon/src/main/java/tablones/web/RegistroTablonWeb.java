package tablones.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import tablones.servicio.TablonResumen;
//import tablones.servicio.IservicioTablones;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import tablones.servicio.IServicioTablones;

@Named
@SessionScoped
public class RegistroTablonWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    // Datos del tablon
    
    private String nombre;
       
    // ID del tablon creado
    private String idTablon;
    
    // Control de estado
    private boolean tablonRegistrado = false;
  
    // datos del anuncio
    private String titulo;
    private String resumen;
    
    private String enlace;
    
    
    // Servicio
    private IServicioTablones servicioTablon;
    @Inject
    protected FacesContext facesContext;

    @PostConstruct
    public void init() {
        servicioTablon = FactoriaServicios.getServicio(IServicioTablones.class);
        try {
			cargarTodosLosTablones();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Registra un nuevo candidato
     */
    public void registrarTablon() {
        try {
            idTablon = servicioTablon.crearTablon(nombre);
            tablonRegistrado = true;
            
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                    "Tablon registrado correctamente. Ahora puedes registrar sus anuncios."));
        } catch (RepositorioException e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Error al registrar el tablon: " + e.getMessage()));
        }
    }

    /**
     * Registra la iniciativa del candidato
     */
    public void registrarAnuncio() {
    	// no se puede registrar iniciativa si no se ha registrado el candidato antes
        if (!tablonRegistrado || idTablon == null) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Debes registrar primero el tablon antes de registrar sus anuncios."));
            return;
        }
        
        try {
        	// Registrar la iniciativa para el candidato recién creado
            servicioTablon.añadirMensaje(idTablon, titulo,  resumen,enlace );
            
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                    "Anuncio registrado correctamente."));
            
            // Reiniciar el formulario
            limpiarFormulario();
        } catch (RepositorioException | EntidadNoEncontrada e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Error al registrar el anuncio: " + e.getMessage()));
        }
    }
    
    /**
     * Registra una iniciativa para un candidato ya existente (seleccionado)
     */
    public void registrarAnuncioParaExistente() {
        if (idTablon == null || idTablon.isEmpty()) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debes seleccionar un tablon."));
            return;
        }
        try {
        	servicioTablon.añadirMensaje(idTablon, titulo,  resumen,enlace );
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Anuncio registrado correctamente para el tablon seleccionado."));
            limpiarFormulario();
            
        } catch (RepositorioException | EntidadNoEncontrada e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el anuncio: " + e.getMessage()));
        }
    }
    
    /**
     * Limpia el formulario para un nuevo registro
     */
    public void limpiarFormulario() {
        nombre = null;
        titulo = null;
        resumen = null;
        idTablon = null;
        tablonRegistrado = false;
    }

    /**
     * Carga la lista de candidatos sin iniciativa
     * @throws EntidadNoEncontrada 
     */
    public List<TablonResumen> cargarTodosLosTablones() throws EntidadNoEncontrada {
        try {
            // Recupera todos los candidatos y filtra los que no tienen eslogan (sin iniciativa)
            return servicioTablon.getListadoResumen();
                       
        } catch (RepositorioException e) {
          
        }
		return null;
    }
		// Getters y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getIdTablon() {
		return idTablon;
	}

	public void setIdTablon(String idTablon) {
		this.idTablon = idTablon;
	}

	public boolean isTablonRegistrado() {
		return tablonRegistrado;
	}

	public void setTablonRegistrado(boolean tablonRegistrado) {
		this.tablonRegistrado = tablonRegistrado;
	}

	public IServicioTablones getServicioTablon() {
		return servicioTablon;
	}

	public void setServicioTablon(IServicioTablones servicioTablon) {
		this.servicioTablon = servicioTablon;
	}
	
	
   

    
}