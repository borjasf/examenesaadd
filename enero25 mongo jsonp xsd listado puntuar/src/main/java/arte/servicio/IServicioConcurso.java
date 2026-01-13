package arte.servicio;


import java.util.List;
import java.util.Optional;

import arte.modelo.Dimensiones;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioConcurso {
	String crearObra(String titulo, String descripcion, String tecnica, Dimensiones dimensiones, String nombre) throws RepositorioException,EntidadNoEncontrada;
	void puntuarObra(String id, String numeroInscripcion, String usuario, double notaTecnica, double notaArtistica, Optional<String> comentario) throws RepositorioException,EntidadNoEncontrada;

	
	List<ConcursoResumen> recuperarObras(Optional<Double> notaTecnica)throws RepositorioException,EntidadNoEncontrada;
	

}