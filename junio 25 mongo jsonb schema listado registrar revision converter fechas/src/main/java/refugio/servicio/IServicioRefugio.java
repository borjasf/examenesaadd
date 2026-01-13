package refugio.servicio;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.json.bind.JsonbException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioRefugio {
	String nuevoAnimal(String nombre, String especie, int edad, LocalDate fechaEntrada) throws RepositorioException,EntidadNoEncontrada;
	void anadirRevision(String id, LocalDate fechaRevision, double peso, String observaciones) throws RepositorioException,EntidadNoEncontrada;
	void anadirAdopcion(String id, String nombreAdoptante, String telefono, LocalDate fechaAdopcion) throws RepositorioException,EntidadNoEncontrada;

	
	
	List<RefugioResumen> recuperarAnimales(Optional<Integer> anyos)throws RepositorioException,EntidadNoEncontrada;
	public void almacenarEnJSON(String idAnimal) throws JsonbException, FileNotFoundException;

}