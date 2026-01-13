package tareas.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;
import tareas.modelo.Estado;
import tareas.modelo.EstadoTarea;
import tareas.modelo.Tarea;
import tareas.repositorio.RepositorioTareasAdHoc;

public class ServicioTareas implements IServicioTareas {

	private RepositorioTareasAdHoc repositorio = FactoriaRepositorios.getRepositorioTareasAdHoc(Tarea.class);

	@Override
	public String crearTarea(String nombre, String descripcion, LocalDate fechaInicio) throws RepositorioException {
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("Descripción: no debe ser nulo ni vacio");
		if (fechaInicio == null) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
		}
		Tarea tarea = new Tarea(nombre, descripcion, fechaInicio, Estado.PENDIENTE);
		return repositorio.add(tarea);
	}

	@Override
	public void anadirEstado(String idTarea, Estado estado, LocalDate inicio)
			throws RepositorioException, EntidadNoEncontrada {
		if (idTarea == null || idTarea.isEmpty())
			throw new IllegalArgumentException("El id: no debe ser nulo ni vacio");
		if (estado == null)
			throw new IllegalArgumentException("El estado no puede ser nulo.");
		if (inicio == null) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
		}
		Tarea tarea = repositorio.getById(idTarea);
		List<EstadoTarea> estados = tarea.getEstados();
		estados.getLast().setFechaFin(inicio);
		estados.add(new EstadoTarea(estado, inicio));
		repositorio.update(tarea);
	}

	@Override
	public List<TareaResumen> recuperarTareas(Optional<Estado> estado)
			throws RepositorioException, EntidadNoEncontrada {
		List<TareaResumen> salida;
		if (estado.isPresent()) {
			salida = repositorio
					.buscarTareasEstado(estado.get()).stream().map(t -> new TareaResumen(t.getId(), t.getNombre(),
							t.getEstados().getLast().getFechaInicio(), t.getEstados().getLast().getEstado()))
					.collect(Collectors.toList());

		} else {
			salida = repositorio
					.getAll().stream().map(t -> new TareaResumen(t.getId(), t.getNombre(),
							t.getEstados().getLast().getFechaInicio(), t.getEstados().getLast().getEstado()))
					.collect(Collectors.toList());
		}
		return salida;
	}

}