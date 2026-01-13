package tablones.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import tablones.dto.TablonDTO;
import tablones.modelo.Tablon;
import tablones.modelo.Anuncio;
import tablones.repositorio.RepositorioTablonesAdHoc;

public class ServicioTablones implements IServicioTablones {
	
	private RepositorioTablonesAdHoc repositorio = FactoriaRepositorios.getRepositorioTablonesAdHoc(Tablon.class);
	
	@Override
	public String crearTablon(String nombre) throws RepositorioException{
		if (nombre == null || nombre.isEmpty()) 
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		
		// Verificar si ya existe un tablón con ese nombre
		Tablon tablonExistente = repositorio.buscarPorNombre(nombre);
		if (tablonExistente != null) {
			// Si ya existe, retornar su ID sin crear uno nuevo
			return tablonExistente.getId();
		}
	
		// Si no existe, crear uno nuevo
		Tablon tablon = new Tablon(nombre);
		String id = repositorio.add(tablon);
		return id;
	}
	

	@Override
	public Integer añadirMensaje(String idTablon, String tituloAnuncio, String resumen, String enlace) throws RepositorioException, EntidadNoEncontrada {
		// Validaciones de parámetros obligatorios
		if (idTablon == null || idTablon.isEmpty()) 
			throw new IllegalArgumentException("idTablon: no debe ser nulo ni vacio");
		if (tituloAnuncio == null || tituloAnuncio.isEmpty()) 
			throw new IllegalArgumentException("tituloAnuncio: no debe ser nulo ni vacio");
		if (resumen == null || resumen.isEmpty()) 
			throw new IllegalArgumentException("resumen: no debe ser nulo ni vacio");
		// enlace es opcional, puede ser null
		
		// Obtener el tablón
		Tablon tablon = repositorio.getById(idTablon);
		
		// Crear el anuncio con fecha de publicación actual (se establece en el constructor)
		Anuncio anuncio = new Anuncio(tituloAnuncio, resumen, enlace);
		
		// Añadir el anuncio al tablón (relación bidireccional)
		tablon.addAnuncio(anuncio);
		
		// Actualizar el tablón en el repositorio
		repositorio.update(tablon);
		// en q momento se asigna el id al anuncio? al hacer el update del tablon, se persisten los anuncios asociados
		// Retornar el identificador del nuevo anuncio
		return anuncio.getId();
	}
	
	//ecuperar tablones: Este método no recibe parámetros y devuelve todos los tablones existentes. Retorna una lista con un resumen de cada tablón (id y nombre).
	
	private TablonResumen toResumen(Tablon tablon) {
		TablonResumen resumen = new TablonResumen();
		resumen.setId(tablon.getId());
		resumen.setTitulo(tablon.getNombre());
		return resumen;
	}
	
	@Override
	public List<TablonResumen> getListadoResumen() throws RepositorioException, EntidadNoEncontrada {
		List<Tablon> tablones = repositorio.getAll();
		List<TablonResumen> resumenList = new LinkedList<>();
		for (Tablon tablon : tablones) {
			resumenList.add(toResumen(tablon));
		}
		return resumenList;
	}
	
	
	@Override
	public List<Anuncio> getMensajes(String idTablon, String texto, LocalDateTime fecha) throws RepositorioException, EntidadNoEncontrada {
		// Validaciones de parámetros
		if (idTablon == null || idTablon.isEmpty()) 
			throw new IllegalArgumentException("idTablon: no debe ser nulo ni vacio");
		
		// Usar el repositorio AdHoc para recuperar los mensajes con filtros opcionales
		return repositorio.recuperarMensajes(idTablon, texto, fecha);
	} 
	
}