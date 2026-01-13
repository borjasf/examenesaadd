package programas;

import java.util.Optional;

import arte.modelo.Dimensiones;
import arte.servicio.IServicioConcurso;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

public class Ejercicio2 {
	public static void main(String[] args) {
		IServicioConcurso servicio = FactoriaServicios.getServicio(IServicioConcurso.class);
		try {
			String id1=servicio.crearObra("La persistencia de la memoria", "Relojes blandos en un paisaje desértico", "Óleo sobre lienzo", new Dimensiones(2,2,2), "Salvador Dalí");
			servicio.crearObra("La noche estrellada", "Cielo nocturno sobre un pueblo", "Óleo sobre lienzo", new Dimensiones(1,0.8,0.05), "Vincent van Gogh");
			servicio.crearObra("El grito", "Figura humana con expresión de angustia", "Óleo, temple y pastel sobre cartón", new Dimensiones(0.9,0.7,0.02), "Edvard Munch");
			servicio.puntuarObra(id1, "INS123", "usuario1", 4.5, 4.8, Optional.of("Impresionante obra maestra"));
			servicio.puntuarObra(id1, "INS124", "usuario2", 4.0, 4.2, Optional.of("Colores vibrantes y emotivos"));
			servicio.recuperarObras(Optional.of(4.5)).forEach(System.out::println);
			servicio.recuperarObras(Optional.empty()).forEach(System.out::println);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
