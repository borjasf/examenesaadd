package arte.repositorio;

import java.util.List;

import org.bson.Document;

import arte.modelo.Obra;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Obra).
 */
public interface RepositorioObraAdHoc extends RepositorioString<Obra> {

	public List<Obra> buscarObrasMinTecnica(double minTecnica) throws RepositorioException, EntidadNoEncontrada;
	public List<Document> recuperarObrasNotasMedias() throws RepositorioException, EntidadNoEncontrada;
	
}