package refugio.servicio;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import refugio.modelo.Adopcion;
import refugio.modelo.Animal;
import refugio.modelo.Revision;
import refugio.repositorio.RepositorioAnimalAdHoc;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;

public class ServicioRefugio implements IServicioRefugio {

	// NO PONER IMPLEMENTACION CONCRETA DE REPOSITORIO
	private RepositorioAnimalAdHoc repositorio = FactoriaRepositorios.getRepositorio(Animal.class);



	@Override
	public String nuevoAnimal(String nombre, String especie, int edad, LocalDate fechaEntrada)
			throws RepositorioException, EntidadNoEncontrada {
		if (nombre == null || nombre.isBlank()) {
			throw new RepositorioException("El nombre no puede ser nulo o vacío");
		}
		if (especie == null || especie.isBlank()) {
			throw new RepositorioException("La especie no puede ser nula o vacía");
		}
		if (edad <= 0) {
			throw new RepositorioException("La edad no puede ser 0 meses o menos");
		}
		if (fechaEntrada == null) {
			throw new RepositorioException("La fecha de entrada no puede ser nula");
		}
		Animal animal = new Animal(nombre, especie, edad, fechaEntrada);
		return repositorio.add(animal);
	}

	@Override
	public void anadirRevision(String id, LocalDate fechaRevision, double peso, String observaciones)
			throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isBlank()) {
			throw new RepositorioException("El id no puede ser nulo o vacío");
		}
		if (fechaRevision == null) {
			throw new RepositorioException("La fecha de revisión no puede ser nula");
		}
		if (peso <= 0.0) {
			throw new RepositorioException("El peso no puede ser 0 o menos");
		}
		if (observaciones == null || observaciones.isBlank()) {
			throw new RepositorioException("Deben haber observaciones");
		}
		Animal animal = repositorio.getById(id);
		Revision revision = new Revision(fechaRevision, observaciones, peso);
		animal.getRevisiones().add(revision);
		repositorio.update(animal);

	}

	@Override
	public void anadirAdopcion(String id, String nombreAdoptante, String telefono, LocalDate fechaAdopcion)
			throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isBlank()) {
			throw new RepositorioException("El id no puede ser nulo o vacío");
		}
		if (nombreAdoptante == null || nombreAdoptante.isBlank()) {
			throw new RepositorioException("El nombreAdoptante no puede ser nulo o vacío");
		}
		if (telefono == null || telefono.isBlank()) {
			throw new RepositorioException("El telefono del adoptante no puede ser nulo o vacío");
		}
		if (fechaAdopcion == null) {
			throw new RepositorioException("La fecha de adopción no puede ser nula");
		}

		Animal animal = repositorio.getById(id);
		Adopcion adopcion = new Adopcion(fechaAdopcion, nombreAdoptante, telefono);
		animal.setAdopcion(adopcion);
		repositorio.update(animal);

	}

	@Override
	public List<RefugioResumen> recuperarAnimales(Optional<Integer> anyos)
			throws RepositorioException, EntidadNoEncontrada {
		
		List<Animal> animales;
		if (anyos.isPresent()) {
			animales = repositorio.buscarAnimalesSinAdoptar(anyos.get());

		} else {
			animales = repositorio.getAll();
		}
		return animales.stream()
		        .map(a -> new RefugioResumen(
		                a.getId(),
		                a.getNombre(),
		                a.getEspecie(),
		                a.getFechaEntrada(),
		                a.getAdopcion() != null))
		        .collect(Collectors.toList());

	
	}
	@Override
	public void almacenarEnJSON(String idAnimal) throws JsonbException, FileNotFoundException {

				try {
					// Construcción del contexto
					Animal animal;
					animal = repositorio.getById(idAnimal);
					
					// Serialización a JSON en un fichero
					JsonbConfig config = new JsonbConfig()
			                .withNullValues(true)
			                .withFormatting(true)
			                .withPropertyNamingStrategy(
								PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
			                .withPropertyOrderStrategy(
								PropertyOrderStrategy.LEXICOGRAPHICAL);   
			    	// Nuevo contexto con configuración
					Jsonb contexto = JsonbProvider.provider().create().withConfig(config).build();
					//Serialización a JSON con nueva configuración
					String cadenaJSON = contexto.toJson(animal);

					System.out.println("Animal: " + cadenaJSON);
					//	 Escritura en fichero
					contexto.toJson(animal, new PrintStream("json/animal.json"));
				
					
					System.out.println("fin.");
				} catch (RepositorioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EntidadNoEncontrada e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}


}