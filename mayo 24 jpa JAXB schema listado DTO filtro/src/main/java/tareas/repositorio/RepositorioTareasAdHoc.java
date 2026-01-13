package tareas.repositorio;

import java.util.List;

import repositorio.RepositorioString;
import tareas.modelo.Estado;
import tareas.modelo.Tarea;


/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Tarea).
 */
public interface RepositorioTareasAdHoc extends RepositorioString<Tarea> {

	public List<Tarea> buscarTareasEstado(Estado estado);
}