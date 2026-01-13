package refugio.repositorio;

import java.util.List;

import refugio.modelo.Animal;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Obra).
 */
public interface RepositorioAnimalAdHoc extends RepositorioString<Animal> {

	public List<Animal> buscarAnimalesSinAdoptar(int anyos) throws RepositorioException, EntidadNoEncontrada;

	
}