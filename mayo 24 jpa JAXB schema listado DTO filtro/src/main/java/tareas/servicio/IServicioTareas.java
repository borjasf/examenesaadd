package tareas.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import tareas.modelo.Estado;

public interface IServicioTareas {

	String crearTarea(String nombre, String descripcion, LocalDate fechaInicio) throws RepositorioException;

	void anadirEstado(String idTarea, Estado estado,LocalDate inicio) throws RepositorioException, EntidadNoEncontrada;
	

	List<TareaResumen> recuperarTareas(Optional<Estado> estado) throws RepositorioException, EntidadNoEncontrada;
	
	
}