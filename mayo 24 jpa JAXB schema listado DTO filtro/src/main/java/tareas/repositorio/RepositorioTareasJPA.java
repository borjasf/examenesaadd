package tareas.repositorio;

import repositorio.RepositorioJPA;
import tareas.modelo.Tarea;

public class RepositorioTareasJPA extends RepositorioJPA<Tarea> {

	@Override

	public Class<Tarea> getClase() {

		return Tarea.class;

	}

}
