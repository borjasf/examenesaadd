package tareas.servicio.test;

import java.time.LocalDate;
import java.util.Optional;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import tareas.modelo.Estado;
import tareas.servicio.IServicioTareas;
import tareas.servicio.ServicioTareas;


/**
 * Programa para poblar la base de datos con tablones y anuncios de prueba.
 */
public class Programa {
	
    public static void main(String[] args) {
        
        IServicioTareas servicio = FactoriaServicios.getServicio(IServicioTareas.class);
        
        try {
            String id1= servicio.crearTarea("Ir al medico", "dentista", LocalDate.of(2025,12, 1));
            servicio.crearTarea("Ir al medicasdado", "otorrino", LocalDate.now());
           servicio.anadirEstado(id1, Estado.FINALIZADA, LocalDate.now());
           System.out.println("recuperando todas las tareas finalizadas:");
           servicio.recuperarTareas(Optional.of(Estado.FINALIZADA)).forEach(System.out::println);
           System.out.println("recuperando todas las tareas:");
           servicio.recuperarTareas(Optional.empty()).forEach(System.out::println);
        } catch (RepositorioException e) {
            System.err.println("Error de repositorio: " + e.getMessage());
            e.printStackTrace();
        } catch (EntidadNoEncontrada e) {
            System.err.println("Entidad no encontrada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}