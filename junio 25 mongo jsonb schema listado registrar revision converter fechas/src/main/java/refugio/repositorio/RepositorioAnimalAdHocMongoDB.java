package refugio.repositorio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


import org.bson.conversions.Bson;


import com.mongodb.client.model.Filters;

import refugio.modelo.Animal;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioAnimalAdHocMongoDB extends RepositorioAnimalMongoDB implements RepositorioAnimalAdHoc {
	
	
	public RepositorioAnimalAdHocMongoDB() throws IOException {
		super();
	}

	@Override
	public List<Animal> buscarAnimalesSinAdoptar(int anyos) throws RepositorioException, EntidadNoEncontrada {
		try {
			Bson filtro = Filters.eq("animales.adopcion", null); //animales sin adoptar
			return getCollection().find(filtro)
			        .into(new ArrayList<>())
			        .stream() //año actual - año ingreso = años en el refugio
			        .filter(a -> LocalDate.now().getYear() - a.getFechaEntrada().getYear() >= anyos)
			        .collect(Collectors.toList());

		} catch (Exception e) {
			throw new RepositorioException("Error al buscar animales sin adoptar", e);
		}
	}
}