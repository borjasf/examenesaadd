package tablones.servicio;

import java.time.LocalDateTime;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import tablones.dto.TablonDTO;
import tablones.modelo.Anuncio;
import tablones.modelo.Tablon;

public interface IServicioTablones {

	String crearTablon(String nombre) throws RepositorioException;

	Integer añadirMensaje(String idTablon,String tituloAnuncio,String resumen,String enlace) throws RepositorioException, EntidadNoEncontrada;
	
	List<TablonResumen> getListadoResumen() throws RepositorioException, EntidadNoEncontrada;

	List<Anuncio> getMensajes(String idTablon, String texto, LocalDateTime fecha) throws RepositorioException, EntidadNoEncontrada;
	
	
}