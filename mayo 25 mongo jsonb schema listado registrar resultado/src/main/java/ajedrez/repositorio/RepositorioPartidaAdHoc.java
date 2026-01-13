package ajedrez.repositorio;

import java.util.List;

import ajedrez.modelo.Partida;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Tablon).
 */
public interface RepositorioPartidaAdHoc extends RepositorioString<Partida> {

	public List<Partida> buscarPartidasRondaYEstado(int ronda,boolean pendiente) throws RepositorioException, EntidadNoEncontrada;
	public List<Partida> buscarPartidasRondaYEstado(boolean pendiente) throws RepositorioException, EntidadNoEncontrada;
	
}