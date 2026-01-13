package tablones.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import repositorio.RepositorioString;
import tablones.modelo.Anuncio;
import tablones.modelo.Tablon;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Tablon).
 */
public interface RepositorioTablonesAdHoc extends RepositorioString<Tablon> {

	/**
	 * Buscar todos los mensajes por tablón y palabra clave: recibe el identificador del
	 * tablón y una cadena de texto. Devuelve los mensajes del tablón cuyo título o resumen
	 * contenga la cadena de texto.
	 * 
	 * @param idTablon identificador del tablón
	 * @param texto cadena de texto a buscar
	 * @return lista de anuncios que contienen la palabra clave en título o resumen
	 */
	public List<Anuncio> buscarMensajeTablonPalabraClave(String idTablon, String texto);
	
	/**
	 * Buscar todos los mensajes de un tablón anteriores o iguales a una fecha.
	 * 
	 * @param idTablon identificador del tablón
	 * @param fecha fecha límite
	 * @return lista de anuncios
	 */
	public List<Anuncio> todosMensajesTablonYFecha(String idTablon, LocalDateTime fecha);
	
	
	public List<Anuncio> buscarMensajesTablonPalabraClaveYFecha(String idTablon,String texto,LocalDateTime fecha);
	
	/**
	 * Recuperar mensajes: recibe el identificador del tablón, una cadena de texto y una fecha.
	 * Devuelve los mensajes cuyo título o resumen contenga la cadena de texto y hayan sido 
	 * publicados a partir de la fecha indicada. Se permite que la cadena de texto y/o la fecha 
	 * sean nulos.
	 * 
	 * @param idTablon identificador del tablón (obligatorio)
	 * @param texto cadena de texto a buscar (opcional, puede ser null)
	 * @param fecha fecha a partir de la cual buscar (opcional, puede ser null)
	 * @return lista de anuncios que cumplen los criterios
	 */
	public List<Anuncio> recuperarMensajes(String idTablon, String texto, LocalDateTime fecha);
	
	/**
	 * Busca un tablón por su nombre exacto.
	 * 
	 * @param nombre nombre del tablón a buscar
	 * @return el tablón encontrado o null si no existe
	 */
	public Tablon buscarPorNombre(String nombre);
}