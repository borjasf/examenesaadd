package arte.web;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arte.servicio.ConcursoResumen;
import arte.servicio.IServicioConcurso;
import servicio.FactoriaServicios;

@Named
@ViewScoped
public class PuntuarWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private IServicioConcurso servicioConcurso;

    private List<ConcursoResumen> obrasDisponibles;
    private String idObraSeleccionada;
    
    private String usuario;
    private Double notaTecnica;
    private Double notaArtistica;
    private String comentario;

    @Inject
    protected FacesContext facesContext;

    public PuntuarWeb() {
        servicioConcurso = FactoriaServicios.getServicio(IServicioConcurso.class);
    }

    @PostConstruct
    public void init() {
        cargarObras();
    }

    private void cargarObras() {
        try {
            obrasDisponibles = servicioConcurso.recuperarObras(Optional.empty());
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se pudieron cargar las obras: " + e.getMessage()));
        }
    }

    public String puntuar() {
        try {
            // Buscamos el codigo de inscripcion correspondiente al ID seleccionado
            String codigoInscripcion = obrasDisponibles.stream()
                .filter(o -> o.getId().equals(idObraSeleccionada))
                .findFirst()
                .map(ConcursoResumen::getCodigoInscripcion)
                .orElse(null);

            if (codigoInscripcion == null) {
                 throw new Exception("Obra no encontrada en la lista");
            }
            
            servicioConcurso.puntuarObra(idObraSeleccionada, codigoInscripcion, usuario, notaTecnica, notaArtistica, Optional.ofNullable(comentario));
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Puntuación registrada correctamente."));
            
            // Limpiar formulario
            usuario = "";
            notaTecnica = null;
            notaArtistica = null;
            comentario = "";
            idObraSeleccionada = null;
            
            return "listado?faces-redirect=true"; // Navegar al listado
            
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Error al puntuar la obra: " + e.getMessage()));
            return null;
        }
    }

    // Getters y Setters

    public List<ConcursoResumen> getObrasDisponibles() {
        return obrasDisponibles;
    }

    public void setObrasDisponibles(List<ConcursoResumen> obrasDisponibles) {
        this.obrasDisponibles = obrasDisponibles;
    }

    public String getIdObraSeleccionada() {
        return idObraSeleccionada;
    }

    public void setIdObraSeleccionada(String idObraSeleccionada) {
        this.idObraSeleccionada = idObraSeleccionada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getNotaTecnica() {
        return notaTecnica;
    }

    public void setNotaTecnica(Double notaTecnica) {
        this.notaTecnica = notaTecnica;
    }

    public Double getNotaArtistica() {
        return notaArtistica;
    }

    public void setNotaArtistica(Double notaArtistica) {
        this.notaArtistica = notaArtistica;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}