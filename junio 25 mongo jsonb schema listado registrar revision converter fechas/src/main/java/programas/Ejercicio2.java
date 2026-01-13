package programas;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import javax.json.bind.JsonbException;

import refugio.servicio.IServicioRefugio;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

public class Ejercicio2 {
	public static void main(String[] args) {
		IServicioRefugio servicio = FactoriaServicios.getServicio(IServicioRefugio.class);
		try {
			String id1=servicio.nuevoAnimal("Luna", "gato", 24, LocalDate.of(2020, 12, 1));
			servicio.nuevoAnimal("Fran", "perro", 12, LocalDate.of(2025, 12, 1));
			servicio.nuevoAnimal("Juan", "gato", 24, LocalDate.of(2020, 12, 1));
			servicio.anadirAdopcion(id1, "Samuel", "123456789", LocalDate.now());
			servicio.anadirRevision(id1, LocalDate.now(), 15.2, "revision porque si");
			System.out.println("Mostrando animales con 1 año de antiguedad o mas");
			servicio.recuperarAnimales(Optional.of(1)).forEach(System.out::println);
			System.out.println("Mostrando todos los animales");
			servicio.recuperarAnimales(Optional.empty()).forEach(System.out::println);
			try {
				servicio.almacenarEnJSON(id1);
			} catch (JsonbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
