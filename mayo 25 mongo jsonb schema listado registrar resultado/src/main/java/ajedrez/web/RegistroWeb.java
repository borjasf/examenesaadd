/*package ajedrez.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ajedrez.servicio.IServicioPartida;
import ajedrez.servicio.PartidaResumen;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;


@Named
@SessionScoped
public class RegistroWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    // Datos de la obra
    
    private String titulo;
    private String descripcion;
    private String tecnica;
    private String artista;
    
    // ID del tablon creado
    private String idObra;
    
    // Control de estado
    private boolean obraRegistrado = false;
  
    // datos de la puntuacion
    ;
    private String usuario;
    private Double nota_tecnica;
    private Double nota_artistica;
    private String comentario;
    
    
    // Servicio
    private IServicioPartida servicioObra;
    @Inject
    protected FacesContext facesContext;

    @PostConstruct
    public void init() {
    	servicioObra = FactoriaServicios.getServicio(IServicioPartida.class);
        try {
        	cargarTodosLasObras();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
   
    public void registrarObra() {
        try {
            idObra = servicioObra.crearObra(titulo,descripcion,tecnica,artista);
            obraRegistrado = true;
            
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                    "Obra registrado correctamente. Ahora puedes registrar sus puntuaciones."));
        } catch (RepositorioException e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Error al registrar la obra:" + e.getMessage()));
        }
    }

   
    public void registrarPuntuacion() {
    	// no se puede registrar iniciativa si no se ha registrado el candidato antes
        if (!obraRegistrado || idObra == null) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Debes registrar primero la obra antes de registrar sus puntuaciones."));
            return;
        }
        
        try {
        	// Registrar la iniciativa para el candidato recién creado
            servicioObra.puntuarObra(idObra, usuario, nota_tecnica, nota_artistica, comentario);
            
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                    "Puntuacion registrado correctamente."));
            
            // Reiniciar el formulario
            limpiarFormulario();
        } catch (RepositorioException | EntidadNoEncontrada e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Error al registrar la puntuacion: " + e.getMessage()));
        }
    }
    
    
    public void registrarPuntuacionParaExistente() {
        if (idObra == null || idObra.isEmpty()) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debes seleccionar una obra."));
            return;
        }
        try {
        	servicioObra.puntuarObra(idObra, usuario, nota_tecnica, nota_artistica, comentario);
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Puntuacion registrada correctamente para la obra seleccionada."));
            limpiarFormulario();
            
        } catch (RepositorioException | EntidadNoEncontrada e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el anuncio: " + e.getMessage()));
        }
    }
    
   
    public void limpiarFormulario() {
    	descripcion=null;
    	tecnica =null;
        titulo = null;
        artista = null;
        idObra = null;
        obraRegistrado = false;
        usuario=null;
        nota_artistica=null;
        nota_tecnica=null;
        comentario=null;
        
    }

    
    public List<PartidaResumen> cargarTodosLasObras() throws EntidadNoEncontrada {
        // obtener todas las obras
    	try {
			return servicioObra.recuperarObras();
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new LinkedList<>();
		}
    }
	

	

	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getTecnica() {
		return tecnica;
	}



	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}



	public String getArtista() {
		return artista;
	}



	public void setArtista(String artista) {
		this.artista = artista;
	}



	public String getIdObra() {
		return idObra;
	}



	public void setIdObra(String idObra) {
		this.idObra = idObra;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public Double getNota_tecnica() {
		return nota_tecnica;
	}



	public void setNota_tecnica(Double nota_tecnica) {
		this.nota_tecnica = nota_tecnica;
	}



	public Double getNota_artistica() {
		return nota_artistica;
	}



	public void setNota_artistica(Double nota_artistica) {
		this.nota_artistica = nota_artistica;
	}



	public String getComentario() {
		return comentario;
	}



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



	public IServicioPartida getServicioObra() {
		return servicioObra;
	}



	public void setServicioObra(IServicioPartida servicioObra) {
		this.servicioObra = servicioObra;
	}



	public FacesContext getFacesContext() {
		return facesContext;
	}



	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	

	

	public String getidObra() {
		return idObra;
	}

	public void setidObra(String idObra) {
		this.idObra = idObra;
	}

	public boolean isObraRegistrado() {
		return obraRegistrado;
	}

	public void setObraRegistrado(boolean obraRegistrado) {
		this.obraRegistrado = obraRegistrado;
	}

	public IServicioPartida getservicioObra() {
		return servicioObra;
	}

	public void setservicioObra(IServicioPartida servicioObra) {
		this.servicioObra = servicioObra;
	}
	
	
   

    
}*/