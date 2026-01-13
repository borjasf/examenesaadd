package ajedrez.servicio;


import java.io.FileNotFoundException;
import java.util.List;

import javax.json.bind.JsonbException;

import ajedrez.modelo.TipoFinalizacion;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioPartida {
	String crearPartida(int ronda,String torneo,String jugador_blancas, String jugador_negras,double tiempo_limite,double incremento) throws RepositorioException,EntidadNoEncontrada;
	void registrarMovimiento(String idPartida,String blancas,String negras) throws RepositorioException,EntidadNoEncontrada;
	void establecerResultadoPartida(String idPartidam,String ganador,TipoFinalizacion tipoFinalizacion)  throws RepositorioException,EntidadNoEncontrada;
	
	List<PartidaResumen> recuperarPartidas(int ronda,boolean pendiente)throws RepositorioException,EntidadNoEncontrada;
	
	List<PartidaResumen> recuperarPartidas()throws RepositorioException,EntidadNoEncontrada;
	public void añadirJson(String idPartida) throws JsonbException, FileNotFoundException;
	List<PartidaResumen> recuperarPartidas(boolean pendiente)throws RepositorioException,EntidadNoEncontrada;
}