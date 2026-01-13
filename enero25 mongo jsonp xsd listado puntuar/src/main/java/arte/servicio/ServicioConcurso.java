package arte.servicio;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import arte.modelo.*;
import arte.repositorio.RepositorioObraAdHoc;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ServicioConcurso implements IServicioConcurso {

	// NO PONER IMPLEMENTACION CONCRETA DE REPOSITORIO
	private RepositorioObraAdHoc repositorio = FactoriaRepositorios.getRepositorio(Obra.class);

	@Override
	public String crearObra(String titulo, String descripcion, String tecnica, Dimensiones dimensiones, String nombre)
			throws RepositorioException, EntidadNoEncontrada {
		if (titulo == null || titulo.isBlank()) {
			throw new RepositorioException("El título no puede ser nulo o vacío");
		}
		if (descripcion == null || descripcion.isBlank()) {
			throw new RepositorioException("La descripción no puede ser nula o vacía");
		}
		if (tecnica == null || tecnica.isBlank()) {
			throw new RepositorioException("La técnica no puede ser nula o vacía");
		}
		if (dimensiones == null) {
			throw new RepositorioException("Las dimensiones no pueden ser nulas");
		}
		if (nombre == null || nombre.isBlank()) {
			throw new RepositorioException("El nombre del artista no puede ser nulo o vacío");
		}
		Obra obra = new Obra(UUID.randomUUID().toString(), titulo, descripcion, tecnica, dimensiones, nombre);
		return repositorio.add(obra);
	}

	@Override
	public void puntuarObra(String id, String numeroInscripcion, String usuario, double notaTecnica,
			double notaArtistica, Optional<String> comentario) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isBlank()) {
			throw new RepositorioException("El id no puede ser nulo o vacío");
		}
		if (numeroInscripcion == null || numeroInscripcion.isBlank()) {
			throw new RepositorioException("El número de inscripción no puede ser nulo o vacío");
		}
		if (usuario == null || usuario.isBlank()) {
			throw new RepositorioException("El usuario no puede ser nulo o vacío");
		}
		if (notaTecnica < 0 || notaTecnica > 5) {
			throw new RepositorioException("La nota técnica debe estar entre 0 y 5");
		}
		if (notaArtistica < 0 || notaArtistica > 5) {
			throw new RepositorioException("La nota artística debe estar entre 0 y 5");
		}
		Obra obra = repositorio.getById(id);
		Puntuacion puntuacion = new Puntuacion(usuario, notaTecnica, notaArtistica, comentario.orElse(""));
		obra.getPuntuaciones().add(puntuacion);
		repositorio.update(obra);
	}

	@Override
	public List<ConcursoResumen> recuperarObras(Optional<Double> notaTecnica)
			throws RepositorioException, EntidadNoEncontrada {
		List<ConcursoResumen> resumenes = new LinkedList<>();
		List<Obra> obras;
		if (notaTecnica.isPresent()) {
			obras = repositorio.buscarObrasMinTecnica(notaTecnica.get());

		} else {
			obras = repositorio.getAll();
		}
		for (Obra obra : obras) {
			resumenes.add(new ConcursoResumen(obra.getId(), obra.getCodigoInscripcion(), obra.getTitulo(),obra.getTecnica(),obra.getArtista(),obra.getDescripcion()));
		}
		return resumenes;
	}

}